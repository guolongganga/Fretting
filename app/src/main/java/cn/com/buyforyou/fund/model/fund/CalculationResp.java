package cn.com.buyforyou.fund.model.fund;

import cn.com.buyforyou.fund.model.BaseResp;

/**
 * 作者：sunnyzeng on 2018/3/22 19:54
 * 描述：
 */

public class CalculationResp extends BaseResp<CalculationResp> {
    private String fare_sx;
    private String curr_rate;
    private String source_rate;

    public String getCurr_rate() {
        return curr_rate;
    }

    public void setCurr_rate(String curr_rate) {
        this.curr_rate = curr_rate;
    }

    public String getSource_rate() {
        return source_rate;
    }

    public void setSource_rate(String source_rate) {
        this.source_rate = source_rate;
    }

    public String getFare_sx() {
        return fare_sx;
    }

    public void setFare_sx(String fare_sx) {
        this.fare_sx = fare_sx;
    }
}
