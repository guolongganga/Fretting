package cn.com.buyforyou.fund.present.user;

import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.constant.Constant;
import cn.com.buyforyou.fund.model.BaseResp;
import cn.com.buyforyou.fund.model.user.OccupationResp;
import cn.com.buyforyou.fund.model.user.PersonInfoResp;
import cn.com.buyforyou.fund.net.Api;
import cn.com.buyforyou.fund.params.CommonReqData;
import cn.com.buyforyou.fund.params.PersonInfoParams;
import cn.com.buyforyou.fund.ui.activity.user.PersonInfoActivity;

import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * 作者：sunnyzeng on 2017/12/28 14:21
 * 描述：我的个人信息
 */

public class PersonInfoPresent extends XPresent<PersonInfoActivity> {

    /**
     * 获取个人信息
     *
     * @param token
     * @param userId
     */
    public void getUserInfo(String token, String userId) {
        CommonReqData reqData = new CommonReqData();
        reqData.setToken(token);
        reqData.setUserId(userId);

        Api.getApi()
                .myInformationPage(reqData)
                .compose(XApi.<PersonInfoResp>getApiTransformer())
                .compose(XApi.<PersonInfoResp>getScheduler())
                .compose(getV().<PersonInfoResp>bindToLifecycle())
                .subscribe(new ApiSubscriber<PersonInfoResp>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().requestUserInfoFail();
                        getV().showToast(R.string.request_error);
                    }

                    @Override
                    public void onNext(PersonInfoResp personInfoResp) {
                        if (personInfoResp != null && personInfoResp.getStatus() == 200) {
                            getV().requestUserInfoSuccess(personInfoResp.getData());
                        } else if (personInfoResp != null && personInfoResp.getStatus() == Constant.NO_LOGIN_STATUS) {
                            getV().showToast(personInfoResp.getMessage());
                            getV().areadyLogout();
                        } else {
                            getV().requestUserInfoFail();
                            getV().showToast(personInfoResp.getMessage());
                            XLog.e("返回数据为空");
                        }
                    }
                });

    }

    /**
     * 获取职业集合
     *
     * @param token
     * @param userId
     */
    public void getOccupation(String token, String userId) {
        CommonReqData reqData = new CommonReqData();
        reqData.setToken(token);
        reqData.setUserId(userId);

        Api.getApi()
                .getOccupation(reqData)
                .compose(XApi.<OccupationResp>getApiTransformer())
                .compose(XApi.<OccupationResp>getScheduler())
                .compose(getV().<OccupationResp>bindToLifecycle())
                .subscribe(new ApiSubscriber<OccupationResp>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().getOccupationFail();
                        getV().showToast(R.string.request_error);
                    }

                    @Override
                    public void onNext(OccupationResp occupationResp) {
                        if (occupationResp != null && occupationResp.getStatus() == 200) {
                            getV().getOccupationSuccess(occupationResp.getData());
                        } else if (occupationResp != null && occupationResp.getStatus() == Constant.NO_LOGIN_STATUS) {
                            getV().showToast(occupationResp.getMessage());
                            getV().areadyLogout();
                        } else {
                            getV().getOccupationFail();
                            getV().showToast(occupationResp.getMessage());
                            XLog.e("返回数据为空");
                        }

                    }
                });
    }

    /**
     * 变更个人信息
     * @param token
     * @param userId
     * @param id_enddate
     * @param address
     * @param detaile_address
     * @param email
     * @param selectOccupation
     */
    public void changeMyInformation(String token, String userId, String id_enddate, String address, String detaile_address, String email,
                                    OccupationResp selectOccupation, String trade_password, String currentItemTax) {
        final CommonReqData reqData = new CommonReqData();
        reqData.setToken(token);
        reqData.setUserId(userId);

        PersonInfoParams params = new PersonInfoParams();
        params.setId_enddate(id_enddate);
        params.setAddress(address);
        params.setDetaile_address(detaile_address);
        params.setEmail(email);
        params.setOccupation(selectOccupation);
        params.setTrade_password(trade_password);
        reqData.setData(params);
        params.setCust_flag(currentItemTax);

        Api.getApi()
                .changeMyInformation(reqData)
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
                        }  else if (resp != null && resp.getStatus() == Constant.NO_LOGIN_STATUS) {
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
