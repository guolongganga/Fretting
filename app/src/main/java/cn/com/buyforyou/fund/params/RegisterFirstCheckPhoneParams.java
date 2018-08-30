package cn.com.buyforyou.fund.params;

/**
 * 作者：sunnyzeng on 2017/12/14 18:05
 * 描述：基金开户 手机号检测是否存在
 * {
 * "reqData":{
 * "data":{
 * "mobile_tel":13718690595,
 * "password":"cjh123456"
 * }
 * }
 * }
 */

public class RegisterFirstCheckPhoneParams extends CommonReqData<RegisterFirstCheckPhoneParams> {

    private String phoneNo;

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
