// Generated code from Butter Knife. Do not modify!
package cn.com.buyforyou.fund.ui.adapter.user;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import cn.com.buyforyou.fund.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BankListAdapter$ViewHolder_ViewBinding<T extends BankListAdapter.ViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public BankListAdapter$ViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.bankImage = Utils.findRequiredViewAsType(source, R.id.bank_image, "field 'bankImage'", ImageView.class);
    target.bankName = Utils.findRequiredViewAsType(source, R.id.bank_name, "field 'bankName'", TextView.class);
    target.bankLimit = Utils.findRequiredViewAsType(source, R.id.bank_limit, "field 'bankLimit'", TextView.class);
    target.rlContent = Utils.findRequiredViewAsType(source, R.id.rl_content, "field 'rlContent'", RelativeLayout.class);
    target.viewLine = Utils.findRequiredView(source, R.id.view_line, "field 'viewLine'");
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.bankImage = null;
    target.bankName = null;
    target.bankLimit = null;
    target.rlContent = null;
    target.viewLine = null;

    this.target = null;
  }
}
