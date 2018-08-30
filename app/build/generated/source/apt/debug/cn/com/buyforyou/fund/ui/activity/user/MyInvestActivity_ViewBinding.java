// Generated code from Butter Knife. Do not modify!
package cn.com.buyforyou.fund.ui.activity.user;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import cn.com.buyforyou.fund.R;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MyInvestActivity_ViewBinding<T extends MyInvestActivity> implements Unbinder {
  protected T target;

  @UiThread
  public MyInvestActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.headBack = Utils.findRequiredViewAsType(source, R.id.head_back, "field 'headBack'", ImageButton.class);
    target.headTitle = Utils.findRequiredViewAsType(source, R.id.head_title, "field 'headTitle'", TextView.class);
    target.rlSelector = Utils.findRequiredViewAsType(source, R.id.rl_selector, "field 'rlSelector'", RelativeLayout.class);
    target.tvFund = Utils.findRequiredViewAsType(source, R.id.tv_fund, "field 'tvFund'", TextView.class);
    target.tvRange = Utils.findRequiredViewAsType(source, R.id.tv_range, "field 'tvRange'", TextView.class);
    target.contentLayout = Utils.findRequiredViewAsType(source, R.id.content_layout, "field 'contentLayout'", XRecyclerContentLayout.class);
    target.btnAddInvest = Utils.findRequiredViewAsType(source, R.id.btn_add_invest, "field 'btnAddInvest'", Button.class);
    target.viewLine = Utils.findRequiredView(source, R.id.view_line, "field 'viewLine'");
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.headBack = null;
    target.headTitle = null;
    target.rlSelector = null;
    target.tvFund = null;
    target.tvRange = null;
    target.contentLayout = null;
    target.btnAddInvest = null;
    target.viewLine = null;

    this.target = null;
  }
}
