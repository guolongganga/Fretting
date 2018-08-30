package cn.com.buyforyou.fund.model.user;

import android.os.Parcel;
import android.os.Parcelable;

import cn.com.buyforyou.fund.model.BaseResp;

import java.util.ArrayList;

/**
 * 作者：sunnyzeng on 2017/12/19 10:21
 * 描述：银行列表
 * <p>
 * bank_name": "工商银行",
 * "bank_no": "002",
 * "capital_mode": "6",
 * "detail_fund_way": "00",
 * "is_support_fast_mode": "1",
 * "limit_per_day": 200,
 * "limit_per_month": 500,
 * "limit_per_payment": 50
 */

public class BankResp extends BaseResp<ArrayList<BankResp>> implements Parcelable {
    private String bankLogo;
    private String bank_name;
    private String bank_no;
    private String capital_mode;
    private String detail_fund_way;
    private String is_support_fast_mode;
    private String limit_per_day;
    private String limit_per_month;
    private String limit_per_payment;

    public String getBankLogo() {
        return bankLogo;
    }

    public void setBankLogo(String bankLogo) {
        this.bankLogo = bankLogo;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getBank_no() {
        return bank_no;
    }

    public void setBank_no(String bank_no) {
        this.bank_no = bank_no;
    }

    public String getCapital_mode() {
        return capital_mode;
    }

    public void setCapital_mode(String capital_mode) {
        this.capital_mode = capital_mode;
    }

    public String getDetail_fund_way() {
        return detail_fund_way;
    }

    public void setDetail_fund_way(String detail_fund_way) {
        this.detail_fund_way = detail_fund_way;
    }

    public String getIs_support_fast_mode() {
        return is_support_fast_mode;
    }

    public void setIs_support_fast_mode(String is_support_fast_mode) {
        this.is_support_fast_mode = is_support_fast_mode;
    }

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
        dest.writeString(this.bank_name);
        dest.writeString(this.bank_no);
        dest.writeString(this.capital_mode);
        dest.writeString(this.detail_fund_way);
        dest.writeString(this.is_support_fast_mode);
        dest.writeString(this.limit_per_day);
        dest.writeString(this.limit_per_month);
        dest.writeString(this.limit_per_payment);
    }

    public BankResp() {
    }

    protected BankResp(Parcel in) {
        this.bankLogo = in.readString();
        this.bank_name = in.readString();
        this.bank_no = in.readString();
        this.capital_mode = in.readString();
        this.detail_fund_way = in.readString();
        this.is_support_fast_mode = in.readString();
        this.limit_per_day = in.readString();
        this.limit_per_month = in.readString();
        this.limit_per_payment = in.readString();
    }

    public static final Creator<BankResp> CREATOR = new Creator<BankResp>() {
        @Override
        public BankResp createFromParcel(Parcel source) {
            return new BankResp(source);
        }

        @Override
        public BankResp[] newArray(int size) {
            return new BankResp[size];
        }
    };
}
