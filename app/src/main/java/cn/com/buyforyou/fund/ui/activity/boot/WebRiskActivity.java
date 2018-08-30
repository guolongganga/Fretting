package cn.com.buyforyou.fund.ui.activity.boot;

import android.os.Bundle;

import cn.com.buyforyou.fund.App;
import cn.com.buyforyou.fund.constant.Constant;
import cn.com.buyforyou.fund.ui.activity.base.BaseWebActivity;

/**
 * 作者：sunnyzeng on 2018/2/1 14:13
 * 描述：带token和userID H5页面 （风险测评）
 */

public class WebRiskActivity extends BaseWebActivity {
    /** 用户登录标识 */
    private String token = "";
    /** 用户编号 */
    private String userId = "";

    @Override
    protected void myLoadUrl(Bundle bundle) {
        token = App.getSharedPref().getString(Constant.TOKEN, "");
        userId = App.getSharedPref().getString(Constant.USERID, "");
        //添加header
        String ua = mWeb.getSettings().getUserAgentString();
        mWeb.getSettings().setUserAgentString(ua.replace("appType", "Android"));

        link = link + "?token=" + token + "&userId=" + userId;
        mWeb.loadUrl(link);
    }

    @Override
    public Object newP() {
        return null;
    }

    @Override
    public void onBackPressed() {
        setResult(Constant.RISK_BACK_ACTIVITY);
        finish();
    }
}
