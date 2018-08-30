package cn.com.buyforyou.fund.present.user;

import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.model.BaseResp;
import cn.com.buyforyou.fund.net.Api;
import cn.com.buyforyou.fund.params.CommonReqData;
import cn.com.buyforyou.fund.params.FindTradePwdSecondParams;
import cn.com.buyforyou.fund.ui.activity.user.FindPwdTradeSecondActivity;

import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * 作者：sunnyzeng on 2017/12/15 13:43
 * 描述：找回交易密码
 */

public class FindPwdTradeSecondPresent extends XPresent<FindPwdTradeSecondActivity> {

    /**
     * 找回交易密码 第二步
     *
     * @param password 密码
     */
    public void findPassword(String phone, String password, String repetPassword) {

        CommonReqData reqData = new CommonReqData();

        FindTradePwdSecondParams params = new FindTradePwdSecondParams();
        params.setPhone(phone);
        params.setTradePassword(password);
        params.setRepetTradePassword(repetPassword);
        reqData.setData(params);
        //找回交易密码接口
        Api.getApi()
                .findTradePasswordReset(reqData)
                .compose(XApi.<BaseResp>getApiTransformer())
                .compose(XApi.<BaseResp>getScheduler())
                .compose(getV().<BaseResp>bindToLifecycle())
                .subscribe(new ApiSubscriber<BaseResp>() {
                    @Override
                    protected void onFail(NetError error) {
                        error.printStackTrace();
                        getV().requestFail();
                        getV().showToast(R.string.request_error);
                    }

                    @Override
                    public void onNext(BaseResp model) {
                        if (model != null && model.getStatus() == 200) {
                            getV().requestSuccess(model.getData());
                        } else {
                            getV().requestFail();
                            getV().showToast(model.getMessage());
                            XLog.e("返回数据为空");
                        }
                    }
                });

    }
}
