package cn.com.buyforyou.fund.present.index;

import android.util.Log;

import com.orhanobut.logger.Logger;

import cn.com.buyforyou.fund.model.index.IndexResp;
import cn.com.buyforyou.fund.model.index.UpdateVersionResp;
import cn.com.buyforyou.fund.net.Api;
import cn.com.buyforyou.fund.params.CheckVersionParams;
import cn.com.buyforyou.fund.params.CommonReqData;
import cn.com.buyforyou.fund.ui.fragment.index.IndexFragment;

import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * 首页
 * Created by ${Yis}
 * data: 2017/12/16
 */

public class IndexPresent extends XPresent<IndexFragment> {
    /**
     * 首页数据
     */
    public void loadData() {

        CommonReqData reqData = new CommonReqData();
        reqData.setData("");
        Api.getApi().getHome(reqData)
                .compose(XApi.<IndexResp>getApiTransformer())
                .compose(XApi.<IndexResp>getScheduler())
                .compose(getV().<IndexResp>bindToLifecycle())
                .subscribe(new ApiSubscriber<IndexResp>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().requestIndexDataFail();
                    }

                    @Override
                    public void onNext(IndexResp resp) {
                        if (resp != null && resp.getStatus() == 200) {
                            getV().showIndexData(resp.getData());
                        } else {
                            getV().requestIndexDataFail();
                            getV().showToast(resp.getMessage());
                            XLog.e("返回数据为空");
                        }
                    }
                });
    }

    public void checkUpdate(String versionName) {

        CommonReqData<CheckVersionParams> reqData = new CommonReqData<CheckVersionParams>();
        CheckVersionParams checkVersionParams = new CheckVersionParams();
        checkVersionParams.setDeviceType("android");
        checkVersionParams.setPreVerNum(versionName);
        reqData.setData(checkVersionParams);

        Api.getApi().checkVersion(reqData)
                .compose(XApi.<UpdateVersionResp>getApiTransformer())
                .compose(XApi.<UpdateVersionResp>getScheduler())
                .compose(getV().<UpdateVersionResp>bindToLifecycle())
                .subscribe(new ApiSubscriber<UpdateVersionResp>() {
                    @Override
                    protected void onFail(NetError error) {

                    }

                    @Override
                    public void onNext(UpdateVersionResp resp) {
                        if (resp != null && resp.getStatus() == 200) {

                            getV().checkVersionSuccess(resp.getData());
                        }
                    }
                });

    }
}
