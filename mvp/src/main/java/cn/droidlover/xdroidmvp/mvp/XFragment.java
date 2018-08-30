package cn.droidlover.xdroidmvp.mvp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.trello.rxlifecycle2.components.support.RxFragment;

import butterknife.Unbinder;
import cn.droidlover.xdroidmvp.XDroidConf;
import cn.droidlover.xdroidmvp.event.BusProvider;
import cn.droidlover.xdroidmvp.kit.KnifeKit;
import cn.droidlover.xdroidmvp.net.NetUtils;
import cn.droidlover.xdroidmvp.widget.ToastUtils;

/**
 * Created by wanglei on 2016/12/29.
 */

public abstract class XFragment<P extends IPresent> extends RxFragment implements IView<P> {

    private P p;
    protected Activity context;
    private View rootView;
    protected LayoutInflater layoutInflater;
    private RxPermissions rxPermissions;
    private Unbinder unbinder;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layoutInflater = inflater;
        if (rootView == null && getLayoutId() > 0) {
            rootView = inflater.inflate(getLayoutId(), null);
            bindUI(rootView);
        } else {
            ViewGroup viewGroup = (ViewGroup) rootView.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(rootView);
            }
        }
        return rootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (useEventBus()) {
            BusProvider.getBus().register(this);
        }
        bindEvent();
        initData(this.getArguments());
        initEvents();
    }

    @Override
    public void bindUI(View rootView) {
        unbinder = KnifeKit.bind(this, rootView);
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
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.context = (Activity) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        context = null;
    }

    @Override
    public boolean useEventBus() {
        return false;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (useEventBus()) {
            BusProvider.getBus().unregister(this);
        }
        if (getP() != null) {
            getP().detachV();
        }

        p = null;
    }

    protected RxPermissions getRxPermissions() {
        rxPermissions = new RxPermissions(getActivity());
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

    public void startActivity(Class<?> clazz, Bundle bundle) {
        this.startActivity(clazz, bundle, 0);
    }

    public void startActivity(Class<?> clazz, int requestCode) {
        this.startActivity(clazz, (Bundle) null, requestCode);
    }

    public void startActivity(Class<?> clazz, Bundle bundle, int requestCode) {
        Intent intent = new Intent(this.getActivity(), clazz);
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
