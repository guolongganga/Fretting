// Generated code from Butter Knife. Do not modify!
package cn.com.buyforyou.fund.ui.adapter.user;

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

public class InvestRecordRecyleAdapter$ViewHolder_ViewBinding<T extends InvestRecordRecyleAdapter.ViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public InvestRecordRecyleAdapter$ViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.rlContent = Utils.findRequiredViewAsType(source, R.id.rl_content, "field 'rlContent'", RelativeLayout.class);
    target.tvDay = Utils.findRequiredViewAsType(source, R.id.tv_day, "field 'tvDay'", TextView.class);
    target.tvDayWeek = Utils.findRequiredViewAsType(source, R.id.tv_day_week, "field 'tvDayWeek'", TextView.class);
    target.tvMoney = Utils.findRequiredViewAsType(source, R.id.tv_money, "field 'tvMoney'", TextView.class);
    target.tvInvestStatus = Utils.findRequiredViewAsType(source, R.id.tv_invest_status, "field 'tvInvestStatus'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.rlContent = null;
    target.tvDay = null;
    target.tvDayWeek = null;
    target.tvMoney = null;
    target.tvInvestStatus = null;

    this.target = null;
  }
}
