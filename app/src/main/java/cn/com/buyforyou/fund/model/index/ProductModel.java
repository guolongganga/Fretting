package cn.com.buyforyou.fund.model.index;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;

/**
 * 作者：sunnyzeng on 2018/1/2 10:44
 * 描述：首页 基金实体类
 */

public class ProductModel implements Parcelable {

    /**
     * 基金名称
     */
    private String fund_name;
    /**
     * 基金代码
     */
    private String fund_code;
    /**
     * 基金描述
     */
    private String riseTermDesc;
    /**
     * 基金收益涨幅
     */
    private BigDecimal fund_rose;
    /**
     * 基金净值
     */
    private BigDecimal net_value;
    /**
     * 特色1
     */
    private String featureone;
    /**
     * 特色2
     */
    private String featuretwo;

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

    public String getRiseTermDesc() {
        //TODO 未显示
        StringBuffer stringBuffer = new StringBuffer(riseTermDesc);
        stringBuffer.append("收益率");
        return stringBuffer.toString();
//        return riseTermDesc + "收益率";
    }

    public void setRiseTermDesc(String riseTermDesc) {
        this.riseTermDesc = riseTermDesc;
    }

    public BigDecimal getFund_rose() {
        return fund_rose;
    }

    public void setFund_rose(BigDecimal fund_rose) {
        this.fund_rose = fund_rose;
    }

    public BigDecimal getNet_value() {
        return net_value;
    }

    public void setNet_value(BigDecimal net_value) {
        this.net_value = net_value;
    }

    public String getFeatureone() {
        return featureone;
    }

    public void setFeatureone(String featureone) {
        this.featureone = featureone;
    }

    public String getFeaturetwo() {
        return featuretwo;
    }

    public void setFeaturetwo(String featuretwo) {
        this.featuretwo = featuretwo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.fund_name);
        dest.writeString(this.fund_code);
        dest.writeString(this.riseTermDesc);
        dest.writeSerializable(this.fund_rose);
        dest.writeSerializable(this.net_value);
        dest.writeString(this.featureone);
        dest.writeString(this.featuretwo);
    }

    public ProductModel() {
    }

    protected ProductModel(Parcel in) {
        this.fund_name = in.readString();
        this.fund_code = in.readString();
        this.riseTermDesc = in.readString();
        this.fund_rose = (BigDecimal) in.readSerializable();
        this.net_value = (BigDecimal) in.readSerializable();
        this.featureone = in.readString();
        this.featuretwo = in.readString();
    }

    public static final Creator<ProductModel> CREATOR = new Creator<ProductModel>() {
        @Override
        public ProductModel createFromParcel(Parcel source) {
            return new ProductModel(source);
        }

        @Override
        public ProductModel[] newArray(int size) {
            return new ProductModel[size];
        }
    };
}
