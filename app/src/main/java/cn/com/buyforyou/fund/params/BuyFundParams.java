package cn.com.buyforyou.fund.params;

/**
 * 作者：sunnyzeng on 2018/1/11 17:41
 * 描述：
 */

public class BuyFundParams extends CommonReqData<BuyFundParams> {
    private String fund_code;
    private String balance;
    private String share_type;
    private String password;
    private String trade_acco;

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getShare_type() {
        return share_type;
    }

    public void setShare_type(String share_type) {
        this.share_type = share_type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTrade_acco() {
        return trade_acco;
    }

    public void setTrade_acco(String trade_acco) {
        this.trade_acco = trade_acco;
    }

    public String getFund_code() {
        return fund_code;
    }

    public void setFund_code(String fund_code) {
        this.fund_code = fund_code;
    }
}
