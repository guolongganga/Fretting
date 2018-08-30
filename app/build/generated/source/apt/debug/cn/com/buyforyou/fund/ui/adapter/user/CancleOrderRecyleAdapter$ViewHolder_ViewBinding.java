// Generated code from Butter Knife. Do not modify!
package cn.com.buyforyou.fund.ui.adapter.user;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import cn.com.buyforyou.fund.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CancleOrderRecyleAdapter$ViewHolder_ViewBinding<T extends CancleOrderRecyleAdapter.ViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public CancleOrderRecyleAdapter$ViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.llContent = Utils.findRequiredViewAsType(source, R.id.ll_content, "field 'llContent'", LinearLayout.class);
    target.tvFundName = Utils.findRequiredViewAsType(source, R.id.tv_fund_name, "field 'tvFundName'", TextView.class);
    target.tvFundCode = Utils.findRequiredViewAsType(source, R.id.tv_fund_code, "field 'tvFundCode'", TextView.class);
    target.tvType = Utils.findRequiredViewAsType(source, R.id.tv_type, "field 'tvType'", TextView.class);
    target.tvTime = Utils.findRequiredViewAsType(source, R.id.tv_time, "field 'tvTime'", TextView.class);
    target.tvmount = Utils.findRequiredViewAsType(source, R.id.tv_amount, "field 'tvmount'", TextView.class);
    target.btnCancle = Utils.findRequiredViewAsType(source, R.id.btn_cancle, "field 'btnCancle'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.llContent = null;
    target.tvFundName = null;
    target.tvFundCode = null;
    target.tvType = null;
    target.tvTime = null;
    target.tvmount = null;
    target.btnCancle = null;

    this.target = null;
  }
}
