// Generated code from Butter Knife. Do not modify!
package cn.com.buyforyou.fund.ui.activity.boot;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import cn.com.buyforyou.fund.R;
import com.zhsoft.fretting.ui.widget.InkPageIndicator;
import java.lang.IllegalStateException;
import java.lang.Override;

public class WelcomeActivity_ViewBinding<T extends WelcomeActivity> implements Unbinder {
  protected T target;

  @UiThread
  public WelcomeActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.mViewPager = Utils.findRequiredViewAsType(source, R.id.view_pager, "field 'mViewPager'", ViewPager.class);
    target.indicator = Utils.findRequiredViewAsType(source, R.id.indicator, "field 'indicator'", InkPageIndicator.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mViewPager = null;
    target.indicator = null;

    this.target = null;
  }
}
