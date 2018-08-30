package cn.com.buyforyou.fund.ui.fragment.fund;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.constant.Constant;
import cn.com.buyforyou.fund.ui.activity.boot.SearchActivity;
import cn.com.buyforyou.fund.ui.activity.user.SwitchAccountActivity;
import cn.com.buyforyou.fund.ui.adapter.fund.FundTabViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.mvp.XFragment;
import cn.droidlover.xdroidmvp.mvp.XLazyFragment;

/**
 * 作者：sunnyzeng on 2017/12/5
 * 描述：基金
 */

public class FundFragment extends XLazyFragment {

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.rl_name_search)
    RelativeLayout rlNameSeach;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_fund;
    }

    @Override
    public Object newP() {
        return null;
    }


    @Override
    public void initData(Bundle savedInstanceState) {
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
        //搜索
        rlNameSeach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(SearchActivity.class);
            }
        });
    }

    /**
     * 频道数据展示
     */
    public void showChannel() {
        //在Fragment中嵌套ViewPager，然后ViewPager中呈现Fragment的时候
        FragmentManager fragmentManager = getChildFragmentManager();
        List<Fragment> fragmentList = new ArrayList<>();

        List<String> tabName = new ArrayList<>();
        tabName.add(Constant.FUND_TAB_CURRENCY);
        tabName.add(Constant.FUND_TAB_SHARES);
        tabName.add(Constant.FUND_TAB_BLEND);
        tabName.add(Constant.FUND_TAB_BOND);
        tabName.add(Constant.FUND_TAB_FINGER);

        int fragmentSize = tabName.size();

        //货币型
        FundCurrencyFragment currencyFragment = new FundCurrencyFragment();
        Bundle mbundle = new Bundle();
        mbundle.putInt(Constant.ACTIVITY_NAME, Constant.FUND_INDEX);
        currencyFragment.setArguments(mbundle);
        fragmentList.add(currencyFragment);

        for (int i = 1; i < fragmentSize; i++) {
            FundContentFragment fragment = new FundContentFragment();
            Bundle bundle = new Bundle();
            bundle.putString(Constant.FUND_TAB_NAME, tabName.get(i));
            bundle.putInt(Constant.ACTIVITY_NAME, Constant.FUND_INDEX);
            fragment.setArguments(bundle);
            fragmentList.add(fragment);
        }

        FundTabViewPagerAdapter adapter = new FundTabViewPagerAdapter(fragmentManager, fragmentList, tabName);
        mViewPager.setAdapter(adapter);

        mTabLayout.setupWithViewPager(mViewPager);
    }

}
