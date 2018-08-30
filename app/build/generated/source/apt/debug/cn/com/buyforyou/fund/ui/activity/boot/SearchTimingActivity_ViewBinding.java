// Generated code from Butter Knife. Do not modify!
package cn.com.buyforyou.fund.ui.activity.boot;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import cn.com.buyforyou.fund.R;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SearchTimingActivity_ViewBinding<T extends SearchTimingActivity> implements Unbinder {
  protected T target;

  @UiThread
  public SearchTimingActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.etSearchKey = Utils.findRequiredViewAsType(source, R.id.et_search_key, "field 'etSearchKey'", EditText.class);
    target.tvCancle = Utils.findRequiredViewAsType(source, R.id.tv_cancle, "field 'tvCancle'", TextView.class);
    target.viewLine = Utils.findRequiredView(source, R.id.view_line, "field 'viewLine'");
    target.contentLayout = Utils.findRequiredViewAsType(source, R.id.content_layout, "field 'contentLayout'", XRecyclerContentLayout.class);
    target.llHot = Utils.findRequiredViewAsType(source, R.id.ll_hot, "field 'llHot'", LinearLayout.class);
    target.ivDelete = Utils.findRequiredViewAsType(source, R.id.search_iv_delete, "field 'ivDelete'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.etSearchKey = null;
    target.tvCancle = null;
    target.viewLine = null;
    target.contentLayout = null;
    target.llHot = null;
    target.ivDelete = null;

    this.target = null;
  }
}
