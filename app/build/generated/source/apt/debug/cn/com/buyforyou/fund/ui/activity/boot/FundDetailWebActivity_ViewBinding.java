// Generated code from Butter Knife. Do not modify!
package cn.com.buyforyou.fund.ui.activity.boot;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import cn.com.buyforyou.fund.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FundDetailWebActivity_ViewBinding<T extends FundDetailWebActivity> implements Unbinder {
  protected T target;

  @UiThread
  public FundDetailWebActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.headBack = Utils.findRequiredViewAsType(source, R.id.head_back, "field 'headBack'", ImageButton.class);
    target.headTitle = Utils.findRequiredViewAsType(source, R.id.head_title, "field 'headTitle'", TextView.class);
    target.headRightImgbtn = Utils.findRequiredViewAsType(source, R.id.head_right_imgbtn, "field 'headRightImgbtn'", ImageButton.class);
    target.mWeb = Utils.findRequiredViewAsType(source, R.id.my_web, "field 'mWeb'", WebView.class);
    target.pb = Utils.findRequiredViewAsType(source, R.id.pb, "field 'pb'", ProgressBar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.headBack = null;
    target.headTitle = null;
    target.headRightImgbtn = null;
    target.mWeb = null;
    target.pb = null;

    this.target = null;
  }
}
