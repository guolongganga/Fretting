// Generated code from Butter Knife. Do not modify!
package cn.com.buyforyou.fund.ui.fragment.index;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import cn.com.buyforyou.fund.R;
import cn.droidlover.xdroidmvp.banner.FlyBanner;
import cn.droidlover.xrecyclerview.XRecyclerView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class IndexFragment_ViewBinding<T extends IndexFragment> implements Unbinder {
  protected T target;

  @UiThread
  public IndexFragment_ViewBinding(T target, View source) {
    this.target = target;

    target.banner = Utils.findRequiredViewAsType(source, R.id.banner, "field 'banner'", FlyBanner.class);
    target.xrvPopularity = Utils.findRequiredViewAsType(source, R.id.xrv_popularity, "field 'xrvPopularity'", XRecyclerView.class);
    target.popularityMore = Utils.findRequiredViewAsType(source, R.id.popularity_more, "field 'popularityMore'", TextView.class);
    target.llStar = Utils.findRequiredViewAsType(source, R.id.ll_star, "field 'llStar'", RelativeLayout.class);
    target.tvSevenEarnings = Utils.findRequiredViewAsType(source, R.id.tv_seven_earnings, "field 'tvSevenEarnings'", TextView.class);
    target.tvStarRateDesc = Utils.findRequiredViewAsType(source, R.id.tv_star_rate_desc, "field 'tvStarRateDesc'", TextView.class);
    target.tvPercent = Utils.findRequiredViewAsType(source, R.id.tv_percent, "field 'tvPercent'", TextView.class);
    target.tvWanarnings = Utils.findRequiredViewAsType(source, R.id.tv_wan_earnings, "field 'tvWanarnings'", TextView.class);
    target.btnBuy = Utils.findRequiredViewAsType(source, R.id.btn_buy, "field 'btnBuy'", Button.class);
    target.rlFingerOne = Utils.findRequiredViewAsType(source, R.id.rl_finger_one, "field 'rlFingerOne'", RelativeLayout.class);
    target.tvNvshen = Utils.findRequiredViewAsType(source, R.id.tv_nvshen, "field 'tvNvshen'", TextView.class);
    target.tvNvshenShouyi = Utils.findRequiredViewAsType(source, R.id.tv_nvshen_shouyi, "field 'tvNvshenShouyi'", TextView.class);
    target.tvFingerOneDesc = Utils.findRequiredViewAsType(source, R.id.tv_finger_one_desc, "field 'tvFingerOneDesc'", TextView.class);
    target.rlFingerTwo = Utils.findRequiredViewAsType(source, R.id.rl_finger_two, "field 'rlFingerTwo'", RelativeLayout.class);
    target.tvChihuo = Utils.findRequiredViewAsType(source, R.id.tv_chihuo, "field 'tvChihuo'", TextView.class);
    target.tvChihuoShouyi = Utils.findRequiredViewAsType(source, R.id.tv_chihuo_shouyi, "field 'tvChihuoShouyi'", TextView.class);
    target.tvFingerTwoDesc = Utils.findRequiredViewAsType(source, R.id.tv_finger_two_desc, "field 'tvFingerTwoDesc'", TextView.class);
    target.timingMore = Utils.findRequiredViewAsType(source, R.id.timing_more, "field 'timingMore'", TextView.class);
    target.xrvPrefer = Utils.findRequiredViewAsType(source, R.id.xrv_prefer, "field 'xrvPrefer'", XRecyclerView.class);
    target.rlNameSearch = Utils.findRequiredViewAsType(source, R.id.rl_name_search, "field 'rlNameSearch'", RelativeLayout.class);
    target.ivThemeOne = Utils.findRequiredViewAsType(source, R.id.iv_theme_one, "field 'ivThemeOne'", ImageView.class);
    target.ivThemeTwo = Utils.findRequiredViewAsType(source, R.id.iv_theme_two, "field 'ivThemeTwo'", ImageView.class);
    target.ivThemeThree = Utils.findRequiredViewAsType(source, R.id.iv_theme_three, "field 'ivThemeThree'", ImageView.class);
    target.scrollView = Utils.findRequiredViewAsType(source, R.id.scroll_view, "field 'scrollView'", NestedScrollView.class);
    target.swipeRefreshLayout = Utils.findRequiredViewAsType(source, R.id.swipe_container, "field 'swipeRefreshLayout'", SwipeRefreshLayout.class);
    target.errorView = Utils.findRequiredViewAsType(source, R.id.error_view, "field 'errorView'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.banner = null;
    target.xrvPopularity = null;
    target.popularityMore = null;
    target.llStar = null;
    target.tvSevenEarnings = null;
    target.tvStarRateDesc = null;
    target.tvPercent = null;
    target.tvWanarnings = null;
    target.btnBuy = null;
    target.rlFingerOne = null;
    target.tvNvshen = null;
    target.tvNvshenShouyi = null;
    target.tvFingerOneDesc = null;
    target.rlFingerTwo = null;
    target.tvChihuo = null;
    target.tvChihuoShouyi = null;
    target.tvFingerTwoDesc = null;
    target.timingMore = null;
    target.xrvPrefer = null;
    target.rlNameSearch = null;
    target.ivThemeOne = null;
    target.ivThemeTwo = null;
    target.ivThemeThree = null;
    target.scrollView = null;
    target.swipeRefreshLayout = null;
    target.errorView = null;

    this.target = null;
  }
}
