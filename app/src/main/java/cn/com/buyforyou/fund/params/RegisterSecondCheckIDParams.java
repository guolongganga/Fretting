package cn.com.buyforyou.fund.params;

/**
 * 作者：sunnyzeng on 2017/12/14 18:05
 * 描述：基金开户 id检测是否存在
 * {
 * "reqData":{
 * "data":{
 * "mobile_tel":13718690595,
 * "password":"cjh123456"
 * }
 * }
 * }
 */

public class RegisterSecondCheckIDParams extends CommonReqData<RegisterSecondCheckIDParams> {

    private String certNo;

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }
}
