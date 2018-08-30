package cn.com.buyforyou.fund.model.fund;

import android.os.Parcel;
import android.os.Parcelable;

import cn.com.buyforyou.fund.model.BaseResp;

/**
 * 作者：sunnyzeng on 2018/1/16 18:45
 * 描述：
 */

public class InvestSureResp extends BaseResp<InvestSureResp> implements Parcelable {
    /**
     * 申请编号 S 24 0 N v4.0.3.0
     */
    private String allot_no;
    /**
     * 下次交易日期 N 8 0 N v4.0.3.0
     */
    private String next_fixrequest_date;
    /**
     * 定投协议号 S 20 0 N v4.0.3.0
     */
    private String scheduled_protocol_id;

    public String getAllot_no() {
        return allot_no;
    }

    public void setAllot_no(String allot_no) {
        this.allot_no = allot_no;
    }

    public String getNext_fixrequest_date() {
        return next_fixrequest_date;
    }

    public void setNext_fixrequest_date(String next_fixrequest_date) {
        this.next_fixrequest_date = next_fixrequest_date;
    }

    public String getScheduled_protocol_id() {
        return scheduled_protocol_id;
    }

    public void setScheduled_protocol_id(String scheduled_protocol_id) {
        this.scheduled_protocol_id = scheduled_protocol_id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.allot_no);
        dest.writeString(this.next_fixrequest_date);
        dest.writeString(this.scheduled_protocol_id);
    }

    public InvestSureResp() {
    }

    protected InvestSureResp(Parcel in) {
        this.allot_no = in.readString();
        this.next_fixrequest_date = in.readString();
        this.scheduled_protocol_id = in.readString();
    }

    public static final Parcelable.Creator<InvestSureResp> CREATOR = new Parcelable.Creator<InvestSureResp>() {
        @Override
        public InvestSureResp createFromParcel(Parcel source) {
            return new InvestSureResp(source);
        }

        @Override
        public InvestSureResp[] newArray(int size) {
            return new InvestSureResp[size];
        }
    };
}
