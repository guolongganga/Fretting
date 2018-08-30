package cn.com.buyforyou.fund.ui.fragment.fund;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.constant.Constant;
import cn.com.buyforyou.fund.model.ApplyBaseInfo;
import cn.com.buyforyou.fund.model.fund.NewestFundResp;
import cn.com.buyforyou.fund.net.Api;
import cn.com.buyforyou.fund.net.HttpContent;
import cn.com.buyforyou.fund.present.fund.FundContentPresent;
import cn.com.buyforyou.fund.ui.activity.boot.FundDetailWebActivity;
import cn.com.buyforyou.fund.ui.adapter.fund.FundContentRecycleAdapter;
import com.zhsoft.fretting.ui.widget.PopShow;
import com.zhsoft.fretting.ui.widget.StateView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.mvp.XFragment;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xrecyclerview.RecyclerAdapter;
import cn.droidlover.xrecyclerview.RecyclerItemCallback;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import cn.droidlover.xrecyclerview.XRecyclerView;

/**
 * Created by ${sunny}
 * data: 2017/12/19
 */

public class FundContentFragment extends XFragment<FundContentPresent> {

    @BindView(R.id.tv_range)
    TextView tvRange;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.content_layout)
    XRecyclerContentLayout contentLayout;

    private FundContentRecycleAdapter adapter;
    private List<ApplyBaseInfo> list;

    private int isSelector = 0;
    private String fundTabName;
    private int activityName;
    private int pageno = 1;
    private final int pageSize = 15;
    /** 基金类型 */
    private String tabType;
//    private StateView errorView;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_fund_content;
    }

    @Override
    public FundContentPresent newP() {
        return new FundContentPresent();
    }

    @Override
    public void initData(Bundle bundle) {
        //tab类型 请求接口的时候需要
        fundTabName = bundle.getString(Constant.FUND_TAB_NAME, "");
        tabType = fundTabType(fundTabName);

        //页面（基金页面或者人气产品）
        activityName = bundle.getInt(Constant.ACTIVITY_NAME, 0);
        //初始化排序条件的集合
        initOrderList();
        tvRange.setText(list.get(isSelector).getContent());
        contentLayout.getSwipeRefreshLayout().setColorSchemeResources(
                R.color.color_main,
                cn.droidlover.xrecyclerview.R.color.x_blue,
                cn.droidlover.xrecyclerview.R.color.x_yellow,
                cn.droidlover.xrecyclerview.R.color.x_green
        );

        contentLayout.getRecyclerView().verticalLayoutManager(context);
        contentLayout.getRecyclerView().setAdapter(getAdapter());
        contentLayout.getRecyclerView().horizontalDivider(R.color.color_E7E7E7, R.dimen.dimen_01);  //设置divider


        refreshFundData(activityName);

        contentLayout.getRecyclerView()
                .setOnRefreshAndLoadMoreListener(new XRecyclerView.OnRefreshAndLoadMoreListener() {
                    @Override
                    public void onRefresh() {
                        getP().loadFundData(1, pageSize, tabType, list.get(isSelector).getCode());
                    }

                    @Override
                    public void onLoadMore(int page) {
                        getP().loadFundData(page, pageSize, tabType, list.get(isSelector).getCode());
                    }
                });

//        if (errorView == null) {
//            errorView = new StateView(context);
//        }
//        contentLayout.errorView(errorView);

        contentLayout.loadingView(View.inflate(getContext(), R.layout.view_loading, null));
        contentLayout.showLoading();
        contentLayout.getRecyclerView().useDefLoadMoreView();

    }

    /**
     * 刷新基金数据
     *
     * @param activityName
     */
    private void refreshFundData(int activityName) {
        //判断是哪个Activity
        switch (activityName) {
            case Constant.POPULARITY://人气产品
                //0表示tab的类型
                getP().loadFundData(pageno, pageSize, tabType, list.get(isSelector).getCode());
                break;
            case Constant.FUND_INDEX://基金主页
                //0表示tab的类型
                getP().loadFundData(pageno, pageSize, tabType, list.get(isSelector).getCode());
                break;
        }
    }

    /**
     * 根据tab类型请求相对应类型的基金
     *
     * @param fundTabName
     * @return
     */
    private String fundTabType(String fundTabName) {
        if (Constant.FUND_TAB_SHARES.equals(fundTabName)) {
            //股票型
            return Constant.FUND_TAB_SHARES_TYPE;
        } else if (Constant.FUND_TAB_BLEND.equals(fundTabName)) {
            //混合型
            return Constant.FUND_TAB_BLEND_TYPE;
        } else if (Constant.FUND_TAB_BOND.equals(fundTabName)) {
            //债券型
            return Constant.FUND_TAB_BOND_TYPE;
        } else if (Constant.FUND_TAB_FINGER.equals(fundTabName)) {
            //指数型
            return Constant.FUND_TAB_FINGER_TYPE;
        }
        return "";
    }

    /**
     * 初始化排序条件的集合
     */
    private void initOrderList() {
        list = new ArrayList<>();
        ApplyBaseInfo baseInfo1 = new ApplyBaseInfo("1", "日涨幅");
        ApplyBaseInfo baseInfo2 = new ApplyBaseInfo("2", "近7天");
        ApplyBaseInfo baseInfo3 = new ApplyBaseInfo("3", "近1月");
        ApplyBaseInfo baseInfo4 = new ApplyBaseInfo("4", "近3月");
        ApplyBaseInfo baseInfo5 = new ApplyBaseInfo("5", "近半年");
        ApplyBaseInfo baseInfo6 = new ApplyBaseInfo("6", "今年以来");
        ApplyBaseInfo baseInfo7 = new ApplyBaseInfo("7", "近1年");
        ApplyBaseInfo baseInfo8 = new ApplyBaseInfo("8", "近2年");
        ApplyBaseInfo baseInfo9 = new ApplyBaseInfo("9", "近3年");
        ApplyBaseInfo baseInfo10 = new ApplyBaseInfo("10", "成立以来");
        list.add(baseInfo1);
        list.add(baseInfo2);
        list.add(baseInfo3);
        list.add(baseInfo4);
        list.add(baseInfo5);
        list.add(baseInfo6);
        list.add(baseInfo7);
        list.add(baseInfo8);
        list.add(baseInfo9);
        list.add(baseInfo10);
    }

    @Override
    public void initEvents() {
        //涨幅
        tvRange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PopShow popShow = new PopShow(context, viewLine);
                popShow.showRangeSelector(list, isSelector);
                popShow.setOnClickPop(new PopShow.OnClickPop() {
                    @Override
                    public void setRange(int position) {
                        isSelector = position;
                        tvRange.setText(list.get(position).getContent());
                        //回到顶部
                        contentLayout.getRecyclerView().scrollToPosition(0);
                        getP().loadFundData(1, pageSize, tabType, list.get(isSelector).getCode());
                    }
                });
            }
        });
        adapter.setRecItemClick(new RecyclerItemCallback<NewestFundResp, FundContentRecycleAdapter.ViewHolder>() {
            @Override
            public void onItemClick(int position, NewestFundResp model, int tag, FundContentRecycleAdapter.ViewHolder holder) {
                super.onItemClick(position, model, tag, holder);
                switch (tag) {
                    //点击
                    case FundContentRecycleAdapter.ITEM_CLICK:
                        //跳转基金详情页
                        Bundle bundle = new Bundle();
                        bundle.putInt(Constant.WEB_TITLE, R.string.fund_detail);
                        bundle.putString(Constant.WEB_LINK, Api.API_BASE_URL + HttpContent.fund_detail);
                        bundle.putString(Constant.FUND_DETAIL_CODE, model.getFund_code());
                        bundle.putString(Constant.FUND_DETAIL_NAME, model.getFund_name());
                        startActivity(FundDetailWebActivity.class, bundle);
                        break;
                }
            }
        });

    }

    /**
     * 初始化adapter
     *
     * @return
     */
    public RecyclerAdapter getAdapter() {
        if (adapter == null) {
            adapter = new FundContentRecycleAdapter(context);
        }
        return adapter;
    }

    /**
     * 数据展示
     *
     * @param pageno
     * @param item
     */
    public void showData(int pageno, List<NewestFundResp> item) {

        if (item != null && item.size() > 0) {
            if (pageno > 1) {
                getAdapter().addData(item);
            } else {
                getAdapter().setData(item);
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

    public void showError(NetError error) {
//        if (error != null) {
//            switch (error.getType()) {
//                case NetError.ParseError:
//                    errorView.setMsg("数据解析异常");
//                    break;
//
//                case NetError.AuthError:
//                    errorView.setMsg("身份验证异常");
//                    break;
//
//                case NetError.BusinessError:
//                    errorView.setMsg("业务异常");
//                    break;
//
//                case NetError.NoConnectError:
//                    errorView.setMsg("网络无连接，点击重试");
//                    errorView.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            refreshFundData(activityName);
//                        }
//                    });
//                    break;
//
//                case NetError.NoDataError:
//                    errorView.setMsg("数据为空");
//                    break;
//
//                case NetError.OtherError:
//                    errorView.setMsg("其他异常");
//                    break;
//            }
//            contentLayout.showError();
//        }
        showToast("网络加载失败");
        contentLayout.showError();
    }
}
