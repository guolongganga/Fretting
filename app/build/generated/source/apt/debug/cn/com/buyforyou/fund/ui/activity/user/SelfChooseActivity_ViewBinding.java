// Generated code from Butter Knife. Do not modify!
package cn.com.buyforyou.fund.ui.activity.user;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import cn.com.buyforyou.fund.R;
import cn.droidlover.xrecyclerview.XRecyclerView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SelfChooseActivity_ViewBinding<T extends SelfChooseActivity> implements Unbinder {
  protected T target;

  @UiThread
  public SelfChooseActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.ckNetWorth = Utils.findRequiredViewAsType(source, R.id.ck_networth, "field 'ckNetWorth'", CheckBox.class);
    target.ckRange = Utils.findRequiredViewAsType(source, R.id.ck_range, "field 'ckRange'", CheckBox.class);
    target.xrvMyRecyler = Utils.findRequiredViewAsType(source, R.id.xrv_my_recyler, "field 'xrvMyRecyler'", XRecyclerView.class);
    target.headBack = Utils.findRequiredViewAsType(source, R.id.head_back, "field 'headBack'", ImageButton.class);
    target.headTitle = Utils.findRequiredViewAsType(source, R.id.head_title, "field 'headTitle'", TextView.class);
    target.tvEmpty = Utils.findRequiredViewAsType(source, R.id.tv_empty, "field 'tvEmpty'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.ckNetWorth = null;
    target.ckRange = null;
    target.xrvMyRecyler = null;
    target.headBack = null;
    target.headTitle = null;
    target.tvEmpty = null;

    this.target = null;
  }
}
