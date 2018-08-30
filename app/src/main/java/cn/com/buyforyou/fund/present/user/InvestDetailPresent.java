package cn.com.buyforyou.fund.present.user;

import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.constant.Constant;
import cn.com.buyforyou.fund.model.BaseResp;
import cn.com.buyforyou.fund.model.fund.InvestResp;
import cn.com.buyforyou.fund.model.index.IndexResp;
import cn.com.buyforyou.fund.model.user.InvestPlanResp;
import cn.com.buyforyou.fund.model.user.InvestRecordResp;
import cn.com.buyforyou.fund.net.Api;
import cn.com.buyforyou.fund.params.CommonReqData;
import cn.com.buyforyou.fund.params.InvestParams;
import cn.com.buyforyou.fund.params.InvestSuccessParams;
import cn.com.buyforyou.fund.params.StateChangeParams;
import cn.com.buyforyou.fund.ui.activity.user.InvestDeatilActivity;

import java.util.ArrayList;

import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * 作者：sunnyzeng on 2018/1/22 17:03
 * 描述：
 */

public class InvestDetailPresent extends XPresent<InvestDeatilActivity> {
    /**
     * 定投详情
     *
     * @param token
     * @param userId
     * @param protocol_id
     */
    public void investDetailData(String token, String userId, String protocol_id) {
        CommonReqData reqData = new CommonReqData();
        reqData.setToken(token);
        reqData.setUserId(userId);

        InvestSuccessParams params = new InvestSuccessParams();
        params.setScheduled_protocol_id(protocol_id);
        reqData.setData(params);

        Api.getApi()
                .myTimesBuyDetail(reqData)
                .compose(XApi.<InvestResp>getApiTransformer())
                .compose(XApi.<InvestResp>getScheduler())
                .compose(getV().<InvestResp>bindToLifecycle())
                .subscribe(new ApiSubscriber<InvestResp>() {
                    @Override
                    protected void onFail(NetError error) {
                        error.printStackTrace();
                        getV().requestInvestDetailFail();
                        getV().showToast(R.string.request_error);
                    }

                    @Override
                    public void onNext(InvestResp model) {
                        if (model != null && model.getStatus() == 200) {
                            getV().requestInvestDetailSuccess(model.getData());
                        } else if (model != null && model.getStatus() == Constant.NO_LOGIN_STATUS) {
                            getV().showToast(model.getMessage());
                            getV().areadyLogout();
                        } else {
                            getV().showToast(model.getMessage());
                            getV().requestInvestDetailFail();
                            XLog.e("返回数据为空");
                        }
                    }
                });

    }

    /**
     * 修改定投
     *
     * @param token
     * @param userId
     * @param protocol_id
     */
    public void investTimeUpdata(String token, String userId, String protocol_id) {
        CommonReqData reqData = new CommonReqData();
        reqData.setToken(token);
        reqData.setUserId(userId);
        InvestSuccessParams params = new InvestSuccessParams();
        params.setScheduled_protocol_id(protocol_id);
        reqData.setData(params);

        Api.getApi().buyUpdataData(reqData)
                .compose(XApi.<InvestResp>getApiTransformer())
                .compose(XApi.<InvestResp>getScheduler())
                .compose(getV().<InvestResp>bindToLifecycle())
                .subscribe(new ApiSubscriber<InvestResp>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().requestInvestFail();
                        getV().showToast(R.string.request_error);
                    }

                    @Override
                    public void onNext(InvestResp model) {
                        if (model != null && model.getStatus() == 200) {
                            getV().requestInvestSuccess(model.getData());
                        } else if (model != null && model.getStatus() == Constant.NO_LOGIN_STATUS) {
                            getV().showToast(model.getMessage());
                            getV().areadyLogout();
                        } else {
                            getV().requestInvestFail();
                            getV().showToast(model.getMessage());
                            XLog.e("返回数据为空");
                        }
                    }
                });

    }

    /**
     * 修改定投状态
     *
     * @param token
     * @param userId
     * @param protocol_id
     * @param investState
     * @param password
     */
    public void changeState(String token, String userId, String protocol_id, final String investState, String password) {
        CommonReqData reqData = new CommonReqData();
        reqData.setToken(token);
        reqData.setUserId(userId);
        StateChangeParams params = new StateChangeParams();
        params.setScheduled_protocol_id(protocol_id);
        params.setScheduled_protocol_state(investState);
        params.setPassword(password);
        reqData.setData(params);

        Api.getApi().myTimesBuyStateChange(reqData)
                .compose(XApi.<BaseResp>getApiTransformer())
                .compose(XApi.<BaseResp>getScheduler())
                .compose(getV().<BaseResp>bindToLifecycle())
                .subscribe(new ApiSubscriber<BaseResp>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().requestChangeStateFail();
                        getV().showToast(R.string.request_error);
                    }

                    @Override
                    public void onNext(BaseResp resp) {
                        if (resp != null && resp.getStatus() == 200) {
                            getV().requestChangeStateSuccess();
                        } else if (resp != null && resp.getStatus() == Constant.PASSWORD_ERROR_STATUS) {
                            //密码错误的状态码
//                            getV().passwordError(investState);
                            getV().passwordError(investState,resp.getMessage());
                        } else if (resp != null && resp.getStatus() == Constant.NO_LOGIN_STATUS) {
                            getV().showToast(resp.getMessage());
                            getV().areadyLogout();
                        } else {
                            getV().requestChangeStateFail();
                            getV().showToast(resp.getMessage());
                            XLog.e("返回数据为空");
                        }
                    }
                });
    }
}
