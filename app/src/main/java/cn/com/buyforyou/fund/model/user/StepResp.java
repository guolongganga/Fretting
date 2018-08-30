package cn.com.buyforyou.fund.model.user;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者：sunnyzeng on 2018/1/31 16:25
 * 描述：结果页状态
 * "iscpl": "0",
 * "name": "基金公司确认份额",
 * "time": null
 */

public class StepResp implements Parcelable {
    /**
     * 0表示不选中 1表示选中
     */
    private String iscpl;
    /**
     * 状态
     */
    private String name;
    /**
     * 时间
     */
    private String time;

    public String getIscpl() {
        return iscpl;
    }

    public void setIscpl(String iscpl) {
        this.iscpl = iscpl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.iscpl);
        dest.writeString(this.name);
        dest.writeString(this.time);
    }

    public StepResp() {
    }

    protected StepResp(Parcel in) {
        this.iscpl = in.readString();
        this.name = in.readString();
        this.time = in.readString();
    }

    public static final Parcelable.Creator<StepResp> CREATOR = new Parcelable.Creator<StepResp>() {
        @Override
        public StepResp createFromParcel(Parcel source) {
            return new StepResp(source);
        }

        @Override
        public StepResp[] newArray(int size) {
            return new StepResp[size];
        }
    };
}
