// Generated code from Butter Knife. Do not modify!
package cn.com.buyforyou.fund.ui.activity.fund;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import cn.com.buyforyou.fund.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class InvestSuccessActivity_ViewBinding<T extends InvestSuccessActivity> implements Unbinder {
  protected T target;

  @UiThread
  public InvestSuccessActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.headBack = Utils.findRequiredViewAsType(source, R.id.head_back, "field 'headBack'", ImageButton.class);
    target.headTitle = Utils.findRequiredViewAsType(source, R.id.head_title, "field 'headTitle'", TextView.class);
    target.tvFundName = Utils.findRequiredViewAsType(source, R.id.tv_fund_name, "field 'tvFundName'", TextView.class);
    target.tvFundCode = Utils.findRequiredViewAsType(source, R.id.tv_fund_code, "field 'tvFundCode'", TextView.class);
    target.tvDay = Utils.findRequiredViewAsType(source, R.id.tv_day, "field 'tvDay'", TextView.class);
    target.tvFundAmount = Utils.findRequiredViewAsType(source, R.id.tv_fund_amount, "field 'tvFundAmount'", TextView.class);
    target.tvBankName = Utils.findRequiredViewAsType(source, R.id.tv_bank_name, "field 'tvBankName'", TextView.class);
    target.tvLastNumber = Utils.findRequiredViewAsType(source, R.id.tv_last_number, "field 'tvLastNumber'", TextView.class);
    target.tvDayWeek = Utils.findRequiredViewAsType(source, R.id.tv_day_week, "field 'tvDayWeek'", TextView.class);
    target.sure = Utils.findRequiredViewAsType(source, R.id.sure, "field 'sure'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.headBack = null;
    target.headTitle = null;
    target.tvFundName = null;
    target.tvFundCode = null;
    target.tvDay = null;
    target.tvFundAmount = null;
    target.tvBankName = null;
    target.tvLastNumber = null;
    target.tvDayWeek = null;
    target.sure = null;

    this.target = null;
  }
}
