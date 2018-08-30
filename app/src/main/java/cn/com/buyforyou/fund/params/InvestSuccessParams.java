package cn.com.buyforyou.fund.params;

/**
 * 作者：sunnyzeng on 2018/1/17 10:22
 * 描述：
 */

public class InvestSuccessParams extends CommonReqData<InvestSuccessParams> {
    private String fundCode;
    private String scheduled_protocol_id;

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    public String getScheduled_protocol_id() {
        return scheduled_protocol_id;
    }

    public void setScheduled_protocol_id(String scheduled_protocol_id) {
        this.scheduled_protocol_id = scheduled_protocol_id;
    }
}
