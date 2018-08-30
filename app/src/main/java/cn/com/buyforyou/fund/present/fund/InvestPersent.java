package cn.com.buyforyou.fund.present.fund;

import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.constant.Constant;
import cn.com.buyforyou.fund.model.fund.GetNextTimeResp;
import cn.com.buyforyou.fund.model.fund.InvestResp;
import cn.com.buyforyou.fund.model.fund.InvestSureResp;
import cn.com.buyforyou.fund.net.Api;
import cn.com.buyforyou.fund.params.CommonReqData;
import cn.com.buyforyou.fund.params.GetNextTimeParams;
import cn.com.buyforyou.fund.params.InvestBuyParams;
import cn.com.buyforyou.fund.params.InvestParams;
import cn.com.buyforyou.fund.ui.activity.fund.InvestActivity;

import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * 作者：sunnyzeng on 2018/1/9 18:10
 * 描述：定投
 */

public class InvestPersent extends XPresent<InvestActivity> {

    /**
     * 获得我的银行卡信息
     *
     * @param token
     * @param userId
     */
    public void investTime(String token, String userId, String fund_code, String fund_name) {
        CommonReqData reqData = new CommonReqData();
        reqData.setToken(token);
        reqData.setUserId(userId);
        InvestParams params = new InvestParams();
        params.setFundCode(fund_code);
        params.setFund_name(fund_name);
        reqData.setData(params);

        Api.getApi().fundInvestTime(reqData)
                .compose(XApi.<InvestResp>getApiTransformer())
                .compose(XApi.<InvestResp>getScheduler())
                .compose(getV().<InvestResp>bindToLifecycle())
                .subscribe(new ApiSubscriber<InvestResp>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().requestInvestFail();
                        getV().showToast(R.string.request_error);
                    }

                    @Override
                    public void onNext(InvestResp resp) {
                        if (resp != null && resp.getStatus() == 200) {
                            getV().requestInvestSuccess(resp.getData().getBankCardPageEntity());
                        } else if (resp != null && resp.getStatus() == Constant.NO_LOGIN_STATUS) {
                            getV().showToast(resp.getMessage());
                            getV().areadyLogout();
                        } else {
                            getV().requestInvestFail();
                            getV().showToast(resp.getMessage());
                            XLog.e("返回数据为空");
                        }
                    }
                });

    }

    /**
     * 获取下次扣款时间
     *
     * @param token
     * @param userId
     */
    public void nextDeductTime(String token, String userId, String fund_code, String protocol_period_unit, String first_exchdate) {
        CommonReqData reqData = new CommonReqData();
        reqData.setToken(token);
        reqData.setUserId(userId);
        GetNextTimeParams params = new GetNextTimeParams();
        params.setFundCode(fund_code);
        params.setProtocol_period_unit(protocol_period_unit);
        params.setFirst_exchdate(first_exchdate);

        reqData.setData(params);

        Api.getApi()
                .getNextTime(reqData)
                .compose(XApi.<GetNextTimeResp>getApiTransformer())
                .compose(XApi.<GetNextTimeResp>getScheduler())
                .compose(getV().<GetNextTimeResp>bindToLifecycle())
                .subscribe(new ApiSubscriber<GetNextTimeResp>() {
                    @Override
                    protected void onFail(NetError error) {
                        //请求失败
                        getV().requestDeductTimeFail();
                        getV().showToast(R.string.request_error);
                    }

                    @Override
                    public void onNext(GetNextTimeResp resp) {
                        if (resp != null && resp.getStatus() == 200) {
                            //请求成功 返回实体
                            getV().requestDeductTimeSuccess(resp.getData());
                        } else if (resp != null && resp.getStatus() == Constant.NO_LOGIN_STATUS) {
                            getV().showToast(resp.getMessage());
                            getV().areadyLogout();
                        } else {
                            //请求失败
                            getV().requestDeductTimeFail();
                            getV().showToast(resp.getMessage());
                            XLog.e("返回数据为空");
                        }

                    }
                });

    }

    /**
     * 确定投资
     *
     * @param token                登录标识
     * @param userId               用户编号
     * @param fundCode             基金编码
     * @param fund_name            基金名称
     * @param apply_sum            购买金额
     * @param first_trade_month    首次交易月
     * @param protocol_period_unit 定投周期
     * @param fix_date             定投日
     * @param password             密码
     */
    public void sureInvest(String token, String userId, String fundCode, String fund_name, String apply_sum,
                           String first_trade_month, String protocol_period_unit, String fix_date, String password, String protocol_id) {
        CommonReqData reqData = new CommonReqData();
        reqData.setToken(token);
        reqData.setUserId(userId);

        InvestBuyParams params = new InvestBuyParams();
        params.setFundCode(fundCode);
        params.setFund_name(fund_name);
        params.setApply_sum(apply_sum);
        params.setFirst_trade_month(first_trade_month);
        params.setProtocol_period_unit(protocol_period_unit);
        params.setFix_date(fix_date);
        params.setPassword(password);
        params.setScheduled_protocol_id(protocol_id);

        reqData.setData(params);

        Api.getApi()
                .fundTimesSave(reqData)
                .compose(XApi.<InvestSureResp>getApiTransformer())
                .compose(XApi.<InvestSureResp>getScheduler())
                .compose(getV().<InvestSureResp>bindToLifecycle())
                .subscribe(new ApiSubscriber<InvestSureResp>() {
                    @Override
                    protected void onFail(NetError error) {
                        //请求失败
                        getV().requestSureInvestFail();
                        getV().showToast(R.string.request_error);
                    }

                    @Override
                    public void onNext(InvestSureResp resp) {
                        if (resp != null && resp.getStatus() == 200) {
                            //请求成功 返回实体
                            getV().requestSureInvestSuccess(resp.getData());
                        } else if (resp != null && resp.getStatus() == Constant.PASSWORD_ERROR_STATUS) {
                            getV().passwordError(resp.getMessage());
                        } else if (resp != null && resp.getStatus() == Constant.NO_LOGIN_STATUS) {
                            getV().showToast(resp.getMessage());
                            getV().areadyLogout();
                        } else {
                            //请求失败
                            getV().requestSureInvestFail();
                            getV().showToast(resp.getMessage());
                            XLog.e("返回数据为空");
                        }

                    }
                });

    }
}
