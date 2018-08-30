// Generated code from Butter Knife. Do not modify!
package com.zhsoft.fretting.ui.widget;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import cn.com.buyforyou.fund.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TitleView_ViewBinding<T extends TitleView> implements Unbinder {
  protected T target;

  @UiThread
  public TitleView_ViewBinding(T target, View source) {
    this.target = target;

    target.rlBack = Utils.findRequiredViewAsType(source, R.id.rl_back, "field 'rlBack'", RelativeLayout.class);
    target.titleName = Utils.findRequiredViewAsType(source, R.id.title_name, "field 'titleName'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.rlBack = null;
    target.titleName = null;

    this.target = null;
  }
}
