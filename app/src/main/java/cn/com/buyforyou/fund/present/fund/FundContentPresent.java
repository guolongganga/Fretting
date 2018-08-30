package cn.com.buyforyou.fund.present.fund;

import cn.com.buyforyou.fund.model.fund.FundResp;
import cn.com.buyforyou.fund.model.fund.NewestFundResp;
import cn.com.buyforyou.fund.net.Api;
import cn.com.buyforyou.fund.params.CommonReqData;
import cn.com.buyforyou.fund.params.NewestFundParams;
import cn.com.buyforyou.fund.ui.fragment.fund.FundContentFragment;

import java.util.ArrayList;
import java.util.List;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by ${sunny}
 * data: 2017/12/19
 */

public class FundContentPresent extends XPresent<FundContentFragment> {

    /**
     * 基金列表
     *
     * @param pageno
     * @param pagesize
     * @param type
     */
    public void loadFundData(final int pageno, int pagesize, String type, String orderBy) {
        CommonReqData reqData = new CommonReqData();
        NewestFundParams params = new NewestFundParams();
        params.setPageSize(pagesize);
        params.setPageNum(pageno);
        params.setOfund_type(type);
        params.setPerformance_term(orderBy);
        reqData.setData(params);

        Api.getApi().getNewestFund(reqData)
                .compose(XApi.<NewestFundResp>getApiTransformer())
                .compose(XApi.<NewestFundResp>getScheduler())
                .compose(getV().<NewestFundResp>bindToLifecycle())
                .subscribe(new ApiSubscriber<NewestFundResp>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                    @Override
                    public void onNext(NewestFundResp resp) {
                        if (resp != null && resp.getStatus() == 200) {
                            getV().showData(pageno, resp.getData());
                        } else {
                            getV().showToast(resp.getMessage());
                        }

                    }
                });

    }

}
