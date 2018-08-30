package cn.com.buyforyou.fund.ui.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.com.buyforyou.fund.App;
import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.constant.Constant;
import cn.com.buyforyou.fund.event.InvalidTokenEvent;
import cn.com.buyforyou.fund.model.ApplyBaseInfo;
import cn.com.buyforyou.fund.model.user.InvestInfoResp;
import cn.com.buyforyou.fund.model.user.InvestPlanResp;
import cn.com.buyforyou.fund.present.user.MyInvestPresent;
import cn.com.buyforyou.fund.ui.activity.index.TimingActivity;
import cn.com.buyforyou.fund.ui.adapter.user.InvestPlanRecyleAdapter;
import cn.com.buyforyou.fund.ui.adapter.user.MyFundRecyleAdapter;
import com.zhsoft.fretting.ui.widget.PopShow;
import cn.com.buyforyou.fund.utils.RuntimeHelper;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.dialog.httploadingdialog.HttpLoadingDialog;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xrecyclerview.RecyclerItemCallback;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import cn.droidlover.xrecyclerview.XRecyclerView;

/**
 * 作者：sunnyzeng on 2018/1/22 11:38
 * 描述：我的定投
 */

public class MyInvestActivity extends XActivity<MyInvestPresent> {
    /** 返回 */
    @BindView(R.id.head_back) ImageButton headBack;
    /** 标题 */
    @BindView(R.id.head_title) TextView headTitle;
    /** 条件选择 */
    @BindView(R.id.rl_selector) RelativeLayout rlSelector;
    /** 基金名称选择 */
    @BindView(R.id.tv_fund) TextView tvFund;
    /** 定投状态 */
    @BindView(R.id.tv_range) TextView tvRange;
    /** 我的定投计划 */
    @BindView(R.id.content_layout) XRecyclerContentLayout contentLayout;
    /** 新增定投 */
    @BindView(R.id.btn_add_invest) Button btnAddInvest;
    /** 线 */
    @BindView(R.id.view_line) View viewLine;

    /** adapter */
    private InvestPlanRecyleAdapter adapter;
    /** 每页显示条数 */
    private int pageSize = 10;
    /** 登录标识 */
    private String token;
    /** 用户编号 */
    private String userId;
    /** 加载圈 */
    private HttpLoadingDialog httpLoadingDialog;
    /** 基金选择 */
    private int fundSelector = 0;
    /** 状态选择 */
    private int statusSelector = 0;
    /** 基金选择 */
    private String fundSelectorCode;
    /** 状态选择 */
    private String statusSelectorCode;
    /** 基金选择集合 */
    private List<ApplyBaseInfo> fundList;
    /** 状态选择集合 */
    private List<ApplyBaseInfo> statusList;
    /** 全部状态 */
    private ApplyBaseInfo allStatus;
    /** 全部基金 */
    private ApplyBaseInfo allFund;
    private String isFirst = "1";


    @Override
    public int getLayoutId() {
        return R.layout.activity_user_my_invest;
    }

    @Override
    public MyInvestPresent newP() {
        return new MyInvestPresent();
    }

    @Override
    public void initData(Bundle bundle) {
        headTitle.setText("我的定投");

        httpLoadingDialog = new HttpLoadingDialog(context);
        token = App.getSharedPref().getString(Constant.TOKEN, "");
        userId = App.getSharedPref().getString(Constant.USERID, "");

        allStatus = new ApplyBaseInfo("0", "全部状态");
        allFund = new ApplyBaseInfo("0", "全部基金");
        //初始化基金选项框
        fundList = new ArrayList<>();
        fundList.add(allFund);

        //初始化状态选择框
        statusList = new ArrayList<>();
        statusList.add(allStatus);
        //初始化为 全部基金，全部状态
        tvFund.setText(fundList.get(fundSelector).getContent());
        tvRange.setText(statusList.get(statusSelector).getContent());

        contentLayout.getSwipeRefreshLayout().setColorSchemeResources(
                R.color.color_main,
                cn.droidlover.xrecyclerview.R.color.x_blue,
                cn.droidlover.xrecyclerview.R.color.x_yellow,
                cn.droidlover.xrecyclerview.R.color.x_green
        );

        contentLayout.getRecyclerView().verticalLayoutManager(context);
        contentLayout.getRecyclerView().setAdapter(getAdapter());
        contentLayout.getRecyclerView().horizontalDivider(R.color.color_F9F9F9, R.dimen.dimen_1);  //设置divider

        httpLoadingDialog.visible();
        getP().myInvestData(1, pageSize, token, userId, null, null, isFirst);

        contentLayout.getRecyclerView()
                .setOnRefreshAndLoadMoreListener(new XRecyclerView.OnRefreshAndLoadMoreListener() {
                    @Override
                    public void onRefresh() {
                        codeInfo(fundSelector, statusSelector);
                        getP().myInvestData(1, pageSize, token, userId, fundSelectorCode, statusSelectorCode, null);
                    }

                    @Override
                    public void onLoadMore(int page) {
                        codeInfo(fundSelector, statusSelector);
                        getP().myInvestData(page, pageSize, token, userId, fundSelectorCode, statusSelectorCode, null);
                    }
                });

        contentLayout.loadingView(View.inflate(context, R.layout.view_loading, null));
        contentLayout.getRecyclerView().useDefLoadMoreView();

    }

    private void codeInfo(int fundSelector, int statusSelector) {
        if (fundSelector == 0) {
            fundSelectorCode = null;
        } else {
            fundSelectorCode = fundList.get(fundSelector).getCode();
        }
        if (statusSelector == 0) {
            statusSelectorCode = null;
        } else {
            statusSelectorCode = statusList.get(statusSelector).getCode();
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
        tvFund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //上一次选择的条件
                final String lastChooseFund = getText(tvFund);

                PopShow popShow = new PopShow(context, viewLine);
                popShow.showRangeSelector(fundList, fundSelector);
                popShow.setOnClickPop(new PopShow.OnClickPop() {
                    @Override
                    public void setRange(int position) {
                        fundSelector = position;
                        tvFund.setText(fundList.get(fundSelector).getContent());
                        //如果选项改变
                        if (!lastChooseFund.equals(getText(tvFund))) {
                            //需要传 fundList.get(fundSelector).getCode()，statusList.get(position).getCode()
                            httpLoadingDialog.visible();
                            codeInfo(fundSelector, statusSelector);
                            getP().myInvestData(1, pageSize, token, userId, fundSelectorCode, statusSelectorCode, null);
                        }

                    }
                });
            }
        });
        tvRange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //上一次选择的条件
                final String lastChooseStatus = getText(tvRange);

                PopShow popShow = new PopShow(context, viewLine);
                popShow.showRangeSelector(statusList, statusSelector);
                popShow.setOnClickPop(new PopShow.OnClickPop() {
                    @Override
                    public void setRange(int position) {
                        statusSelector = position;
                        tvRange.setText(statusList.get(statusSelector).getContent());
                        //如果选项改变
                        if (!lastChooseStatus.equals(getText(tvRange))) {
                            // 需要传 fundList.get(fundSelector).getCode()，statusList.get(position).getCode()
                            httpLoadingDialog.visible();
                            codeInfo(fundSelector, statusSelector);
                            getP().myInvestData(1, pageSize, token, userId, fundSelectorCode, statusSelectorCode, null);
                        }
                    }
                });
            }
        });
        btnAddInvest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(TimingActivity.class);
            }
        });

        adapter.setRecItemClick(new RecyclerItemCallback<InvestInfoResp, InvestPlanRecyleAdapter.ViewHolder>() {
            @Override
            public void onItemClick(int position, InvestInfoResp model, int tag, InvestPlanRecyleAdapter.ViewHolder holder) {
                super.onItemClick(position, model, tag, holder);
                switch (tag) {
                    //点击
                    case MyFundRecyleAdapter.ITEM_CLICK:
                        //定投详情
                        Bundle bundle = new Bundle();
                        //协议号
                        bundle.putString(Constant.INVEST_PROTOCOL_ID, model.getScheduled_protocol_id());
                        bundle.putString(Constant.INVEST_STATUS, model.getScheduled_protocol_state());
                        startActivity(InvestDeatilActivity.class, bundle, Constant.INVEST_PLAN_ACTIVITY);
                        break;
                }
            }
        });
    }

    /**
     * 初始化定投计划adapter
     *
     * @return
     */
    public SimpleRecAdapter getAdapter() {
        if (adapter == null) {
            adapter = new InvestPlanRecyleAdapter(context);
        }
        return adapter;
    }

    /**
     * 请求我的定投计划数据失败
     */
    public void requestDataFail() {
        httpLoadingDialog.dismiss();
    }

    /**
     * 请求我的定投计划数据成功
     */
    public void requestDataSuccess(int pageno, InvestPlanResp resp, boolean first) {
        httpLoadingDialog.dismiss();
        //初始化基金选择框
        if (resp != null) {

            if (first) {
                if (resp.getAllFunds() != null && resp.getAllFunds().size() > 0) {
                    fundList.addAll(resp.getAllFunds());
                }
                if (resp.getAllStatus() != null && resp.getAllStatus().size() > 0) {
                    statusList.addAll(resp.getAllStatus());
                }
            }

            if (resp.getResResult() != null && resp.getResResult().size() > 0) {
                if (pageno > 1) {
                    getAdapter().addData(resp.getResResult());
                } else {
                    getAdapter().setData(resp.getResResult());
                }
                contentLayout.getRecyclerView().setPage(pageno, pageno + 1);
            } else {
                if (pageno == 1) {
                    contentLayout.showEmpty();
                } else {
                    //没有更多数据了
                    contentLayout.getRecyclerView().setPage(pageno, pageno - 1);
                }

            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.INVEST_PLAN_ACTIVITY && resultCode == Constant.INVEST_DETAIL_BACK) {
//            showToast("刷新页面数据");
            httpLoadingDialog.visible();
            codeInfo(fundSelector, statusSelector);
            getP().myInvestData(1, pageSize, token, userId, fundSelectorCode, statusSelectorCode, null);
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
