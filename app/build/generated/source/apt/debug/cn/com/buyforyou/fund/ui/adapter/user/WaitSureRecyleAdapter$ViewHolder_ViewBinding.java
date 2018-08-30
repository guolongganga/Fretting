// Generated code from Butter Knife. Do not modify!
package cn.com.buyforyou.fund.ui.adapter.user;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import cn.com.buyforyou.fund.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class WaitSureRecyleAdapter$ViewHolder_ViewBinding<T extends WaitSureRecyleAdapter.ViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public WaitSureRecyleAdapter$ViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.llContent = Utils.findRequiredViewAsType(source, R.id.ll_content, "field 'llContent'", LinearLayout.class);
    target.tvFundName = Utils.findRequiredViewAsType(source, R.id.tv_fund_name, "field 'tvFundName'", TextView.class);
    target.tvAmount = Utils.findRequiredViewAsType(source, R.id.tv_amount, "field 'tvAmount'", TextView.class);
    target.tvTime = Utils.findRequiredViewAsType(source, R.id.tv_time, "field 'tvTime'", TextView.class);
    target.tvType = Utils.findRequiredViewAsType(source, R.id.tv_type, "field 'tvType'", TextView.class);
    target.tvPaystatus = Utils.findRequiredViewAsType(source, R.id.tv_paystatus, "field 'tvPaystatus'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.llContent = null;
    target.tvFundName = null;
    target.tvAmount = null;
    target.tvTime = null;
    target.tvType = null;
    target.tvPaystatus = null;

    this.target = null;
  }
}
