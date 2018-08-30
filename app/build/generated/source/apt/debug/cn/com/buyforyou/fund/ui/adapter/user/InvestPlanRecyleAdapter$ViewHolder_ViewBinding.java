// Generated code from Butter Knife. Do not modify!
package cn.com.buyforyou.fund.ui.adapter.user;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import cn.com.buyforyou.fund.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class InvestPlanRecyleAdapter$ViewHolder_ViewBinding<T extends InvestPlanRecyleAdapter.ViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public InvestPlanRecyleAdapter$ViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.rlContent = Utils.findRequiredViewAsType(source, R.id.rl_content, "field 'rlContent'", RelativeLayout.class);
    target.tvFundName = Utils.findRequiredViewAsType(source, R.id.tv_fund_name, "field 'tvFundName'", TextView.class);
    target.tvFundCode = Utils.findRequiredViewAsType(source, R.id.tv_fund_code, "field 'tvFundCode'", TextView.class);
    target.tvInvesType = Utils.findRequiredViewAsType(source, R.id.tv_invest_type, "field 'tvInvesType'", TextView.class);
    target.tvBankName = Utils.findRequiredViewAsType(source, R.id.tv_bank_name, "field 'tvBankName'", TextView.class);
    target.tvBankTail = Utils.findRequiredViewAsType(source, R.id.tv_bank_tail, "field 'tvBankTail'", TextView.class);
    target.tvNextTime = Utils.findRequiredViewAsType(source, R.id.tv_next_time, "field 'tvNextTime'", TextView.class);
    target.tvInvestStatus = Utils.findRequiredViewAsType(source, R.id.tv_invest_status, "field 'tvInvestStatus'", TextView.class);
    target.ivArrow = Utils.findRequiredViewAsType(source, R.id.iv_arrow, "field 'ivArrow'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.rlContent = null;
    target.tvFundName = null;
    target.tvFundCode = null;
    target.tvInvesType = null;
    target.tvBankName = null;
    target.tvBankTail = null;
    target.tvNextTime = null;
    target.tvInvestStatus = null;
    target.ivArrow = null;

    this.target = null;
  }
}
