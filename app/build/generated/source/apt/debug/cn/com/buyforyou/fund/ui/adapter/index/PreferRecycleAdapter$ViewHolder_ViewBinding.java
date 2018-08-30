// Generated code from Butter Knife. Do not modify!
package cn.com.buyforyou.fund.ui.adapter.index;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import cn.com.buyforyou.fund.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PreferRecycleAdapter$ViewHolder_ViewBinding<T extends PreferRecycleAdapter.ViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public PreferRecycleAdapter$ViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.llContent = Utils.findRequiredViewAsType(source, R.id.ll_content, "field 'llContent'", LinearLayout.class);
    target.preferredName = Utils.findRequiredViewAsType(source, R.id.preferred_name, "field 'preferredName'", TextView.class);
    target.preferredRate = Utils.findRequiredViewAsType(source, R.id.preferred_rate, "field 'preferredRate'", TextView.class);
    target.tvRateDesc = Utils.findRequiredViewAsType(source, R.id.tv_rate_desc, "field 'tvRateDesc'", TextView.class);
    target.tvPercent = Utils.findRequiredViewAsType(source, R.id.tv_percent, "field 'tvPercent'", TextView.class);
    target.btnInvest = Utils.findRequiredViewAsType(source, R.id.btn_invest, "field 'btnInvest'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.llContent = null;
    target.preferredName = null;
    target.preferredRate = null;
    target.tvRateDesc = null;
    target.tvPercent = null;
    target.btnInvest = null;

    this.target = null;
  }
}
