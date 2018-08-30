package cn.com.buyforyou.fund.params;

/**
 * 作者：sunnyzeng on 2018/1/16 13:49
 * 描述：
 * {"reqData":{"data":{
 * "fundCode":"050003",
 * "protocol_period_unit":"1",
 * "first_exchdate":"02"},
 * "token":"7af37b692611438cbda677386223bd0d",
 * "userId":"ffa68a63c1e34aa48d17088e33d39b4f"}}
 */

public class GetNextTimeParams extends CommonReqData<GetNextTimeParams> {
    private String fundCode;
    private String protocol_period_unit;
    private String first_exchdate;

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    public String getProtocol_period_unit() {
        return protocol_period_unit;
    }

    public void setProtocol_period_unit(String protocol_period_unit) {
        this.protocol_period_unit = protocol_period_unit;
    }

    public String getFirst_exchdate() {
        return first_exchdate;
    }

    public void setFirst_exchdate(String first_exchdate) {
        this.first_exchdate = first_exchdate;
    }
}
