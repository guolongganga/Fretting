package cn.com.buyforyou.fund.model;

/**
 * 作者：sunnyzeng on 2017/12/15 10:46
 * 描述：{"data":
 * {"data":{"code":"ETS-5BP0000","isOpenAccount":"1","message":"交易成功",
 * "token":"bd80b94279ba4aed995d5f741e0a4412","userId":"2c3f9daf3c474fd6ba2935f695bcb8f8"},
 * "message":"成功","status":200}
 */

public class LoginResp extends BaseResp<LoginResp> {
    private String token;
    private String userId;
    private String isOpenAccount;

    public String getIsOpenAccount() {
        return isOpenAccount;
    }

    public void setIsOpenAccount(String isOpenAccount) {
        this.isOpenAccount = isOpenAccount;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
