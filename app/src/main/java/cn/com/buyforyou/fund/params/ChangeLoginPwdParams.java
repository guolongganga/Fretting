package cn.com.buyforyou.fund.params;

/**
 * 作者：sunnyzeng on 2017/12/27 16:49
 * 描述：修改登录密码参数
 */

public class ChangeLoginPwdParams extends CommonReqData<ChangeLoginPwdParams> {
    private String password;
    private String repetPassword;

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
