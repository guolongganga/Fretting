package cn.com.buyforyou.fund.params;

/**
 * 作者：sunnyzeng on 2017/12/14 18:05
 * 描述：
 */

public class LoginParams extends CommonReqData<LoginParams> {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
