package cn.com.buyforyou.fund.ui.activity.boot;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import cn.com.buyforyou.fund.App;
import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.constant.Constant;
import cn.com.buyforyou.fund.event.ChangeTabEvent;
import cn.com.buyforyou.fund.event.InvalidTokenEvent;
import cn.com.buyforyou.fund.event.RefreshBonusEvent;
import cn.com.buyforyou.fund.model.fund.BuyFundResp;
import cn.com.buyforyou.fund.model.fund.InvestResp;
import cn.com.buyforyou.fund.model.fund.SellResp;
import cn.com.buyforyou.fund.model.user.InvestPlanResp;
import cn.com.buyforyou.fund.model.user.UpdateBonusResp;
import cn.com.buyforyou.fund.model.user.WebBonusResp;
import cn.com.buyforyou.fund.net.Api;
import cn.com.buyforyou.fund.net.HttpContent;
import cn.com.buyforyou.fund.present.boot.FundDetailPresent;
import cn.com.buyforyou.fund.ui.activity.MainActivity;
import cn.com.buyforyou.fund.ui.activity.fund.BuyActivity;
import cn.com.buyforyou.fund.ui.activity.fund.InvestActivity;
import cn.com.buyforyou.fund.ui.activity.fund.SellActivity;
import cn.com.buyforyou.fund.ui.activity.user.BonusChangeActivity;
import cn.com.buyforyou.fund.ui.activity.user.InvestPlanActivity;
import cn.com.buyforyou.fund.ui.activity.user.LoginActivity;
import cn.com.buyforyou.fund.ui.activity.user.PersonInfoActivity;
import cn.com.buyforyou.fund.ui.activity.user.RegisterSecondActivity;
import cn.com.buyforyou.fund.ui.activity.user.TransactionQuerySingleActivity;

import com.zhsoft.fretting.ui.widget.CustomDialog;

import cn.com.buyforyou.fund.utils.RuntimeHelper;
import cn.com.buyforyou.fund.webjs.JSInterfaceClick;
import cn.com.buyforyou.fund.webjs.JSInterfaceUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.dialog.httploadingdialog.HttpLoadingDialog;
import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.mvp.XActivity;

/**
 * 基金详情WebView
 * Created by sunny on 2017/4/25
 */

public class FundDetailWebActivity extends XActivity<FundDetailPresent> {

    /**
     * 返回按钮
     */
    @BindView(R.id.head_back)
    ImageButton headBack;
    /**
     * 标题
     */
    @BindView(R.id.head_title)
    TextView headTitle;
    /**
     * 右侧图片按钮
     */
    @BindView(R.id.head_right_imgbtn)
    ImageButton headRightImgbtn;
    /**
     * WebView
     */
    @BindView(R.id.my_web)
    WebView mWeb;
    /**
     * 进度条
     */
    @BindView(R.id.pb)
    ProgressBar pb;
    /**
     * 用户登录标识
     */
    private String token = "";
    /**
     * 用户编号
     */
    private String userId = "";
    /**
     * 基金代码
     */
    private String fundCode;
    /**
     * 基金名称
     */
    private String fundName;
    /**
     * 加载圈
     */
    private HttpLoadingDialog httpLoadingDialog;

    /**
     * 去开户 弹出框
     */
    private CustomDialog openAccountDialog;
    /**
     * 去补全个人信息 弹出框
     */
    private CustomDialog personInfoDialog;
    /**
     * 去开户 去风险测评
     */
    private CustomDialog riskTestDialog;
    /**
     * 风险等级 弹出框
     */
    private CustomDialog validateDialog;

    @Override
    public int getLayoutId() {
        return R.layout.activity_web_public;
    }

    @Override
    public FundDetailPresent newP() {
        return new FundDetailPresent();
    }

    @Override
    public void initData(Bundle bundle) {
        //右侧搜索按钮
        headRightImgbtn.setVisibility(View.VISIBLE);
        headRightImgbtn.setImageResource(R.drawable.icon_common_search_white);
        EventBus.getDefault().register(this);
        httpLoadingDialog = new HttpLoadingDialog(context);

        //标题
//        int title = bundle.getInt(Constant.WEB_TITLE);
        //链接
        String link = bundle.getString(Constant.WEB_LINK);
        //基金代码
        fundCode = bundle.getString(Constant.FUND_DETAIL_CODE);
        //基金名称
        fundName = bundle.getString(Constant.FUND_DETAIL_NAME);

        if (RuntimeHelper.getInstance().isLogin()) {
            //用户登录标识
            token = App.getSharedPref().getString(Constant.TOKEN, "");
            //用户编号
            userId = App.getSharedPref().getString(Constant.USERID, "");
        }
        pb.setMax(100);

        WebSettings webSettings = mWeb.getSettings();
        // 设置可以支持缩放
        webSettings.setSupportZoom(true);
        // 设置出现缩放工具
        webSettings.setBuiltInZoomControls(true);

        // 设置WebViewClient，保证新的链接地址不打开系统的浏览器窗口
        mWeb.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                httpLoadingDialog.visible();
                return super.shouldOverrideUrlLoading(view, url);
            }

            /**
             * 在结束加载网页时会回调
             */
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                String title = view.getTitle();

                XLog.d("WebView", "TITLE=" + title);
                headTitle.setText(title);
                httpLoadingDialog.dismiss();
                /**
                 *  如果紧跟着
                 *  webView.loadUrl(file:///android_asset/index.html);
                 *  调用Js中的方法是不起作用的，必须页面加载完成才可以
                 */
                //调用js中的函数：showInfoFromJava(msg)
//                mWeb.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        mWeb.loadUrl("javascript:callJS('" + "123456" + "')");
//                    }
//                });
            }

            @Override
            public void onReceivedSslError(WebView view,
                                           SslErrorHandler handler, SslError error) {
                // handler.cancel();// Android默认的处理方式
                handler.proceed();// 接受所有网站的证书
                // handleMessage(Message msg);// 进行其他处理
            }
        });
        // 设置WebView支持运行普通的Javascript
        webSettings.setJavaScriptEnabled(true);
        // 设置允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        //加载视频需要 支持插件
        webSettings.setPluginState(WebSettings.PluginState.ON);
        //将图片调整到适合WebView的大小  无效
        webSettings.setUseWideViewPort(true);
        // android 5.0以上默认不支持Mixed Content
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(mWeb.getSettings().MIXED_CONTENT_ALWAYS_ALLOW);
        }
        //支持自动加载图片
        webSettings.setLoadsImagesAutomatically(true);
        // 缩放至屏幕的大小
        webSettings.setLoadWithOverviewMode(true);

        // 通过addJavascriptInterface()将Java对象映射到JS对象
        //参数1：Javascript对象名
        //参数2：Java对象名
        // webview设置JS
        JSInterfaceUtils jsInterfaceUtils = new JSInterfaceUtils(this, mWeb);
        //jsInterfaceUtils类对象映射到js的connObj对象
        mWeb.addJavascriptInterface(jsInterfaceUtils, jsInterfaceUtils.JS_ID);
        setJSClick(jsInterfaceUtils);

        mWeb.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        // 设置WebChromeClient，以支持运行特殊的Javascript
        mWeb.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                pb.setVisibility(View.VISIBLE);
                pb.setProgress(newProgress);
                if (newProgress == 100) {
                    pb.setVisibility(View.INVISIBLE);
//                    httpLoadingDialog.dismiss();
                }
                super.onProgressChanged(view, newProgress);
            }

            //设置标题
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                XLog.d("WebView", "TITLE=" + title);
                headTitle.setText(title);
            }
        });
        //添加header
        String ua = webSettings.getUserAgentString();
        webSettings.setUserAgentString(ua.replace("appType", "Android"));

        link = link + "?fund_code=" + fundCode + "&token=" + token + "&userId=" + userId;
        XLog.e(link);
        mWeb.loadUrl(link);

        // 加载JS代码
        // 格式规定为:file:///android_asset/文件名.html
//        mWeb.loadUrl("file:///android_asset/javascript.html");
//        mWeb.loadUrl("https://20.1.149.117:8443/htmlNoPermission/fundDeta");

    }

    /**
     * 事件
     */
    @Override
    public void initEvents() {
        headBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        headRightImgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(SearchActivity.class);
            }
        });
    }

    /**
     * JS
     *
     * @param jsInterfaceUtils
     */
    @SuppressLint("SetJavaScriptEnabled")
    @JavascriptInterface
    protected void setJSClick(JSInterfaceUtils jsInterfaceUtils) {
        // 设置js未登录触发事件
        jsInterfaceUtils.setJSInterfaceClick(new JSInterfaceClick() {

            @Override
            public void toLogin() {
                baseToLogin();
            }

            @Override
            public void toBuy() {
                baseToBuy();
            }

            @Override
            public void toInvest() {
                baseToInvest();
            }

            @Override
            public void toInvestPlan() {
                baseToInvestPlan();
            }

            @Override
            public void toBonus() {
                baseToBonus();
            }

            @Override
            public void toRecord() {
                baseToRecord();
            }

            @Override
            public void toSellOut() {
                baseToSellOut();
            }

            public void toAppIndex() {
                baseToAppIndex();
            }
        });
    }

    /**
     * 登录 关注
     */
    private void baseToLogin() {
        if (RuntimeHelper.getInstance().isLogin()) {
            //用户登录标识
            token = App.getSharedPref().getString(Constant.TOKEN, "");
            //用户编号
            userId = App.getSharedPref().getString(Constant.USERID, "");
            //调用js中的函数：showInfoFromJava(msg)
            mWeb.post(new Runnable() {
                @Override
                public void run() {
                    mWeb.loadUrl("javascript:callJS('" + token + "','" + userId + "')");
                }
            });

        } else {
            //跳转回登录界面
            Bundle bundle = new Bundle();
            bundle.putString(Constant.SKIP_SIGN, Constant.WEB_ACTIVITY);
            startActivity(LoginActivity.class, bundle);
        }
    }

    /**
     * 立即购买
     * 购买
     */
    private void baseToBuy() {
        if (RuntimeHelper.getInstance().isLogin()) {
            //用户登录标识
            token = App.getSharedPref().getString(Constant.TOKEN, "");
            //用户编号
            userId = App.getSharedPref().getString(Constant.USERID, "");
            getP().buyFund(token, userId, fundCode);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    httpLoadingDialog.visible();
                }
            });
        } else {
            //跳转回登录界面
            Bundle bundle = new Bundle();
            bundle.putString(Constant.SKIP_SIGN, Constant.WEB_ACTIVITY);
            startActivity(LoginActivity.class, bundle);
        }
    }

    /**
     * 定投
     */
    private void baseToInvest() {
        if (RuntimeHelper.getInstance().isLogin()) {
            //用户登录标识
            token = App.getSharedPref().getString(Constant.TOKEN, "");
            //用户编号
            userId = App.getSharedPref().getString(Constant.USERID, "");
            //判断是否能够定投
            getP().investTime(token, userId, fundCode, fundName);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    httpLoadingDialog.visible();
                }
            });

        } else {
            //跳转回登录界面
            Bundle bundle = new Bundle();
            bundle.putString(Constant.SKIP_SIGN, Constant.WEB_ACTIVITY);
            startActivity(LoginActivity.class, bundle);
        }
    }

    /**
     * 赎回
     */
    private void baseToSellOut() {
        if (RuntimeHelper.getInstance().isLogin()) {
            //用户登录标识
            token = App.getSharedPref().getString(Constant.TOKEN, "");
            //用户编号
            userId = App.getSharedPref().getString(Constant.USERID, "");
            getP().sellFundPre(token, userId, fundCode);
            showLoading();
        } else {
            //跳转回登录界面
            Bundle bundle = new Bundle();
            bundle.putString(Constant.SKIP_SIGN, Constant.WEB_ACTIVITY);
            startActivity(LoginActivity.class, bundle);
        }

    }

    private void showLoading() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                httpLoadingDialog.visible();

            }
        });
    }

    private void closeLoading() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                httpLoadingDialog.dismiss();

            }
        });
    }

    /**
     * 定投计划
     */
    private void baseToInvestPlan() {
        showLoading();
        getP().buyOnFundData(token, userId, fundCode);
    }

    /**
     * 变更分红方式
     */
    private void baseToBonus() {
//        showLoading();
        getP().loadBonusData(fundCode, token, userId);

    }

    /**
     * 交易记录
     */
    private void baseToRecord() {
        //需要传fundCode
//        Logger.d("baseToAppIndex");
        Bundle bundle = new Bundle();
        bundle.putString(Constant.FUND_DETAIL_CODE, fundCode);
        startActivity(TransactionQuerySingleActivity.class, bundle);
    }

    /**
     * 返回首页
     */
    private void baseToAppIndex() {
        //返回首页

        EventBus.getDefault().post(new ChangeTabEvent(Constant.MAIN_INDEX));
        startActivity(MainActivity.class);
        finish();
    }

    /**
     * 购买（是否符合购买资格）失败
     */
    public void requestBuyFundFail() {
        httpLoadingDialog.dismiss();
    }


    /**
     * 购买（是否符合购买资格）成功 跳转购买页面
     */
    public void requestBuyFundSuccess(final BuyFundResp resp) {
        httpLoadingDialog.dismiss();
        if (Constant.TO_OPEN_ACCOUNT.equals(resp.getCanBuy()) || Constant.TO_PERSON_INFO.equals(resp.getCanBuy())
                || Constant.TO_RISK_TEST.equals(resp.getCanBuy())) {
            switchDialog(resp.getCanBuy());
        }
        else if (Constant.TO_VALIDATE.equals(resp.getCanBuy())) {
            //弹出风险等级框
            if (validateDialog == null) {
                validateDialog = new CustomDialog.Builder(context)
                        .setMessage(resp.getFundRisk())
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                validateDialog.dismiss();
                            }
                        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                validateDialog.dismiss();
                                //去购买
                                Bundle bundle = new Bundle();
                                bundle.putString(Constant.FUND_DETAIL_CODE, fundCode);
                                bundle.putString(Constant.FUND_DETAIL_NAME, fundName);
                                bundle.putParcelable(Constant.BUY_FUND_OBJECT, resp);
                                startActivity(BuyActivity.class, bundle);
                            }
                        }).create();
            }
            validateDialog.show();
        } else {

            //去购买
            Bundle bundle = new Bundle();
            bundle.putString(Constant.FUND_DETAIL_CODE, fundCode);
            bundle.putString(Constant.FUND_DETAIL_NAME, fundName);
            bundle.putParcelable(Constant.BUY_FUND_OBJECT, resp);
            startActivity(BuyActivity.class, bundle);
        }

    }

    /**
     * 请求定投验证接口失败
     */
    public void requestInvestFail() {
        httpLoadingDialog.dismiss();
    }

    /**
     * 请求定投验证接口成功
     *
     * @param resp
     */
    public void requestInvestSuccess(final InvestResp resp) {

        httpLoadingDialog.dismiss();
        if (Constant.TO_OPEN_ACCOUNT.equals(resp.getCanBuy()) || Constant.TO_PERSON_INFO.equals(resp.getCanBuy())
                || Constant.TO_RISK_TEST.equals(resp.getCanBuy())) {
            switchDialog(resp.getCanBuy());
        } else if (Constant.TO_VALIDATE.equals(resp.getCanBuy())) {
            //弹出风险等级框
            if (validateDialog == null) {
                validateDialog = new CustomDialog.Builder(context)
                        .setMessage(resp.getFundRisk())
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                validateDialog.dismiss();
                            }
                        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                validateDialog.dismiss();
                                //去定投
                                Bundle bundle = new Bundle();
                                bundle.putString(Constant.INVEST_ACTIVITY_TYPE, Constant.INVEST_ACTIVITY);
                                bundle.putString(Constant.FUND_DETAIL_CODE, fundCode);
                                bundle.putString(Constant.FUND_DETAIL_NAME, fundName);
                                bundle.putParcelable(Constant.INVEST_FUND_OBJECT, resp);
                                startActivity(InvestActivity.class, bundle);
                            }
                        }).create();
            }
            validateDialog.show();
        } else if (Constant.TO_CHANGE_ACCOUNT.equals(resp.getCanBuy())) {
            new CustomDialog.Builder(context).setMessage(R.string.invest_notallow).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).create().show();

        } else {

            //去定投
            Bundle bundle = new Bundle();
            bundle.putString(Constant.INVEST_ACTIVITY_TYPE, Constant.INVEST_ACTIVITY);
            bundle.putString(Constant.FUND_DETAIL_CODE, fundCode);
            bundle.putString(Constant.FUND_DETAIL_NAME, fundName);
            bundle.putParcelable(Constant.INVEST_FUND_OBJECT, resp);
            startActivity(InvestActivity.class, bundle);
        }

    }


    /***
     * 去定投计划成功
     * @param planResp
     */
    public void requestInvestPlanSuccess(InvestPlanResp planResp) {
        closeLoading();
        //1有 0没有 定投
        if ("0".equals(planResp.getHasDt())) {
            //跳转定投购买
            //判断是否能够定投
            getP().investTime(token, userId, fundCode, fundName);
        } else {
            Bundle bundle = new Bundle();
            bundle.putString(Constant.FUND_DETAIL_CODE, fundCode);
            bundle.putString(Constant.FUND_DETAIL_NAME, fundName);
            bundle.putParcelableArrayList(Constant.ACTIVITY_OBJECT, planResp.getResResult());
            startActivity(InvestPlanActivity.class, bundle);
        }
    }

    /***
     * 去定投计划失败
     */
    public void requestInvestPlanFail() {
        httpLoadingDialog.dismiss();
    }

    /**
     * 三种弹出框
     *
     * @param canBuy
     */
    private void switchDialog(String canBuy) {
        if (Constant.TO_OPEN_ACCOUNT.equals(canBuy)) {
            //弹出弹出框 去开户
            if (openAccountDialog == null) {
                openAccountDialog = new CustomDialog.Builder(context)
                        .setMessage("您还没有开户，现在去开户！")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                openAccountDialog.dismiss();
                            }
                        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                openAccountDialog.dismiss();
                                startActivity(RegisterSecondActivity.class);
                            }
                        }).create();
            }
            openAccountDialog.show();
        } else if (Constant.TO_PERSON_INFO.equals(canBuy)) {
            //弹出弹出框 去补全个人信息
            if (personInfoDialog == null) {
                personInfoDialog = new CustomDialog.Builder(context)
                        .setMessage("购买基金前请先完善您的个人信息！")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                personInfoDialog.dismiss();
                            }
                        }).setPositiveButton("去完成", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                personInfoDialog.dismiss();
                                startActivity(PersonInfoActivity.class);
                            }
                        }).create();
            }
            personInfoDialog.show();
        } else if (Constant.TO_RISK_TEST.equals(canBuy)) {
            //弹出弹出框 去风险测评
            if (riskTestDialog == null) {
                riskTestDialog = new CustomDialog.Builder(context)
                        .setMessage("购买基金前请先完成风险等级测评！")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                riskTestDialog.dismiss();
                            }
                        }).setPositiveButton("去完成", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                riskTestDialog.dismiss();
                                Bundle bundle = new Bundle();
                                bundle.putInt(Constant.WEB_TITLE, R.string.user_risk_test);
                                bundle.putString(Constant.WEB_LINK, Api.API_BASE_URL + HttpContent.risk_question);
                                startActivity(WebRiskActivity.class, bundle);
                            }
                        }).create();
            }
            riskTestDialog.show();

        }
    }

    /**
     * 修改分红方式
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onRefreshBonusEvent(RefreshBonusEvent event) {
        final String chooseStyle = event.getBonusType();
        //调用js中的函数：bonusJS(value) 修改分红方式
        mWeb.post(new Runnable() {
            @Override
            public void run() {
                mWeb.loadUrl("javascript:bonusJS('" + chooseStyle + "')");
            }
        });
    }

    /**
     * 是否能赎回 请求访问失败
     */
    public void requestSellPreFail() {
        closeLoading();
    }

    /**
     * 可以赎回
     */
    public void requestSellPreSuccess() {
        closeLoading();
        // 卖出
        Bundle bundle = new Bundle();
        bundle.putString(Constant.FUND_DETAIL_CODE, fundCode);
        bundle.putString(Constant.FUND_DETAIL_NAME, fundName);
        startActivity(SellActivity.class, bundle);
    }

    /**
     * 已经登出系统，请重新登录
     */
    public void areadyLogout() {
        httpLoadingDialog.dismiss();
//        EventBus.getDefault().post(new InvalidTokenEvent());
        //清除本地缓存，设置成未登录
        RuntimeHelper.getInstance().isInvalidToken();
        //跳转登录界面
        Bundle bundle = new Bundle();
        bundle.putString(Constant.SKIP_SIGN, Constant.SKIP_INDEX_ACTIVITY);
        startActivity(LoginActivity.class, bundle);
    }


    /**
     * 网页能返回上一级页面
     */
    @Override
    public void onBackPressed() {

        if (null != mWeb && mWeb.canGoBack()) {
            httpLoadingDialog.visible();
            mWeb.goBack();// 返回前一个页面
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mWeb != null) {
            mWeb.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mWeb != null) {
            mWeb.onPause();
        }
    }

    /**
     * 销毁
     */
    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        try {
            if (mWeb != null) {
                mWeb.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
                mWeb.clearHistory();

                ((ViewGroup) mWeb.getParent()).removeView(mWeb);
                mWeb.destroy();
                mWeb = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //关闭弹出框 避免has leaked window android.widget
        if (httpLoadingDialog != null) {
            httpLoadingDialog.dismiss();
        }
        if (openAccountDialog != null) {
            openAccountDialog.dismiss();
            openAccountDialog = null;
        }
        if (personInfoDialog != null) {
            personInfoDialog.dismiss();
            personInfoDialog = null;
        }
        if (riskTestDialog != null) {
            riskTestDialog.dismiss();
            riskTestDialog = null;
        }
        if (validateDialog != null) {
            validateDialog.dismiss();
            validateDialog = null;
        }
        super.onDestroy();
    }

    /**
     * 获取分红成功，跳转分红界面
     *
     * @param model
     */
    public void loadBonusDataSuccess(final WebBonusResp model) {
//        httpLoadingDialog.dismiss();
        Bundle bundle = new Bundle();
        bundle.putBoolean("isFromWeb", true);
        bundle.putParcelable(Constant.ACTIVITY_OBJECT, model);
        startActivity(BonusChangeActivity.class, bundle);

    }
}