package cn.com.buyforyou.fund.model.user;

import cn.com.buyforyou.fund.model.BaseResp;

import java.util.List;

public class BonusResp extends BaseResp<List<BonusResp>> {

    private String autoBuy;//业务大类
    private String balance;//持有金额或者份额
    private String applyDate;
    private String fundCode;
    private String fundName;

    public String getAutoBuy() {
        return autoBuy;
    }

    public void setAutoBuy(String autoBuy) {
        this.autoBuy = autoBuy;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
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
}
