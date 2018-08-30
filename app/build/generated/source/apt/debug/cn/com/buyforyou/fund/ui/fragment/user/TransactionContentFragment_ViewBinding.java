// Generated code from Butter Knife. Do not modify!
package cn.com.buyforyou.fund.ui.fragment.user;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import cn.com.buyforyou.fund.R;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TransactionContentFragment_ViewBinding<T extends TransactionContentFragment> implements Unbinder {
  protected T target;

  @UiThread
  public TransactionContentFragment_ViewBinding(T target, View source) {
    this.target = target;

    target.contentLayout = Utils.findRequiredViewAsType(source, R.id.content_layout, "field 'contentLayout'", XRecyclerContentLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.contentLayout = null;

    this.target = null;
  }
}
