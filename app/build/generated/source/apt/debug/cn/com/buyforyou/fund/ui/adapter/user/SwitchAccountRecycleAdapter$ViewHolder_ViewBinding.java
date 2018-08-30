// Generated code from Butter Knife. Do not modify!
package cn.com.buyforyou.fund.ui.adapter.user;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import cn.com.buyforyou.fund.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SwitchAccountRecycleAdapter$ViewHolder_ViewBinding<T extends SwitchAccountRecycleAdapter.ViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public SwitchAccountRecycleAdapter$ViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.llContent = Utils.findRequiredViewAsType(source, R.id.ll_content, "field 'llContent'", LinearLayout.class);
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tvName'", TextView.class);
    target.ivSelector = Utils.findRequiredViewAsType(source, R.id.iv_selector, "field 'ivSelector'", ImageView.class);
    target.tvDelete = Utils.findRequiredViewAsType(source, R.id.tv_delete, "field 'tvDelete'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.llContent = null;
    target.tvName = null;
    target.ivSelector = null;
    target.tvDelete = null;

    this.target = null;
  }
}
