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
import java.lang.IllegalStateException;
import java.lang.Override;

public class RegisterSuccessActivity_ViewBinding<T extends RegisterSuccessActivity> implements Unbinder {
  protected T target;

  @UiThread
  public RegisterSuccessActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.headBack = Utils.findRequiredViewAsType(source, R.id.head_back, "field 'headBack'", ImageButton.class);
    target.headTitle = Utils.findRequiredViewAsType(source, R.id.head_title, "field 'headTitle'", TextView.class);
    target.name = Utils.findRequiredViewAsType(source, R.id.name, "field 'name'", TextView.class);
    target.identity = Utils.findRequiredViewAsType(source, R.id.identity, "field 'identity'", TextView.class);
    target.btnFinish = Utils.findRequiredViewAsType(source, R.id.btn_finish, "field 'btnFinish'", Button.class);
    target.riskTest = Utils.findRequiredViewAsType(source, R.id.risk_test, "field 'riskTest'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.headBack = null;
    target.headTitle = null;
    target.name = null;
    target.identity = null;
    target.btnFinish = null;
    target.riskTest = null;

    this.target = null;
  }
}
