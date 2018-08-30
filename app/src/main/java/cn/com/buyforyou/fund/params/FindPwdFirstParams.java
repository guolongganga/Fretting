package cn.com.buyforyou.fund.params;

/**
 * 作者：sunnyzeng on 2017/12/15 13:47
 * 描述：找回 交易/登录 密码第一步参数
 * 示例：{"reqData": {"data":{"phone":"15032269871","validateCode":"1234"},"token":null,"userId":null}}
 */

public class FindPwdFirstParams extends CommonReqData<FindPwdFirstParams> {
    private String phone;
    private String validateCode;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }
}
