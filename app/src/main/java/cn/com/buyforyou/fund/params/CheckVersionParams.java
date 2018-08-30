package cn.com.buyforyou.fund.params;

/**
 * Created by limxing on 2018/5/9.
 */

public class CheckVersionParams extends CommonReqData<CheckVersionParams> {

    private String preVerNum;
    private String deviceType;

    public String getPreVerNum() {
        return preVerNum;
    }

    public void setPreVerNum(String preVerNum) {
        this.preVerNum = preVerNum;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }
}
