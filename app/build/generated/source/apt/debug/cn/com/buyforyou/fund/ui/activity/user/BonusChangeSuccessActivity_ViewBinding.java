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

public class BonusChangeSuccessActivity_ViewBinding<T extends BonusChangeSuccessActivity> implements Unbinder {
  protected T target;

  @UiThread
  public BonusChangeSuccessActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.headBack = Utils.findRequiredViewAsType(source, R.id.head_back, "field 'headBack'", ImageButton.class);
    target.headTitle = Utils.findRequiredViewAsType(source, R.id.head_title, "field 'headTitle'", TextView.class);
    target.headRight = Utils.findRequiredViewAsType(source, R.id.head_right, "field 'headRight'", Button.class);
    target.headRightImgbtn = Utils.findRequiredViewAsType(source, R.id.head_right_imgbtn, "field 'headRightImgbtn'", ImageButton.class);
    target.btnSave = Utils.findRequiredViewAsType(source, R.id.btn_save, "field 'btnSave'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.headBack = null;
    target.headTitle = null;
    target.headRight = null;
    target.headRightImgbtn = null;
    target.btnSave = null;

    this.target = null;
  }
}
