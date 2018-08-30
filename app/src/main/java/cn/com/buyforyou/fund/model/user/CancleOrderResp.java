package cn.com.buyforyou.fund.model.user;

import cn.com.buyforyou.fund.model.BaseResp;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * 作者：sunnyzeng on 2018/1/31 14:10
 * 描述：撤单列表
 */

public class CancleOrderResp extends BaseResp<ArrayList<CancleOrderResp>> {
    /**
     * 页面展示需要，添加 买入卖出
     * （1、买入 0、卖出）
     */
    private String buyType;
    /**
     * 下单时间 N 6 0 N v4.0.2.0
     */
    private String accept_time;
    /**
     * 申请编号 S 24 0 N v4.0.2.0
     */
    private String allot_no;
    /**
     * 申请日期 N 8 0 N v4.0.2.0
     */
    private String apply_date;
    /**
     * 申请份数 F 16 2 N v4.0.2.0
     */
    private BigDecimal apply_share;
    /**
     * 申请金额 N 16 2 N v4.0.2.0
     */
    private BigDecimal apply_sum;
    /**
     * 申请时间 N 6 0 N v4.0.2.0
     */
    private String apply_time;
    /**
     * 分红方式 C 1 0 N v4.0.2.0
     */
    private String auto_buy;
    /**
     * 回款账户 S 28 0 N v4.0.2.0  银行卡号/银行账号
     */
    private String back_bankacco;
    /**
     * 银行名称 S 64 0 N v4.0.2.0
     */
    private String bank_name;
    /**
     * 业务辅助代码 S 2 0 N v4.0.2.0  直销字典【业务辅助代码】
     */
    private String busin_ass_code;
    /**
     * 业务大类 S 2 0 N v4.0.2.0  直销字典【业务大类】
     */
    private String busin_board_type;
    /**
     * 资金方式 S 2 0 N v4.0.2.0  直销字典【资金方式】
     */
    private String capital_mode;
    /**
     * 组合编号 S 4 0 N v4.0.2.0
     */
    private String comb_code;
    /**
     * 组合申请编号 S 24 0 N v4.0.2.0
     */
    private String comb_requestno;
    /**
     * 交易来源 S 60 0 N v4.0.2.0
     */
    private String come_from;
    /**
     * 确认标志 C 1 0 N v4.0.2.0
     */
    private String confirm_flag;
    /**
     * 扣款状态 C 1 0 N v4.0.2.0
     */
    private String deduct_status;
    /**
     * 明细资金方式 S 2 0 N v4.0.2.0  直销字典【明细资金方式】
     */
    private String detail_fund_way;
    /**
     * 指数类型 S 2 0 N v4.0.2.0
     */
    private String exp_type;
    /**
     * 指数代码 S 6 0 N v4.0.2.0
     */
    private String exponent_code;
    /**
     * 预约条件 C 1 0 N v4.0.2.0
     */
    private String exponent_flag;
    /**
     * 预约点位 F 18 4 N v4.0.2.0
     */
    private BigDecimal exponent_value;
    /**
     * 基金账号 S 12 0 N v4.0.2.0
     */
    private String fund_account;
    /**
     * 业务代码 S 3 0 N v4.0.2.0
     */
    private String fund_busin_code;
    /**
     * 基金代码 S 6 0 N v4.0.2.0
     */
    private String fund_code;
    /**
     * 基金名称 S 32 0 N v4.0.2.0
     */
    private String fund_name;
    /**
     * 风险标志 C 1 0 N v4.0.2.0
     */
    private String fund_risk_flag;
    /**
     * 预约日期 N 8 0 N v4.0.2.0
     */
    private String hope_date;
    /**
     * 原申请日期 N 8 0 N v4.0.2.0
     */
    private String original_date;
    /**
     * 协议号 S 20 0 N v4.0.2.0
     */
    private String protocol_id;
    /**
     * 份额分类 C 1 0 N v4.0.2.0
     */
    private String share_type;
    /**
     * 目标基金代码 S 6 0 N v4.0.2.0
     */
    private String target_fund_code;
    /**
     * 目标份额类型 C 1 0 N v4.0.2.0
     */
    private String target_share_type;
    /**
     * 交易账号 S 17 0 N v4.0.2.0
     */
    private String trade_acco;

    public String getBuyType() {
        return buyType;
    }

    public void setBuyType(String buyType) {
        this.buyType = buyType;
    }

    public String getAccept_time() {
        return accept_time;
    }

    public void setAccept_time(String accept_time) {
        this.accept_time = accept_time;
    }

    public String getAllot_no() {
        return allot_no;
    }

    public void setAllot_no(String allot_no) {
        this.allot_no = allot_no;
    }

    public String getApply_date() {
        return apply_date;
    }

    public void setApply_date(String apply_date) {
        this.apply_date = apply_date;
    }

    public BigDecimal getApply_share() {
        return apply_share;
    }

    public void setApply_share(BigDecimal apply_share) {
        this.apply_share = apply_share;
    }

    public BigDecimal getApply_sum() {
        return apply_sum;
    }

    public void setApply_sum(BigDecimal apply_sum) {
        this.apply_sum = apply_sum;
    }

    public String getApply_time() {
        return apply_time;
    }

    public void setApply_time(String apply_time) {
        this.apply_time = apply_time;
    }

    public String getAuto_buy() {
        return auto_buy;
    }

    public void setAuto_buy(String auto_buy) {
        this.auto_buy = auto_buy;
    }

    public String getBack_bankacco() {
        return back_bankacco;
    }

    public void setBack_bankacco(String back_bankacco) {
        this.back_bankacco = back_bankacco;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getBusin_ass_code() {
        return busin_ass_code;
    }

    public void setBusin_ass_code(String busin_ass_code) {
        this.busin_ass_code = busin_ass_code;
    }

    public String getBusin_board_type() {
        return busin_board_type;
    }

    public void setBusin_board_type(String busin_board_type) {
        this.busin_board_type = busin_board_type;
    }

    public String getCapital_mode() {
        return capital_mode;
    }

    public void setCapital_mode(String capital_mode) {
        this.capital_mode = capital_mode;
    }

    public String getComb_code() {
        return comb_code;
    }

    public void setComb_code(String comb_code) {
        this.comb_code = comb_code;
    }

    public String getComb_requestno() {
        return comb_requestno;
    }

    public void setComb_requestno(String comb_requestno) {
        this.comb_requestno = comb_requestno;
    }

    public String getCome_from() {
        return come_from;
    }

    public void setCome_from(String come_from) {
        this.come_from = come_from;
    }

    public String getConfirm_flag() {
        return confirm_flag;
    }

    public void setConfirm_flag(String confirm_flag) {
        this.confirm_flag = confirm_flag;
    }

    public String getDeduct_status() {
        return deduct_status;
    }

    public void setDeduct_status(String deduct_status) {
        this.deduct_status = deduct_status;
    }

    public String getDetail_fund_way() {
        return detail_fund_way;
    }

    public void setDetail_fund_way(String detail_fund_way) {
        this.detail_fund_way = detail_fund_way;
    }

    public String getExp_type() {
        return exp_type;
    }

    public void setExp_type(String exp_type) {
        this.exp_type = exp_type;
    }

    public String getExponent_code() {
        return exponent_code;
    }

    public void setExponent_code(String exponent_code) {
        this.exponent_code = exponent_code;
    }

    public String getExponent_flag() {
        return exponent_flag;
    }

    public void setExponent_flag(String exponent_flag) {
        this.exponent_flag = exponent_flag;
    }

    public BigDecimal getExponent_value() {
        return exponent_value;
    }

    public void setExponent_value(BigDecimal exponent_value) {
        this.exponent_value = exponent_value;
    }

    public String getFund_account() {
        return fund_account;
    }

    public void setFund_account(String fund_account) {
        this.fund_account = fund_account;
    }

    public String getFund_busin_code() {
        return fund_busin_code;
    }

    public void setFund_busin_code(String fund_busin_code) {
        this.fund_busin_code = fund_busin_code;
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

    public String getFund_risk_flag() {
        return fund_risk_flag;
    }

    public void setFund_risk_flag(String fund_risk_flag) {
        this.fund_risk_flag = fund_risk_flag;
    }

    public String getHope_date() {
        return hope_date;
    }

    public void setHope_date(String hope_date) {
        this.hope_date = hope_date;
    }

    public String getOriginal_date() {
        return original_date;
    }

    public void setOriginal_date(String original_date) {
        this.original_date = original_date;
    }

    public String getProtocol_id() {
        return protocol_id;
    }

    public void setProtocol_id(String protocol_id) {
        this.protocol_id = protocol_id;
    }

    public String getShare_type() {
        return share_type;
    }

    public void setShare_type(String share_type) {
        this.share_type = share_type;
    }

    public String getTarget_fund_code() {
        return target_fund_code;
    }

    public void setTarget_fund_code(String target_fund_code) {
        this.target_fund_code = target_fund_code;
    }

    public String getTarget_share_type() {
        return target_share_type;
    }

    public void setTarget_share_type(String target_share_type) {
        this.target_share_type = target_share_type;
    }

    public String getTrade_acco() {
        return trade_acco;
    }

    public void setTrade_acco(String trade_acco) {
        this.trade_acco = trade_acco;
    }
}
