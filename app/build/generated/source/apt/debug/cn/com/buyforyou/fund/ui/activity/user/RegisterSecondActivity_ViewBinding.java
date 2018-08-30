// Generated code from Butter Knife. Do not modify!
package cn.com.buyforyou.fund.ui.activity.user;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import cn.com.buyforyou.fund.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RegisterSecondActivity_ViewBinding<T extends RegisterSecondActivity> implements Unbinder {
  protected T target;

  @UiThread
  public RegisterSecondActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.headBack = Utils.findRequiredViewAsType(source, R.id.head_back, "field 'headBack'", ImageButton.class);
    target.headTitle = Utils.findRequiredViewAsType(source, R.id.head_title, "field 'headTitle'", TextView.class);
    target.userName = Utils.findRequiredViewAsType(source, R.id.user_name, "field 'userName'", EditText.class);
    target.identity = Utils.findRequiredViewAsType(source, R.id.identity, "field 'identity'", EditText.class);
    target.email = Utils.findRequiredViewAsType(source, R.id.email, "field 'email'", EditText.class);
    target.banckName = Utils.findRequiredViewAsType(source, R.id.banck_name, "field 'banckName'", TextView.class);
    target.llChooseBank = Utils.findRequiredViewAsType(source, R.id.ll_choose_bank, "field 'llChooseBank'", LinearLayout.class);
    target.banknumber = Utils.findRequiredViewAsType(source, R.id.banknumber, "field 'banknumber'", EditText.class);
    target.phone = Utils.findRequiredViewAsType(source, R.id.phone, "field 'phone'", EditText.class);
    target.password = Utils.findRequiredViewAsType(source, R.id.password, "field 'password'", EditText.class);
    target.passwordAgain = Utils.findRequiredViewAsType(source, R.id.password_again, "field 'passwordAgain'", EditText.class);
    target.registerServiceSelect = Utils.findRequiredViewAsType(source, R.id.register_service_select, "field 'registerServiceSelect'", ImageView.class);
    target.registerService = Utils.findRequiredViewAsType(source, R.id.register_service, "field 'registerService'", TextView.class);
    target.btnSave = Utils.findRequiredViewAsType(source, R.id.btn_save, "field 'btnSave'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.headBack = null;
    target.headTitle = null;
    target.userName = null;
    target.identity = null;
    target.email = null;
    target.banckName = null;
    target.llChooseBank = null;
    target.banknumber = null;
    target.phone = null;
    target.password = null;
    target.passwordAgain = null;
    target.registerServiceSelect = null;
    target.registerService = null;
    target.btnSave = null;

    this.target = null;
  }
}
