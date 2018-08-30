// Generated code from Butter Knife. Do not modify!
package cn.com.buyforyou.fund.ui.activity.user;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import cn.com.buyforyou.fund.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BonusChangeActivity_ViewBinding<T extends BonusChangeActivity> implements Unbinder {
  protected T target;

  @UiThread
  public BonusChangeActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.headBack = Utils.findRequiredViewAsType(source, R.id.head_back, "field 'headBack'", ImageButton.class);
    target.headTitle = Utils.findRequiredViewAsType(source, R.id.head_title, "field 'headTitle'", TextView.class);
    target.tvFundName = Utils.findRequiredViewAsType(source, R.id.tv_fund_name, "field 'tvFundName'", TextView.class);
    target.tvFundCode = Utils.findRequiredViewAsType(source, R.id.tv_fund_code, "field 'tvFundCode'", TextView.class);
    target.rbAgain = Utils.findRequiredViewAsType(source, R.id.rb_again, "field 'rbAgain'", RadioButton.class);
    target.rbCarsh = Utils.findRequiredViewAsType(source, R.id.rb_carsh, "field 'rbCarsh'", RadioButton.class);
    target.password = Utils.findRequiredViewAsType(source, R.id.password, "field 'password'", EditText.class);
    target.btnSave = Utils.findRequiredViewAsType(source, R.id.btn_save, "field 'btnSave'", Button.class);
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
    target.rbAgain = null;
    target.rbCarsh = null;
    target.password = null;
    target.btnSave = null;

    this.target = null;
  }
}
