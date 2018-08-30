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

public class InvestPlanActivity_ViewBinding<T extends InvestPlanActivity> implements Unbinder {
  protected T target;

  @UiThread
  public InvestPlanActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.headBack = Utils.findRequiredViewAsType(source, R.id.head_back, "field 'headBack'", ImageButton.class);
    target.headTitle = Utils.findRequiredViewAsType(source, R.id.head_title, "field 'headTitle'", TextView.class);
    target.xrvMyInvest = Utils.findRequiredViewAsType(source, R.id.xrv_my_invest, "field 'xrvMyInvest'", XRecyclerView.class);
    target.btnAddInvest = Utils.findRequiredViewAsType(source, R.id.btn_add_invest, "field 'btnAddInvest'", Button.class);
    target.tvmpty = Utils.findRequiredViewAsType(source, R.id.tv_empty, "field 'tvmpty'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.headBack = null;
    target.headTitle = null;
    target.xrvMyInvest = null;
    target.btnAddInvest = null;
    target.tvmpty = null;

    this.target = null;
  }
}
