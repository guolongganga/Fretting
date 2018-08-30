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
import cn.com.buyforyou.fund.ui.fragment.user.TransactionContentFragment;
import cn.com.buyforyou.fund.ui.fragment.user.TransactionSingleContentFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.mvp.XActivity;

/**
 * 作者：sunnyzeng on 2017/12/21 17:52
 * 描述：交易查询 单只基金
 */

public class TransactionQuerySingleActivity extends XActivity {
    /** 返回按钮 */
    @BindView(R.id.head_back) ImageButton headBack;
    /** 标题 */
    @BindView(R.id.head_title) TextView headTitle;
    /** 页卡标题 */
    @BindView(R.id.tab_layout) TabLayout mTabLayout;
    /** 滑动内容 */
    @BindView(R.id.view_pager) ViewPager mViewPager;
    /** 基金编号 */
    private String fundCode;

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
        headTitle.setText("交易查询");
        fundCode = bundle.getString(Constant.FUND_DETAIL_CODE);
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
        tabName.add(Constant.TRANSACTION_TAB_PURCHASE);
        tabName.add(Constant.TRANSACTION_TAB_SELLOUT);
        tabName.add(Constant.TRANSACTION_TAB_ONPASSAGE);
        tabName.add(Constant.TRANSACTION_TAB_BONUS);

        int fragmentSize = tabName.size();

        for (int i = 0; i < fragmentSize; i++) {
            //单只基金
            TransactionSingleContentFragment fragment = new TransactionSingleContentFragment();
            Bundle bundle = new Bundle();
            bundle.putString(Constant.FUND_TAB_NAME, tabName.get(i));
            bundle.putString(Constant.FUND_DETAIL_CODE, fundCode);
            fragment.setArguments(bundle);
            fragmentList.add(fragment);
        }

        FundTabViewPagerAdapter adapter = new FundTabViewPagerAdapter(fragmentManager, fragmentList, tabName);
        mViewPager.setAdapter(adapter);//将viewPager与Adapter关联

        mTabLayout.setupWithViewPager(mViewPager);  //将TabLayout和ViewPager关联起来。
    }
}
