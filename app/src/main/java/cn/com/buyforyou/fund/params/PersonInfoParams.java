package cn.com.buyforyou.fund.params;

import cn.com.buyforyou.fund.model.user.OccupationResp;

/**
 * 作者：sunnyzeng on 2017/12/28 15:22
 * 描述：
 */

public class PersonInfoParams extends CommonReqData<PersonInfoParams> {
    /** 身份证有效期 */
    private String id_enddate;
    /** 国籍 */
    private String fund_nationality;
    /** 职业 */
    private OccupationResp occupation;
    /** 联系地址 */
    private String address;
    /** 详细地址 */
    private String detaile_address;
    /** 邮箱 */
    private String email;
    private String trade_password;
    private String cust_flag;

    public String getTrade_password() {
        return trade_password;
    }

    public void setTrade_password(String trade_password) {
        this.trade_password = trade_password;
    }

    public String getId_enddate() {
        return id_enddate;
    }

    public void setId_enddate(String id_enddate) {
        this.id_enddate = id_enddate;
    }

    public String getFund_nationality() {
        return fund_nationality;
    }

    public void setFund_nationality(String fund_nationality) {
        this.fund_nationality = fund_nationality;
    }

    public OccupationResp getOccupation() {
        return occupation;
    }

    public void setOccupation(OccupationResp occupation) {
        this.occupation = occupation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetaile_address() {
        return detaile_address;
    }

    public void setDetaile_address(String detaile_address) {
        this.detaile_address = detaile_address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCust_flag() {
        return cust_flag;
    }

    public void setCust_flag(String cust_flag) {
        this.cust_flag = cust_flag;
    }
}
