package cn.com.buyforyou.fund.present.user;

import cn.com.buyforyou.fund.App;
import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.constant.Constant;
import cn.com.buyforyou.fund.model.BaseResp;
import cn.com.buyforyou.fund.model.user.BankResp;
import cn.com.buyforyou.fund.net.Api;
import cn.com.buyforyou.fund.params.CommonReqData;
import cn.com.buyforyou.fund.params.OpenAccountParams;
import cn.com.buyforyou.fund.params.RegisterSecondCheckIDParams;
import cn.com.buyforyou.fund.ui.activity.user.RegisterSecondActivity;

import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * 作者：sunnyzeng on 2017/12/15 16:26
 * 描述：
 */

public class RegisterSecondPresent extends XPresent<RegisterSecondActivity> {

    /**
     * 开户绑卡
     *
     * @param userId        用户编号
     * @param token         登录标识
     * @param userName      姓名
     * @param certNo        身份证号
     * @param email         邮箱（选填）
     * @param bankResp      选择银行
     * @param bankAccount   银行卡号
     * @param mobile        预留手机号
     * @param tradePassword 交易密码
     */
    public void openAccount(String userId, String token, String userName, String certNo, String email, BankResp bankResp, String bankAccount, String mobile, String tradePassword) {
        final CommonReqData reqData = new CommonReqData();
        reqData.setUserId(userId);
        reqData.setToken(token);

        OpenAccountParams params = new OpenAccountParams();
        params.setUserName(userName);
        params.setCertNo(certNo);
        params.setEmail(email);
        params.setSelectBank(bankResp);
        params.setBankAccount(bankAccount);
        params.setMobile(mobile);
        params.setTradePassword(tradePassword);

        reqData.setData(params);

        Api.getApi().openAccount(reqData)
                .compose(XApi.<BaseResp>getApiTransformer())
                .compose(XApi.<BaseResp>getScheduler())
                .compose(getV().<BaseResp>bindToLifecycle())
                .subscribe(new ApiSubscriber<BaseResp>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().requestOpenAccountFail("");
                        getV().showToast(R.string.request_error);
                    }

                    @Override
                    public void onNext(BaseResp resp) {
                        if (resp != null && resp.getStatus() == 200) {
                            getV().requestOpenAccountSuccess();
                        } else if (resp != null && resp.getStatus() == Constant.NO_LOGIN_STATUS) {
                            getV().showToast(resp.getMessage());
                            getV().areadyLogout();
                        } else {
                            getV().requestOpenAccountFail(resp.getMessage());
//                            getV().showToast(resp.getMessage());
                            XLog.e("返回数据为空");
                        }
                    }
                });
    }

    public void checkIDExist(String text) {
        CommonReqData reqData = new CommonReqData();
        reqData.setToken(App.getSharedPref().getString(Constant.TOKEN, ""));
        reqData.setUserId(App.getSharedPref().getString(Constant.USERID, ""));
        RegisterSecondCheckIDParams params = new RegisterSecondCheckIDParams();
        params.setCertNo(text);
        reqData.setData(params);
        Api.getApi().checkIDExist(reqData)
                .compose(XApi.<BaseResp>getApiTransformer())
                .compose(XApi.<BaseResp>getScheduler())
                .compose(getV().<BaseResp>bindToLifecycle())
                .subscribe(new ApiSubscriber<BaseResp>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().checkIDExistFail(false);
                    }

                    @Override
                    public void onNext(BaseResp resp) {
                        if (resp != null && resp.getStatus() == 200) {
                            getV().checkIDExistSuccess();
                        } else {
                            getV().checkIDExistFail(true);
                            getV().showToast(resp.getMessage());

                        }
                    }
                });
    }
}
