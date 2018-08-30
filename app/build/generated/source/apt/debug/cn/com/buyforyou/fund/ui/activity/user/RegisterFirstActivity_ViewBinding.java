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
import com.zhsoft.fretting.ui.widget.CountdownButton;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RegisterFirstActivity_ViewBinding<T extends RegisterFirstActivity> implements Unbinder {
  protected T target;

  @UiThread
  public RegisterFirstActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.headBack = Utils.findRequiredViewAsType(source, R.id.head_back, "field 'headBack'", ImageButton.class);
    target.headTitle = Utils.findRequiredViewAsType(source, R.id.head_title, "field 'headTitle'", TextView.class);
    target.getVerifyCode = Utils.findRequiredViewAsType(source, R.id.get_verify_code, "field 'getVerifyCode'", CountdownButton.class);
    target.phoneNumber = Utils.findRequiredViewAsType(source, R.id.phone_number, "field 'phoneNumber'", EditText.class);
    target.invite_code = Utils.findRequiredViewAsType(source, R.id.invite_code, "field 'invite_code'", EditText.class);
    target.password = Utils.findRequiredViewAsType(source, R.id.password, "field 'password'", EditText.class);
    target.passwordAgain = Utils.findRequiredViewAsType(source, R.id.password_again, "field 'passwordAgain'", EditText.class);
    target.msgCode = Utils.findRequiredViewAsType(source, R.id.msg_code, "field 'msgCode'", EditText.class);
    target.messageFail = Utils.findRequiredViewAsType(source, R.id.message_fail, "field 'messageFail'", TextView.class);
    target.btnNext = Utils.findRequiredViewAsType(source, R.id.btn_next, "field 'btnNext'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.headBack = null;
    target.headTitle = null;
    target.getVerifyCode = null;
    target.phoneNumber = null;
    target.invite_code = null;
    target.password = null;
    target.passwordAgain = null;
    target.msgCode = null;
    target.messageFail = null;
    target.btnNext = null;

    this.target = null;
  }
}
