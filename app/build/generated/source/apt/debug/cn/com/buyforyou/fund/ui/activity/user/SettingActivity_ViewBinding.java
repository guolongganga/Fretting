// Generated code from Butter Knife. Do not modify!
package cn.com.buyforyou.fund.ui.activity.user;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import cn.com.buyforyou.fund.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SettingActivity_ViewBinding<T extends SettingActivity> implements Unbinder {
  protected T target;

  @UiThread
  public SettingActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.headBack = Utils.findRequiredViewAsType(source, R.id.head_back, "field 'headBack'", ImageButton.class);
    target.headTitle = Utils.findRequiredViewAsType(source, R.id.head_title, "field 'headTitle'", TextView.class);
    target.personinfo = Utils.findRequiredViewAsType(source, R.id.personinfo, "field 'personinfo'", TextView.class);
    target.phone = Utils.findRequiredViewAsType(source, R.id.phone, "field 'phone'", TextView.class);
    target.tvRiskGrade = Utils.findRequiredViewAsType(source, R.id.tv_risk_grade, "field 'tvRiskGrade'", TextView.class);
    target.passwordManager = Utils.findRequiredViewAsType(source, R.id.password_manager, "field 'passwordManager'", TextView.class);
    target.bankcard = Utils.findRequiredViewAsType(source, R.id.bankcard, "field 'bankcard'", TextView.class);
    target.riskTest = Utils.findRequiredViewAsType(source, R.id.risk_test, "field 'riskTest'", LinearLayout.class);
    target.callAgent = Utils.findRequiredViewAsType(source, R.id.call_agent, "field 'callAgent'", TextView.class);
    target.aboutUs = Utils.findRequiredViewAsType(source, R.id.about_us, "field 'aboutUs'", TextView.class);
    target.exit = Utils.findRequiredViewAsType(source, R.id.exit, "field 'exit'", Button.class);
    target.inviteCode = Utils.findRequiredViewAsType(source, R.id.invite_code, "field 'inviteCode'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.headBack = null;
    target.headTitle = null;
    target.personinfo = null;
    target.phone = null;
    target.tvRiskGrade = null;
    target.passwordManager = null;
    target.bankcard = null;
    target.riskTest = null;
    target.callAgent = null;
    target.aboutUs = null;
    target.exit = null;
    target.inviteCode = null;

    this.target = null;
  }
}
