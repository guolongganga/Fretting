package cn.com.buyforyou.fund.model.fund;

import android.os.Parcel;
import android.os.Parcelable;

import cn.com.buyforyou.fund.model.BaseResp;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * 作者：sunnyzeng on 2017/12/29 10:54
 * 描述：基金页面数据
 * <p>
 * "fund_code": "050004",
 * "fund_name": "博时精选股票",
 * "fund_rose": 5.27,
 * "net_value": 1.9406
 */

public class NewestFundResp extends BaseResp<ArrayList<NewestFundResp>> implements Parcelable {
    /**
     * 基金代码
     */
    private String fund_code;
    /**
     * 基金名称
     */
    private String fund_name;
    /**
     * 基金净值
     */
    private String net_value;
    /**
     * 基金涨跌幅（用来展示具体涨幅：日涨幅、周涨幅、月涨幅等）
     */
    private BigDecimal fund_rose;

    public String getFund_code() {
        return fund_code;
    }

    public void setFund_code(String fund_code) {
        this.fund_code = fund_code;
    }

    public String getFund_name() {
        return fund_name;
    }

    public void setFund_name(String fund_name) {
        this.fund_name = fund_name;
    }

    public String getNet_value() {
        return net_value;
    }

    public void setNet_value(String net_value) {
        this.net_value = net_value;
    }

    public BigDecimal getFund_rose() {
        return fund_rose;
    }

    public void setFund_rose(BigDecimal fund_rose) {
        this.fund_rose = fund_rose;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.fund_code);
        dest.writeString(this.fund_name);
        dest.writeSerializable(this.net_value);
        dest.writeSerializable(this.fund_rose);
    }

    public NewestFundResp() {
    }

    protected NewestFundResp(Parcel in) {
        this.fund_code = in.readString();
        this.fund_name = in.readString();
        this.net_value = (String) in.readSerializable();
        this.fund_rose = (BigDecimal) in.readSerializable();
    }

    public static final Creator<NewestFundResp> CREATOR = new Creator<NewestFundResp>() {
        @Override
        public NewestFundResp createFromParcel(Parcel source) {
            return new NewestFundResp(source);
        }

        @Override
        public NewestFundResp[] newArray(int size) {
            return new NewestFundResp[size];
        }
    };
}
