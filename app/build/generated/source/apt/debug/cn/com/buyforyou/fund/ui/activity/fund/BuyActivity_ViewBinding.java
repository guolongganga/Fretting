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

public class BuyActivity_ViewBinding<T extends BuyActivity> implements Unbinder {
  protected T target;

  @UiThread
  public BuyActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.headBack = Utils.findRequiredViewAsType(source, R.id.head_back, "field 'headBack'", ImageButton.class);
    target.headTitle = Utils.findRequiredViewAsType(source, R.id.head_title, "field 'headTitle'", TextView.class);
    target.bankImage = Utils.findRequiredViewAsType(source, R.id.bank_image, "field 'bankImage'", ImageView.class);
    target.bankName = Utils.findRequiredViewAsType(source, R.id.bank_name, "field 'bankName'", TextView.class);
    target.bankLimit = Utils.findRequiredViewAsType(source, R.id.bank_limit, "field 'bankLimit'", TextView.class);
    target.rlChange = Utils.findRequiredViewAsType(source, R.id.rl_change, "field 'rlChange'", RelativeLayout.class);
    target.tvApplyFee = Utils.findRequiredViewAsType(source, R.id.tv_apply_fee, "field 'tvApplyFee'", TextView.class);
    target.tvSureTime = Utils.findRequiredViewAsType(source, R.id.tv_sure_time, "field 'tvSureTime'", TextView.class);
    target.tvLookTime = Utils.findRequiredViewAsType(source, R.id.tv_look_time, "field 'tvLookTime'", TextView.class);
    target.etAmount = Utils.findRequiredViewAsType(source, R.id.et_amount, "field 'etAmount'", EditText.class);
    target.sure = Utils.findRequiredViewAsType(source, R.id.sure, "field 'sure'", Button.class);
    target.llBonus = Utils.findRequiredViewAsType(source, R.id.ll_bonus, "field 'llBonus'", LinearLayout.class);
    target.tvBonusType = Utils.findRequiredViewAsType(source, R.id.tv_bonus_type, "field 'tvBonusType'", TextView.class);
    target.tvPoundage = Utils.findRequiredViewAsType(source, R.id.tv_poundage, "field 'tvPoundage'", TextView.class);
    target.tvRate = Utils.findRequiredViewAsType(source, R.id.tv_rate, "field 'tvRate'", TextView.class);
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
    target.tvApplyFee = null;
    target.tvSureTime = null;
    target.tvLookTime = null;
    target.etAmount = null;
    target.sure = null;
    target.llBonus = null;
    target.tvBonusType = null;
    target.tvPoundage = null;
    target.tvRate = null;

    this.target = null;
  }
}
