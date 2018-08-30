// Generated code from Butter Knife. Do not modify!
package cn.com.buyforyou.fund.ui.fragment.user;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import cn.com.buyforyou.fund.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class UserFragment_ViewBinding<T extends UserFragment> implements Unbinder {
  protected T target;

  @UiThread
  public UserFragment_ViewBinding(T target, View source) {
    this.target = target;

    target.headBack = Utils.findRequiredViewAsType(source, R.id.head_back, "field 'headBack'", ImageButton.class);
    target.headTitle = Utils.findRequiredViewAsType(source, R.id.head_title, "field 'headTitle'", TextView.class);
    target.headRight = Utils.findRequiredViewAsType(source, R.id.head_right, "field 'headRight'", Button.class);
    target.tvTotalAssets = Utils.findRequiredViewAsType(source, R.id.tv_total_assets, "field 'tvTotalAssets'", TextView.class);
    target.tvYesterdayIncome = Utils.findRequiredViewAsType(source, R.id.tv_yesterday_income, "field 'tvYesterdayIncome'", TextView.class);
    target.tvTimeAccumlate = Utils.findRequiredViewAsType(source, R.id.tv_time_accumlate, "field 'tvTimeAccumlate'", TextView.class);
    target.tvAccumulateEarn = Utils.findRequiredViewAsType(source, R.id.tv_accumulate_earn, "field 'tvAccumulateEarn'", TextView.class);
    target.tvPassage = Utils.findRequiredViewAsType(source, R.id.tv_passage, "field 'tvPassage'", TextView.class);
    target.tvTime = Utils.findRequiredViewAsType(source, R.id.tv_time, "field 'tvTime'", TextView.class);
    target.login = Utils.findRequiredViewAsType(source, R.id.login, "field 'login'", Button.class);
    target.register = Utils.findRequiredViewAsType(source, R.id.register, "field 'register'", Button.class);
    target.selfChoose = Utils.findRequiredViewAsType(source, R.id.self_choose, "field 'selfChoose'", TextView.class);
    target.timing = Utils.findRequiredViewAsType(source, R.id.timing, "field 'timing'", TextView.class);
    target.transaction = Utils.findRequiredViewAsType(source, R.id.transaction, "field 'transaction'", TextView.class);
    target.profit = Utils.findRequiredViewAsType(source, R.id.profit, "field 'profit'", TextView.class);
    target.remove = Utils.findRequiredViewAsType(source, R.id.remove, "field 'remove'", TextView.class);
    target.llLogout = Utils.findRequiredViewAsType(source, R.id.ll_logout, "field 'llLogout'", LinearLayout.class);
    target.llFundContent = Utils.findRequiredViewAsType(source, R.id.ll_fund_content, "field 'llFundContent'", RelativeLayout.class);
    target.toFinishRegister = Utils.findRequiredViewAsType(source, R.id.to_finish_register, "field 'toFinishRegister'", Button.class);
    target.mTabLayout = Utils.findRequiredViewAsType(source, R.id.tab_layout, "field 'mTabLayout'", TabLayout.class);
    target.mViewPager = Utils.findRequiredViewAsType(source, R.id.view_pager, "field 'mViewPager'", ViewPager.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.headBack = null;
    target.headTitle = null;
    target.headRight = null;
    target.tvTotalAssets = null;
    target.tvYesterdayIncome = null;
    target.tvTimeAccumlate = null;
    target.tvAccumulateEarn = null;
    target.tvPassage = null;
    target.tvTime = null;
    target.login = null;
    target.register = null;
    target.selfChoose = null;
    target.timing = null;
    target.transaction = null;
    target.profit = null;
    target.remove = null;
    target.llLogout = null;
    target.llFundContent = null;
    target.toFinishRegister = null;
    target.mTabLayout = null;
    target.mViewPager = null;

    this.target = null;
  }
}
