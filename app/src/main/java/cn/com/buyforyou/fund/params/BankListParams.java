package cn.com.buyforyou.fund.params;

/**
 * 作者：sunnyzeng on 2017/12/15 16:30
 * 描述：银行列表参数
 */

public class BankListParams extends CommonReqData<BankListParams>{

    private String partner_id;

    public String getPartner_id() {
        return partner_id;
    }

    public void setPartner_id(String partner_id) {
        this.partner_id = partner_id;
    }
}
