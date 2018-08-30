package cn.com.buyforyou.fund.model.user;

import cn.com.buyforyou.fund.model.BaseResp;

/**
 * 作者：sunnyzeng on 2017/12/25 14:22
 * 描述：短信验证码接口返回数据
 */

public class PhoneCodeResp extends BaseResp<PhoneCodeResp> {
    private String messageCode;

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }
}
