package cn.com.buyforyou.fund.params;

/**
 * 作者：sunnyzeng on 2018/1/26 10:06
 * 描述：定投详情修改定投状态参数
 * {"reqData":{"data":{"scheduled_protocol_id":"201712180009","scheduled_protocol_state":"","password":"qew312"}
 * ,"token":"7af37b692611438cbda677386223bd0d","userId":"3c8aeffcc2fc486da8b334f24576a118"}}
 */

public class StateChangeParams extends CommonReqData<StateChangeParams> {
    /**
     * 协议编号
     */
    private String scheduled_protocol_id;
    /**
     * 定投状态 A：启用P：暂停H：终止
     */
    private String scheduled_protocol_state;
    /**
     * 交易密码
     */
    private String password;

    public String getScheduled_protocol_id() {
        return scheduled_protocol_id;
    }

    public void setScheduled_protocol_id(String scheduled_protocol_id) {
        this.scheduled_protocol_id = scheduled_protocol_id;
    }

    public String getScheduled_protocol_state() {
        return scheduled_protocol_state;
    }

    public void setScheduled_protocol_state(String scheduled_protocol_state) {
        this.scheduled_protocol_state = scheduled_protocol_state;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
