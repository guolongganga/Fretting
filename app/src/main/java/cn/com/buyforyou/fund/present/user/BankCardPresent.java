package cn.com.buyforyou.fund.present.user;

import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.constant.Constant;
import cn.com.buyforyou.fund.model.user.BankCardResp;
import cn.com.buyforyou.fund.net.Api;
import cn.com.buyforyou.fund.params.BankCardChangeParams;
import cn.com.buyforyou.fund.params.CommonReqData;
import cn.com.buyforyou.fund.ui.activity.user.BankCardActivity;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * 作者：sunnyzeng on 2017/12/22 15:48
 * 描述：
 */

public class BankCardPresent extends XPresent<BankCardActivity> {
    /**
     * 获取银行卡信息
     *
     * @param token
     * @param userId
     */
    public void getBankCardInfo(String token, String userId) {
        CommonReqData reqData = new CommonReqData();

        reqData.setToken(token);
        reqData.setUserId(userId);

        Api.getApi()
                .getMyBankCard(reqData)
                .compose(XApi.<BankCardResp>getApiTransformer())
                .compose(XApi.<BankCardResp>getScheduler())
                .compose(getV().<BankCardResp>bindToLifecycle())
                .subscribe(new ApiSubscriber<BankCardResp>() {
                    @Override
                    public void onNext(BankCardResp resp) {
                        if (resp != null && resp.getStatus() == 200) {
                            getV().showBankCardData(resp.getData());
                        } else if (resp != null && resp.getStatus() == Constant.NO_LOGIN_STATUS) {
                            getV().showToast(resp.getMessage());
                            getV().areadyLogout();
                        } else {
                            getV().requestFail();
                            getV().showToast(resp.getMessage());
                        }
                    }

                    @Override
                    protected void onFail(NetError error) {
                        getV().requestFail();
                        getV().showToast(R.string.request_error);
                    }
                });

    }

    /**
     * 点击更换银行卡 检查是否可以更换银行卡
     *
     * @param token
     * @param userId
     */
    public void changeBankCardCheck(String token, String userId, String trade_password) {
        CommonReqData reqData = new CommonReqData();

        reqData.setToken(token);
        reqData.setUserId(userId);

        BankCardChangeParams params = new BankCardChangeParams();
        params.setTrade_password(trade_password);
        reqData.setData(params);

        Api.getApi().changeBankCardCheck(reqData)
                .compose(XApi.<BankCardResp>getApiTransformer())
                .compose(XApi.<BankCardResp>getScheduler())
                .compose(getV().<BankCardResp>bindToLifecycle())
                .subscribe(new ApiSubscriber<BankCardResp>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().requestFail();
                        getV().showToast(R.string.request_error);
                    }

                    @Override
                    public void onNext(BankCardResp resp) {
                        if (resp != null && resp.getStatus() == 200) {
                            getV().isCanChange();
                        } else if (resp != null && resp.getStatus() == Constant.PASSWORD_ERROR_STATUS) {
                            getV().passwordError(resp.getMessage());
                        } else if (resp != null && resp.getStatus() == Constant.NO_LOGIN_STATUS) {
                            getV().showToast(resp.getMessage());
                            getV().areadyLogout();
                        } else {
                            getV().requestFail();
                            getV().showToast(resp.getMessage());
                        }
                    }
                });
    }

}
