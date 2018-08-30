// Generated code from Butter Knife. Do not modify!
package cn.com.buyforyou.fund.ui.activity.user;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import cn.com.buyforyou.fund.R;
import cn.droidlover.xrecyclerview.XRecyclerView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class InvestDeatilActivity_ViewBinding<T extends InvestDeatilActivity> implements Unbinder {
  protected T target;

  @UiThread
  public InvestDeatilActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.headBack = Utils.findRequiredViewAsType(source, R.id.head_back, "field 'headBack'", ImageButton.class);
    target.headTitle = Utils.findRequiredViewAsType(source, R.id.head_title, "field 'headTitle'", TextView.class);
    target.tvFundName = Utils.findRequiredViewAsType(source, R.id.tv_fund_name, "field 'tvFundName'", TextView.class);
    target.tvFundCode = Utils.findRequiredViewAsType(source, R.id.tv_fund_code, "field 'tvFundCode'", TextView.class);
    target.tvInvestStatus = Utils.findRequiredViewAsType(source, R.id.tv_invest_status, "field 'tvInvestStatus'", TextView.class);
    target.tvInvestDay = Utils.findRequiredViewAsType(source, R.id.tv_invest_day, "field 'tvInvestDay'", TextView.class);
    target.tvSum = Utils.findRequiredViewAsType(source, R.id.tv_sum, "field 'tvSum'", TextView.class);
    target.tvTotal = Utils.findRequiredViewAsType(source, R.id.tv_total, "field 'tvTotal'", TextView.class);
    target.tvStage = Utils.findRequiredViewAsType(source, R.id.tv_stage, "field 'tvStage'", TextView.class);
    target.tvBankName = Utils.findRequiredViewAsType(source, R.id.tv_bank_name, "field 'tvBankName'", TextView.class);
    target.tvBankTail = Utils.findRequiredViewAsType(source, R.id.tv_bank_tail, "field 'tvBankTail'", TextView.class);
    target.tvProtocolNumber = Utils.findRequiredViewAsType(source, R.id.tv_protocol_number, "field 'tvProtocolNumber'", TextView.class);
    target.tvDayWeek = Utils.findRequiredViewAsType(source, R.id.tv_day_week, "field 'tvDayWeek'", TextView.class);
    target.tvNextTime = Utils.findRequiredViewAsType(source, R.id.tv_next_time, "field 'tvNextTime'", TextView.class);
    target.tvNextTimeHint = Utils.findRequiredViewAsType(source, R.id.tv_next_time_hint, "field 'tvNextTimeHint'", TextView.class);
    target.xrvInvestRecord = Utils.findRequiredViewAsType(source, R.id.xrv_invest_record, "field 'xrvInvestRecord'", XRecyclerView.class);
    target.btnEnd = Utils.findRequiredViewAsType(source, R.id.btn_end, "field 'btnEnd'", Button.class);
    target.btnStop = Utils.findRequiredViewAsType(source, R.id.btn_stop, "field 'btnStop'", Button.class);
    target.btnUpdate = Utils.findRequiredViewAsType(source, R.id.btn_update, "field 'btnUpdate'", Button.class);
    target.btnRecovery = Utils.findRequiredViewAsType(source, R.id.btn_recovery, "field 'btnRecovery'", Button.class);
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
    target.tvInvestStatus = null;
    target.tvInvestDay = null;
    target.tvSum = null;
    target.tvTotal = null;
    target.tvStage = null;
    target.tvBankName = null;
    target.tvBankTail = null;
    target.tvProtocolNumber = null;
    target.tvDayWeek = null;
    target.tvNextTime = null;
    target.tvNextTimeHint = null;
    target.xrvInvestRecord = null;
    target.btnEnd = null;
    target.btnStop = null;
    target.btnUpdate = null;
    target.btnRecovery = null;

    this.target = null;
  }
}
