package cn.com.buyforyou.fund.model.user;

import java.math.BigDecimal;

/**
 * 作者：sunnyzeng on 2018/1/31 16:23
 * 描述：
 */

public class RecordResp {

    /**
     * 添加显示字段，非数据库字段
     */
    private String bankAcco;  //银行卡号
    private String bankName;  //银行名称
    private String withdrawaData; //撤单返回钱的时间
    private String jywater; //扣款信息或回款信息
    private Integer id;
    private String user_id;
    private String fund_code;//'基金代码',
    private String fund_name;// '基金名称',
    private String share_type;// '份额分类',
    private String trade_acco;// '交易账号',
    private BigDecimal fund_amount; //'购买金额',
    private BigDecimal trade_confirm_shares;// '交易确认份额',
    private BigDecimal trade_confirm_balance;//'交易确认金额',
    private String affirm_date; //'确认日期',
    private String apply_time;// '申请时间',
    private String apply_date;//'申请日期',
    private String query_date;//'查询收益/赎回到账日期',
    private String allot_no;//'申请编号',
    private String clear_date;//'清算日期',
    private String trade_mode;// '交易模式（1、买入 0、卖出）',
    private String trade_status;// '交易处理状态',
    private String scheduled_protocol_id;//'定投协议号(定投交易)',
    private String create_time;// '创建时间',
    private String net_value;//确认净值
    private BigDecimal fare_sx;//手续费

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getJywater() {
        return jywater;
    }

    public void setJywater(String jywater) {
        this.jywater = jywater;
    }

    public String getFund_code() {
        return fund_code;
    }

    public void setFund_code(String fund_code) {
        this.fund_code = fund_code;
    }

    public String getFund_name() {
        return fund_name;
    }

    public void setFund_name(String fund_name) {
        this.fund_name = fund_name;
    }

    public String getShare_type() {
        return share_type;
    }

    public void setShare_type(String share_type) {
        this.share_type = share_type;
    }

    public String getTrade_acco() {
        return trade_acco;
    }

    public void setTrade_acco(String trade_acco) {
        this.trade_acco = trade_acco;
    }

    public BigDecimal getFund_amount() {
        return fund_amount;
    }

    public void setFund_amount(BigDecimal fund_amount) {
        this.fund_amount = fund_amount;
    }

    public BigDecimal getTrade_confirm_shares() {
        return trade_confirm_shares;
    }

    public void setTrade_confirm_shares(BigDecimal trade_confirm_shares) {
        this.trade_confirm_shares = trade_confirm_shares;
    }

    public BigDecimal getTrade_confirm_balance() {
        return trade_confirm_balance;
    }

    public void setTrade_confirm_balance(BigDecimal trade_confirm_balance) {
        this.trade_confirm_balance = trade_confirm_balance;
    }

    public String getAffirm_date() {
        return affirm_date;
    }

    public void setAffirm_date(String affirm_date) {
        this.affirm_date = affirm_date;
    }

    public String getApply_time() {
        return apply_time;
    }

    public void setApply_time(String apply_time) {
        this.apply_time = apply_time;
    }

    public String getApply_date() {
        return apply_date;
    }

    public void setApply_date(String apply_date) {
        this.apply_date = apply_date;
    }

    public String getQuery_date() {
        return query_date;
    }

    public void setQuery_date(String query_date) {
        this.query_date = query_date;
    }

    public String getAllot_no() {
        return allot_no;
    }

    public void setAllot_no(String allot_no) {
        this.allot_no = allot_no;
    }

    public String getClear_date() {
        return clear_date;
    }

    public void setClear_date(String clear_date) {
        this.clear_date = clear_date;
    }

    public String getTrade_mode() {
        return trade_mode;
    }

    public void setTrade_mode(String trade_mode) {
        this.trade_mode = trade_mode;
    }

    public String getTrade_status() {
        return trade_status;
    }

    public void setTrade_status(String trade_status) {
        this.trade_status = trade_status;
    }

    public String getScheduled_protocol_id() {
        return scheduled_protocol_id;
    }

    public void setScheduled_protocol_id(String scheduled_protocol_id) {
        this.scheduled_protocol_id = scheduled_protocol_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getNet_value() {
        return net_value;
    }

    public void setNet_value(String net_value) {
        this.net_value = net_value;
    }

    public BigDecimal getFare_sx() {
        return fare_sx;
    }

    public void setFare_sx(BigDecimal fare_sx) {
        this.fare_sx = fare_sx;
    }

    public String getBankAcco() {
        return bankAcco;
    }

    public void setBankAcco(String bankAcco) {
        this.bankAcco = bankAcco;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getWithdrawaData() {
        return withdrawaData;
    }

    public void setWithdrawaData(String withdrawaData) {
        this.withdrawaData = withdrawaData;
    }
}
