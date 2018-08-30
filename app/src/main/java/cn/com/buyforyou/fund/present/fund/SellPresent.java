package cn.com.buyforyou.fund.present.fund;

import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.constant.Constant;
import cn.com.buyforyou.fund.model.BaseResp;
import cn.com.buyforyou.fund.model.fund.BuyFundResp;
import cn.com.buyforyou.fund.model.fund.BuyNowResp;
import cn.com.buyforyou.fund.model.fund.FundStatusResp;
import cn.com.buyforyou.fund.model.fund.SellResp;
import cn.com.buyforyou.fund.net.Api;
import cn.com.buyforyou.fund.params.BuyFundParams;
import cn.com.buyforyou.fund.params.BuyNowParams;
import cn.com.buyforyou.fund.params.CommonReqData;
import cn.com.buyforyou.fund.params.SellSureParams;
import cn.com.buyforyou.fund.ui.activity.fund.BuyActivity;
import cn.com.buyforyou.fund.ui.activity.fund.SellActivity;

import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * 作者：sunnyzeng on 2018/1/12 14:35
 * 描述：
 */

public class SellPresent extends XPresent<SellActivity> {

    /***
     * 更换银行卡刷新赎回验证接口，得到最新的银行卡信息
     * @param token
     * @param userId
     * @param fund_code
     */
    public void sellFundPre(String token, String userId, String fund_code) {
        CommonReqData reqData = new CommonReqData();
        reqData.setToken(token);
        reqData.setUserId(userId);
        BuyFundParams params = new BuyFundParams();
        params.setFund_code(fund_code);
        reqData.setData(params);

        Api.getApi().sellFundPre(reqData)
                .compose(XApi.<SellResp>getApiTransformer())
                .compose(XApi.<SellResp>getScheduler())
                .compose(getV().<SellResp>bindToLifecycle())
                .subscribe(new ApiSubscriber<SellResp>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().requestFundFail();
                        getV().showToast(R.string.request_error);
                    }

                    @Override
                    public void onNext(SellResp resp) {
                        if (resp != null && resp.getStatus() == 200) {
                            getV().requestFundSuccess(resp.getData());
                        } else if (resp != null && resp.getStatus() == Constant.NO_LOGIN_STATUS) {
                            getV().showToast(resp.getMessage());
                            getV().areadyLogout();
                        } else {
                            getV().requestFundFail();
                            getV().showToast(resp.getMessage());
                            XLog.e("返回数据为空");
                        }
                    }
                });

    }

    /**
     * 赎回
     *
     * @param token
     * @param userId
     * @param fund_code
     * @param password
     */
    public void sellFund(String token, String userId, String fund_code, String trade_acco, String password
            , String fund_name, String shares, String share_type, String fund_exceed_flag) {
        CommonReqData reqData = new CommonReqData();
        reqData.setToken(token);
        reqData.setUserId(userId);

        SellSureParams params = new SellSureParams();
        params.setFund_code(fund_code);
        params.setTrade_acco(trade_acco);
        params.setPassword(password);
        params.setFund_name(fund_name);
        params.setFund_exceed_flag(fund_exceed_flag);
        params.setShares(shares);
        params.setShare_type(share_type);


        reqData.setData(params);

        Api.getApi().sellFundSure(reqData)
                .compose(XApi.<FundStatusResp>getApiTransformer())
                .compose(XApi.<FundStatusResp>getScheduler())
                .compose(getV().<FundStatusResp>bindToLifecycle())
                .subscribe(new ApiSubscriber<FundStatusResp>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().requestSellFail();
                        getV().showToast(R.string.request_error);
                    }

                    @Override
                    public void onNext(FundStatusResp resp) {
                        if (resp != null && resp.getStatus() == 200) {
                            getV().requestSellSuccess(resp.getData());
                        } else if (resp != null && resp.getStatus() == Constant.PASSWORD_ERROR_STATUS) {
                            //密码错误状态码
                            getV().passwordError(resp.getMessage());
                        } else if (resp != null && resp.getStatus() == Constant.NO_LOGIN_STATUS) {
                            getV().showToast(resp.getMessage());
                            getV().areadyLogout();
                        } else if (resp.getStatus() == 536) {
                            getV().showToast(resp.getMessage());
                        } else {
                            getV().requestSellFail();
                            getV().showToast(resp.getMessage());
                            XLog.e("返回数据为空");
                        }
                    }
                });

    }

}
