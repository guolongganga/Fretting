// Generated code from Butter Knife. Do not modify!
package cn.com.buyforyou.fund.ui.adapter.index;

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

public class TimingRecycleAdapter$ViewHolder_ViewBinding<T extends TimingRecycleAdapter.ViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public TimingRecycleAdapter$ViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.rlContent = Utils.findRequiredViewAsType(source, R.id.rl_content, "field 'rlContent'", RelativeLayout.class);
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tvName'", TextView.class);
    target.tvCode = Utils.findRequiredViewAsType(source, R.id.tv_code, "field 'tvCode'", TextView.class);
    target.tvRange = Utils.findRequiredViewAsType(source, R.id.tv_range, "field 'tvRange'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.rlContent = null;
    target.tvName = null;
    target.tvCode = null;
    target.tvRange = null;

    this.target = null;
  }
}
