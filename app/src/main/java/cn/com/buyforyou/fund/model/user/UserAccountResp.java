package cn.com.buyforyou.fund.model.user;

import cn.com.buyforyou.fund.model.BaseResp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者：sunnyzeng on 2017/12/26 17:00
 * 描述：我的用户账户信息
 */

public class UserAccountResp extends BaseResp<UserAccountResp> {
    /**
     * 总资产
     */
    private BigDecimal totalAssets;
    /**
     * 在途资产
     */
    private BigDecimal ontheRoadAssets;
    /**
     *昨日收益
     */
    private BigDecimal yesterdayIncome;
    /**
     * 累计收益
     */
    private  BigDecimal totalIncome;

    /**
     * 我的持仓基金
     */
    private ArrayList<MyHoldFundResp> fundList;
    /**
     * 待确认基金 待确认
     */
    private ArrayList<HoldFundResp> holdList;
    /**
     * 昨天
     */
    private String yesterday;

    public BigDecimal getTotalAssets() {
        return totalAssets;
    }

    public void setTotalAssets(BigDecimal totalAssets) {
        this.totalAssets = totalAssets;
    }

    public BigDecimal getOntheRoadAssets() {
        return ontheRoadAssets;
    }

    public void setOntheRoadAssets(BigDecimal ontheRoadAssets) {
        this.ontheRoadAssets = ontheRoadAssets;
    }

    public BigDecimal getYesterdayIncome() {
        return yesterdayIncome;
    }

    public void setYesterdayIncome(BigDecimal yesterdayIncome) {
        this.yesterdayIncome = yesterdayIncome;
    }

    public BigDecimal getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(BigDecimal totalIncome) {
        this.totalIncome = totalIncome;
    }

    public ArrayList<MyHoldFundResp> getFundList() {
        return fundList;
    }

    public void setFundList(ArrayList<MyHoldFundResp> fundList) {
        this.fundList = fundList;
    }

    public ArrayList<HoldFundResp> getHoldList() {
        return holdList;
    }

    public void setHoldList(ArrayList<HoldFundResp> holdList) {
        this.holdList = holdList;
    }

    public String getYesterday() {
        return yesterday;
    }

    public void setYesterday(String yesterday) {
        this.yesterday = yesterday;
    }
}
