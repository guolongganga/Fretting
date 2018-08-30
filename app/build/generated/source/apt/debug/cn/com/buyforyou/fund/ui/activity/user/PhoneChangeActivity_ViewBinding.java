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

public class PhoneChangeActivity_ViewBinding<T extends PhoneChangeActivity> implements Unbinder {
  protected T target;

  @UiThread
  public PhoneChangeActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.headBack = Utils.findRequiredViewAsType(source, R.id.head_back, "field 'headBack'", ImageButton.class);
    target.headTitle = Utils.findRequiredViewAsType(source, R.id.head_title, "field 'headTitle'", TextView.class);
    target.phoneNumber = Utils.findRequiredViewAsType(source, R.id.phone_number, "field 'phoneNumber'", EditText.class);
    target.verifyCode = Utils.findRequiredViewAsType(source, R.id.verify_code, "field 'verifyCode'", EditText.class);
    target.getVerifyCode = Utils.findRequiredViewAsType(source, R.id.get_verify_code, "field 'getVerifyCode'", CountdownButton.class);
    target.btnSave = Utils.findRequiredViewAsType(source, R.id.btn_save, "field 'btnSave'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.headBack = null;
    target.headTitle = null;
    target.phoneNumber = null;
    target.verifyCode = null;
    target.getVerifyCode = null;
    target.btnSave = null;

    this.target = null;
  }
}
