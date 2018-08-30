package cn.com.buyforyou.fund.model.user;

import android.os.Parcel;
import android.os.Parcelable;

import cn.com.buyforyou.fund.model.BaseResp;

import java.math.BigDecimal;

/**
 * 作者：sunnyzeng on 2017/12/22 15:53
 * 描述：我的银行卡
 */

public class BankCardResp extends BaseResp<BankCardResp> implements Parcelable {
    /** 银行卡logo */
    private String bankLogo;
    /** 银行名称 */
    private String bankName;
    /** 银行卡尾号 */
    private String bankNoTail;
    /** 交易账号 */
    private String ta_acco;
    /** 是否能够更改银行卡  0代表不能 1代表能 */
//    private String isCanChangeBankNo;
    private String limit_per_day;
    private String limit_per_month;
    private String limit_per_payment;

    public String getBankLogo() {
        return bankLogo;
    }

    public void setBankLogo(String bankLogo) {
        this.bankLogo = bankLogo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankNoTail() {
        return bankNoTail;
    }

    public void setBankNoTail(String bankNoTail) {
        this.bankNoTail = bankNoTail;
    }

    public String getTa_acco() {
        return ta_acco;
    }

    public void setTa_acco(String ta_acco) {
        this.ta_acco = ta_acco;
    }

//    public String getIsCanChangeBankNo() {
//        return isCanChangeBankNo;
//    }
//
//    public void setIsCanChangeBankNo(String isCanChangeBankNo) {
//        this.isCanChangeBankNo = isCanChangeBankNo;
//    }

    public String getLimit_per_day() {
        return limit_per_day;
    }

    public void setLimit_per_day(String limit_per_day) {
        this.limit_per_day = limit_per_day;
    }

    public String getLimit_per_month() {
        return limit_per_month;
    }

    public void setLimit_per_month(String limit_per_month) {
        this.limit_per_month = limit_per_month;
    }

    public String getLimit_per_payment() {
        return limit_per_payment;
    }

    public void setLimit_per_payment(String limit_per_payment) {
        this.limit_per_payment = limit_per_payment;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.bankLogo);
        dest.writeString(this.bankName);
        dest.writeString(this.bankNoTail);
        dest.writeString(this.ta_acco);
        dest.writeString(this.limit_per_day);
        dest.writeString(this.limit_per_month);
        dest.writeString(this.limit_per_payment);
    }

    public BankCardResp() {
    }

    protected BankCardResp(Parcel in) {
        this.bankLogo = in.readString();
        this.bankName = in.readString();
        this.bankNoTail = in.readString();
        this.ta_acco = in.readString();
        this.limit_per_day = in.readString();
        this.limit_per_month = in.readString();
        this.limit_per_payment = in.readString();
    }

    public static final Creator<BankCardResp> CREATOR = new Creator<BankCardResp>() {
        @Override
        public BankCardResp createFromParcel(Parcel source) {
            return new BankCardResp(source);
        }

        @Override
        public BankCardResp[] newArray(int size) {
            return new BankCardResp[size];
        }
    };
}
