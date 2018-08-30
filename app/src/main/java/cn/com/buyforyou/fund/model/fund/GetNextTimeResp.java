package cn.com.buyforyou.fund.model.fund;

import cn.com.buyforyou.fund.model.BaseResp;

/**
 * 作者：sunnyzeng on 2018/1/16 14:58
 * 描述：定投查询首次抠款时间类
 */

public class GetNextTimeResp extends BaseResp<GetNextTimeResp> {
    /**
     * 扣款时间(根据实际情况拼接起来的)
     */
    private String exchdate;

    /**
     * 扣款星期几
     */
    private String exchWeek;

    /**
     * 首次交易月 S 6 0 Y v4.0.3.0
     */
    private String first_trade_month;

    public String getFirst_trade_month() {
        return first_trade_month;
    }

    public void setFirst_trade_month(String first_trade_month) {
        this.first_trade_month = first_trade_month;
    }

    public String getExchdate() {
        return exchdate;
    }

    public void setExchdate(String exchdate) {
        this.exchdate = exchdate;
    }

    public String getExchWeek() {
        return exchWeek;
    }

    public void setExchWeek(String exchWeek) {
        this.exchWeek = exchWeek;
    }
}
