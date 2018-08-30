package cn.com.buyforyou.fund.model.fund;

import android.os.Parcel;
import android.os.Parcelable;

import cn.com.buyforyou.fund.model.ApplyBaseInfo;
import cn.com.buyforyou.fund.model.BaseResp;
import cn.com.buyforyou.fund.model.user.BankCardResp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者：sunnyzeng on 2018/1/16 10:03
 * 描述：定投数据
 */

public class SellResp extends BaseResp<SellResp> implements Parcelable {
    /**
     * 银行卡信息
     */
    private BankCardResp bankCardPageEntity;

    /**
     *
     */
    private String capital_mode;
    /**
     * 可赎回份额
     */
    private BigDecimal enable_shares;

    /**
     * 放弃超额部分
     */
    private List<ApplyBaseInfo> fundExceedFlagList;

    /**
     * 基金代码
     */
    private String fund_code;

    /**
     *
     */
    private String fund_exceed_flag;
    /**
     * 基金名称
     */
    private String fund_name;


    /**
     * 最低购买金额
     */
    private BigDecimal minVar;

    /**
     * 交易密码
     */
    private String password;

    /**
     *
     */
    private BigDecimal remainVar;

    /**
     * 份额分类 C 1 0 Y v4.0.3.0  A:前收费,B:后收费
     */
    private String share_type;

    /**
     * 交易账号 S 17 0 Y v4.0.3.0
     */
    private String trade_acco;

    public BankCardResp getBankCardPageEntity() {
        return bankCardPageEntity;
    }

    public void setBankCardPageEntity(BankCardResp bankCardPageEntity) {
        this.bankCardPageEntity = bankCardPageEntity;
    }

    public String getCapital_mode() {
        return capital_mode;
    }

    public void setCapital_mode(String capital_mode) {
        this.capital_mode = capital_mode;
    }

    public BigDecimal getEnable_shares() {
        return enable_shares;
    }

    public void setEnable_shares(BigDecimal enable_shares) {
        this.enable_shares = enable_shares;
    }

    public List<ApplyBaseInfo> getFundExceedFlagList() {
        return fundExceedFlagList;
    }

    public void setFundExceedFlagList(List<ApplyBaseInfo> fundExceedFlagList) {
        this.fundExceedFlagList = fundExceedFlagList;
    }

    public String getFund_code() {
        return fund_code;
    }

    public void setFund_code(String fund_code) {
        this.fund_code = fund_code;
    }

    public String getFund_exceed_flag() {
        return fund_exceed_flag;
    }

    public void setFund_exceed_flag(String fund_exceed_flag) {
        this.fund_exceed_flag = fund_exceed_flag;
    }

    public String getFund_name() {
        return fund_name;
    }

    public void setFund_name(String fund_name) {
        this.fund_name = fund_name;
    }

    public BigDecimal getMinVar() {
        return minVar;
    }

    public void setMinVar(BigDecimal minVar) {
        this.minVar = minVar;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BigDecimal getRemainVar() {
        return remainVar;
    }

    public void setRemainVar(BigDecimal remainVar) {
        this.remainVar = remainVar;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.bankCardPageEntity, flags);
        dest.writeString(this.capital_mode);
        dest.writeSerializable(this.enable_shares);
        dest.writeList(this.fundExceedFlagList);
        dest.writeString(this.fund_code);
        dest.writeString(this.fund_exceed_flag);
        dest.writeString(this.fund_name);
        dest.writeSerializable(this.minVar);
        dest.writeString(this.password);
        dest.writeSerializable(this.remainVar);
        dest.writeString(this.share_type);
        dest.writeString(this.trade_acco);
    }

    public SellResp() {
    }

    protected SellResp(Parcel in) {
        this.bankCardPageEntity = in.readParcelable(BankCardResp.class.getClassLoader());
        this.capital_mode = in.readString();
        this.enable_shares = (BigDecimal) in.readSerializable();
        this.fundExceedFlagList = new ArrayList<ApplyBaseInfo>();
        in.readList(this.fundExceedFlagList, ApplyBaseInfo.class.getClassLoader());
        this.fund_code = in.readString();
        this.fund_exceed_flag = in.readString();
        this.fund_name = in.readString();
        this.minVar = (BigDecimal) in.readSerializable();
        this.password = in.readString();
        this.remainVar = (BigDecimal) in.readSerializable();
        this.share_type = in.readString();
        this.trade_acco = in.readString();
    }

    public static final Creator<SellResp> CREATOR = new Creator<SellResp>() {
        @Override
        public SellResp createFromParcel(Parcel source) {
            return new SellResp(source);
        }

        @Override
        public SellResp[] newArray(int size) {
            return new SellResp[size];
        }
    };
}
