// Generated code from Butter Knife. Do not modify!
package cn.com.buyforyou.fund.ui.adapter.user;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import cn.com.buyforyou.fund.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class UpdateBonusRecycleAdapter$ViewHolder_ViewBinding<T extends UpdateBonusRecycleAdapter.ViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public UpdateBonusRecycleAdapter$ViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.llContent = Utils.findRequiredViewAsType(source, R.id.ll_content, "field 'llContent'", LinearLayout.class);
    target.tvBonusType = Utils.findRequiredViewAsType(source, R.id.tv_bonus_type, "field 'tvBonusType'", TextView.class);
    target.tvFundName = Utils.findRequiredViewAsType(source, R.id.tv_fund_name, "field 'tvFundName'", TextView.class);
    target.tvFundCode = Utils.findRequiredViewAsType(source, R.id.tv_fund_code, "field 'tvFundCode'", TextView.class);
    target.ivArrow = Utils.findRequiredViewAsType(source, R.id.iv_arrow, "field 'ivArrow'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.llContent = null;
    target.tvBonusType = null;
    target.tvFundName = null;
    target.tvFundCode = null;
    target.ivArrow = null;

    this.target = null;
  }
}
