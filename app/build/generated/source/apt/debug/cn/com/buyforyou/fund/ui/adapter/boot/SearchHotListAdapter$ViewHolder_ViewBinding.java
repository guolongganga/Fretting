// Generated code from Butter Knife. Do not modify!
package cn.com.buyforyou.fund.ui.adapter.boot;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import cn.com.buyforyou.fund.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SearchHotListAdapter$ViewHolder_ViewBinding<T extends SearchHotListAdapter.ViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public SearchHotListAdapter$ViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.tvFundName = Utils.findRequiredViewAsType(source, R.id.tv_fund_name, "field 'tvFundName'", TextView.class);
    target.tvFundCode = Utils.findRequiredViewAsType(source, R.id.tv_fund_code, "field 'tvFundCode'", TextView.class);
    target.llContent = Utils.findRequiredViewAsType(source, R.id.ll_content, "field 'llContent'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.tvFundName = null;
    target.tvFundCode = null;
    target.llContent = null;

    this.target = null;
  }
}
