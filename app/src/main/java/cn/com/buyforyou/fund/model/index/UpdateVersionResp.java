package cn.com.buyforyou.fund.model.index;

import android.os.Parcel;
import android.os.Parcelable;

import cn.com.buyforyou.fund.model.BaseResp;
import cn.droidlover.xdroidmvp.net.IModel;

/**
 * Created by limxing on 2018/5/9.
 */

public class UpdateVersionResp extends BaseResp<UpdateVersionResp> implements Parcelable {
    private String updradeCode;
    private String versionDesc;
    private String downloadAdd;
    private String upgradeProperties;
    private String versionNumber;
    private String versionType;

    protected UpdateVersionResp(Parcel in) {
        updradeCode = in.readString();
        versionDesc = in.readString();
        downloadAdd = in.readString();
        upgradeProperties = in.readString();
        versionNumber = in.readString();
        versionType = in.readString();
    }

    public static final Creator<UpdateVersionResp> CREATOR = new Creator<UpdateVersionResp>() {
        @Override
        public UpdateVersionResp createFromParcel(Parcel in) {
            return new UpdateVersionResp(in);
        }

        @Override
        public UpdateVersionResp[] newArray(int size) {
            return new UpdateVersionResp[size];
        }
    };

    public String getDownloadAdd() {
        return downloadAdd;
    }

    public void setDownloadAdd(String downloadAdd) {
        this.downloadAdd = downloadAdd;
    }

    public String getUpgradeProperties() {
        return upgradeProperties;
    }

    public void setUpgradeProperties(String upgradeProperties) {
        this.upgradeProperties = upgradeProperties;
    }

    public String getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
    }

    public String getVersionType() {
        return versionType;
    }

    public void setVersionType(String versionType) {
        this.versionType = versionType;
    }

    public String getUpdradeCode() {
        return updradeCode;
    }

    public void setUpdradeCode(String updradeCode) {
        this.updradeCode = updradeCode;
    }

    public String getVersionDesc() {
        return versionDesc;
    }

    public void setVersionDesc(String versionDesc) {
        this.versionDesc = versionDesc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.updradeCode);
        dest.writeString(this.versionDesc);
        dest.writeString(this.downloadAdd);
        dest.writeString(this.upgradeProperties);
        dest.writeString(this.versionNumber);
        dest.writeString(this.versionType);
    }
}
