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

public class PopularityRecycleAdapter$ViewHolder_ViewBinding<T extends PopularityRecycleAdapter.ViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public PopularityRecycleAdapter$ViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.rlContent = Utils.findRequiredViewAsType(source, R.id.rl_content, "field 'rlContent'", RelativeLayout.class);
    target.tvValue = Utils.findRequiredViewAsType(source, R.id.tv_value, "field 'tvValue'", TextView.class);
    target.tvDescribe = Utils.findRequiredViewAsType(source, R.id.tv_describe, "field 'tvDescribe'", TextView.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.tvLocationOne = Utils.findRequiredViewAsType(source, R.id.tv_location_one, "field 'tvLocationOne'", TextView.class);
    target.tvLocationTwo = Utils.findRequiredViewAsType(source, R.id.tv_location_two, "field 'tvLocationTwo'", TextView.class);
    target.viewLine = Utils.findRequiredView(source, R.id.view_line, "field 'viewLine'");
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.rlContent = null;
    target.tvValue = null;
    target.tvDescribe = null;
    target.tvTitle = null;
    target.tvLocationOne = null;
    target.tvLocationTwo = null;
    target.viewLine = null;

    this.target = null;
  }
}
