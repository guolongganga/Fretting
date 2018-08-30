package cn.com.buyforyou.fund.model.user;

import android.os.Parcel;
import android.os.Parcelable;

import cn.com.buyforyou.fund.model.BaseResp;

import java.util.List;

/**
 * 作者：sunnyzeng on 2018/1/25 16:52
 * 描述：
 */

public class InvestInfoResp implements Parcelable {

    private String scheduled_protocol_id;//协议id
    private String scheduled_protocol_state;
    private String fund_name;
    private String fund_code;
    private String dt_way;
    private String bank_name;
    private String bank_account;
    private String next_fixrequest_date;

    public String getScheduled_protocol_id() {
        return scheduled_protocol_id;
    }

    public void setScheduled_protocol_id(String scheduled_protocol_id) {
        this.scheduled_protocol_id = scheduled_protocol_id;
    }

    public String getScheduled_protocol_state() {
        return scheduled_protocol_state;
    }

    public void setScheduled_protocol_state(String scheduled_protocol_state) {
        this.scheduled_protocol_state = scheduled_protocol_state;
    }

    public String getFund_name() {
        return fund_name;
    }

    public void setFund_name(String fund_name) {
        this.fund_name = fund_name;
    }

    public String getFund_code() {
        return fund_code;
    }

    public void setFund_code(String fund_code) {
        this.fund_code = fund_code;
    }

    public String getDt_way() {
        return dt_way;
    }

    public void setDt_way(String dt_way) {
        this.dt_way = dt_way;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getBank_account() {
        return bank_account;
    }

    public void setBank_account(String bank_account) {
        this.bank_account = bank_account;
    }

    public String getNext_fixrequest_date() {
        return next_fixrequest_date;
    }

    public void setNext_fixrequest_date(String next_fixrequest_date) {
        this.next_fixrequest_date = next_fixrequest_date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.scheduled_protocol_id);
        dest.writeString(this.scheduled_protocol_state);
        dest.writeString(this.fund_name);
        dest.writeString(this.fund_code);
        dest.writeString(this.dt_way);
        dest.writeString(this.bank_name);
        dest.writeString(this.bank_account);
        dest.writeString(this.next_fixrequest_date);
    }

    public InvestInfoResp() {
    }

    protected InvestInfoResp(Parcel in) {
        this.scheduled_protocol_id = in.readString();
        this.scheduled_protocol_state = in.readString();
        this.fund_name = in.readString();
        this.fund_code = in.readString();
        this.dt_way = in.readString();
        this.bank_name = in.readString();
        this.bank_account = in.readString();
        this.next_fixrequest_date = in.readString();
    }

    public static final Parcelable.Creator<InvestInfoResp> CREATOR = new Parcelable.Creator<InvestInfoResp>() {
        @Override
        public InvestInfoResp createFromParcel(Parcel source) {
            return new InvestInfoResp(source);
        }

        @Override
        public InvestInfoResp[] newArray(int size) {
            return new InvestInfoResp[size];
        }
    };
}
