package cn.com.buyforyou.fund.present.user;

import cn.com.buyforyou.fund.constant.Constant;
import cn.com.buyforyou.fund.model.user.TransactionResp;
import cn.com.buyforyou.fund.net.Api;
import cn.com.buyforyou.fund.params.CommonReqData;
import cn.com.buyforyou.fund.params.TransactionQueryParams;
import cn.com.buyforyou.fund.ui.fragment.user.TransactionSingleContentFragment;

import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * 作者：sunnyzeng on 2018/1/24 16:08
 * 描述：
 */

public class TransactionSingleContentPresent extends XPresent<TransactionSingleContentFragment> {
    /**
     * 交易查询 (买入，赎回，定投)
     *
     * @param token
     * @param userId
     * @param pageno
     * @param pageSize
     * @param tabType
     * @param fundCode
     */
    public void loadTransactionData(String token, String userId, final int pageno, int pageSize, String tabType, String fundCode) {
        CommonReqData reqData = new CommonReqData();
        reqData.setToken(token);
        reqData.setUserId(userId);

        TransactionQueryParams params = new TransactionQueryParams();
        params.setPageNum(pageno);
        params.setPageSize(pageSize);
        params.setTransactionCategory(tabType);
        params.setFundCode(fundCode);
        reqData.setData(params);

        Api.getApi()
                .tradeQueryData(reqData)
                .compose(XApi.<TransactionResp>getApiTransformer())
                .compose(XApi.<TransactionResp>getScheduler())
                .compose(getV().<TransactionResp>bindToLifecycle())
                .subscribe(new ApiSubscriber<TransactionResp>() {
                    @Override
                    protected void onFail(NetError error) {
                        error.printStackTrace();
                        getV().showError();
                    }

                    @Override
                    public void onNext(TransactionResp model) {
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

    /**
     * 分红
     *
     * @param token
     * @param userId
     * @param pageno
     * @param pageSize
     * @param tabType
     * @param fundCode
     */
    public void shareOutBonusTradeQuery(String token, String userId, final int pageno, int pageSize, String tabType, String fundCode) {
        CommonReqData reqData = new CommonReqData();
        reqData.setToken(token);
        reqData.setUserId(userId);

        TransactionQueryParams params = new TransactionQueryParams();
        params.setPageNum(pageno);
        params.setPageSize(pageSize);
        params.setTransactionCategory(tabType);
        params.setFundCode(fundCode);
        reqData.setData(params);

        Api.getApi()
                .shareOutBonusTradeQuery(reqData)
                .compose(XApi.<TransactionResp>getApiTransformer())
                .compose(XApi.<TransactionResp>getScheduler())
                .compose(getV().<TransactionResp>bindToLifecycle())
                .subscribe(new ApiSubscriber<TransactionResp>() {
                    @Override
                    protected void onFail(NetError error) {
                        error.printStackTrace();
                        getV().showError();
                    }

                    @Override
                    public void onNext(TransactionResp model) {
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
