// Generated code from Butter Knife. Do not modify!
package cn.com.buyforyou.fund.ui.fragment.user;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import cn.com.buyforyou.fund.R;
import cn.droidlover.xrecyclerview.XRecyclerView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BonusModeFragment_ViewBinding<T extends BonusModeFragment> implements Unbinder {
  protected T target;

  @UiThread
  public BonusModeFragment_ViewBinding(T target, View source) {
    this.target = target;

    target.xrvMyRecyler = Utils.findRequiredViewAsType(source, R.id.xrv_my_recyler, "field 'xrvMyRecyler'", XRecyclerView.class);
    target.tvEmpty = Utils.findRequiredViewAsType(source, R.id.tv_empty, "field 'tvEmpty'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.xrvMyRecyler = null;
    target.tvEmpty = null;

    this.target = null;
  }
}
