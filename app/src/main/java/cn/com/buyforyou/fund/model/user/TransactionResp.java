package cn.com.buyforyou.fund.model.user;

import android.os.Parcel;
import android.os.Parcelable;

import cn.com.buyforyou.fund.model.BaseResp;

import java.util.List;

/**
 * 作者：sunnyzeng on 2018/1/24 16:52
 * 描述：
 */

public class TransactionResp extends BaseResp<List<TransactionResp>> implements Parcelable {
    private String type;
    private String allot_no;//交易流水号
    private String fundName;
    private String fundCode;
    private String time;
    private String amount;
    private String tans_status;
    private String incomeType;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAllot_no() {
        return allot_no;
    }

    public void setAllot_no(String allot_no) {
        this.allot_no = allot_no;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTans_status() {
        return tans_status;
    }

    public void setTans_status(String tans_status) {
        this.tans_status = tans_status;
    }

    public String getIncomeType() {
        return incomeType;
    }

    public void setIncomeType(String incomeType) {
        this.incomeType = incomeType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeString(this.allot_no);
        dest.writeString(this.fundName);
        dest.writeString(this.fundCode);
        dest.writeString(this.time);
        dest.writeString(this.amount);
        dest.writeString(this.tans_status);
        dest.writeString(this.incomeType);
    }

    public TransactionResp() {
    }

    protected TransactionResp(Parcel in) {
        this.type = in.readString();
        this.allot_no = in.readString();
        this.fundName = in.readString();
        this.fundCode = in.readString();
        this.time = in.readString();
        this.amount = in.readString();
        this.tans_status = in.readString();
        this.incomeType = in.readString();
    }

    public static final Creator<TransactionResp> CREATOR = new Creator<TransactionResp>() {
        @Override
        public TransactionResp createFromParcel(Parcel source) {
            return new TransactionResp(source);
        }

        @Override
        public TransactionResp[] newArray(int size) {
            return new TransactionResp[size];
        }
    };
}
