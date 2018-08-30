package cn.com.buyforyou.fund.present.user;

import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.constant.Constant;
import cn.com.buyforyou.fund.model.BaseResp;
import cn.com.buyforyou.fund.model.user.CancleOrderResp;
import cn.com.buyforyou.fund.net.Api;
import cn.com.buyforyou.fund.params.CommonReqData;
import cn.com.buyforyou.fund.params.ResultParams;
import cn.com.buyforyou.fund.ui.activity.user.CancleOrderActivity;

import java.util.ArrayList;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * 作者：sunnyzeng on 2018/1/25 13:22
 * 描述：
 */

public class CancleOrderPresent extends XPresent<CancleOrderActivity> {

    /**
     * 撤单列表
     *
     * @param token
     * @param userId
     */
    public void cancleOrderListData(String token, String userId) {
        CommonReqData reqData = new CommonReqData();
        reqData.setToken(token);
        reqData.setUserId(userId);

        Api.getApi().withdrawApplyList(reqData)
                .compose(XApi.<CancleOrderResp>getApiTransformer())
                .compose(XApi.<CancleOrderResp>getScheduler())
                .compose(getV().<CancleOrderResp>bindToLifecycle())
                .subscribe(new ApiSubscriber<CancleOrderResp>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().requestOrderFail();
                        getV().showToast(R.string.request_error);
                    }

                    @Override
                    public void onNext(CancleOrderResp resp) {
                        if (resp != null && resp.getStatus() == 200) {
                            getV().requestOrderSuccess(resp.getData());
                        } else if (resp != null && resp.getStatus() == Constant.NO_LOGIN_STATUS) {
                            getV().showToast(resp.getMessage());
                            getV().areadyLogout();
                        } else {
                            getV().requestOrderFail();
                            getV().showToast(resp.getMessage());
                        }
                    }
                });
    }

    /**
     * 撤单操作
     *
     * @param allot_no
     * @param token
     * @param userId
     */
    public void withdrawApplyOperate(String allot_no, String password, String fund_code, String token, String userId) {
        CommonReqData reqData = new CommonReqData();
        reqData.setToken(token);
        reqData.setUserId(userId);

        ResultParams params = new ResultParams();
        params.setAllot_no(allot_no);
        params.setPassword(password);
        params.setBusin_board_type("");
        params.setComb_requestno("");
        params.setFund_code(fund_code);
        reqData.setData(params);

        Api.getApi().withdrawApplyOperate(reqData)
                .compose(XApi.<BaseResp>getApiTransformer())
                .compose(XApi.<BaseResp>getScheduler())
                .compose(getV().<BaseResp>bindToLifecycle())
                .subscribe(new ApiSubscriber<BaseResp>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().requestCancleFail();
                        getV().showToast(R.string.request_error);
                    }

                    @Override
                    public void onNext(BaseResp resp) {
                        if (resp != null && resp.getStatus() == 200) {
                            getV().requestCancleSuccess();
                        } else if (resp != null && resp.getStatus() == Constant.PASSWORD_ERROR_STATUS) {
                            getV().passwordError(resp.getMessage());
                        } else if (resp != null && resp.getStatus() == Constant.NO_LOGIN_STATUS) {
                            getV().showToast(resp.getMessage());
                            getV().areadyLogout();
                        } else {
                            getV().requestCancleFail();
                            getV().showToast(resp.getMessage());
                        }
                    }
                });
    }
}
