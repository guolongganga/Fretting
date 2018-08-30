package cn.com.buyforyou.fund.model.user;

import java.util.ArrayList;
import java.util.List;

import cn.com.buyforyou.fund.model.BaseResp;


public class CrsmainResp extends BaseResp<ArrayList<CrsmainResp>> {

    private String born_nation;
    private String client_id;
    private String current_addr;
    private String current_work_addr;
    private String current_work_city;
    private String current_work_city_no;
    private String current_work_nation;
    private String current_work_provice_code;
    private String cust_flag;
    private String eng_born_nation;
    private String eng_current_addr;
    private String eng_current_work_nation;
    private String eng_home_place;
    private String eng_last_name;
    private String eng_name;
    private String home_place;
    private String json_id;
    private String last_date;
    private String sign;

    private List<TaxInfoEResp> taxinfo;

    public String getBorn_nation() {
        return born_nation;
    }

    public void setBorn_nation(String born_nation) {
        this.born_nation = born_nation;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getCurrent_addr() {
        return current_addr;
    }

    public void setCurrent_addr(String current_addr) {
        this.current_addr = current_addr;
    }

    public String getCurrent_work_addr() {
        return current_work_addr;
    }

    public void setCurrent_work_addr(String current_work_addr) {
        this.current_work_addr = current_work_addr;
    }

    public String getCurrent_work_city() {
        return current_work_city;
    }

    public void setCurrent_work_city(String current_work_city) {
        this.current_work_city = current_work_city;
    }

    public String getCurrent_work_city_no() {
        return current_work_city_no;
    }

    public void setCurrent_work_city_no(String current_work_city_no) {
        this.current_work_city_no = current_work_city_no;
    }

    public String getCurrent_work_nation() {
        return current_work_nation;
    }

    public void setCurrent_work_nation(String current_work_nation) {
        this.current_work_nation = current_work_nation;
    }

    public String getCurrent_work_provice_code() {
        return current_work_provice_code;
    }

    public void setCurrent_work_provice_code(String current_work_provice_code) {
        this.current_work_provice_code = current_work_provice_code;
    }

    public String getCust_flag() {
        return cust_flag;
    }

    public void setCust_flag(String cust_flag) {
        this.cust_flag = cust_flag;
    }

    public String getEng_born_nation() {
        return eng_born_nation;
    }

    public void setEng_born_nation(String eng_born_nation) {
        this.eng_born_nation = eng_born_nation;
    }

    public String getEng_current_addr() {
        return eng_current_addr;
    }

    public void setEng_current_addr(String eng_current_addr) {
        this.eng_current_addr = eng_current_addr;
    }

    public String getEng_current_work_nation() {
        return eng_current_work_nation;
    }

    public void setEng_current_work_nation(String eng_current_work_nation) {
        this.eng_current_work_nation = eng_current_work_nation;
    }

    public String getEng_home_place() {
        return eng_home_place;
    }

    public void setEng_home_place(String eng_home_place) {
        this.eng_home_place = eng_home_place;
    }

    public String getEng_last_name() {
        return eng_last_name;
    }

    public void setEng_last_name(String eng_last_name) {
        this.eng_last_name = eng_last_name;
    }

    public String getEng_name() {
        return eng_name;
    }

    public void setEng_name(String eng_name) {
        this.eng_name = eng_name;
    }

    public String getHome_place() {
        return home_place;
    }

    public void setHome_place(String home_place) {
        this.home_place = home_place;
    }

    public String getJson_id() {
        return json_id;
    }

    public void setJson_id(String json_id) {
        this.json_id = json_id;
    }

    public String getLast_date() {
        return last_date;
    }

    public void setLast_date(String last_date) {
        this.last_date = last_date;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public List<TaxInfoEResp> getTaxinfo() {
        return taxinfo;
    }

    public void setTaxinfo(List<TaxInfoEResp> taxinfo) {
        this.taxinfo = taxinfo;
    }

    @Override
    public String toString() {
        return "CrsmainResp{" +
                "born_nation='" + born_nation + '\'' +
                ", client_id='" + client_id + '\'' +
                ", current_addr='" + current_addr + '\'' +
                ", current_work_addr='" + current_work_addr + '\'' +
                ", current_work_city='" + current_work_city + '\'' +
                ", current_work_city_no='" + current_work_city_no + '\'' +
                ", current_work_nation='" + current_work_nation + '\'' +
                ", current_work_provice_code='" + current_work_provice_code + '\'' +
                ", cust_flag='" + cust_flag + '\'' +
                ", eng_born_nation='" + eng_born_nation + '\'' +
                ", eng_current_addr='" + eng_current_addr + '\'' +
                ", eng_current_work_nation='" + eng_current_work_nation + '\'' +
                ", eng_home_place='" + eng_home_place + '\'' +
                ", eng_last_name='" + eng_last_name + '\'' +
                ", eng_name='" + eng_name + '\'' +
                ", home_place='" + home_place + '\'' +
                ", json_id='" + json_id + '\'' +
                ", last_date='" + last_date + '\'' +
                ", sign='" + sign + '\'' +
                ", taxinfo=" + taxinfo +
                '}';
    }
}
