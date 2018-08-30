// Generated code from Butter Knife. Do not modify!
package cn.com.buyforyou.fund.ui.activity.user;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import cn.com.buyforyou.fund.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ResultDetailThreeActivity_ViewBinding<T extends ResultDetailThreeActivity> implements Unbinder {
  protected T target;

  @UiThread
  public ResultDetailThreeActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.headBack = Utils.findRequiredViewAsType(source, R.id.head_back, "field 'headBack'", ImageButton.class);
    target.headTitle = Utils.findRequiredViewAsType(source, R.id.head_title, "field 'headTitle'", TextView.class);
    target.tvFundName = Utils.findRequiredViewAsType(source, R.id.tv_fund_name, "field 'tvFundName'", TextView.class);
    target.tvFundAmount = Utils.findRequiredViewAsType(source, R.id.tv_fund_amount, "field 'tvFundAmount'", TextView.class);
    target.tvBankName = Utils.findRequiredViewAsType(source, R.id.tv_bank_name, "field 'tvBankName'", TextView.class);
    target.ivPaySuccess = Utils.findRequiredViewAsType(source, R.id.iv_pay_success, "field 'ivPaySuccess'", ImageView.class);
    target.fontPaySuccess = Utils.findRequiredViewAsType(source, R.id.font_pay_success, "field 'fontPaySuccess'", TextView.class);
    target.tvPaySuccess = Utils.findRequiredViewAsType(source, R.id.tv_pay_success, "field 'tvPaySuccess'", TextView.class);
    target.ivSureNumber = Utils.findRequiredViewAsType(source, R.id.iv_sure_number, "field 'ivSureNumber'", ImageView.class);
    target.tvSureNumber = Utils.findRequiredViewAsType(source, R.id.tv_sure_number, "field 'tvSureNumber'", TextView.class);
    target.fontSureNumber = Utils.findRequiredViewAsType(source, R.id.font_sure_number, "field 'fontSureNumber'", TextView.class);
    target.ivQueryIncome = Utils.findRequiredViewAsType(source, R.id.iv_query_income, "field 'ivQueryIncome'", ImageView.class);
    target.tvQueryIncome = Utils.findRequiredViewAsType(source, R.id.tv_query_income, "field 'tvQueryIncome'", TextView.class);
    target.fontQueryIncome = Utils.findRequiredViewAsType(source, R.id.font_query_income, "field 'fontQueryIncome'", TextView.class);
    target.llProgressInfo = Utils.findRequiredViewAsType(source, R.id.ll_progress_info, "field 'llProgressInfo'", LinearLayout.class);
    target.tvSureAmount = Utils.findRequiredViewAsType(source, R.id.tv_sure_amount, "field 'tvSureAmount'", TextView.class);
    target.tvSureShare = Utils.findRequiredViewAsType(source, R.id.tv_sure_share, "field 'tvSureShare'", TextView.class);
    target.tvSureRate = Utils.findRequiredViewAsType(source, R.id.tv_sure_rate, "field 'tvSureRate'", TextView.class);
    target.tvSureDate = Utils.findRequiredViewAsType(source, R.id.tv_sure_date, "field 'tvSureDate'", TextView.class);
    target.tvFeeSx = Utils.findRequiredViewAsType(source, R.id.tv_fee_sx, "field 'tvFeeSx'", TextView.class);
    target.llSureInfo = Utils.findRequiredViewAsType(source, R.id.ll_sure_info, "field 'llSureInfo'", LinearLayout.class);
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
    target.ivPaySuccess = null;
    target.fontPaySuccess = null;
    target.tvPaySuccess = null;
    target.ivSureNumber = null;
    target.tvSureNumber = null;
    target.fontSureNumber = null;
    target.ivQueryIncome = null;
    target.tvQueryIncome = null;
    target.fontQueryIncome = null;
    target.llProgressInfo = null;
    target.tvSureAmount = null;
    target.tvSureShare = null;
    target.tvSureRate = null;
    target.tvSureDate = null;
    target.tvFeeSx = null;
    target.llSureInfo = null;
    target.tvAllotNo = null;

    this.target = null;
  }
}
