// Generated code from Butter Knife. Do not modify!
package cn.com.buyforyou.fund.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.ui.DownloadProgressButton;
import java.lang.IllegalStateException;
import java.lang.Override;

public class UpdateAppActivity_ViewBinding<T extends UpdateAppActivity> implements Unbinder {
  protected T target;

  @UiThread
  public UpdateAppActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.cvText = Utils.findRequiredViewAsType(source, R.id.uodrade_tv_curVersion, "field 'cvText'", TextView.class);
    target.nvText = Utils.findRequiredViewAsType(source, R.id.uodrade_tv_newVersion, "field 'nvText'", TextView.class);
    target.desText = Utils.findRequiredViewAsType(source, R.id.uodrade_tv_content, "field 'desText'", TextView.class);
    target.progressButton = Utils.findRequiredViewAsType(source, R.id.upgrade_download, "field 'progressButton'", DownloadProgressButton.class);
    target.toInstallText = Utils.findRequiredViewAsType(source, R.id.upgrade_tv_install, "field 'toInstallText'", TextView.class);
    target.closeImage = Utils.findRequiredViewAsType(source, R.id.upgrade_close, "field 'closeImage'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.cvText = null;
    target.nvText = null;
    target.desText = null;
    target.progressButton = null;
    target.toInstallText = null;
    target.closeImage = null;

    this.target = null;
  }
}
