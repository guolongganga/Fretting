// Generated code from Butter Knife. Do not modify!
package cn.com.buyforyou.fund.ui.activity.user;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import cn.com.buyforyou.fund.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ChangeTradePwdActivity_ViewBinding<T extends ChangeTradePwdActivity> implements Unbinder {
  protected T target;

  @UiThread
  public ChangeTradePwdActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.headBack = Utils.findRequiredViewAsType(source, R.id.head_back, "field 'headBack'", ImageButton.class);
    target.headTitle = Utils.findRequiredViewAsType(source, R.id.head_title, "field 'headTitle'", TextView.class);
    target.oldpassword = Utils.findRequiredViewAsType(source, R.id.oldpassword, "field 'oldpassword'", EditText.class);
    target.password = Utils.findRequiredViewAsType(source, R.id.password, "field 'password'", EditText.class);
    target.passwordAgain = Utils.findRequiredViewAsType(source, R.id.password_again, "field 'passwordAgain'", EditText.class);
    target.resetPassword = Utils.findRequiredViewAsType(source, R.id.reset_password, "field 'resetPassword'", TextView.class);
    target.btnSave = Utils.findRequiredViewAsType(source, R.id.btn_save, "field 'btnSave'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.headBack = null;
    target.headTitle = null;
    target.oldpassword = null;
    target.password = null;
    target.passwordAgain = null;
    target.resetPassword = null;
    target.btnSave = null;

    this.target = null;
  }
}
