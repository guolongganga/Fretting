package cn.droidlover.xdroidmvp.mvp;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
//import com.umeng.analytics.MobclickAgent;

import butterknife.Unbinder;
import cn.droidlover.xdroidmvp.R;
import cn.droidlover.xdroidmvp.XDroidConf;
import cn.droidlover.xdroidmvp.event.BusProvider;
import cn.droidlover.xdroidmvp.kit.KnifeKit;
import cn.droidlover.xdroidmvp.net.NetUtils;
import cn.droidlover.xdroidmvp.widget.ScreenUtil;
import cn.droidlover.xdroidmvp.widget.ToastUtils;
import cn.droidlover.xdroidmvp.widget.theme.ColorRelativeLayout;

/**
 * Created by wanglei on 2016/12/29.
 */

public abstract  class XActivity<P extends IPresent> extends RxAppCompatActivity implements IView<P> {

    private P p;
    protected Activity context;

    private RxPermissions rxPermissions;

    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        if (getLayoutId() > 0) {
            setContentView(getLayoutId());
            bindUI(null);
            bindEvent();
        }
        setTranslucentStatus(true);
        initData(this.getIntent().getExtras());
        initEvents();
    }

    @Override
    public void bindUI(View rootView) {
        unbinder = KnifeKit.bind(this);
    }

    protected P getP() {
        if (p == null) {
            p = newP();
            if (p != null) {
                p.attachV(this);
            }
        }
        return p;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (useEventBus()) {
            BusProvider.getBus().register(this);
        }
        setTitleHeight(getRootView(this));
    }


    @Override
    protected void onResume() {
        super.onResume();
        // 设置页面竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        MobclickAgent.onResume(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
//        MobclickAgent.onPause(this);
    }

    @Override
    public boolean useEventBus() {
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (useEventBus()) {
            BusProvider.getBus().unregister(this);
        }
        if (getP() != null) {
            getP().detachV();
        }
        p = null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (getOptionsMenuId() > 0) {
            getMenuInflater().inflate(getOptionsMenuId(), menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    protected RxPermissions getRxPermissions() {
        rxPermissions = new RxPermissions(this);
        rxPermissions.setLogging(XDroidConf.DEV);
        return rxPermissions;
    }

    @Override
    public int getOptionsMenuId() {
        return 0;
    }

    @Override
    public void bindEvent() {
    }

    /**
     * 设置沉浸式
     *
     * @param on
     */
    protected void setTranslucentStatus(boolean on) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            if (on) {
                winParams.flags |= bits;
            } else {
                winParams.flags &= ~bits;
            }
            win.setAttributes(winParams);
        }
    }

    private void setTitleHeight(View view) {
        if (view != null) {
            ColorRelativeLayout title =  view.findViewById(R.id.title);
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                if (title != null) {
                    ViewGroup.LayoutParams lp = title.getLayoutParams();
                    lp.height = ScreenUtil.dip2px(this, 48);
                    title.setLayoutParams(lp);
                    title.setPadding(0, 0, 0, 0);
                }
            }
        }
    }

    protected static View getRootView(Activity context) {
        return ((ViewGroup) context.findViewById(android.R.id.content)).getChildAt(0);
    }

    /***********************************************小工具*****************************************************/
    //Toast弹框
    public void showToast(int resourceId) {
        ToastUtils.show(context, resourceId);
    }

    public void showToast(String msg) {
        ToastUtils.show(context, msg);
    }

    //字符串判空
    public boolean isNotEmpty(CharSequence str) {
        return !TextUtils.isEmpty(str);
    }

    //EditText判空
    public boolean isEmpty(EditText editText) {
        if (editText == null) {
            throw new NullPointerException("EditText 对象为空");
        } else {
            return TextUtils.isEmpty(editText.getText().toString().trim());
        }
    }

    //获取控件EditText值
    public String getText(EditText editText) {
        if (editText == null) {
            throw new NullPointerException("EditText 对象为空");
        } else {
            return editText.getText().toString();
        }
    }

    //获取控件TextView值
    public String getText(TextView textView) {
        if(textView == null) {
            throw new NullPointerException("TextView 对象为空");
        } else {
            return textView.getText().toString();
        }
    }

    //获取控件TextView值-去掉前后空白
    public String getTextTrim(TextView textView) {
        if(textView == null) {
            throw new NullPointerException("TextView 对象为空");
        } else {
            return textView.getText().toString().trim();
        }
    }

    //启动Activity
    public void startActivity(Class<?> clazz) {
        this.startActivity(clazz, (Bundle) null, 0);
    }
    //启动Activity
    public void startActivityDelay(final Class<?> clazz) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(clazz, (Bundle) null, 0);
            }
        },300);

    }

    public void startActivity(Class<?> clazz, Bundle bundle) {
        this.startActivity(clazz, bundle, 0);
    }
    public void startActivityDelay(final Class<?> clazz, final Bundle bundle) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(clazz, bundle, 0);
            }
        },300);
    }

    public void startActivity(Class<?> clazz, int requestCode) {
        this.startActivity(clazz, (Bundle) null, requestCode);
    }

    public void startActivity(Class<?> clazz, Bundle bundle, int requestCode) {
        Intent intent = new Intent(this, clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }

        if (requestCode == 0) {
            this.startActivity(intent);
        } else {
            this.startActivityForResult(intent, requestCode);
        }
    }

    //网络检测
    public boolean noNetWork() {
        return !hasNetWork();
    }

    public boolean hasNetWork() {
        return NetUtils.isConnected(context);
    }

    public void showNetWorkError() {
        showToast("请检查您的网络!");
    }


}
