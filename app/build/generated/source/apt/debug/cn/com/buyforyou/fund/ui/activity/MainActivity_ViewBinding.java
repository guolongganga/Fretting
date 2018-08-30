// Generated code from Butter Knife. Do not modify!
package cn.com.buyforyou.fund.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import cn.com.buyforyou.fund.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding<T extends MainActivity> implements Unbinder {
  protected T target;

  @UiThread
  public MainActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.radioGroup = Utils.findRequiredViewAsType(source, R.id.main_bottom_rg, "field 'radioGroup'", RadioGroup.class);
    target.radioButtonIndex = Utils.findRequiredViewAsType(source, R.id.main_bottom_index, "field 'radioButtonIndex'", RadioButton.class);
    target.radioButtonFund = Utils.findRequiredViewAsType(source, R.id.main_bottom_fund, "field 'radioButtonFund'", RadioButton.class);
    target.radioButtonMine = Utils.findRequiredViewAsType(source, R.id.main_bottom_mine, "field 'radioButtonMine'", RadioButton.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.radioGroup = null;
    target.radioButtonIndex = null;
    target.radioButtonFund = null;
    target.radioButtonMine = null;

    this.target = null;
  }
}
