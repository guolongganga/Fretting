package cn.com.buyforyou.fund.params;

/**
 * 作者：sunnyzeng on 2017/12/15 16:30
 * 描述：找回登录密码 第二步
 */

public class FindLoginPwdSecondParams extends CommonReqData<FindLoginPwdSecondParams> {

    private String phone;
    private String password;
    private String repetPassword;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRepetPassword() {
        return repetPassword;
    }

    public void setRepetPassword(String repetPassword) {
        this.repetPassword = repetPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
