package cn.com.buyforyou.fund.params;

/**
 * 作者：sunnyzeng on 2018/1/3 13:24
 * 描述：
 */

public class SendPhoneCodeParams extends CommonReqData<SendPhoneCodeParams>{
    private String phoneNo;

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
