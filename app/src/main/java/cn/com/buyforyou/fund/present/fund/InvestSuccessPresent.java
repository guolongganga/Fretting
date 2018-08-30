package cn.com.buyforyou.fund.present.fund;

import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.constant.Constant;
import cn.com.buyforyou.fund.model.fund.InvestResp;
import cn.com.buyforyou.fund.net.Api;
import cn.com.buyforyou.fund.params.CommonReqData;
import cn.com.buyforyou.fund.params.InvestSuccessParams;
import cn.com.buyforyou.fund.ui.activity.fund.InvestSuccessActivity;

import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * 作者：sunnyzeng on 2018/1/17 10:12
 * 描述：
 */

public class InvestSuccessPresent extends XPresent<InvestSuccessActivity> {
    /**
     * 定投成功回显 定投详情数据
     *
     * @param token
     * @param userId
     * @param fundCode
     * @param scheduled_protocol_id
     */
    public void investSuccessData(String token, String userId, String fundCode, String scheduled_protocol_id) {
        CommonReqData reqData = new CommonReqData();
        reqData.setToken(token);
        reqData.setUserId(userId);

        InvestSuccessParams params = new InvestSuccessParams();
        params.setFundCode(fundCode);
        params.setScheduled_protocol_id(scheduled_protocol_id);

        reqData.setData(params);

        Api.getApi()
                .fundTimesSuccDetails(reqData)
                .compose(XApi.<InvestResp>getApiTransformer())
                .compose(XApi.<InvestResp>getScheduler())
                .compose(getV().<InvestResp>bindToLifecycle())
                .subscribe(new ApiSubscriber<InvestResp>() {
                    @Override
                    protected void onFail(NetError error) {
                        //请求失败
                        getV().requestFail();
                        getV().showToast(R.string.request_error);
                    }

                    @Override
                    public void onNext(InvestResp resp) {
                        if (resp != null && resp.getStatus() == 200) {
                            //请求成功 返回实体
                            getV().requestSuccess(resp.getData());
                        } else if (resp != null && resp.getStatus() == Constant.NO_LOGIN_STATUS) {
                            getV().showToast(resp.getMessage());
                            getV().areadyLogout();
                        }  else {
                            //请求失败
                            getV().requestFail();
                            getV().showToast(resp.getMessage());
                            XLog.e("返回数据为空");
                        }

                    }
                });
    }
}
