package cn.com.buyforyou.fund.present.user;

import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.constant.Constant;
import cn.com.buyforyou.fund.model.BaseResp;
import cn.com.buyforyou.fund.net.Api;
import cn.com.buyforyou.fund.params.ChangeTradePwdParams;
import cn.com.buyforyou.fund.params.CommonReqData;
import cn.com.buyforyou.fund.ui.activity.user.ChangeTradePwdActivity;

import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * 作者：sunnyzeng on 2017/12/27 16:36
 * 描述：更改交易密码
 */

public class ChangeTradePwdPresent extends XPresent<ChangeTradePwdActivity> {

    /**
     * 修改交易密码
     *
     * @param token
     * @param userId
     * @param oldpwd
     * @param pwd
     * @param pwdnumbe
     */
    public void changePassword(String token, String userId, String oldpwd, String pwd, String pwdnumbe) {
        CommonReqData reqData = new CommonReqData();
        reqData.setToken(token);
        reqData.setUserId(userId);

        ChangeTradePwdParams params = new ChangeTradePwdParams();
        params.setOldTradePassword(oldpwd);
        params.setTradePassword(pwd);
        params.setRepetTradePassword(pwdnumbe);
        reqData.setData(params);

        Api.getApi()
                .passwordChangeTrade(reqData)
                .compose(XApi.<BaseResp>getApiTransformer())
                .compose(XApi.<BaseResp>getScheduler())
                .compose(getV().<BaseResp>bindToLifecycle())
                .subscribe(new ApiSubscriber<BaseResp>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().requestFail();
                        getV().showToast(R.string.request_error);
                    }

                    @Override
                    public void onNext(BaseResp resp) {
                        if (resp != null && resp.getStatus() == 200) {
                            getV().requestSuccess();
                        } else if (resp != null && resp.getStatus() == Constant.NO_LOGIN_STATUS) {
                            getV().showToast(resp.getMessage());
                            getV().areadyLogout();
                        }  else {
                            getV().requestFail();
                            getV().showToast(resp.getMessage());
                            XLog.e("返回数据为空");
                        }
                    }
                });


    }
}
