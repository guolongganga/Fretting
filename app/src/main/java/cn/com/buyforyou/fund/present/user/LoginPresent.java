package cn.com.buyforyou.fund.present.user;

import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.model.BaseResp;
import cn.com.buyforyou.fund.model.LoginResp;
import cn.com.buyforyou.fund.net.Api;
import cn.com.buyforyou.fund.params.CommonReqData;
import cn.com.buyforyou.fund.params.LoginParams;
import cn.com.buyforyou.fund.params.RegisterFirstCheckPhoneParams;
import cn.com.buyforyou.fund.ui.activity.user.LoginActivity;

import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * 作者：sunnyzeng on 2017/12/14 18:08
 * 描述：登录Present
 */

public class LoginPresent extends XPresent<LoginActivity> {

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     */
    public void login(String username, String password) {

        CommonReqData reqData = new CommonReqData();

        LoginParams loginParams = new LoginParams();
        loginParams.setUsername(username);
        loginParams.setPassword(password);
        reqData.setData(loginParams);

        Api.getApi()
                .login(reqData)
                .compose(XApi.<LoginResp>getApiTransformer())
                .compose(XApi.<LoginResp>getScheduler())
                .compose(getV().<LoginResp>bindToLifecycle())
                .subscribe(new ApiSubscriber<LoginResp>() {
                    @Override
                    protected void onFail(NetError error) {
                        error.printStackTrace();
                        getV().loginFail();
                        getV().showToast(R.string.request_error);
                    }

                    @Override
                    public void onNext(LoginResp model) {
                        if (model != null && model.getStatus() == 200) {
                            getV().showData(model.getData());
                        } else {
                            getV().loginFail();
                            getV().showToast(model.getMessage());
                            XLog.e("返回数据为空");
                        }
                    }
                });

    }

}
