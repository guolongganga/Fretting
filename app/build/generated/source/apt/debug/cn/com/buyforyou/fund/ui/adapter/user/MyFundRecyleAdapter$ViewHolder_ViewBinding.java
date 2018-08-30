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

public class MyFundRecyleAdapter$ViewHolder_ViewBinding<T extends MyFundRecyleAdapter.ViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public MyFundRecyleAdapter$ViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.llContent = Utils.findRequiredViewAsType(source, R.id.ll_content, "field 'llContent'", LinearLayout.class);
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tvName'", TextView.class);
    target.tvMoney = Utils.findRequiredViewAsType(source, R.id.tv_money, "field 'tvMoney'", TextView.class);
    target.tvYesterday = Utils.findRequiredViewAsType(source, R.id.tv_yesterday, "field 'tvYesterday'", TextView.class);
    target.tvHold = Utils.findRequiredViewAsType(source, R.id.tv_hold, "field 'tvHold'", TextView.class);
    target.viewLine = Utils.findRequiredView(source, R.id.view_line, "field 'viewLine'");
    target.llNumberIng = Utils.findRequiredViewAsType(source, R.id.ll_number_ing, "field 'llNumberIng'", LinearLayout.class);
    target.tvNumberIng = Utils.findRequiredViewAsType(source, R.id.tv_number_ing, "field 'tvNumberIng'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.llContent = null;
    target.tvName = null;
    target.tvMoney = null;
    target.tvYesterday = null;
    target.tvHold = null;
    target.viewLine = null;
    target.llNumberIng = null;
    target.tvNumberIng = null;

    this.target = null;
  }
}
