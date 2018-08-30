package cn.com.buyforyou.fund.model.fund;

import android.os.Parcel;
import android.os.Parcelable;

import cn.com.buyforyou.fund.model.BaseResp;

import java.math.BigDecimal;

/**
 * 作者：sunnyzeng on 2018/1/12 15:59
 * 描述：
 * {
 * "data": {
 * "income_time": "2017/12/15  18:00",
 * "fund_amount": 5000,
 * "bank_info": "中国银行(3833) 支付成功",
 * "fund_name": "博时价值增长基金",
 * "succ_time": "2017/12/13  13:16:43",
 * "confirm_time": "2017/12/14"
 * },
 * "message": "成功",
 * "status": 200
 * }
 */

public class BuyNowResp extends BaseResp<BuyNowResp> implements Parcelable {
    //金额
    private BigDecimal fund_amount;
    //银行信息
    private String bank_info;
    //基金名称
    private String fund_name;
    //成功时间
    private String succ_time;
    //确认时间
    private String confirm_time;
    //收益时间
    private String income_time;

    public BigDecimal getFund_amount() {
        return fund_amount;
    }

    public void setFund_amount(BigDecimal fund_amount) {
        this.fund_amount = fund_amount;
    }

    public String getBank_info() {
        return bank_info;
    }

    public void setBank_info(String bank_info) {
        this.bank_info = bank_info;
    }

    public String getFund_name() {
        return fund_name;
    }

    public void setFund_name(String fund_name) {
        this.fund_name = fund_name;
    }

    public String getSucc_time() {
        return succ_time;
    }

    public void setSucc_time(String succ_time) {
        this.succ_time = succ_time;
    }

    public String getConfirm_time() {
        return confirm_time;
    }

    public void setConfirm_time(String confirm_time) {
        this.confirm_time = confirm_time;
    }

    public String getIncome_time() {
        return income_time;
    }

    public void setIncome_time(String income_time) {
        this.income_time = income_time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(this.fund_amount);
        dest.writeString(this.bank_info);
        dest.writeString(this.fund_name);
        dest.writeString(this.succ_time);
        dest.writeString(this.confirm_time);
        dest.writeString(this.income_time);
    }

    public BuyNowResp() {
    }

    protected BuyNowResp(Parcel in) {
        this.fund_amount = (BigDecimal) in.readSerializable();
        this.bank_info = in.readString();
        this.fund_name = in.readString();
        this.succ_time = in.readString();
        this.confirm_time = in.readString();
        this.income_time = in.readString();
    }

    public static final Creator<BuyNowResp> CREATOR = new Creator<BuyNowResp>() {
        @Override
        public BuyNowResp createFromParcel(Parcel source) {
            return new BuyNowResp(source);
        }

        @Override
        public BuyNowResp[] newArray(int size) {
            return new BuyNowResp[size];
        }
    };
}
