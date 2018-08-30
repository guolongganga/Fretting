package cn.com.buyforyou.fund.present.user;

import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.constant.Constant;
import cn.com.buyforyou.fund.model.BaseResp;
import cn.com.buyforyou.fund.model.user.ResultDetailResp;
import cn.com.buyforyou.fund.net.Api;
import cn.com.buyforyou.fund.params.CommonReqData;
import cn.com.buyforyou.fund.params.ResultParams;
import cn.com.buyforyou.fund.ui.activity.user.ResultDetailOneActivity;
import cn.com.buyforyou.fund.ui.activity.user.ResultDetailThreeActivity;

import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * 作者：sunnyzeng on 2018/1/31 10:59
 * 描述：
 */

public class ResultDetailThreePresent extends XPresent<ResultDetailThreeActivity> {

    /**
     * 撤单交易详情
     *
     * @param allot_no
     * @param token
     * @param userId
     */
    public void withdrawApplyDetail(String allot_no, String token, String userId) {
        CommonReqData reqData = new CommonReqData();
        reqData.setToken(token);
        reqData.setUserId(userId);

        ResultParams params = new ResultParams();
        params.setAllot_no(allot_no);
        reqData.setData(params);

        Api.getApi().withdrawApplyDetail(reqData)
                .compose(XApi.<ResultDetailResp>getApiTransformer())
                .compose(XApi.<ResultDetailResp>getScheduler())
                .compose(getV().<ResultDetailResp>bindToLifecycle())
                .subscribe(new ApiSubscriber<ResultDetailResp>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().requestDetailFail();
                        getV().showToast(R.string.request_error);
                    }

                    @Override
                    public void onNext(ResultDetailResp resp) {
                        if (resp != null && resp.getStatus() == 200) {
                            getV().requestDetailSuccess(resp.getData());
                        }  else if (resp != null && resp.getStatus() == Constant.NO_LOGIN_STATUS) {
                            getV().showToast(resp.getMessage());
                            getV().areadyLogout();
                        } else {
                            getV().requestDetailFail();
                            getV().showToast(resp.getMessage());
                            XLog.e("返回数据为空");
                        }
                    }
                });
    }

}
