package cn.com.buyforyou.fund.model.user;

import cn.com.buyforyou.fund.model.BaseResp;

/**
 * 作者：sunnyzeng on 2017/12/28 11:21
 * 描述：我的电话号码
 */

public class PhoneResp extends BaseResp<PhoneResp> {
    //手机号码
    private String mobile_tel;

    public String getMobile_tel() {
        return mobile_tel;
    }

    public void setMobile_tel(String mobile_tel) {
        this.mobile_tel = mobile_tel;
    }
}
