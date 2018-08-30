package cn.droidlover.xdroidmvp.mvp;

import android.os.Bundle;
import android.view.View;

/**
 * Created by wanglei on 2016/12/29.
 */

public interface IView<P> {
    void bindUI(View rootView);

    int getLayoutId();

    P newP();

    void bindEvent();

    void initData(Bundle lbundle);

    void initEvents();

    int getOptionsMenuId();

    boolean useEventBus();

}
