package cn.com.buyforyou.fund.model.fund;

import android.os.Parcel;
import android.os.Parcelable;

import cn.com.buyforyou.fund.model.BaseResp;
import cn.com.buyforyou.fund.model.user.BankCardResp;
import cn.com.buyforyou.fund.model.user.StepResp;

import java.util.ArrayList;

/**
 * 作者：sunnyzeng on 2018/3/22 16:32
 * 描述：
 */

public class FundStatusResp extends BaseResp<FundStatusResp> implements Parcelable {
    private BankCardResp bankCardPageEntity;
    private String fundCode;
    private String fundName;
    private String shares;
    private ArrayList<StepResp> stepList;

    public BankCardResp getBankCardPageEntity() {
        return bankCardPageEntity;
    }

    public void setBankCardPageEntity(BankCardResp bankCardPageEntity) {
        this.bankCardPageEntity = bankCardPageEntity;
    }

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public String getShares() {
        return shares;
    }

    public void setShares(String shares) {
        this.shares = shares;
    }

    public ArrayList<StepResp> getStepList() {
        return stepList;
    }

    public void setStepList(ArrayList<StepResp> stepList) {
        this.stepList = stepList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.bankCardPageEntity, flags);
        dest.writeString(this.fundCode);
        dest.writeString(this.fundName);
        dest.writeString(this.shares);
        dest.writeList(this.stepList);
    }

    public FundStatusResp() {
    }

    protected FundStatusResp(Parcel in) {
        this.bankCardPageEntity = in.readParcelable(BankCardResp.class.getClassLoader());
        this.fundCode = in.readString();
        this.fundName = in.readString();
        this.shares = in.readString();
        this.stepList = new ArrayList<StepResp>();
        in.readList(this.stepList, StepResp.class.getClassLoader());
    }

    public static final Parcelable.Creator<FundStatusResp> CREATOR = new Parcelable.Creator<FundStatusResp>() {
        @Override
        public FundStatusResp createFromParcel(Parcel source) {
            return new FundStatusResp(source);
        }

        @Override
        public FundStatusResp[] newArray(int size) {
            return new FundStatusResp[size];
        }
    };
}
