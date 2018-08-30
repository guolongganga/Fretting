// Generated code from Butter Knife. Do not modify!
package cn.com.buyforyou.fund.ui.activity.user;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import cn.com.buyforyou.fund.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BankCardActivity_ViewBinding<T extends BankCardActivity> implements Unbinder {
  protected T target;

  @UiThread
  public BankCardActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.headBack = Utils.findRequiredViewAsType(source, R.id.head_back, "field 'headBack'", ImageButton.class);
    target.headTitle = Utils.findRequiredViewAsType(source, R.id.head_title, "field 'headTitle'", TextView.class);
    target.imageBanck = Utils.findRequiredViewAsType(source, R.id.image_banck, "field 'imageBanck'", ImageView.class);
    target.banckName = Utils.findRequiredViewAsType(source, R.id.banck_name, "field 'banckName'", TextView.class);
    target.tradeNumber = Utils.findRequiredViewAsType(source, R.id.trade_number, "field 'tradeNumber'", TextView.class);
    target.btnChange = Utils.findRequiredViewAsType(source, R.id.btn_change, "field 'btnChange'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.headBack = null;
    target.headTitle = null;
    target.imageBanck = null;
    target.banckName = null;
    target.tradeNumber = null;
    target.btnChange = null;

    this.target = null;
  }
}
