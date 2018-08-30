package cn.com.buyforyou.fund.present.user;

import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.constant.Constant;
import cn.com.buyforyou.fund.model.user.InvestPlanResp;
import cn.com.buyforyou.fund.net.Api;
import cn.com.buyforyou.fund.params.CommonReqData;
import cn.com.buyforyou.fund.params.MyInvestParams;
import cn.com.buyforyou.fund.ui.activity.user.MyInvestActivity;

import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * 作者：sunnyzeng on 2018/1/22 13:45
 * 描述：
 */

public class MyInvestPresent extends XPresent<MyInvestActivity> {

    /**
     * 我的定投
     *
     * @param pageno
     * @param pageSize
     * @param token
     * @param userId
     * @param fundCode
     * @param dtStatus
     * @param isFirst
     */
    public void myInvestData(final int pageno, int pageSize, String token, String userId, String fundCode, String dtStatus, final String isFirst) {

        CommonReqData reqData = new CommonReqData();
        reqData.setToken(token);
        reqData.setUserId(userId);

        MyInvestParams params = new MyInvestParams();
        params.setPageNum(pageno);
        params.setPageSize(pageSize);
        params.setFundCode(fundCode);
        params.setDtStatus(dtStatus);
        params.setIsFirst(isFirst);
        reqData.setData(params);

        Api.getApi()
                .myTimesBuyIndex(reqData)
                .compose(XApi.<InvestPlanResp>getApiTransformer())
                .compose(XApi.<InvestPlanResp>getScheduler())
                .compose(getV().<InvestPlanResp>bindToLifecycle())
                .subscribe(new ApiSubscriber<InvestPlanResp>() {
                    @Override
                    protected void onFail(NetError error) {
                        error.printStackTrace();
                        getV().requestDataFail();
                        getV().showToast(R.string.request_error);
                    }

                    @Override
                    public void onNext(InvestPlanResp model) {
                        if (model != null && model.getStatus() == 200) {
                            boolean first = false;
                            if (isFirst != null && "1".equals(isFirst)) {
                                first = true;
                            }
                            getV().requestDataSuccess(pageno, model.getData(), first);
                        } else if (model != null && model.getStatus() == Constant.NO_LOGIN_STATUS) {
                            getV().showToast(model.getMessage());
                            getV().areadyLogout();
                        } else {
                            getV().showToast(model.getMessage());
                            getV().requestDataFail();
                            XLog.e("返回数据为空");
                        }
                    }
                });
    }


}
