package cn.com.buyforyou.fund.present.user;

import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.constant.Constant;
import cn.com.buyforyou.fund.model.BaseResp;
import cn.com.buyforyou.fund.model.user.OccupationResp;
import cn.com.buyforyou.fund.model.user.ResidentsTaxInfoResp;
import cn.com.buyforyou.fund.net.Api;
import cn.com.buyforyou.fund.params.CommonReqData;
import cn.com.buyforyou.fund.params.PersonInfoParams;
import cn.com.buyforyou.fund.params.TaxInfoEParams;
import cn.com.buyforyou.fund.ui.activity.user.ResidentsTaxInfoActivity;
import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * 涉税信息
 */

public class ResidentsTaxInfoPresent extends XPresent<ResidentsTaxInfoActivity> {

    /**
     * 获取涉税信息
     */
    public void getResidentsTax(String token, String userId) {
        CommonReqData reqData = new CommonReqData();
        reqData.setToken(token);
        reqData.setUserId(userId);

        Api.getApi()
                .myResidentsTax(reqData)
                .compose(XApi.<ResidentsTaxInfoResp>getApiTransformer())
                .compose(XApi.<ResidentsTaxInfoResp>getScheduler())
                .compose(getV().<ResidentsTaxInfoResp>bindToLifecycle())
                .subscribe(new ApiSubscriber<ResidentsTaxInfoResp>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().requestUserInfoFail();
                        getV().showToast(R.string.request_error);
                    }

                    @Override
                    public void onNext(ResidentsTaxInfoResp residentsTaxInfoResp) {
                        if (residentsTaxInfoResp != null && residentsTaxInfoResp.getStatus() == 200) {
                            getV().requestUserInfoSuccess(residentsTaxInfoResp.getData());
                        } else if (residentsTaxInfoResp != null && residentsTaxInfoResp.getStatus() == Constant.NO_LOGIN_STATUS) {
                            getV().showToast(residentsTaxInfoResp.getMessage());
                            getV().areadyLogout();
                        } else {
                            getV().requestUserInfoFail();
                            getV().showToast(residentsTaxInfoResp.getMessage());
                            XLog.e("返回数据为空");
                        }
                    }
                });

    }


    public void saveTaxInfo(String token, String userId, ResidentsTaxInfoResp residentsTaxInfoResp) {
        final CommonReqData reqData = new CommonReqData();
        reqData.setToken(token);
        reqData.setUserId(userId);


        TaxInfoEParams params = new TaxInfoEParams();

        params.setBorn_nation(residentsTaxInfoResp.getCrsmain().getBorn_nation());
        params.setClient_id(residentsTaxInfoResp.getCrsmain().getClient_id());
        params.setCurrent_addr(residentsTaxInfoResp.getCrsmain().getCurrent_addr());
        params.setCurrent_work_addr(residentsTaxInfoResp.getCrsmain().getCurrent_work_addr());
        params.setCurrent_work_city(residentsTaxInfoResp.getCrsmain().getCurrent_work_city());
        params.setCurrent_work_city_no(residentsTaxInfoResp.getCrsmain().getCurrent_work_city_no());
        params.setCurrent_work_nation(residentsTaxInfoResp.getCrsmain().getCurrent_work_nation());
        params.setCurrent_work_provice_code(residentsTaxInfoResp.getCrsmain().getCurrent_work_provice_code());
        params.setCust_flag(residentsTaxInfoResp.getCrsmain().getCust_flag());
        params.setEng_born_nation(residentsTaxInfoResp.getCrsmain().getEng_born_nation());
        params.setEng_current_addr(residentsTaxInfoResp.getCrsmain().getEng_current_addr());
        params.setEng_current_work_nation(residentsTaxInfoResp.getCrsmain().getEng_current_work_nation());
        params.setEng_home_place(residentsTaxInfoResp.getCrsmain().getEng_home_place());
        params.setEng_last_name(residentsTaxInfoResp.getCrsmain().getEng_last_name());
        params.setEng_name(residentsTaxInfoResp.getCrsmain().getEng_name());
        params.setHome_place(residentsTaxInfoResp.getCrsmain().getHome_place());
        params.setJson_id(residentsTaxInfoResp.getCrsmain().getJson_id());
        params.setLast_date(residentsTaxInfoResp.getCrsmain().getLast_date());
        params.setSign(residentsTaxInfoResp.getCrsmain().getSign());
        params.setTaxinfo(residentsTaxInfoResp.getCrsmain().getTaxinfo());


        reqData.setData(params);

        Api.getApi()
                .saveTaxInfo(reqData)
                .compose(XApi.<BaseResp>getApiTransformer())
                .compose(XApi.<BaseResp>getScheduler())
                .compose(getV().<BaseResp>bindToLifecycle())
                .subscribe(new ApiSubscriber<BaseResp>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().changeMyInformationFail();
                        getV().showToast(R.string.request_error);
                    }

                    @Override
                    public void onNext(BaseResp resp) {
                        if (resp != null && resp.getStatus() == 200) {
                            getV().changeMyInformationSuccess();
                        } else if (resp != null && resp.getStatus() == Constant.PASSWORD_ERROR_STATUS) {
                            getV().passwordError(resp.getMessage());
                        } else if (resp != null && resp.getStatus() == Constant.NO_LOGIN_STATUS) {
                            getV().showToast(resp.getMessage());
                            getV().areadyLogout();
                        } else {
                            getV().changeMyInformationFail();
                            getV().showToast(resp.getMessage());
                            XLog.e("返回数据为空");
                        }
                    }
                });
    }

}
