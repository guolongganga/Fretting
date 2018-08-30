package cn.com.buyforyou.fund.model;

import cn.droidlover.xdroidmvp.net.IModel;

/**
 * 作者：sunnyzeng on 2017/12/7 18:15
 * 描述：
 */

public class Resp implements IModel {
    private int status;
    private String message;


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public boolean isAuthError() {
        return false;
    }

    @Override
    public boolean isBizError() {
        return false;
    }

    @Override
    public String getErrorMsg() {
        return null;
    }
}
