// Generated code from Butter Knife. Do not modify!
package cn.com.buyforyou.fund.ui.activity.fund;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import cn.com.buyforyou.fund.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class InvestActivity_ViewBinding<T extends InvestActivity> implements Unbinder {
  protected T target;

  @UiThread
  public InvestActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.headBack = Utils.findRequiredViewAsType(source, R.id.head_back, "field 'headBack'", ImageButton.class);
    target.headTitle = Utils.findRequiredViewAsType(source, R.id.head_title, "field 'headTitle'", TextView.class);
    target.bankImage = Utils.findRequiredViewAsType(source, R.id.bank_image, "field 'bankImage'", ImageView.class);
    target.bankName = Utils.findRequiredViewAsType(source, R.id.bank_name, "field 'bankName'", TextView.class);
    target.bankLimit = Utils.findRequiredViewAsType(source, R.id.bank_limit, "field 'bankLimit'", TextView.class);
    target.rlChange = Utils.findRequiredViewAsType(source, R.id.rl_change, "field 'rlChange'", RelativeLayout.class);
    target.etAmount = Utils.findRequiredViewAsType(source, R.id.et_amount, "field 'etAmount'", EditText.class);
    target.etInvestWeek = Utils.findRequiredViewAsType(source, R.id.tv_invest_week, "field 'etInvestWeek'", TextView.class);
    target.etInvestDay = Utils.findRequiredViewAsType(source, R.id.tv_invest_day, "field 'etInvestDay'", TextView.class);
    target.tvNextTime = Utils.findRequiredViewAsType(source, R.id.tv_next_time, "field 'tvNextTime'", TextView.class);
    target.sure = Utils.findRequiredViewAsType(source, R.id.sure, "field 'sure'", Button.class);
    target.registerServiceSelect = Utils.findRequiredViewAsType(source, R.id.register_service_select, "field 'registerServiceSelect'", ImageView.class);
    target.tvAgreement = Utils.findRequiredViewAsType(source, R.id.tv_agreement, "field 'tvAgreement'", TextView.class);
    target.rlInvestDay = Utils.findRequiredViewAsType(source, R.id.rl_invest_day, "field 'rlInvestDay'", RelativeLayout.class);
    target.rlInvestWeek = Utils.findRequiredViewAsType(source, R.id.rl_invest_week, "field 'rlInvestWeek'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.headBack = null;
    target.headTitle = null;
    target.bankImage = null;
    target.bankName = null;
    target.bankLimit = null;
    target.rlChange = null;
    target.etAmount = null;
    target.etInvestWeek = null;
    target.etInvestDay = null;
    target.tvNextTime = null;
    target.sure = null;
    target.registerServiceSelect = null;
    target.tvAgreement = null;
    target.rlInvestDay = null;
    target.rlInvestWeek = null;

    this.target = null;
  }
}
