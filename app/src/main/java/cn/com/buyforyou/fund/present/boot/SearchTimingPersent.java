package cn.com.buyforyou.fund.present.boot;

import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.model.fund.NewestFundResp;
import cn.com.buyforyou.fund.net.Api;
import cn.com.buyforyou.fund.params.CommonReqData;
import cn.com.buyforyou.fund.params.SearchParams;
import cn.com.buyforyou.fund.ui.activity.boot.SearchActivity;
import cn.com.buyforyou.fund.ui.activity.boot.SearchTimingActivity;

import java.util.ArrayList;

import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * 作者：sunnyzeng on 2018/1/10 17:33
 * 描述：定投排行 搜索页面控制器
 */

public class SearchTimingPersent extends XPresent<SearchTimingActivity> {

    /**
     * 热搜
     */
    public void hotListData() {
        //伪数据
        ArrayList<NewestFundResp> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            NewestFundResp newestFundResp = new NewestFundResp();
            newestFundResp.setFund_name("招商安泰股票" + i);
            newestFundResp.setFund_code("21700" + i);
            list.add(newestFundResp);
        }
        if (true) {
            getV().requestHotListSuccess(list);
        } else {
            getV().requestHotListFail();
        }

    }

    /**
     * 实时搜索
     *
     * @param pageno
     * @param pageSize
     * @param keyword
     */
    public void searchTimingData(final int pageno, int pageSize, String keyword) {

        CommonReqData reqData = new CommonReqData();
        SearchParams params = new SearchParams();
        params.setPageNum(pageno);
        params.setPageSize(pageSize);
        params.setFund_code(keyword);
        reqData.setData(params);

        Api.getApi()
                .findFixedFundLike(reqData)
                .compose(XApi.<NewestFundResp>getApiTransformer())
                .compose(XApi.<NewestFundResp>getScheduler())
                .compose(getV().<NewestFundResp>bindToLifecycle())
                .subscribe(new ApiSubscriber<NewestFundResp>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().requestSearchDataFail(error);
                        getV().showToast(R.string.request_error);
                    }

                    @Override
                    public void onNext(NewestFundResp newestFundResp) {
                        if (newestFundResp != null && newestFundResp.getStatus() == 200) {
                            getV().requestSearchDataSuccess(pageno, newestFundResp.getData());
                        } else {
                            getV().showToast(newestFundResp.getMessage());
                            XLog.e("返回数据为空");
                        }
                    }
                });


    }
}
