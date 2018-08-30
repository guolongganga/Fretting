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

public class SellSureParams extends CommonReqData<SellSureParams> {
    /** 基金代码 */
    private String fund_code;
    /** 交易号 */
    private String trade_acco;
    /** 交易密码 */
    private String password;
    /** 基金名称 */
    private String fund_name;
    /** 赎回份额 */
    private String shares;
    /** 选择方式 */
    private String fund_exceed_flag;
    /** 份额类型 */
    private String share_type;

    public String getFund_code() {
        return fund_code;
    }

    public void setFund_code(String fund_code) {
        this.fund_code = fund_code;
    }

    public String getTrade_acco() {
        return trade_acco;
    }

    public void setTrade_acco(String trade_acco) {
        this.trade_acco = trade_acco;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFund_name() {
        return fund_name;
    }

    public void setFund_name(String fund_name) {
        this.fund_name = fund_name;
    }

    public String getShares() {
        return shares;
    }

    public void setShares(String shares) {
        this.shares = shares;
    }

    public String getFund_exceed_flag() {
        return fund_exceed_flag;
    }

    public void setFund_exceed_flag(String fund_exceed_flag) {
        this.fund_exceed_flag = fund_exceed_flag;
    }

    public String getShare_type() {
        return share_type;
    }

    public void setShare_type(String share_type) {
        this.share_type = share_type;
    }
}
