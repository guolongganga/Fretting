package cn.com.buyforyou.fund.present.user;

import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.constant.Constant;
import cn.com.buyforyou.fund.model.user.UpdateBonusResp;
import cn.com.buyforyou.fund.net.Api;
import cn.com.buyforyou.fund.params.CommonReqData;
import cn.com.buyforyou.fund.ui.fragment.user.BonusModeFragment;

import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * 作者：sunnyzeng on 2018/1/25 10:54
 * 描述：
 */

public class BonusModePresent extends XPresent<BonusModeFragment> {

    /**
     * 修改分红方式列表
     *
     * @param token
     * @param userId
     */
    public void loadBonusTypeData(String token, String userId) {
        CommonReqData reqData = new CommonReqData();
        reqData.setToken(token);
        reqData.setUserId(userId);

        reqData.setData("");

        Api.getApi()
                .bonusXgPage(reqData)
                .compose(XApi.<UpdateBonusResp>getApiTransformer())
                .compose(XApi.<UpdateBonusResp>getScheduler())
                .compose(getV().<UpdateBonusResp>bindToLifecycle())
                .subscribe(new ApiSubscriber<UpdateBonusResp>() {
                    @Override
                    protected void onFail(NetError error) {
                        error.printStackTrace();
                        getV().showError();
                        getV().showToast(R.string.request_error);
                    }

                    @Override
                    public void onNext(UpdateBonusResp model) {
                        if (model != null && model.getStatus() == 200) {
                            getV().showData(model.getData());
                        } else if (model != null && model.getStatus() == Constant.NO_LOGIN_STATUS) {
                            getV().showToast(model.getMessage());
                            getV().areadyLogout();
                        } else {
                            getV().showToast(model.getMessage());
                            getV().showError();
                            XLog.e("返回数据为空");
                        }
                    }
                });
    }
}
