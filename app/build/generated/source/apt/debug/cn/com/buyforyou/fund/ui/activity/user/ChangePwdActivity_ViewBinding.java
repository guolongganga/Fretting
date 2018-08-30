// Generated code from Butter Knife. Do not modify!
package cn.com.buyforyou.fund.ui.activity.user;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import cn.com.buyforyou.fund.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ChangePwdActivity_ViewBinding<T extends ChangePwdActivity> implements Unbinder {
  protected T target;

  @UiThread
  public ChangePwdActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.headBack = Utils.findRequiredViewAsType(source, R.id.head_back, "field 'headBack'", ImageButton.class);
    target.headTitle = Utils.findRequiredViewAsType(source, R.id.head_title, "field 'headTitle'", TextView.class);
    target.llChangepwdLogin = Utils.findRequiredViewAsType(source, R.id.ll_changepwd_login, "field 'llChangepwdLogin'", LinearLayout.class);
    target.llChangepwdTrade = Utils.findRequiredViewAsType(source, R.id.ll_changepwd_trade, "field 'llChangepwdTrade'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.headBack = null;
    target.headTitle = null;
    target.llChangepwdLogin = null;
    target.llChangepwdTrade = null;

    this.target = null;
  }
}
