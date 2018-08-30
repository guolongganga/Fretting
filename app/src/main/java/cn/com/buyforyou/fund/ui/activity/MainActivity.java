package cn.com.buyforyou.fund.ui.activity;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.TypedValue;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import cn.com.buyforyou.fund.App;
import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.constant.Constant;
import cn.com.buyforyou.fund.event.ChangeTabEvent;
import cn.com.buyforyou.fund.event.InvalidTokenEvent;
import cn.com.buyforyou.fund.ui.activity.user.LoginActivity;
import cn.com.buyforyou.fund.ui.fragment.fund.FundFragment;
import cn.com.buyforyou.fund.ui.fragment.user.UserFragment;
import cn.com.buyforyou.fund.ui.fragment.index.IndexFragment;
import cn.com.buyforyou.fund.utils.RuntimeHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * 作者：sunnyzeng on 2017/12/5
 * 描述：主页面
 */

public class MainActivity extends XActivity {
    //菜单按钮
//    /** 首页 */
//    @BindView(R.id.tv_index)
//    TextView tvIndex;
//    /** 基金 */
//    @BindView(R.id.tv_fund)
//    TextView tvFund;
//    /** 我的 */
//    @BindView(R.id.tv_user)
//    TextView tvUser;

    @BindView(R.id.main_bottom_rg)
    RadioGroup radioGroup;
    @BindView(R.id.main_bottom_index)
    RadioButton radioButtonIndex;
    @BindView(R.id.main_bottom_fund)
    RadioButton radioButtonFund;
    @BindView(R.id.main_bottom_mine)
    RadioButton radioButtonMine;

    /**
     * 碎片集合
     */
    private List<Fragment> fragments;
    /**
     * 碎片
     */
    private Fragment fragment;
    /**
     * 碎片事务
     */
    private FragmentTransaction ft;
    /**
     * 当前Tab页面索引
     */
    private int currentTab;
    /** 底部tab */
//    private List<TextView> textViews;
    /**
     * 用户编号
     */
    private String userId;
    /**
     * 登录标识
     */
    private String token;

    private long firstTime; //用于点击两次返回退出程序

    //合理调用commitAllowingStateLoss与commit
    //commit必须在状态保存(onSaveInstanceState)之前调用
    //因为MainActivity启动模式使用的是singleTask，当从任务栈中复用时会调用onSaveInstanceState,
    // 此时在使用commit的话就会报出：Can not perform this action after onSaveInstanceState
    private boolean allowStateLoss = false;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                allowStateLoss = true;
//                show(0);
                radioButtonIndex.setChecked(true);
            } else if (msg.what == 2) {
                allowStateLoss = true;
                radioButtonMine.setChecked(true);
//                show(2);
            }
        }
    };

    @Override
    public void initData(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        //获取本地缓存
        userId = App.getSharedPref().getString(Constant.USERID, "");
        token = App.getSharedPref().getString(Constant.TOKEN, "");
        /**
         * 判断是否有本地缓存
         */
        if (isNotEmpty(userId) && isNotEmpty(token)) {
            /**
             * 如果有缓存表示已经登陆了  全局变量设置为登录状态
             */
            //如果有缓存表示已经登录了
            //全局变量设置为登录状态
            RuntimeHelper.getInstance().setLogin(true);
        }
        fragments = new ArrayList<>();
        fragments.add(new IndexFragment());
        fragments.add(new FundFragment());
        fragments.add(new UserFragment());

//        textViews = new ArrayList<>();
//        textViews.add(tvIndex);
//        textViews.add(tvFund);
//        textViews.add(tvUser);

        //默认显示主页
        show(0);
        setRippBac(radioButtonIndex);
        setRippBac(radioButtonFund);
        setRippBac(radioButtonMine);


    }

    @Override
    public void initEvents() {
//        //切换底部菜单
//        tvIndex.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                show(tvIndex, 0);
//            }
//        });
//
//        tvFund.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                show(tvFund, 1);
//            }
//        });
//
//        tvUser.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                if (RuntimeHelper.getInstance().isLogin()) {
//                show(tvUser, 2);
////                } else {
////                    Bundle bundle = new Bundle();
////                    bundle.putString(Constant.SKIP_SIGN, Constant.SKIP_INDEX_ACTIVITY);
////                    startActivity(LoginActivity.class, bundle);
////                }
//
//            }
//        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.main_bottom_index:
                        show(0);
                        break;
                    case R.id.main_bottom_fund:
                        show(1);
                        break;
                    case R.id.main_bottom_mine:
                        show(2);
                        break;
                }


            }
        });
    }

    /**
     * 获取当前Fragment
     *
     * @return
     */
    public Fragment getCurrentFragment() {
        return fragments.get(currentTab);
    }

    /**
     * 切换底部菜单栏
     *
     * @param idx 当前位置
     */
    public void show(int idx) {
        for (int i = 0; i < fragments.size(); i++) {
            fragment = fragments.get(i);
            ft = getSupportFragmentManager().beginTransaction();
            getCurrentFragment().onPause(); // 暂停当前tab
            if (idx == i) {
                if (fragment.isAdded()) {
                    fragment.onResume(); // 启动目标tab的onResume()
                } else {
                    ft.add(R.id.fl_content, fragment);
                }

//                Drawable drawable = null;
//                if (idx == 0) {
//                    drawable = getResources().getDrawable(R.drawable.tab_index);
//
//                } else if (idx == 1) {
//                    drawable = getResources().getDrawable(R.drawable.tab_fund);
//                } else if (idx == 2) {
//                    drawable = getResources().getDrawable(R.drawable.tab_user);
//                }
//                // 这一步必须要做,否则不会显示.
//                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
//                textView.setCompoundDrawables(null, drawable, null, null);
//                textView.setTextColor(getResources().getColor(R.color.color_main));
//                textView.setSelected(true);
                ft.show(fragment);
            } else {
//                textViews.get(i).setTextColor(getResources().getColor(R.color.color_666666));
//                textViews.get(i).setSelected(false);
                ft.hide(fragment);
            }

            if (allowStateLoss) {
                ft.commitAllowingStateLoss();
            } else {
                ft.commit();
            }
            currentTab = idx; // 更新目标tab为当前tab
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public Object newP() {
        return null;
    }

    /**
     * 跳转
     *
     * @param activity
     */
    public static void launch(Activity activity) {
        Router.newIntent(activity)
                .to(MainActivity.class)
                .launch();
    }

    /**
     * 返回按钮
     */
    @Override
    public void onBackPressed() {
        long secondTime = System.currentTimeMillis();
        if (secondTime - firstTime > 1500) {
            showToast(R.string.click_logout);
            firstTime = secondTime;
        } else {
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        }
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    /**
     * 切换Tab
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onChangeTabEvent(ChangeTabEvent event) {
        String msg = event.getMsg();

        if (Constant.MAIN_INDEX.equals(msg)) {
            handler.sendEmptyMessage(0);
            return;
        } else if (Constant.MAIN_MY.equals(msg)) {
            handler.sendEmptyMessage(2);
            return;
        }
    }

    /**
     * 设置水波纹点击背景
     *
     * @param itemView
     */
    private void setRippBac(View itemView) {
        if (itemView.getBackground() == null) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme theme = itemView.getContext().getTheme();
            int top = itemView.getPaddingTop();
            int bottom = itemView.getPaddingBottom();
            int left = itemView.getPaddingLeft();
            int right = itemView.getPaddingRight();
            if (theme.resolveAttribute(android.R.attr.selectableItemBackground, typedValue, true)) {
                itemView.setBackgroundResource(typedValue.resourceId);
            }
            itemView.setPadding(left, top, right, bottom);
        }
    }
//    /**
//     * 无效token
//     *
//     * @param event
//     */
//    @Subscribe(threadMode = ThreadMode.POSTING)
//    public void onInvalidTokenEvent(InvalidTokenEvent event) {
//        //清除本地缓存，设置成未登录
//        RuntimeHelper.getInstance().isInvalidToken();
//        //跳转登录界面
//        Bundle bundle = new Bundle();
//        bundle.putString(Constant.SKIP_SIGN, Constant.SKIP_INDEX_ACTIVITY);
//        startActivity(LoginActivity.class, bundle);
//    }
}

