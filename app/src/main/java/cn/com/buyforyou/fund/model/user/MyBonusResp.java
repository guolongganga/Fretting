package cn.com.buyforyou.fund.model.user;

import cn.com.buyforyou.fund.model.BaseResp;

import java.util.List;

/**
 * 作者：sunnyzeng on 2018/3/21 14:42
 * 描述：
 */

public class MyBonusResp extends BaseResp<List<MyBonusResp>> {
    private String autoBuy;//类型：1
    private String autoBuyVal;//类型：现金分红
    private String fundName;
    private String fundcode;
    private String dividenddate;//日期
    private String bonusTotal;//份额或者是金额
    private String tradeacco;
    private String sharetype;

    public String getAutoBuy() {
        return autoBuy;
    }

    public void setAutoBuy(String autoBuy) {
        this.autoBuy = autoBuy;
    }

    public String getAutoBuyVal() {
        return autoBuyVal;
    }

    public void setAutoBuyVal(String autoBuyVal) {
        this.autoBuyVal = autoBuyVal;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public String getFundcode() {
        return fundcode;
    }

    public void setFundcode(String fundcode) {
        this.fundcode = fundcode;
    }

    public String getDividenddate() {
        return dividenddate;
    }

    public void setDividenddate(String dividenddate) {
        this.dividenddate = dividenddate;
    }

    public String getBonusTotal() {
        return bonusTotal;
    }

    public void setBonusTotal(String bonusTotal) {
        this.bonusTotal = bonusTotal;
    }

    public String getTradeacco() {
        return tradeacco;
    }

    public void setTradeacco(String tradeacco) {
        this.tradeacco = tradeacco;
    }

    public String getSharetype() {
        return sharetype;
    }

    public void setSharetype(String sharetype) {
        this.sharetype = sharetype;
    }
}
