package cn.com.buyforyou.fund.present.user;

import android.util.Log;

import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.model.BaseResp;
import cn.com.buyforyou.fund.net.Api;
import cn.com.buyforyou.fund.params.CommonReqData;
import cn.com.buyforyou.fund.params.FindLoginPwdSecondParams;
import cn.com.buyforyou.fund.ui.activity.user.FindPwdLoginSecondActivity;

import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * 作者：sunnyzeng on 2017/12/15 13:43
 * 描述：
 */

public class FindPwdLoginSecondPresent extends XPresent<FindPwdLoginSecondActivity> {

    /**
     * 找回登录密码 第二步
     *
     * @param password 密码
     */
    public void findPassword(String phone, String password, String repetPassword) {

        CommonReqData reqData = new CommonReqData();

        FindLoginPwdSecondParams params = new FindLoginPwdSecondParams();
        params.setPhone(phone);
        params.setPassword(password);
        params.setRepetPassword(repetPassword);
        reqData.setData(params);

        Api.getApi()
                .findPasswordReset(reqData)
                .compose(XApi.<BaseResp>getApiTransformer())
                .compose(XApi.<BaseResp>getScheduler())
                .compose(getV().<BaseResp>bindToLifecycle())
                .subscribe(new ApiSubscriber<BaseResp>() {
                    @Override
                    protected void onFail(NetError error) {
                        error.printStackTrace();
                        getV().requestFail();
                        getV().showToast(R.string.request_error);
                    }

                    @Override
                    public void onNext(BaseResp model) {
                        if (model != null && model.getStatus() == 200) {
                            getV().requestSuccess(model.getData());
                        } else {
                            getV().requestFail();
                            getV().showToast(model.getMessage());
                            XLog.e("返回数据为空");
                        }
                    }
                });

    }
}
