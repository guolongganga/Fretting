// Generated code from Butter Knife. Do not modify!
package cn.com.buyforyou.fund.ui.fragment.fund;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import cn.com.buyforyou.fund.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FundFragment_ViewBinding<T extends FundFragment> implements Unbinder {
  protected T target;

  @UiThread
  public FundFragment_ViewBinding(T target, View source) {
    this.target = target;

    target.mTabLayout = Utils.findRequiredViewAsType(source, R.id.tab_layout, "field 'mTabLayout'", TabLayout.class);
    target.mViewPager = Utils.findRequiredViewAsType(source, R.id.view_pager, "field 'mViewPager'", ViewPager.class);
    target.rlNameSeach = Utils.findRequiredViewAsType(source, R.id.rl_name_search, "field 'rlNameSeach'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mTabLayout = null;
    target.mViewPager = null;
    target.rlNameSeach = null;

    this.target = null;
  }
}
