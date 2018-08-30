package cn.com.buyforyou.fund.model.user;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;

/**
 * 作者：sunnyzeng on 2017/12/26 17:01
 * 描述：我的持仓基金
 */

public class HoldFundResp implements Parcelable {
    private String businBoardType;//资金方式
    private String balance;//持有金额或者份额
    private String applyDate;
    private String fundName;
    private String payStatus;

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getBusinBoardType() {
        return businBoardType;
    }

    public void setBusinBoardType(String businBoardType) {
        this.businBoardType = businBoardType;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.businBoardType);
        dest.writeString(this.balance);
        dest.writeString(this.applyDate);
        dest.writeString(this.fundName);
        dest.writeString(this.payStatus);
    }

    public HoldFundResp() {
    }

    protected HoldFundResp(Parcel in) {
        this.businBoardType = in.readString();
        this.balance = in.readString();
        this.applyDate = in.readString();
        this.fundName = in.readString();
        this.payStatus = in.readString();
    }

    public static final Creator<HoldFundResp> CREATOR = new Creator<HoldFundResp>() {
        @Override
        public HoldFundResp createFromParcel(Parcel source) {
            return new HoldFundResp(source);
        }

        @Override
        public HoldFundResp[] newArray(int size) {
            return new HoldFundResp[size];
        }
    };
}
