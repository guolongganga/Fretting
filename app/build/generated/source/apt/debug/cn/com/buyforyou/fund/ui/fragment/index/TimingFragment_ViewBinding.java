// Generated code from Butter Knife. Do not modify!
package cn.com.buyforyou.fund.ui.fragment.index;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import cn.com.buyforyou.fund.R;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TimingFragment_ViewBinding<T extends TimingFragment> implements Unbinder {
  protected T target;

  @UiThread
  public TimingFragment_ViewBinding(T target, View source) {
    this.target = target;

    target.tvRange = Utils.findRequiredViewAsType(source, R.id.tv_range, "field 'tvRange'", TextView.class);
    target.viewLine = Utils.findRequiredView(source, R.id.view_line, "field 'viewLine'");
    target.contentLayout = Utils.findRequiredViewAsType(source, R.id.content_layout, "field 'contentLayout'", XRecyclerContentLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.tvRange = null;
    target.viewLine = null;
    target.contentLayout = null;

    this.target = null;
  }
}
