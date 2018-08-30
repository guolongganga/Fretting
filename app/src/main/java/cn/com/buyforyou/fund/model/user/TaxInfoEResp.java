package cn.com.buyforyou.fund.model.user;

import java.util.ArrayList;

import cn.com.buyforyou.fund.model.BaseResp;


public class TaxInfoEResp extends BaseResp<ArrayList<TaxInfoEResp>> {

    private String belong_type;
    private String is_main_tax = "";
    private String json_belong_id;
    private String json_id;
    private String no_tax_no_reason = "";
    private String tax_explain = "";
    private String tax_nation = "";
    private String tax_no;


    public TaxInfoEResp() {

    }

    public String getBelong_type() {
        return belong_type;
    }

    public void setBelong_type(String belong_type) {
        this.belong_type = belong_type;
    }

    public String getIs_main_tax() {
        return is_main_tax;
    }

    public void setIs_main_tax(String is_main_tax) {
        this.is_main_tax = is_main_tax;
    }

    public String getJson_belong_id() {
        return json_belong_id;
    }

    public void setJson_belong_id(String json_belong_id) {
        this.json_belong_id = json_belong_id;
    }

    public String getJson_id() {
        return json_id;
    }

    public void setJson_id(String json_id) {
        this.json_id = json_id;
    }

    public String getNo_tax_no_reason() {
        return no_tax_no_reason;
    }

    public void setNo_tax_no_reason(String no_tax_no_reason) {
        this.no_tax_no_reason = no_tax_no_reason;
    }

    public String getTax_explain() {
        return tax_explain;
    }

    public void setTax_explain(String tax_explain) {
        this.tax_explain = tax_explain;
    }

    public String getTax_nation() {
        return tax_nation;
    }

    public void setTax_nation(String tax_nation) {
        this.tax_nation = tax_nation;
    }

    public String getTax_no() {
        return tax_no;
    }

    public void setTax_no(String tax_no) {
        this.tax_no = tax_no;
    }

    @Override
    public String toString() {
        return "TaxInfoEResp{" +
                "belong_type='" + belong_type + '\'' +
                ", is_main_tax='" + is_main_tax + '\'' +
                ", json_belong_id='" + json_belong_id + '\'' +
                ", json_id='" + json_id + '\'' +
                ", no_tax_no_reason='" + no_tax_no_reason + '\'' +
                ", tax_explain='" + tax_explain + '\'' +
                ", tax_nation='" + tax_nation + '\'' +
                ", tax_no='" + tax_no + '\'' +
                '}';
    }
}
