package cn.com.buyforyou.fund.utils;

import cn.com.buyforyou.fund.App;
import cn.com.buyforyou.fund.constant.Constant;
import cn.com.buyforyou.fund.event.InvalidTokenEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * 单例存储唯一变量
 * Created by ${Yis}
 * data: 2017/12/5
 */

public class RuntimeHelper {

    private static RuntimeHelper mInstance;

    private boolean isLogin;

    private String url;

    public static synchronized RuntimeHelper getInstance() {
        if (mInstance == null) {
            mInstance = new RuntimeHelper();
        }
        return mInstance;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static RuntimeHelper getmInstance() {
        return mInstance;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    /**
     * 退出登录，发送退出登录的事件
     */
    public void isInvalidToken() {
        //清空缓存数据
        App.getSharedPref().putString(Constant.USERID, "");
        App.getSharedPref().putString(Constant.TOKEN, "");
        App.getSharedPref().putString(Constant.USER_CERTNO, "");
        App.getSharedPref().putString(Constant.IS_OPEN_ACCOUNT, "");
//                EventBus.getDefault().post(new RefreshUserDataEvent());
        //更新登录状态
        isLogin = false;
        EventBus.getDefault().post(new InvalidTokenEvent());
    }
}
