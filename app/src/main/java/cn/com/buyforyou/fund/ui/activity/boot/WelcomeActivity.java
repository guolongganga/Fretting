package cn.com.buyforyou.fund.ui.activity.boot;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import cn.com.buyforyou.fund.App;
import cn.com.buyforyou.fund.constant.Constant;
import cn.com.buyforyou.fund.ui.activity.MainActivity;
import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.ui.adapter.boot.WelcomeViewpagerAdapter;
import com.zhsoft.fretting.ui.widget.InkPageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * 作者：sunnyzeng on 2017/12/5
 * 描述：引导页
 */

public class WelcomeActivity extends XActivity {

    private Button btnGo;

    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @BindView(R.id.indicator)
    InkPageIndicator indicator;

    private List<View> dataList;

    @Override
    public int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    public Object newP() {
        return null;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        View viewOne = LayoutInflater.from(context).inflate(R.layout.item_one_welcome, null);
//        View viewTwo = LayoutInflater.from(context).inflate(R.layout.item_two_welcome, null);
        View viewThree = LayoutInflater.from(context).inflate(R.layout.item_three_welcome, null);

        btnGo = viewThree.findViewById(R.id.btn_go);

        dataList = new ArrayList<>();
        dataList.add(viewOne);
//        dataList.add(viewTwo);
        dataList.add(viewThree);

        WelcomeViewpagerAdapter adapter = new WelcomeViewpagerAdapter(context, dataList);
        mViewPager.setAdapter(adapter);
        indicator.setViewPager(mViewPager);
    }

    @Override
    public void initEvents() {
        //开启APP
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.launch(context);
                finish();
                App.getSharedPref().putBoolean(Constant.FIRST_USE, false);
            }
        });
    }

    public static void launch(Activity activity) {
        Router.newIntent(activity)
                .to(WelcomeActivity.class)
                .launch();
    }
}
