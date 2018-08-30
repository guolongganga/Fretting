package cn.com.buyforyou.fund.ui.activity.user;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.constant.Constant;
import cn.com.buyforyou.fund.ui.adapter.fund.FundTabViewPagerAdapter;
import cn.com.buyforyou.fund.ui.fragment.user.BonusModeFragment;
import cn.com.buyforyou.fund.ui.fragment.user.MyBonusFragment;
import cn.com.buyforyou.fund.ui.fragment.user.TransactionContentFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.mvp.XActivity;

/**
 * 作者：sunnyzeng on 2017/12/21 17:52
 * 描述：分红
 */

public class BonusActivity extends XActivity {
    /** 返回按钮 */
    @BindView(R.id.head_back) ImageButton headBack;
    /** 标题 */
    @BindView(R.id.head_title) TextView headTitle;
    /** 页卡标题 */
    @BindView(R.id.tab_layout) TabLayout mTabLayout;
    /** 滑动内容 */
    @BindView(R.id.view_pager) ViewPager mViewPager;

    @Override
    public int getLayoutId() {
        return R.layout.activity_index_popularity;
    }

    @Override
    public Object newP() {
        return null;
    }

    @Override
    public void initData(Bundle bundle) {
        headTitle.setText("分红");

        showChannel();

    }

    @Override
    public void initEvents() {
        //TabLayout监听滑动或点击
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition(), false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        headBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    /**
     * 频道数据展示
     */
    public void showChannel() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        List<Fragment> fragmentList = new ArrayList<>();

        List<String> tabName = new ArrayList<>();
        tabName.add("我的分红");
        tabName.add("修改分红方式");

        MyBonusFragment fragment = new MyBonusFragment();
//        Bundle bundle = new Bundle();
//        //tabName.get(0) 才是 我的分红 方式
//        bundle.putString(Constant.FUND_TAB_NAME, tabName.get(0));
//        fragment.setArguments(bundle);
        fragmentList.add(fragment);

        BonusModeFragment bonusModeFragment = new BonusModeFragment();
        fragmentList.add(bonusModeFragment);

        FundTabViewPagerAdapter adapter = new FundTabViewPagerAdapter(fragmentManager, fragmentList, tabName);
        mViewPager.setAdapter(adapter);//将viewPager与Adapter关联

        mTabLayout.setupWithViewPager(mViewPager);  //将TabLayout和ViewPager关联起来。
    }
}
