// Generated code from Butter Knife. Do not modify!
package cn.com.buyforyou.fund.ui.activity.user;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import cn.com.buyforyou.fund.R;
import cn.droidlover.xrecyclerview.XRecyclerView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SwitchAccountActivity_ViewBinding<T extends SwitchAccountActivity> implements Unbinder {
  protected T target;

  @UiThread
  public SwitchAccountActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.rlBack = Utils.findRequiredViewAsType(source, R.id.rl_back, "field 'rlBack'", RelativeLayout.class);
    target.tvCompile = Utils.findRequiredViewAsType(source, R.id.tv_compile, "field 'tvCompile'", TextView.class);
    target.xRecyclerView = Utils.findRequiredViewAsType(source, R.id.xrv_account, "field 'xRecyclerView'", XRecyclerView.class);
    target.tvAddAccount = Utils.findRequiredViewAsType(source, R.id.tv_add_account, "field 'tvAddAccount'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.rlBack = null;
    target.tvCompile = null;
    target.xRecyclerView = null;
    target.tvAddAccount = null;

    this.target = null;
  }
}
