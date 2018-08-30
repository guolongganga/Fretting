package cn.com.buyforyou.fund.params;

/**
 * 作者：sunnyzeng on 2017/12/15 16:30
 * 描述：找回交易密码 第二步
 */

public class FindTradePwdSecondParams extends CommonReqData<FindTradePwdSecondParams> {

    private String phone;
    private String tradePassword;
    private String repetTradePassword;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTradePassword() {
        return tradePassword;
    }

    public void setTradePassword(String tradePassword) {
        this.tradePassword = tradePassword;
    }

    public String getRepetTradePassword() {
        return repetTradePassword;
    }

    public void setRepetTradePassword(String repetTradePassword) {
        this.repetTradePassword = repetTradePassword;
    }
}
