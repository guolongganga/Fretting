package cn.com.buyforyou.fund.model.fund;

import android.os.Parcel;
import android.os.Parcelable;

import cn.com.buyforyou.fund.model.ApplyBaseInfo;
import cn.com.buyforyou.fund.model.BaseResp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者：sunnyzeng on 2018/1/11 18:14
 * 描述：
 * "name":"中国银行(3833)",
 * <p>
 * "info1":"15点前买入将按照当净值确认份额",
 * "fundRisk":"基金风险高于您的风险承受能力",
 * "canbuy":"OK",
 * "info":"单笔上限50万，单日限额200万",
 * "info2":"12月18日可查看份额信息，12月19号可查看收益"},
 * "message":"成功","status":200}
 */

public class BuyFundResp extends BaseResp<BuyFundResp> implements Parcelable {
    /** 银行名称及尾号 */
    private String name;
    /** 银行logo */
    private String logo;
    /** 风险等级 */
    private String fundRisk;
    /** 是否能够购买 */
    private String canBuy;
    /** 银行限额 */
    private String info;
    /** 提示1 */
    private String info1;
    /** 提示2 */
    private String info2;
    /** 分红方式 */
    private List<ApplyBaseInfo> default_auto_buy;
    /** 费率 */
    private String curr_rate;
    /** 最高购买限额 */
    private BigDecimal high_value;
    /** 最低购买限额 */
    private BigDecimal low_value;
    /** 申购费 */
    private String source_rate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getFundRisk() {
        return fundRisk;
    }

    public void setFundRisk(String fundRisk) {
        this.fundRisk = fundRisk;
    }

    public String getCanBuy() {
        return canBuy;
    }

    public void setCanBuy(String canBuy) {
        this.canBuy = canBuy;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfo1() {
        return info1;
    }

    public void setInfo1(String info1) {
        this.info1 = info1;
    }

    public String getInfo2() {
        return info2;
    }

    public void setInfo2(String info2) {
        this.info2 = info2;
    }

    public List<ApplyBaseInfo> getDefault_auto_buy() {
        return default_auto_buy;
    }

    public void setDefault_auto_buy(List<ApplyBaseInfo> default_auto_buy) {
        this.default_auto_buy = default_auto_buy;
    }

    public String getCurr_rate() {
        return curr_rate;
    }

    public void setCurr_rate(String curr_rate) {
        this.curr_rate = curr_rate;
    }

    public BigDecimal getHigh_value() {
        return high_value;
    }

    public void setHigh_value(BigDecimal high_value) {
        this.high_value = high_value;
    }

    public BigDecimal getLow_value() {
        return low_value;
    }

    public void setLow_value(BigDecimal low_value) {
        this.low_value = low_value;
    }

    public String getSource_rate() {
        return source_rate;
    }

    public void setSource_rate(String source_rate) {
        this.source_rate = source_rate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.logo);
        dest.writeString(this.fundRisk);
        dest.writeString(this.canBuy);
        dest.writeString(this.info);
        dest.writeString(this.info1);
        dest.writeString(this.info2);
        dest.writeList(this.default_auto_buy);
        dest.writeString(this.curr_rate);
        dest.writeSerializable(this.high_value);
        dest.writeSerializable(this.low_value);
        dest.writeString(this.source_rate);
    }

    public BuyFundResp() {
    }

    protected BuyFundResp(Parcel in) {
        this.name = in.readString();
        this.logo = in.readString();
        this.fundRisk = in.readString();
        this.canBuy = in.readString();
        this.info = in.readString();
        this.info1 = in.readString();
        this.info2 = in.readString();
        this.default_auto_buy = new ArrayList<ApplyBaseInfo>();
        in.readList(this.default_auto_buy, ApplyBaseInfo.class.getClassLoader());
        this.curr_rate = in.readString();
        this.high_value = (BigDecimal) in.readSerializable();
        this.low_value = (BigDecimal) in.readSerializable();
        this.source_rate = in.readString();
    }

    public static final Creator<BuyFundResp> CREATOR = new Creator<BuyFundResp>() {
        @Override
        public BuyFundResp createFromParcel(Parcel source) {
            return new BuyFundResp(source);
        }

        @Override
        public BuyFundResp[] newArray(int size) {
            return new BuyFundResp[size];
        }
    };
}
