// Generated code from Butter Knife. Do not modify!
package cn.com.buyforyou.fund.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import cn.com.buyforyou.fund.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_bac_ViewBinding<T extends MainActivity_bac> implements Unbinder {
  protected T target;

  @UiThread
  public MainActivity_bac_ViewBinding(T target, View source) {
    this.target = target;

    target.tvIndex = Utils.findRequiredViewAsType(source, R.id.tv_index, "field 'tvIndex'", TextView.class);
    target.tvFund = Utils.findRequiredViewAsType(source, R.id.tv_fund, "field 'tvFund'", TextView.class);
    target.tvUser = Utils.findRequiredViewAsType(source, R.id.tv_user, "field 'tvUser'", TextView.class);
    target.radioGroup = Utils.findRequiredViewAsType(source, R.id.main_bottom_rg, "field 'radioGroup'", RadioGroup.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.tvIndex = null;
    target.tvFund = null;
    target.tvUser = null;
    target.radioGroup = null;

    this.target = null;
  }
}
