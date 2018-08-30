// Generated code from Butter Knife. Do not modify!
package cn.com.buyforyou.fund.ui.activity.user;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import cn.com.buyforyou.fund.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ResultDetailTwoActivity_ViewBinding<T extends ResultDetailTwoActivity> implements Unbinder {
  protected T target;

  @UiThread
  public ResultDetailTwoActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.headBack = Utils.findRequiredViewAsType(source, R.id.head_back, "field 'headBack'", ImageButton.class);
    target.headTitle = Utils.findRequiredViewAsType(source, R.id.head_title, "field 'headTitle'", TextView.class);
    target.tvFundName = Utils.findRequiredViewAsType(source, R.id.tv_fund_name, "field 'tvFundName'", TextView.class);
    target.tvFundAmount = Utils.findRequiredViewAsType(source, R.id.tv_fund_amount, "field 'tvFundAmount'", TextView.class);
    target.tvBankName = Utils.findRequiredViewAsType(source, R.id.tv_bank_name, "field 'tvBankName'", TextView.class);
    target.tvTransactionStatus = Utils.findRequiredViewAsType(source, R.id.tv_transaction_status, "field 'tvTransactionStatus'", TextView.class);
    target.tvTransactionCause = Utils.findRequiredViewAsType(source, R.id.tv_transaction_cause, "field 'tvTransactionCause'", TextView.class);
    target.tvAllotNo = Utils.findRequiredViewAsType(source, R.id.tv_allot_no, "field 'tvAllotNo'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.headBack = null;
    target.headTitle = null;
    target.tvFundName = null;
    target.tvFundAmount = null;
    target.tvBankName = null;
    target.tvTransactionStatus = null;
    target.tvTransactionCause = null;
    target.tvAllotNo = null;

    this.target = null;
  }
}
