// Generated code from Butter Knife. Do not modify!
package cn.com.buyforyou.fund.ui.adapter.boot;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import cn.com.buyforyou.fund.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SearchRecycleAdapter$ViewHolder_ViewBinding<T extends SearchRecycleAdapter.ViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public SearchRecycleAdapter$ViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.rlContent = Utils.findRequiredViewAsType(source, R.id.rl_content, "field 'rlContent'", RelativeLayout.class);
    target.tvFundName = Utils.findRequiredViewAsType(source, R.id.tv_fund_name, "field 'tvFundName'", TextView.class);
    target.tvFundRate = Utils.findRequiredViewAsType(source, R.id.tv_fund_rate, "field 'tvFundRate'", TextView.class);
    target.tvFundCode = Utils.findRequiredViewAsType(source, R.id.tv_fund_code, "field 'tvFundCode'", TextView.class);
    target.tvFundPerTime = Utils.findRequiredViewAsType(source, R.id.tv_fund_per_time, "field 'tvFundPerTime'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.rlContent = null;
    target.tvFundName = null;
    target.tvFundRate = null;
    target.tvFundCode = null;
    target.tvFundPerTime = null;

    this.target = null;
  }
}
