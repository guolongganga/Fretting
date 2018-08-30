// Generated code from Butter Knife. Do not modify!
package com.zhsoft.fretting.ui.widget;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import cn.com.buyforyou.fund.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class StateView_ViewBinding<T extends StateView> implements Unbinder {
  protected T target;

  @UiThread
  public StateView_ViewBinding(T target, View source) {
    this.target = target;

    target.tvMsg = Utils.findRequiredViewAsType(source, R.id.tv_msg, "field 'tvMsg'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.tvMsg = null;

    this.target = null;
  }
}
