package cn.com.buyforyou.fund.present.user;

import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.constant.Constant;
import cn.com.buyforyou.fund.model.BaseResp;
import cn.com.buyforyou.fund.model.user.PhoneResp;
import cn.com.buyforyou.fund.net.Api;
import cn.com.buyforyou.fund.params.CommonReqData;
import cn.com.buyforyou.fund.params.RegisterFirstCheckPhoneParams;
import cn.com.buyforyou.fund.ui.activity.user.PhoneActivity;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * 作者：sunnyzeng on 2017/12/28 11:02
 * 描述：
 */

public class PhonePresent extends XPresent<PhoneActivity> {

    /**
     * 我的手机号码
     *
     * @param token
     * @param userId
     */
    public void getPhoneInfo(String token, String userId) {

        CommonReqData reqData = new CommonReqData();
        reqData.setToken(token);
        reqData.setUserId(userId);

        Api.getApi()
                .changePhoneIndex(reqData)
                .compose(XApi.<PhoneResp>getApiTransformer())
                .compose(XApi.<PhoneResp>getScheduler())
                .compose(getV().<PhoneResp>bindToLifecycle())
                .subscribe(new ApiSubscriber<PhoneResp>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().requestFail();
                        getV().showToast(R.string.request_error);
                    }

                    @Override
                    public void onNext(PhoneResp resp) {
                        if (resp != null && resp.getStatus() == 200) {
                            getV().requestSuccess(resp.getData());
                        } else if (resp != null && resp.getStatus() == Constant.NO_LOGIN_STATUS) {
                            getV().showToast(resp.getMessage());
                            getV().areadyLogout();
                        } else {
                            getV().requestFail();
                            getV().showToast(resp.getMessage());
                            XLog.e("返回数据为空");
                        }
                    }
                });
    }

}
