// Generated code from Butter Knife. Do not modify!
package cn.com.buyforyou.fund.ui.activity.boot;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import cn.com.buyforyou.fund.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class StartActivity_ViewBinding<T extends StartActivity> implements Unbinder {
  protected T target;

  @UiThread
  public StartActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.ivJump = Utils.findRequiredViewAsType(source, R.id.iv_jump, "field 'ivJump'", TextView.class);
    target.progressBar = Utils.findRequiredViewAsType(source, R.id.progress_bar, "field 'progressBar'", ProgressBar.class);
    target.ivStart = Utils.findRequiredViewAsType(source, R.id.iv_start, "field 'ivStart'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.ivJump = null;
    target.progressBar = null;
    target.ivStart = null;

    this.target = null;
  }
}
