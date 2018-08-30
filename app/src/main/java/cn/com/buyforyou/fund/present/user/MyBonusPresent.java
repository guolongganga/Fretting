package cn.com.buyforyou.fund.present.user;

import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.constant.Constant;
import cn.com.buyforyou.fund.model.user.MyBonusResp;
import cn.com.buyforyou.fund.model.user.TransactionResp;
import cn.com.buyforyou.fund.net.Api;
import cn.com.buyforyou.fund.params.CommonReqData;
import cn.com.buyforyou.fund.params.TransactionQueryParams;
import cn.com.buyforyou.fund.ui.fragment.user.MyBonusFragment;

import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * 作者：sunnyzeng on 2018/3/20 20:10
 * 描述：
 */

public class MyBonusPresent extends XPresent<MyBonusFragment> {
    /**
     * 分红方式
     *
     * @param token
     * @param userId
     * @param pageno
     * @param pageSize
     */
    public void loadMyBonusData(String token, String userId, final int pageno, int pageSize) {
        CommonReqData reqData = new CommonReqData();
        reqData.setToken(token);
        reqData.setUserId(userId);

        TransactionQueryParams params = new TransactionQueryParams();
        params.setPageNum(pageno);
        params.setPageSize(pageSize);
        reqData.setData(params);

        Api.getApi()
                .bonusHisPage(reqData)
                .compose(XApi.<MyBonusResp>getApiTransformer())
                .compose(XApi.<MyBonusResp>getScheduler())
                .compose(getV().<MyBonusResp>bindToLifecycle())
                .subscribe(new ApiSubscriber<MyBonusResp>() {
                    @Override
                    protected void onFail(NetError error) {
                        error.printStackTrace();
                        getV().showError();
                        getV().showToast(R.string.request_error);
                    }

                    @Override
                    public void onNext(MyBonusResp model) {
                        if (model != null && model.getStatus() == 200) {
                            getV().showData(pageno, model.getData());
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
