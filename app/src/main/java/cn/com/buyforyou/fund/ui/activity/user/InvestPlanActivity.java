package cn.com.buyforyou.fund.ui.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import cn.com.buyforyou.fund.App;
import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.constant.Constant;
import cn.com.buyforyou.fund.event.InvalidTokenEvent;
import cn.com.buyforyou.fund.model.fund.InvestResp;
import cn.com.buyforyou.fund.model.user.InvestInfoResp;
import cn.com.buyforyou.fund.model.user.InvestPlanResp;
import cn.com.buyforyou.fund.present.user.InvestPlanPresent;
import cn.com.buyforyou.fund.ui.activity.fund.InvestActivity;
import cn.com.buyforyou.fund.ui.adapter.user.InvestPlanRecyleAdapter;
import cn.com.buyforyou.fund.ui.adapter.user.MyFundRecyleAdapter;
import cn.com.buyforyou.fund.utils.RuntimeHelper;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.dialog.httploadingdialog.HttpLoadingDialog;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xrecyclerview.RecyclerItemCallback;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import cn.droidlover.xrecyclerview.XRecyclerView;

/**
 * 作者：sunnyzeng on 2018/1/22 11:38
 * 描述：定投计划
 */

public class InvestPlanActivity extends XActivity<InvestPlanPresent> {
    /** 返回 */
    @BindView(R.id.head_back) ImageButton headBack;
    /** 标题 */
    @BindView(R.id.head_title) TextView headTitle;
    /** 我的定投计划 */
    @BindView(R.id.xrv_my_invest) XRecyclerView xrvMyInvest;
    /** 新增定投 */
    @BindView(R.id.btn_add_invest) Button btnAddInvest;
    /** 暂无数据 */
    @BindView(R.id.tv_empty) TextView tvmpty;
//    /** 线 */
//    @BindView(R.id.view_line) View viewLine;

    /** 登录标识 */
    private String token;
    /** 用户编号 */
    private String userId;
    /** 基金代码 */
    private String fundCode;
    /** 基金名称 */
    private String fundName;
    /** 加载圈 */
    private HttpLoadingDialog httpLoadingDialog;
    /** 每页显示数量 */
    private int pageSize = 10;
    /** 定投计划数据 */
    private ArrayList<InvestInfoResp> planResp;


    @Override
    public int getLayoutId() {
        return R.layout.activity_user_invest_plan;
    }

    @Override
    public InvestPlanPresent newP() {
        return new InvestPlanPresent();
    }

    @Override
    public void initData(Bundle bundle) {
        headTitle.setText("定投计划");

        httpLoadingDialog = new HttpLoadingDialog(context);
        xrvMyInvest.verticalLayoutManager(context);//设置RecycleView类型 - 不设置RecycleView不显示
        xrvMyInvest.setFocusable(false);

        token = App.getSharedPref().getString(Constant.TOKEN, "");
        userId = App.getSharedPref().getString(Constant.USERID, "");

        if (bundle != null) {
            //基金代码
            fundCode = bundle.getString(Constant.FUND_DETAIL_CODE);
            //基金名称
            fundName = bundle.getString(Constant.FUND_DETAIL_NAME);
            planResp = bundle.getParcelableArrayList(Constant.ACTIVITY_OBJECT);
            if (planResp != null && planResp.size() > 0) {
                getAdapter().addData(planResp);
            }
        }

    }


    @Override
    public void initEvents() {
        headBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnAddInvest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getP().investTime(token, userId, fundCode, fundName);
            }
        });


    }

    /**
     * 初始化定投计划adapter
     *
     * @return
     */
    public SimpleRecAdapter getAdapter() {
        InvestPlanRecyleAdapter adapter = new InvestPlanRecyleAdapter(context);
        xrvMyInvest.setAdapter(adapter);
        adapter.setRecItemClick(new RecyclerItemCallback<InvestInfoResp, InvestPlanRecyleAdapter.ViewHolder>() {
            @Override
            public void onItemClick(int position, InvestInfoResp model, int tag, InvestPlanRecyleAdapter.ViewHolder holder) {
                super.onItemClick(position, model, tag, holder);
                switch (tag) {
                    //点击
                    case MyFundRecyleAdapter.ITEM_CLICK:
                        //终止 不能点击
                        if (!"终止".equals(model.getScheduled_protocol_state())) {
                            Bundle bundle = new Bundle();
                            bundle.putString(Constant.INVEST_PROTOCOL_ID, model.getScheduled_protocol_id());
                            bundle.putString(Constant.INVEST_STATUS, model.getScheduled_protocol_state());
                            startActivity(InvestDeatilActivity.class, bundle, Constant.INVEST_PLAN_ACTIVITY);
                        }
                        break;
                }
            }
        });
        return adapter;
    }

    /**
     * 请求定投验证接口失败
     */
    public void requestInvestFail() {

    }

    /**
     * 请求定投验证接口成功
     *
     * @param resp
     */
    public void requestInvestSuccess(final InvestResp resp) {
        //去定投
        Bundle bundle = new Bundle();
        bundle.putString(Constant.INVEST_ACTIVITY_TYPE, Constant.INVEST_ACTIVITY);
        bundle.putString(Constant.FUND_DETAIL_CODE, fundCode);
        bundle.putString(Constant.FUND_DETAIL_NAME, fundName);
        bundle.putParcelable(Constant.INVEST_FUND_OBJECT, resp);
        startActivity(InvestActivity.class, bundle);
    }

    /***
     * 去定投计划成功
     * @param planResp
     */
    public void requestInvestPlanSuccess(InvestPlanResp planResp) {
        httpLoadingDialog.dismiss();
        //刷新页面数据
        if (planResp.getResResult() != null && planResp.getResResult().size() > 0) {
            xrvMyInvest.setVisibility(View.VISIBLE);
            tvmpty.setText(View.GONE);
            getAdapter().addData(planResp.getResResult());
        }else{
            xrvMyInvest.setVisibility(View.GONE);
            tvmpty.setText(View.VISIBLE);
        }
    }

    /***
     * 去定投计划失败
     */
    public void requestInvestPlanFail() {
        httpLoadingDialog.dismiss();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.INVEST_PLAN_ACTIVITY && resultCode == Constant.INVEST_DETAIL_BACK) {
//            showToast("刷新页面数据");
            httpLoadingDialog.visible();
            getP().buyOnFundData(token, userId, fundCode);
        }
    }

    /**
     * 已经登出系统，请重新登录
     */
    public void areadyLogout() {
        httpLoadingDialog.dismiss();
//        EventBus.getDefault().post(new InvalidTokenEvent());
        //清除本地缓存，设置成未登录
        RuntimeHelper.getInstance().isInvalidToken();
        //跳转登录界面
        Bundle bundle = new Bundle();
        bundle.putString(Constant.SKIP_SIGN, Constant.SKIP_INDEX_ACTIVITY);
        startActivity(LoginActivity.class, bundle);
    }
}
