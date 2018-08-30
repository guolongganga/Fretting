package cn.com.buyforyou.fund.model.fund;

import android.os.Parcel;
import android.os.Parcelable;

import cn.com.buyforyou.fund.model.BaseResp;
import cn.com.buyforyou.fund.model.user.BankCardResp;

import java.math.BigDecimal;

/**
 * 作者：sunnyzeng on 2018/1/16 10:03
 * 描述：定投数据
 */

public class InvestResp extends BaseResp<InvestResp> implements Parcelable {
    /**
     * 定投日详情 （每周一（每月2日））
     */
    private String fixDateDetails;

    /**
     * 基金代码
     */
    private String fundCode;

    /**
     * 基金全名
     */
    private String fund_full_name;
    /**
     * 基金名称
     */
    private String fund_name;

    /**
     * 银行卡信息
     */
    private BankCardResp bankCardPageEntity;

    /**
     * 最低购买金额
     */
    private BigDecimal minPurchaseAmt;


    /**
     * 扣款时间(根据实际情况拼接起来的)
     */
    private String exchdate;

    /**
     * 扣款星期几
     */
    private String exchWeek;


    /**
     * 申请金额
     */
    private BigDecimal apply_sum;

    /**
     * 首次交易月 S 6 0 Y v4.0.3.0
     */
    private String first_trade_month;

    /**
     * 定投日 S 2 0 Y v4.0.3.0  当传入协议周期为"0"（月）时，
     * 定投日必须为28号之前；当传入协议周期为"1"（周）时，定投日对应为星期一到星期五(对应02-06)；其余都表示日期
     */
    private String fix_date;

    /**
     * 交易密码
     */
    private String password;

    /**
     * 协议周期单位 C 1 0 Y v4.0.3.0  "0":月,"1":周,"2":日
     */
    private String protocol_period_unit;

    /**
     * 份额分类 C 1 0 Y v4.0.3.0  A:前收费,B:后收费
     */
    private String share_type;

    /**
     * 交易账号 S 17 0 Y v4.0.3.0
     */
    private String trade_acco;

    /**
     * 交易周期 S 8 0 Y v4.0.3.0  根据协议周期单位不同，例如，协议周期单位是"月"，则交易周期1表示1个月，2表示2个月
     */
    private String trade_period;

    /**
     * 定投协议号(恒生成功后的结果的)
     */
    private String scheduled_protocol_id;

    /**
     * 协议定投日单位
     */
    private String first_exchdate;

    /**
     * 是否能够购买
     */
    private String canBuy;

    /**
     * 风险等级
     */
    private String fundRisk;
    /**
     * 定投协议状态
     */
    private String scheduled_protocol_state;
    /**
     * 下次日期
     */
    private String next_fixrequest_date;
    /**
     * 累计金额
     */
    private String total_succ_sum;
    /**
     * 已投期数
     */
    private String total_succ_time;

    public String getFixDateDetails() {
        return fixDateDetails;
    }

    public void setFixDateDetails(String fixDateDetails) {
        this.fixDateDetails = fixDateDetails;
    }

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    public String getFund_full_name() {
        return fund_full_name;
    }

    public void setFund_full_name(String fund_full_name) {
        this.fund_full_name = fund_full_name;
    }

    public String getFund_name() {
        return fund_name;
    }

    public void setFund_name(String fund_name) {
        this.fund_name = fund_name;
    }

    public BankCardResp getBankCardPageEntity() {
        return bankCardPageEntity;
    }

    public void setBankCardPageEntity(BankCardResp bankCardPageEntity) {
        this.bankCardPageEntity = bankCardPageEntity;
    }

    public BigDecimal getMinPurchaseAmt() {
        return minPurchaseAmt;
    }

    public void setMinPurchaseAmt(BigDecimal minPurchaseAmt) {
        this.minPurchaseAmt = minPurchaseAmt;
    }

    public String getExchdate() {
        return exchdate;
    }

    public void setExchdate(String exchdate) {
        this.exchdate = exchdate;
    }

    public String getExchWeek() {
        return exchWeek;
    }

    public void setExchWeek(String exchWeek) {
        this.exchWeek = exchWeek;
    }

    public BigDecimal getApply_sum() {
        return apply_sum;
    }

    public void setApply_sum(BigDecimal apply_sum) {
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

    public String getTrade_period() {
        return trade_period;
    }

    public void setTrade_period(String trade_period) {
        this.trade_period = trade_period;
    }

    public String getScheduled_protocol_id() {
        return scheduled_protocol_id;
    }

    public void setScheduled_protocol_id(String scheduled_protocol_id) {
        this.scheduled_protocol_id = scheduled_protocol_id;
    }

    public String getFirst_exchdate() {
        return first_exchdate;
    }

    public void setFirst_exchdate(String first_exchdate) {
        this.first_exchdate = first_exchdate;
    }

    public String getCanBuy() {
        return canBuy;
    }

    public void setCanBuy(String canBuy) {
        this.canBuy = canBuy;
    }

    public String getFundRisk() {
        return fundRisk;
    }

    public void setFundRisk(String fundRisk) {
        this.fundRisk = fundRisk;
    }

    public String getScheduled_protocol_state() {
        return scheduled_protocol_state;
    }

    public void setScheduled_protocol_state(String scheduled_protocol_state) {
        this.scheduled_protocol_state = scheduled_protocol_state;
    }

    public String getNext_fixrequest_date() {
        return next_fixrequest_date;
    }

    public void setNext_fixrequest_date(String next_fixrequest_date) {
        this.next_fixrequest_date = next_fixrequest_date;
    }

    public String getTotal_succ_sum() {
        return total_succ_sum;
    }

    public void setTotal_succ_sum(String total_succ_sum) {
        this.total_succ_sum = total_succ_sum;
    }

    public String getTotal_succ_time() {
        return total_succ_time;
    }

    public void setTotal_succ_time(String total_succ_time) {
        this.total_succ_time = total_succ_time;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.fixDateDetails);
        dest.writeString(this.fundCode);
        dest.writeString(this.fund_full_name);
        dest.writeString(this.fund_name);
        dest.writeParcelable(this.bankCardPageEntity, flags);
        dest.writeSerializable(this.minPurchaseAmt);
        dest.writeString(this.exchdate);
        dest.writeString(this.exchWeek);
        dest.writeValue(this.apply_sum);
        dest.writeString(this.first_trade_month);
        dest.writeString(this.fix_date);
        dest.writeString(this.password);
        dest.writeString(this.protocol_period_unit);
        dest.writeString(this.share_type);
        dest.writeString(this.trade_acco);
        dest.writeString(this.trade_period);
        dest.writeString(this.scheduled_protocol_id);
        dest.writeString(this.first_exchdate);
        dest.writeString(this.canBuy);
        dest.writeString(this.fundRisk);
        dest.writeString(this.scheduled_protocol_state);
        dest.writeString(this.next_fixrequest_date);
        dest.writeString(this.total_succ_sum);
        dest.writeString(this.total_succ_time);
    }

    public InvestResp() {
    }

    protected InvestResp(Parcel in) {
        this.fixDateDetails = in.readString();
        this.fundCode = in.readString();
        this.fund_full_name = in.readString();
        this.fund_name = in.readString();
        this.bankCardPageEntity = in.readParcelable(BankCardResp.class.getClassLoader());
        this.minPurchaseAmt = (BigDecimal) in.readSerializable();
        this.exchdate = in.readString();
        this.exchWeek = in.readString();
        this.apply_sum = (BigDecimal) in.readValue(Integer.class.getClassLoader());
        this.first_trade_month = in.readString();
        this.fix_date = in.readString();
        this.password = in.readString();
        this.protocol_period_unit = in.readString();
        this.share_type = in.readString();
        this.trade_acco = in.readString();
        this.trade_period = in.readString();
        this.scheduled_protocol_id = in.readString();
        this.first_exchdate = in.readString();
        this.canBuy = in.readString();
        this.fundRisk = in.readString();
        this.scheduled_protocol_state = in.readString();
        this.next_fixrequest_date = in.readString();
        this.total_succ_sum = in.readString();
        this.total_succ_time = in.readString();
    }

    public static final Creator<InvestResp> CREATOR = new Creator<InvestResp>() {
        @Override
        public InvestResp createFromParcel(Parcel source) {
            return new InvestResp(source);
        }

        @Override
        public InvestResp[] newArray(int size) {
            return new InvestResp[size];
        }
    };
}
