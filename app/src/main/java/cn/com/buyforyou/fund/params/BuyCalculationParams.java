package cn.com.buyforyou.fund.params;

/**
 * 作者：sunnyzeng on 2018/1/11 17:41
 * 描述：
 */

public class BuyCalculationParams extends CommonReqData<BuyCalculationParams> {
    private String fund_code;
    private String apply_sum;


    public String getFund_code() {
        return fund_code;
    }

    public void setFund_code(String fund_code) {
        this.fund_code = fund_code;
    }

    public String getApply_sum() {
        return apply_sum;
    }

    public void setApply_sum(String apply_sum) {
        this.apply_sum = apply_sum;
    }
}
