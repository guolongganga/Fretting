// Generated code from Butter Knife. Do not modify!
package cn.com.buyforyou.fund.ui.activity.user;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import cn.com.buyforyou.fund.R;
import com.zhsoft.fretting.ui.widget.CountdownButton;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BankCardChangeActivity_ViewBinding<T extends BankCardChangeActivity> implements Unbinder {
  protected T target;

  @UiThread
  public BankCardChangeActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.headBack = Utils.findRequiredViewAsType(source, R.id.head_back, "field 'headBack'", ImageButton.class);
    target.headTitle = Utils.findRequiredViewAsType(source, R.id.head_title, "field 'headTitle'", TextView.class);
    target.llChooseBank = Utils.findRequiredViewAsType(source, R.id.ll_choose_bank, "field 'llChooseBank'", LinearLayout.class);
    target.bankName = Utils.findRequiredViewAsType(source, R.id.banck_name, "field 'bankName'", TextView.class);
    target.banknumber = Utils.findRequiredViewAsType(source, R.id.banknumber, "field 'banknumber'", EditText.class);
    target.phone = Utils.findRequiredViewAsType(source, R.id.phone, "field 'phone'", EditText.class);
    target.msgCode = Utils.findRequiredViewAsType(source, R.id.msg_code, "field 'msgCode'", EditText.class);
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
    target.llChooseBank = null;
    target.bankName = null;
    target.banknumber = null;
    target.phone = null;
    target.msgCode = null;
    target.getVerifyCode = null;
    target.btnSave = null;

    this.target = null;
  }
}
