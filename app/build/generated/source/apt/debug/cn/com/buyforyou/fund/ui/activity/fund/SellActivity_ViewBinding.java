// Generated code from Butter Knife. Do not modify!
package cn.com.buyforyou.fund.ui.activity.fund;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import cn.com.buyforyou.fund.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SellActivity_ViewBinding<T extends SellActivity> implements Unbinder {
  protected T target;

  @UiThread
  public SellActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.headBack = Utils.findRequiredViewAsType(source, R.id.head_back, "field 'headBack'", ImageButton.class);
    target.headTitle = Utils.findRequiredViewAsType(source, R.id.head_title, "field 'headTitle'", TextView.class);
    target.bankImage = Utils.findRequiredViewAsType(source, R.id.bank_image, "field 'bankImage'", ImageView.class);
    target.bankName = Utils.findRequiredViewAsType(source, R.id.bank_name, "field 'bankName'", TextView.class);
    target.bankLimit = Utils.findRequiredViewAsType(source, R.id.bank_limit, "field 'bankLimit'", TextView.class);
    target.rlChange = Utils.findRequiredViewAsType(source, R.id.rl_change, "field 'rlChange'", RelativeLayout.class);
    target.availableShare = Utils.findRequiredViewAsType(source, R.id.available_share, "field 'availableShare'", TextView.class);
    target.btnAllShare = Utils.findRequiredViewAsType(source, R.id.btn_all_share, "field 'btnAllShare'", Button.class);
    target.etAmount = Utils.findRequiredViewAsType(source, R.id.et_amount, "field 'etAmount'", EditText.class);
    target.sure = Utils.findRequiredViewAsType(source, R.id.sure, "field 'sure'", Button.class);
    target.tvDealType = Utils.findRequiredViewAsType(source, R.id.tv_deal_type, "field 'tvDealType'", TextView.class);
    target.tvMinSell = Utils.findRequiredViewAsType(source, R.id.tv_min_sell, "field 'tvMinSell'", TextView.class);
    target.llBigDeal = Utils.findRequiredViewAsType(source, R.id.ll_big_deal, "field 'llBigDeal'", LinearLayout.class);
    target.viewLine = Utils.findRequiredView(source, R.id.view_line, "field 'viewLine'");
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.headBack = null;
    target.headTitle = null;
    target.bankImage = null;
    target.bankName = null;
    target.bankLimit = null;
    target.rlChange = null;
    target.availableShare = null;
    target.btnAllShare = null;
    target.etAmount = null;
    target.sure = null;
    target.tvDealType = null;
    target.tvMinSell = null;
    target.llBigDeal = null;
    target.viewLine = null;

    this.target = null;
  }
}
