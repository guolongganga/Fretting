package cn.com.buyforyou.fund.params;

/**
 * 作者：sunnyzeng on 2017/12/22 10:02
 * 描述：短信验证码接口参数
 */

public class PhoneCodeParams extends CommonReqData<PhoneCodeParams> {
    private String phoneNo;
    private String image_code;
    private String image_code_id;

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getImage_code() {
        return image_code;
    }

    public void setImage_code(String image_code) {
        this.image_code = image_code;
    }

    public String getImage_code_id() {
        return image_code_id;
    }

    public void setImage_code_id(String image_code_id) {
        this.image_code_id = image_code_id;
    }
}
