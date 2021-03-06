package cn.com.buyforyou.fund.ui.fragment.user;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.constant.Constant;
import cn.com.buyforyou.fund.model.user.HoldFundResp;
import cn.com.buyforyou.fund.net.Api;
import cn.com.buyforyou.fund.net.HttpContent;
import cn.com.buyforyou.fund.ui.activity.boot.FundDetailWebActivity;
import cn.com.buyforyou.fund.ui.adapter.user.WaitSureRecyleAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.mvp.XFragment;
import cn.droidlover.xrecyclerview.RecyclerItemCallback;
import cn.droidlover.xrecyclerview.XRecyclerView;

/**
 * 作者：sunnyzeng on 2018/3/19 19:40
 * 描述：
 */

public class WaitSureFragment extends XFragment {
    @BindView(R.id.xrv_user_hold) XRecyclerView xrvUserHold;
    @BindView(R.id.tv_empty) TextView tvEmpty;
    /**
     * 我的持仓基金
     */
    private ArrayList<HoldFundResp> fundList;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_user_wait_sure;
    }

    @Override
    public Object newP() {
        return null;
    }

    @Override
    public void initData(Bundle bundle) {
        xrvUserHold.setFocusable(false);
        xrvUserHold.verticalLayoutManager(context);//设置RecycleView类型 - 不设置RecycleView不显示
        if (bundle != null) {
            fundList = bundle.getParcelableArrayList(Constant.ACTIVITY_OBJECT);
            if (fundList != null && fundList.size() > 0) {
                xrvUserHold.setVisibility(View.VISIBLE);
                tvEmpty.setVisibility(View.GONE);
                getWaitSureAdapter().addData(fundList);
            } else {
                xrvUserHold.setVisibility(View.GONE);
                tvEmpty.setVisibility(View.VISIBLE);
            }
        }


    }

    /**
     * 初始化我的基金adapter
     *
     * @return
     */
    public SimpleRecAdapter getWaitSureAdapter() {
        WaitSureRecyleAdapter adapter = new WaitSureRecyleAdapter(context);
        xrvUserHold.setAdapter(adapter);
        adapter.setRecItemClick(new RecyclerItemCallback<HoldFundResp, WaitSureRecyleAdapter.ViewHolder>() {
            @Override
            public void onItemClick(int position, HoldFundResp model, int tag, WaitSureRecyleAdapter.ViewHolder holder) {
                super.onItemClick(position, model, tag, holder);
                switch (tag) {
                    //点击
                    case WaitSureRecyleAdapter.ITEM_CLICK:
                        break;
                }
            }
        });
        return adapter;
    }

    @Override
    public void initEvents() {

    }

    public void setList(ArrayList<HoldFundResp> list) {
        this.fundList = list;
        if (fundList != null && fundList.size() > 0) {
            xrvUserHold.setVisibility(View.VISIBLE);
            tvEmpty.setVisibility(View.GONE);
            getWaitSureAdapter().addData(fundList);
        } else {
            xrvUserHold.setVisibility(View.GONE);
            tvEmpty.setVisibility(View.VISIBLE);
        }
    }
}
