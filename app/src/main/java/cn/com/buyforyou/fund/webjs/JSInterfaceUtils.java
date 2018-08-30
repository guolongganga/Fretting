package cn.com.buyforyou.fund.webjs;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

/**
 * 作者：sunnyzeng on 2018/1/10 10:39
 * 描述：JS交互 封裝的公共方法
 */

public class JSInterfaceUtils {
    //后台只需要调用connObj.toLogin();
    public static final String JS_ID = "connObj";
    public Context context;
    public WebView mWebView;

    public JSInterfaceUtils(Context context, WebView mWebView) {
        this.context = context;
        this.mWebView = mWebView;
    }

    // 定义JS需要调用的方法
    // 被JS调用的方法必须加入@JavascriptInterface注解
    @JavascriptInterface
    public void toLogin() {

        if (jSInterfaceClick != null) {
            jSInterfaceClick.toLogin();
        }
    }

    @JavascriptInterface
    public void toBuy() {

        if (jSInterfaceClick != null) {
            jSInterfaceClick.toBuy();
        }
    }

    @JavascriptInterface
    public void toInvest() {

        if (jSInterfaceClick != null) {
            jSInterfaceClick.toInvest();
        }
    }

    @JavascriptInterface
    public void toInvestPlan() {

        if (jSInterfaceClick != null) {
            jSInterfaceClick.toInvestPlan();
        }
    }

    @JavascriptInterface
    public void toBonus() {

        if (jSInterfaceClick != null) {
            jSInterfaceClick.toBonus();
        }
    }

    @JavascriptInterface
    public void toRecord() {

        if (jSInterfaceClick != null) {
            jSInterfaceClick.toRecord();
        }
    }

    @JavascriptInterface
    public void toSellOut() {

        if (jSInterfaceClick != null) {
            jSInterfaceClick.toSellOut();
        }
    }

    @JavascriptInterface
    public void toAppIndex() {

        if (jSInterfaceClick != null) {
            jSInterfaceClick.toAppIndex();
        }
    }
   //定义一个接口，用于回调
    private JSInterfaceClick jSInterfaceClick;

    public void setJSInterfaceClick(JSInterfaceClick jSInterfaceClick) {
        this.jSInterfaceClick = jSInterfaceClick;
    }

}
