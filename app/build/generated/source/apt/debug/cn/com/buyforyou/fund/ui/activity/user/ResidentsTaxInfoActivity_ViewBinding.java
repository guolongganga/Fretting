// Generated code from Butter Knife. Do not modify!
package cn.com.buyforyou.fund.ui.activity.user;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import cn.com.buyforyou.fund.R;
import com.zhsoft.fretting.ui.widget.wheel.ScrollListView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ResidentsTaxInfoActivity_ViewBinding<T extends ResidentsTaxInfoActivity> implements Unbinder {
  protected T target;

  @UiThread
  public ResidentsTaxInfoActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.headBack = Utils.findRequiredViewAsType(source, R.id.head_back, "field 'headBack'", ImageButton.class);
    target.headTitle = Utils.findRequiredViewAsType(source, R.id.head_title, "field 'headTitle'", TextView.class);
    target.tileTop = Utils.findRequiredViewAsType(source, R.id.tax_tiletop, "field 'tileTop'", TextView.class);
    target.countries = Utils.findRequiredViewAsType(source, R.id.countries, "field 'countries'", TextView.class);
    target.born_countries = Utils.findRequiredViewAsType(source, R.id.born_countries, "field 'born_countries'", TextView.class);
    target.now_living_state = Utils.findRequiredViewAsType(source, R.id.now_living_state, "field 'now_living_state'", TextView.class);
    target.country_birth = Utils.findRequiredViewAsType(source, R.id.country_birth, "field 'country_birth'", TextView.class);
    target.address = Utils.findRequiredViewAsType(source, R.id.address, "field 'address'", EditText.class);
    target.address_provinces = Utils.findRequiredViewAsType(source, R.id.address_provinces, "field 'address_provinces'", EditText.class);
    target.surname_e = Utils.findRequiredViewAsType(source, R.id.surname_e, "field 'surname_e'", EditText.class);
    target.name_e = Utils.findRequiredViewAsType(source, R.id.name_e, "field 'name_e'", EditText.class);
    target.now_address_state = Utils.findRequiredViewAsType(source, R.id.now_address_state, "field 'now_address_state'", EditText.class);
    target.now_address_detailed = Utils.findRequiredViewAsType(source, R.id.now_address_detailed, "field 'now_address_detailed'", EditText.class);
    target.born_provinces = Utils.findRequiredViewAsType(source, R.id.born_provinces, "field 'born_provinces'", EditText.class);
    target.listView = Utils.findRequiredViewAsType(source, R.id.listView, "field 'listView'", ScrollListView.class);
    target.add_tax_info = Utils.findRequiredViewAsType(source, R.id.add_tax_info, "field 'add_tax_info'", TextView.class);
    target.btn_save = Utils.findRequiredViewAsType(source, R.id.btn_save, "field 'btn_save'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.headBack = null;
    target.headTitle = null;
    target.tileTop = null;
    target.countries = null;
    target.born_countries = null;
    target.now_living_state = null;
    target.country_birth = null;
    target.address = null;
    target.address_provinces = null;
    target.surname_e = null;
    target.name_e = null;
    target.now_address_state = null;
    target.now_address_detailed = null;
    target.born_provinces = null;
    target.listView = null;
    target.add_tax_info = null;
    target.btn_save = null;

    this.target = null;
  }
}
