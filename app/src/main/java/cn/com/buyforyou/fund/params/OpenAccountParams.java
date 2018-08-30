package cn.com.buyforyou.fund.params;

import cn.com.buyforyou.fund.model.user.BankResp;

/**
 * 作者：sunnyzeng on 2017/12/25 15:52
 * 描述：开户绑卡接口参数
 */

public class OpenAccountParams extends CommonReqData<OpenAccountParams> {
    private String userName;
    private String certNo;
    private String email;
    private BankResp selectBank;
    private String bankAccount;
    private String mobile;
    private String tradePassword;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BankResp getSelectBank() {
        return selectBank;
    }

    public void setSelectBank(BankResp selectBank) {
        this.selectBank = selectBank;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTradePassword() {
        return tradePassword;
    }

    public void setTradePassword(String tradePassword) {
        this.tradePassword = tradePassword;
    }
}
