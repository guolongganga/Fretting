package cn.com.buyforyou.fund.params;

/**
 * 作者：sunnyzeng on 2018/1/16 09:21
 * 描述：定投 验证
 */

public class InvestParams extends CommonReqData<InvestParams> {
    private String fundCode;
    private String fund_full_name;
    private String fund_name;

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    public String getFund_full_name() {
        return fund_full_name;
    }

    public void setFund_full_name(String fund_full_name) {
        this.fund_full_name = fund_full_name;
    }

    public String getFund_name() {
        return fund_name;
    }

    public void setFund_name(String fund_name) {
        this.fund_name = fund_name;
    }
}
