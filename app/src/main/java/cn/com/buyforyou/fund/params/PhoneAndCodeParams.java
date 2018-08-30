package cn.com.buyforyou.fund.params;

/**
 * 作者：sunnyzeng on 2017/12/15 13:47
 * 描述：找回 交易/登录 密码第一步参数
 * 示例：{"reqData": {"data":{"phone":"15032269871","validateCode":"1234"},"token":null,"userId":null}}
 */

public class PhoneAndCodeParams extends CommonReqData<PhoneAndCodeParams> {
    private String phoneNo;
    private String phoneCode;

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }
}
