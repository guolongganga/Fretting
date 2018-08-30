package cn.com.buyforyou.fund.ui.activity.index;

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
import cn.com.buyforyou.fund.ui.activity.boot.SearchActivity;
import cn.com.buyforyou.fund.ui.adapter.fund.FundTabViewPagerAdapter;
import cn.com.buyforyou.fund.ui.fragment.fund.FundContentFragment;
import cn.com.buyforyou.fund.ui.fragment.fund.FundCurrencyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.mvp.XActivity;

/**
 * 作者：sunnyzeng on 2017/12/21 16:15
 * 描述：人气产品 业绩排行
 */

public class PopularityActivity extends XActivity {
    /** 返回按钮 */
    @BindView(R.id.head_back) ImageButton headBack;
    /** 标题 */
    @BindView(R.id.head_title) TextView headTitle;
    /** 右侧图片按钮 */
    @BindView(R.id.head_right_imgbtn) ImageButton headRightImgbtn;
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
    public void initData(Bundle savedInstanceState) {
        headTitle.setText("业绩排行");
        headRightImgbtn.setVisibility(View.VISIBLE);
        headRightImgbtn.setImageResource(R.drawable.icon_common_search_white);
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
        headRightImgbtn.setOnClickListener(new View.OnClickListener() {
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

        FragmentManager fragmentManager = getSupportFragmentManager();
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
            bundle.putInt(Constant.ACTIVITY_NAME, Constant.POPULARITY);
            fragment.setArguments(bundle);
            fragmentList.add(fragment);
        }
        FundTabViewPagerAdapter adapter = new FundTabViewPagerAdapter(fragmentManager, fragmentList, tabName);
        mViewPager.setAdapter(adapter);

        mTabLayout.setupWithViewPager(mViewPager);
    }


}
