package cn.com.buyforyou.fund.params;

/**
 * 作者：sunnyzeng on 2018/1/16 17:04
 * 描述：定投购买
 * {"reqData":{"data":{
 * "fundCode":"050003",
 * "fund_name":"博时现金收益货币A",
 * "apply_sum":"19990",
 * "first_trade_month":"201802"
 * "fix_date":"03",
 * "password":"we3412",
 * "protocol_period_unit":"0"},
 * "token":"7af37b692611438cbda677386223bd0d",
 * "userId":"ffa68a63c1e34aa48d17088e33d39b4f"}}
 */

public class InvestBuyParams extends CommonReqData<InvestBuyParams> {
    /**
     * 基金代码
     */
    private String fundCode;
    /**
     * 基金名称
     */
    private String fund_name;
    /**
     * 定投金额
     */
    private String apply_sum;
    /**
     * 首次交易月
     */
    private String first_trade_month;
    /**
     * 定投日
     */
    private String fix_date;
    /**
     * 交易密码
     */
    private String password;
    /**
     * 协议周期单位
     */
    private String protocol_period_unit;
    /**
     * 协议编号
     */
    private String scheduled_protocol_id;

    public String getScheduled_protocol_id() {
        return scheduled_protocol_id;
    }

    public void setScheduled_protocol_id(String scheduled_protocol_id) {
        this.scheduled_protocol_id = scheduled_protocol_id;
    }

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    public String getFund_name() {
        return fund_name;
    }

    public void setFund_name(String fund_name) {
        this.fund_name = fund_name;
    }

    public String getApply_sum() {
        return apply_sum;
    }

    public void setApply_sum(String apply_sum) {
        this.apply_sum = apply_sum;
    }

    public String getFirst_trade_month() {
        return first_trade_month;
    }

    public void setFirst_trade_month(String first_trade_month) {
        this.first_trade_month = first_trade_month;
    }

    public String getFix_date() {
        return fix_date;
    }

    public void setFix_date(String fix_date) {
        this.fix_date = fix_date;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProtocol_period_unit() {
        return protocol_period_unit;
    }

    public void setProtocol_period_unit(String protocol_period_unit) {
        this.protocol_period_unit = protocol_period_unit;
    }
}
