package cn.com.buyforyou.fund.present.user;

import android.util.TimingLogger;

import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.constant.Constant;
import cn.com.buyforyou.fund.model.BaseResp;
import cn.com.buyforyou.fund.model.user.UpdateBonusResp;
import cn.com.buyforyou.fund.net.Api;
import cn.com.buyforyou.fund.params.BonusChangeParams;
import cn.com.buyforyou.fund.params.CommonReqData;
import cn.com.buyforyou.fund.ui.activity.user.BonusChangeActivity;

import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * 作者：sunnyzeng on 2018/3/21 16:04
 * 描述：
 */

public class BonusChangePresent extends XPresent<BonusChangeActivity> {


    /**
     * 修改分红方式详情
     *
     * @param fundCode
     * @param autoBuy
     * @param sharetype
     * @param tradeacco
     * @param password
     * @param token
     * @param userId
     */
    public void loadBonusXgDeatilData(String fundCode, String autoBuy, String sharetype, String tradeacco, String password, String token, String userId) {
        CommonReqData reqData = new CommonReqData();
        reqData.setToken(token);
        reqData.setUserId(userId);

        BonusChangeParams params = new BonusChangeParams();
        params.setFundCode(fundCode);
        params.setAutoBuy(autoBuy);
        params.setSharetype(sharetype);
        params.setTradeacco(tradeacco);
        params.setPassword(password);
        reqData.setData(params);

        Api.getApi()
                .bonusXgModifyBonus(reqData)
                .compose(XApi.<BaseResp>getApiTransformer())
                .compose(XApi.<BaseResp>getScheduler())
                .compose(getV().<BaseResp>bindToLifecycle())
                .subscribe(new ApiSubscriber<BaseResp>() {
                    @Override
                    protected void onFail(NetError error) {
                        error.printStackTrace();
                        getV().showError();
                        getV().showToast(R.string.request_error);
                    }

                    @Override
                    public void onNext(BaseResp model) {
                        if (model != null && model.getStatus() == 200) {
                            getV().showData();
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
