package cn.com.buyforyou.fund.present.user;

import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.constant.Constant;
import cn.com.buyforyou.fund.model.fund.NewestFundResp;
import cn.com.buyforyou.fund.model.user.SelfChooseResp;
import cn.com.buyforyou.fund.model.user.UserAccountResp;
import cn.com.buyforyou.fund.net.Api;
import cn.com.buyforyou.fund.params.CommonReqData;
import cn.com.buyforyou.fund.params.SelfChooseParams;
import cn.com.buyforyou.fund.ui.activity.user.SelfChooseActivity;

import java.math.BigDecimal;
import java.util.ArrayList;

import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * 作者：sunnyzeng on 2018/1/24 11:20
 * 描述：
 */

public class SelfChoosePresent extends XPresent<SelfChooseActivity> {
    /**
     * 自选基金
     *
     * @param sortJz
     * @param sortZdf
     * @param token
     * @param userId
     */
    public void selfChooseFund(String sortJz, String sortZdf, String token, String userId) {
        CommonReqData reqData = new CommonReqData();
        reqData.setToken(token);
        reqData.setUserId(userId);
        SelfChooseParams params = new SelfChooseParams();
        params.setSortJz(sortJz);
        params.setSortZdf(sortZdf);
        reqData.setData(params);

        Api.getApi()
                .selfChooseFund(reqData)
                .compose(XApi.<SelfChooseResp>getApiTransformer())
                .compose(XApi.<SelfChooseResp>getScheduler())
                .compose(getV().<SelfChooseResp>bindToLifecycle())
                .subscribe(new ApiSubscriber<SelfChooseResp>() {
                    @Override
                    protected void onFail(NetError error) {
                        error.printStackTrace();
                        getV().requestFundFail();
                        getV().showToast(R.string.request_error);
                    }

                    @Override
                    public void onNext(SelfChooseResp model) {
                        if (model != null && model.getStatus() == 200) {
                            getV().showData(model.getData());
                        } else if (model != null && model.getStatus() == Constant.NO_LOGIN_STATUS) {
                            getV().showToast(model.getMessage());
                            getV().areadyLogout();
                        }  else {
                            getV().showToast(model.getMessage());
                            getV().requestFundFail();
                            XLog.e("返回数据为空");
                        }
                    }
                });


    }
}
