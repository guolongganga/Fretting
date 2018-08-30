package cn.com.buyforyou.fund.model.user;

/**
 * 作者：sunnyzeng on 2017/12/18 14:12
 * 描述：
 */

public class MyFundResp {
    /** 基金名称 */
    private String name;
    /** 金额 */
    private Double money;
    /** 昨日收益 */
    private Double yesterday;
    /** 持有收益 */
    private Double hold;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Double getYesterday() {
        return yesterday;
    }

    public void setYesterday(Double yesterday) {
        this.yesterday = yesterday;
    }

    public Double getHold() {
        return hold;
    }

    public void setHold(Double hold) {
        this.hold = hold;
    }

    public MyFundResp(String name, Double money, Double yesterday, Double hold) {
        this.name = name;
        this.money = money;
        this.yesterday = yesterday;
        this.hold = hold;
    }
}
