package cn.com.buyforyou.fund.model;

import java.io.Serializable;

import cn.droidlover.xdroidmvp.log.XLog;

/**
 * 单选选项基础类
 */
public class ApplyBaseInfo implements Comparable, Serializable {


    //选项传递参数标识
    private String code;
    //选项内容
    private String content;

    public ApplyBaseInfo(String code) {
        this.code = code;
    }

    public ApplyBaseInfo(String code, String content) {
        this.code = code;
        this.content = content;
    }

    public ApplyBaseInfo() {
    }

    public String getCode() {
        return code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ApplyBaseInfo{" +
                "code='" + code + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    //排序
    @Override
    public int compareTo(Object another) {
        try {
            if (another instanceof ApplyBaseInfo) {
                ApplyBaseInfo s = (ApplyBaseInfo) another;
                Integer code1 = Integer.parseInt(this.getCode());
                Integer code2 = Integer.parseInt(s.getCode());
                return code1.compareTo(code2);
            }
        } catch (Exception e) {
            XLog.e("ex", e.toString());
        }
        return -1;
    }
}
