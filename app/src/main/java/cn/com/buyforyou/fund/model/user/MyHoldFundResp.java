package cn.com.buyforyou.fund.model.user;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;

/**
 * 作者：sunnyzeng on 2017/12/26 17:01
 * 描述：我的持仓基金
 */

public class MyHoldFundResp implements Parcelable {
    /**
     * 基金名称
     */
    private String fundName;
    /**
     * 持仓金额
     */
    private BigDecimal holdAmount;
    /**
     * 昨日收益
     */
    private BigDecimal earningsLastDay;
    /**
     * 累计收益
     */
    private BigDecimal totalEarn;
    /**
     * 确认中份额数量：0没有
     */
    private String sureNumber;

    /**
     *
     * @return
     */
    private String fundCode;

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public BigDecimal getHoldAmount() {
        return holdAmount;
    }

    public void setHoldAmount(BigDecimal holdAmount) {
        this.holdAmount = holdAmount;
    }

    public BigDecimal getEarningsLastDay() {
        return earningsLastDay;
    }

    public void setEarningsLastDay(BigDecimal earningsLastDay) {
        this.earningsLastDay = earningsLastDay;
    }

    public BigDecimal getTotalEarn() {
        return totalEarn;
    }

    public void setTotalEarn(BigDecimal totalEarn) {
        this.totalEarn = totalEarn;
    }

    public String getSureNumber() {
        return sureNumber;
    }

    public void setSureNumber(String sureNumber) {
        this.sureNumber = sureNumber;
    }

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.fundName);
        dest.writeSerializable(this.holdAmount);
        dest.writeSerializable(this.earningsLastDay);
        dest.writeSerializable(this.totalEarn);
        dest.writeString(this.sureNumber);
        dest.writeString(this.fundCode);
    }

    public MyHoldFundResp() {
    }

    protected MyHoldFundResp(Parcel in) {
        this.fundName = in.readString();
        this.holdAmount = (BigDecimal) in.readSerializable();
        this.earningsLastDay = (BigDecimal) in.readSerializable();
        this.totalEarn = (BigDecimal) in.readSerializable();
        this.sureNumber = in.readString();
        this.fundCode = in.readString();
    }

    public static final Parcelable.Creator<MyHoldFundResp> CREATOR = new Parcelable.Creator<MyHoldFundResp>() {
        @Override
        public MyHoldFundResp createFromParcel(Parcel source) {
            return new MyHoldFundResp(source);
        }

        @Override
        public MyHoldFundResp[] newArray(int size) {
            return new MyHoldFundResp[size];
        }
    };
}
