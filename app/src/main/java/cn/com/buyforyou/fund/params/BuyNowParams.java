package cn.com.buyforyou.fund.params;

/**
 * 作者：sunnyzeng on 2018/1/12 14:56
 * 描述：立即购买参数
 * "data": {
 * "fund_code": "050001",	基金代码
 * "balance":"5000",		购买金额
 * "password":"135792",	交易密码
 * },
 * "userId":"0f4ddf4852e644598d7ade9edc433e87",
 * "token":"8d9f2d6690904d569c1b27133d692db1"
 * }
 */

public class BuyNowParams extends CommonReqData<BuyNowParams> {
    /** 基金代码 */
    private String fund_code;
    /** 购买金额 */
    private String balance;
    /** 交易密码 */
    private String password;
    /** 分红方式 */
    private String auto_buy;

    public String getAuto_buy() {
        return auto_buy;
    }

    public void setAuto_buy(String auto_buy) {
        this.auto_buy = auto_buy;
    }

    public String getFund_code() {
        return fund_code;
    }

    public void setFund_code(String fund_code) {
        this.fund_code = fund_code;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
