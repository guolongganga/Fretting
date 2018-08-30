package cn.com.buyforyou.fund.event;

/**
 * 作者：sunnyzeng on 2018/1/9 14:29
 * 描述：切换主页tab
 */

public class ChangeTabEvent {
    private String msg;

    public ChangeTabEvent(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
