package cn.com.buyforyou.fund.params;

import cn.com.buyforyou.fund.model.user.BankResp;

/**
 * 作者：sunnyzeng on 2018/1/3 13:08
 * 描述：
 */

public class ChangeBankCardParams extends CommonReqData<ChangeBankCardParams> {
    private BankResp selectBank;
    private String bankAccout;
    private String mobile;
    private String phoneCode;
    private String trade_password;
    public String getTrade_password() {
        return trade_password;
    }

    public void setTrade_password(String trade_password) {
        this.trade_password = trade_password;
    }

    public BankResp getSelectBank() {
        return selectBank;
    }

    public void setSelectBank(BankResp selectBank) {
        this.selectBank = selectBank;
    }

    public String getBankAccout() {
        return bankAccout;
    }

    public void setBankAccout(String bankAccout) {
        this.bankAccout = bankAccout;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }
}
