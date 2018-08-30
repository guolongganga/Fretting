package cn.com.buyforyou.fund.present.user;

import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.constant.Constant;
import cn.com.buyforyou.fund.model.user.UserAccountResp;
import cn.com.buyforyou.fund.net.Api;
import cn.com.buyforyou.fund.params.CommonReqData;
import cn.com.buyforyou.fund.ui.activity.user.LoginActivity;
import cn.com.buyforyou.fund.ui.fragment.user.UserFragment;

import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * 作者：sunnyzeng on 2017/12/6 13:02
 * 描述：
 */

public class UserPresent extends XPresent<UserFragment> {


    /**
     * 主页面 我的 - 数据
     *
     * @param token
     * @param userId
     */
    public void getFundHome(String token, String userId) {

        CommonReqData reqData = new CommonReqData();
        reqData.setToken(token);
        reqData.setUserId(userId);
        reqData.setData("");

        Api.getApi()
                .getFundHome(reqData)
                .compose(XApi.<UserAccountResp>getApiTransformer())
                .compose(XApi.<UserAccountResp>getScheduler())
                .compose(getV().<UserAccountResp>bindToLifecycle())
                .subscribe(new ApiSubscriber<UserAccountResp>() {
                    @Override
                    protected void onFail(NetError error) {
                        error.printStackTrace();
                        getV().requestFundFail();
                        getV().showToast(R.string.request_error);
                    }

                    @Override
                    public void onNext(UserAccountResp model) {
                        if (model != null && model.getStatus() == 200) {
                            getV().showMyFund(model.getData());
                        } else if (model != null && model.getStatus() == Constant.NO_LOGIN_STATUS) {
                            getV().showToast(model.getMessage());
                            getV().areadyLogout();
                        } else {
                            getV().showToast(model.getMessage());
                            getV().requestFundFail();
                            XLog.e("返回数据为空");
                        }
                    }
                });
    }

}
