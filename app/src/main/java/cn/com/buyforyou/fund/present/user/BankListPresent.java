package cn.com.buyforyou.fund.present.user;

import android.util.Log;

import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.model.user.BankResp;
import cn.com.buyforyou.fund.net.Api;
import cn.com.buyforyou.fund.params.BankListParams;
import cn.com.buyforyou.fund.params.CommonReqData;
import cn.com.buyforyou.fund.ui.activity.user.BankListActivity;

import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * 作者：sunnyzeng on 2017/12/22 17:34
 * 描述：银行列表
 */

public class BankListPresent extends XPresent<BankListActivity> {

    /**
     * 银行列表
     */
    public void getBankList() {

        CommonReqData reqData = new CommonReqData();

        BankListParams params = new BankListParams();
        params.setPartner_id("");
        reqData.setData(params);

        Api.getApi()
                .bankList(reqData)
                .compose(XApi.<BankResp>getApiTransformer())
                .compose(XApi.<BankResp>getScheduler())
                .compose(getV().<BankResp>bindToLifecycle())
                .subscribe(new ApiSubscriber<BankResp>() {
                    @Override
                    protected void onFail(NetError error) {
                        error.printStackTrace();
                        getV().requestFail();
                        getV().showToast(R.string.request_error);
                    }

                    @Override
                    public void onNext(BankResp model) {
                        if (model != null && model.getStatus() == 200) {
                            getV().bankListData(model.getData());
                        } else {
                            getV().requestFail();
                            getV().showToast(model.getMessage());
                            XLog.e("返回数据为空");
                        }
                    }
                });

    }
}
