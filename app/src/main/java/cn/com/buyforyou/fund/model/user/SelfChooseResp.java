package cn.com.buyforyou.fund.model.user;

import cn.com.buyforyou.fund.model.BaseResp;

import java.math.BigDecimal;
import java.util.List;

/**
 * 作者：sunnyzeng on 2018/1/25 14:18
 * 描述：自选基金
 *  {
 "day_rose":3.81,
 "fund_code":"001223",
 "fund_name":"鹏华文化传媒娱乐股票",
 "hasBuy":null,
 "id":10,
 "is_follow":"1",
 "net_value":1.144,
 "sname":null,
 "sord":null,
 "sortJz":null,
 "sortZdf":null,
 "user_id":"21a7f4c85b9f4076ac72c3b9917b1d85"
 }
 */

public class SelfChooseResp extends BaseResp<List<SelfChooseResp>> {
    /**
     * 涨跌幅
     */
    private BigDecimal day_rose;
    /**
     * 基金代码
     */
    private String fund_code;
    /**
     * 基金名称
     */
    private String fund_name;
    /**
     * 是否持有
     */
    private String hasBuy;
    /**
     *
     */
    private String id;
    /**
     *
     */
    private String is_follow;
    /**
     * 净值
     */
    private String net_value;
    /**
     *
     */
    private String sname;
    /**
     *
     */
    private String sord;
    /**
     *
     */
    private String sortJz;
    /**
     *
     */
    private String sortZdf;
    /**
     *
     */
    private String user_id;

    public BigDecimal getDay_rose() {
        return day_rose;
    }

    public void setDay_rose(BigDecimal day_rose) {
        this.day_rose = day_rose;
    }

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

    public String getHasBuy() {
        return hasBuy;
    }

    public void setHasBuy(String hasBuy) {
        this.hasBuy = hasBuy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIs_follow() {
        return is_follow;
    }

    public void setIs_follow(String is_follow) {
        this.is_follow = is_follow;
    }

    public String getNet_value() {
        return net_value;
    }

    public void setNet_value(String net_value) {
        this.net_value = net_value;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSord() {
        return sord;
    }

    public void setSord(String sord) {
        this.sord = sord;
    }

    public String getSortJz() {
        return sortJz;
    }

    public void setSortJz(String sortJz) {
        this.sortJz = sortJz;
    }

    public String getSortZdf() {
        return sortZdf;
    }

    public void setSortZdf(String sortZdf) {
        this.sortZdf = sortZdf;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
