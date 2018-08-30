// Generated code from Butter Knife. Do not modify!
package cn.com.buyforyou.fund.ui.activity.user;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import cn.com.buyforyou.fund.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PersonInfoActivity_ViewBinding<T extends PersonInfoActivity> implements Unbinder {
  protected T target;

  @UiThread
  public PersonInfoActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.headBack = Utils.findRequiredViewAsType(source, R.id.head_back, "field 'headBack'", ImageButton.class);
    target.headTitle = Utils.findRequiredViewAsType(source, R.id.head_title, "field 'headTitle'", TextView.class);
    target.name = Utils.findRequiredViewAsType(source, R.id.name, "field 'name'", TextView.class);
    target.identity = Utils.findRequiredViewAsType(source, R.id.identity, "field 'identity'", TextView.class);
    target.accountid = Utils.findRequiredViewAsType(source, R.id.accountid, "field 'accountid'", TextView.class);
    target.email = Utils.findRequiredViewAsType(source, R.id.email, "field 'email'", EditText.class);
    target.sex = Utils.findRequiredViewAsType(source, R.id.sex, "field 'sex'", TextView.class);
    target.limitTime = Utils.findRequiredViewAsType(source, R.id.limit_time, "field 'limitTime'", TextView.class);
    target.ivSelector = Utils.findRequiredViewAsType(source, R.id.iv_selector, "field 'ivSelector'", ImageView.class);
    target.nationality = Utils.findRequiredViewAsType(source, R.id.nationality, "field 'nationality'", TextView.class);
    target.linearlayout_duty = Utils.findRequiredViewAsType(source, R.id.linearlayout_duty, "field 'linearlayout_duty'", LinearLayout.class);
    target.duty = Utils.findRequiredViewAsType(source, R.id.duty, "field 'duty'", TextView.class);
    target.residentsTax = Utils.findRequiredViewAsType(source, R.id.residents_tax, "field 'residentsTax'", TextView.class);
    target.residentsTaxInfo = Utils.findRequiredViewAsType(source, R.id.residents_tax_info, "field 'residentsTaxInfo'", TextView.class);
    target.addressDetail = Utils.findRequiredViewAsType(source, R.id.address_detail, "field 'addressDetail'", TextView.class);
    target.btnSave = Utils.findRequiredViewAsType(source, R.id.btn_save, "field 'btnSave'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.headBack = null;
    target.headTitle = null;
    target.name = null;
    target.identity = null;
    target.accountid = null;
    target.email = null;
    target.sex = null;
    target.limitTime = null;
    target.ivSelector = null;
    target.nationality = null;
    target.linearlayout_duty = null;
    target.duty = null;
    target.residentsTax = null;
    target.residentsTaxInfo = null;
    target.addressDetail = null;
    target.btnSave = null;

    this.target = null;
  }
}
