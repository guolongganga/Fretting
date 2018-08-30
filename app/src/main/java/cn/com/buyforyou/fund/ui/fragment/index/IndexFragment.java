package cn.com.buyforyou.fund.ui.fragment.index;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.constant.Constant;
import cn.com.buyforyou.fund.model.index.BannerModel;
import cn.com.buyforyou.fund.model.index.IndexResp;
import cn.com.buyforyou.fund.model.index.ProductModel;
import cn.com.buyforyou.fund.model.index.UpdateVersionResp;
import cn.com.buyforyou.fund.net.Api;
import cn.com.buyforyou.fund.net.HttpContent;
import cn.com.buyforyou.fund.present.index.IndexPresent;
import cn.com.buyforyou.fund.ui.activity.UpdateAppActivity;
import cn.com.buyforyou.fund.ui.activity.boot.FundDetailWebActivity;
import cn.com.buyforyou.fund.ui.activity.boot.SearchActivity;
import cn.com.buyforyou.fund.ui.activity.boot.WebPublicActivity;
import cn.com.buyforyou.fund.ui.activity.index.PopularityActivity;
import cn.com.buyforyou.fund.ui.activity.index.TimingActivity;
import cn.com.buyforyou.fund.ui.adapter.index.PopularityRecycleAdapter;
import cn.com.buyforyou.fund.ui.adapter.index.PreferRecycleAdapter;
import cn.com.buyforyou.fund.utils.BigDecimalUtil;
import cn.com.buyforyou.fund.utils.RateStyleUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.banner.FlyBanner;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.dialog.httploadingdialog.HttpLoadingDialog;
import cn.droidlover.xdroidmvp.imageloader.ILFactory;
import cn.droidlover.xdroidmvp.mvp.XFragment;
import cn.droidlover.xrecyclerview.RecyclerItemCallback;
import cn.droidlover.xrecyclerview.XRecyclerView;

/**
 * 作者：sunnyzeng on 2017/12/5
 * 描述：主页
 */

public class IndexFragment extends XFragment<IndexPresent> {

    /**
     * 轮播图
     */
    @BindView(R.id.banner)
    FlyBanner banner;
    /**
     * 人气产品
     */
    @BindView(R.id.xrv_popularity)
    XRecyclerView xrvPopularity;
    /**
     * 人气产品 更多
     */
    @BindView(R.id.popularity_more)
    TextView popularityMore;
    /**
     * 明星基金
     */
    @BindView(R.id.ll_star)
    RelativeLayout llStar;
    /**
     * 明星基金 收益率
     */
    @BindView(R.id.tv_seven_earnings)
    TextView tvSevenEarnings;
    /**
     * 明星基金 收益率描述
     */
    @BindView(R.id.tv_star_rate_desc)
    TextView tvStarRateDesc;
    /**
     * 明星基金 百分号
     */
    @BindView(R.id.tv_percent)
    TextView tvPercent;
    /**
     * 明星基金 名称
     */
    @BindView(R.id.tv_wan_earnings)
    TextView tvWanarnings;
    /**
     * 明星基金 购买
     */
    @BindView(R.id.btn_buy)
    Button btnBuy;
    /**
     * 指数基金产品1
     */
    @BindView(R.id.rl_finger_one)
    RelativeLayout rlFingerOne;
    /**
     * 指数基金产品1 名称
     */
    @BindView(R.id.tv_nvshen)
    TextView tvNvshen;
    /**
     * 指数基金产品1 收益
     */
    @BindView(R.id.tv_nvshen_shouyi)
    TextView tvNvshenShouyi;
    /**
     * 指数基金产品1 收益描述
     */
    @BindView(R.id.tv_finger_one_desc)
    TextView tvFingerOneDesc;
    /**
     * 指数基金产品2
     */
    @BindView(R.id.rl_finger_two)
    RelativeLayout rlFingerTwo;
    /**
     * 指数基金产品2 名称
     */
    @BindView(R.id.tv_chihuo)
    TextView tvChihuo;
    /**
     * 指数基金产品2 收益
     */
    @BindView(R.id.tv_chihuo_shouyi)
    TextView tvChihuoShouyi;
    /**
     * 指数基金产品2 收益描述
     */
    @BindView(R.id.tv_finger_two_desc)
    TextView tvFingerTwoDesc;
    /**
     * 优选定投 更多
     */
    @BindView(R.id.timing_more)
    TextView timingMore;
    /**
     * 优选定投
     */
    @BindView(R.id.xrv_prefer)
    XRecyclerView xrvPrefer;

//    @BindView(R.id.ll_prefer_vote)
//    LinearLayout llPreferVote;
//    /** 优选定投 名称 */
//    @BindView(R.id.preferred_name)
//    TextView preferredName;
//    /** 优选定投 利率 */
//    @BindView(R.id.preferred_rate)
//    TextView preferredRate;
    /**
     * 搜索
     */
    @BindView(R.id.rl_name_search)
    RelativeLayout rlNameSearch;
    /**
     * 专题一
     */
    @BindView(R.id.iv_theme_one)
    ImageView ivThemeOne;
    /**
     * 专题二
     */
    @BindView(R.id.iv_theme_two)
    ImageView ivThemeTwo;
    /**
     * 专题三
     */
    @BindView(R.id.iv_theme_three)
    ImageView ivThemeThree;
//    /** 立即定投 */
//    @BindView(R.id.btn_invest)
//    Button btnInvest;
    /**
     * ScrollView
     */
    @BindView(R.id.scroll_view)
    NestedScrollView scrollView;
    /**
     * 下拉刷新
     */
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout swipeRefreshLayout;
    /**
     * 网络错误，点击重试
     */
    @BindView(R.id.error_view)
    TextView errorView;


    /**
     * 明星基金
     */
    private ProductModel startModel;
    private ArrayList<BannerModel> themeList;
//    /** 优选定投 */
//    private ProductModel preferredVote;
    /**
     * 指数基金1
     */
    private ProductModel fingerOne;
    /**
     * 指数基金2
     */
    private ProductModel fingertwo;
    /**
     * 加载圈
     */
    private HttpLoadingDialog httpLoadingDialog;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_index;
    }

    @Override
    public IndexPresent newP() {
        return new IndexPresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        //设置刷新时动画的颜色，可以设置4个
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light,
                android.R.color.holo_orange_light, android.R.color.holo_green_light);

        httpLoadingDialog = new HttpLoadingDialog(context);

        xrvPopularity.setFocusable(false);
        xrvPopularity.verticalLayoutManager(context);//设置RecycleView类型 - 不设置RecycleView不显示
        xrvPrefer.setFocusable(false);
        xrvPrefer.verticalLayoutManager(context);//设置RecycleView类型 - 不设置RecycleView不显示

        httpLoadingDialog.visible();
        getP().loadData();
        try{
            getP().checkUpdate(context.getPackageManager().getPackageInfo(context.getPackageName(),0).versionName);

        }catch (Exception e){

        }
    }


    boolean adClickable = false;

    @Override
    public void initEvents() {
        //人气产品 更多
        popularityMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(PopularityActivity.class);
            }
        });
        //优选定投 更多
        timingMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(TimingActivity.class);
            }
        });
        //搜索
        rlNameSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(SearchActivity.class);
            }
        });
        //明星基金 购买
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (startModel != null) {
                    //跳转基金详情页
                    startDetaiActivity(startModel);
                }
            }
        });
//        //优选定投 立即定投
//        btnInvest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //跳转基金详情页
//                startDetaiActivity(preferredVote);
//            }
//        });
        //滑动
        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                if (swipeRefreshLayout != null) {
                    swipeRefreshLayout.setEnabled(scrollView.getScrollY() == 0);
                }
            }
        });
        //下拉刷新
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getP().loadData();
            }
        });
        //网络错误，请重试
        errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                httpLoadingDialog.visible();
                getP().loadData();
            }
        });

        //明星基金 模块
        llStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (startModel != null) {
                    //跳转基金详情页
                    startDetaiActivity(startModel);
                }
            }
        });
        //指数基金1 模块
        rlFingerOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fingerOne != null) {
                    //跳转基金详情页
                    startDetaiActivity(fingerOne);
                }
            }
        });
        //指数基金2 模块
        rlFingerTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fingertwo != null) {
                    //跳转基金详情页
                    startDetaiActivity(fingertwo);
                }
            }
        });

//        //优选定投 模块
//        llPreferVote.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //跳转基金详情页
//                startDetaiActivity(preferredVote);
//
//            }
//        });
        //专题一
        ivThemeOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (themeList != null && adClickable) {
                    Bundle bundle = new Bundle();
                    bundle.putString(Constant.WEB_LINK, themeList.get(0).getAppurl());
                    startActivity(WebPublicActivity.class, bundle);
                }
            }
        });
        //专题二
        ivThemeTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (themeList != null && adClickable) {
                    Bundle bundle = new Bundle();
                    bundle.putString(Constant.WEB_LINK, themeList.get(1).getAppurl());
                    startActivity(WebPublicActivity.class, bundle);
                }
            }
        });
        //专题三
        ivThemeThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (themeList != null && adClickable) {
                    Bundle bundle = new Bundle();
                    bundle.putString(Constant.WEB_LINK, themeList.get(1).getAppurl());
                    startActivity(WebPublicActivity.class, bundle);
                }
            }
        });

    }

//    /**
//     * 展示banner
//     *
//     * @param bannerList
//     */
//    public void showBanner(List<BannerModel> bannerList) {
//        List<String> bannerUrlList = new ArrayList<>();
//        for (BannerModel bannerModel : bannerList) {
//            bannerUrlList.add(bannerModel.getBannerImageUrl());
//        }
//        XLog.e("qqq", bannerUrlList.size() + "");
//        banner.setImagesUrl(bannerUrlList);
//
//        banner.setOnItemClickListener(new FlyBanner.OnItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
//                showToast("点击了第" + position + "张图片");
//            }
//        });
//    }

    /**
     * 初始化人气产品adapter
     *
     * @return
     */
    public SimpleRecAdapter getPopularityAdapter() {
        PopularityRecycleAdapter adapter = new PopularityRecycleAdapter(context);
        xrvPopularity.setAdapter(adapter);
        adapter.setRecItemClick(new RecyclerItemCallback<ProductModel, PopularityRecycleAdapter.ViewHolder>() {
            @Override
            public void onItemClick(int position, ProductModel model, int tag, PopularityRecycleAdapter.ViewHolder holder) {
                super.onItemClick(position, model, tag, holder);
                switch (tag) {
                    //点击
                    case PopularityRecycleAdapter.ITEM_CLICK:
                        startDetaiActivity(model);
                        break;
                }
            }
        });
        return adapter;
    }

    /**
     * 初始化优选定投adapter
     *
     * @return
     */
    public SimpleRecAdapter getPreferAdapter() {
        PreferRecycleAdapter adapter = new PreferRecycleAdapter(context);
        xrvPrefer.setAdapter(adapter);
        adapter.setRecItemClick(new RecyclerItemCallback<ProductModel, PreferRecycleAdapter.ViewHolder>() {
            @Override
            public void onItemClick(int position, ProductModel model, int tag, PreferRecycleAdapter.ViewHolder holder) {
                super.onItemClick(position, model, tag, holder);
                switch (tag) {
                    //点击
                    case PreferRecycleAdapter.ITEM_CLICK:
                        startDetaiActivity(model);
                        break;
                }
            }
        });
        return adapter;
    }

//    /**
//     * 人气产品数据展示
//     *
//     * @param resps
//     */
//    public void showPopularity(List<PopularityResp> resps) {
//        if (resps != null && resps.size() > 0) {
//            getPopularityAdapter().addData(resps);
//        }
//    }


    /**
     * 请求主页数据
     *
     * @param data
     */
    public void showIndexData(final IndexResp data) {
        swipeRefreshLayout.setRefreshing(false);
        httpLoadingDialog.dismiss();
        if (data != null) {
            swipeRefreshLayout.setVisibility(View.VISIBLE);
            errorView.setVisibility(View.GONE);
            //banner展示
            if (data.getBannerList() != null && data.getBannerList().size() > 0) {
                final ArrayList<BannerModel> bannerList = data.getBannerList();
                //图片地址
                List<String> bannerUrlList = new ArrayList<>();
                for (BannerModel bannerModel : bannerList) {
                    //把图片地址添加到集合中去
                    bannerUrlList.add(bannerModel.getImgurl());
                }
                banner.setImagesUrl(bannerUrlList);

                banner.setOnItemClickListener(new FlyBanner.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
//                        showToast("点击了第" + position + "张图片");
                        if (adClickable) {
                            Bundle bundle = new Bundle();
                            bundle.putString(Constant.WEB_LINK, bannerList.get(position).getAppurl());
                            startActivity(WebPublicActivity.class, bundle);
                        }
                    }
                });
            }

            //明星基金
            if (data.getStarFund() != null) {
                startModel = data.getStarFund();
                RateStyleUtil.rateStyleNoPer(context, tvSevenEarnings, startModel.getFund_rose());
                if (startModel.getFund_rose() != null && startModel.getFund_rose().compareTo(BigDecimal.ZERO) > 0) {
                    tvPercent.setTextColor(context.getResources().getColor(R.color.color_main));
                } else {
                    tvPercent.setTextColor(context.getResources().getColor(R.color.x_green));
                }
//                tvSevenEarnings.setText(BigDecimalUtil.bigdecimalToString() + "%");
                tvStarRateDesc.setText(startModel.getRiseTermDesc());
                tvWanarnings.setText(startModel.getFund_name());
            }

            //微动利专题
            if (data.getThemeList() != null && data.getThemeList().size() > 2) {
                themeList = data.getThemeList();
                ILFactory.getLoader().loadNet(ivThemeOne, themeList.get(0).getImgurl(), null);
                ILFactory.getLoader().loadNet(ivThemeTwo, themeList.get(1).getImgurl(), null);
                ILFactory.getLoader().loadNet(ivThemeThree, themeList.get(2).getImgurl(), null);

            }

            //人气产品
            if (data.getHotFunds() != null && data.getHotFunds().size() > 0) {
                getPopularityAdapter().addData(data.getHotFunds());
            }
            if (data.getIndexFunds() != null && data.getIndexFunds().size() > 1) {

                //指数基金1
                if (data.getIndexFunds().get(0) != null) {
                    fingerOne = data.getIndexFunds().get(0);
                    tvNvshen.setText(fingerOne.getFund_name());
                    RateStyleUtil.rateStyle(context, tvNvshenShouyi, fingerOne.getFund_rose());
//                    tvNvshenShouyi.setText("+" + BigDecimalUtil.bigdecimalToString(fingerOne.getFund_rose()) + "%");
                    tvFingerOneDesc.setText(fingerOne.getRiseTermDesc());

                }
                //指数基金2
                if (data.getIndexFunds().get(1) != null) {
                    fingertwo = data.getIndexFunds().get(1);
                    tvChihuo.setText(fingertwo.getFund_name());
                    RateStyleUtil.rateStyle(context, tvChihuoShouyi, fingertwo.getFund_rose());
//                    tvChihuoShouyi.setText("+" + BigDecimalUtil.bigdecimalToString(fingertwo.getFund_rose()) + "%");
                    tvFingerTwoDesc.setText(fingertwo.getRiseTermDesc());
                }
            }

            //优选定投
            if (data.getFixedFunds() != null && data.getFixedFunds().size() > 0) {
                getPreferAdapter().addData(data.getFixedFunds());
            }

        } else {
            swipeRefreshLayout.setVisibility(View.GONE);
            errorView.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 请求主页数据失败
     */
    public void requestIndexDataFail() {
        swipeRefreshLayout.setRefreshing(false);
        httpLoadingDialog.dismiss();
        errorView.setVisibility(View.VISIBLE);
    }

    private void startDetaiActivity(ProductModel model) {
        //跳转基金详情页
        Bundle bundle = new Bundle();
        bundle.putInt(Constant.WEB_TITLE, R.string.fund_detail);
        bundle.putString(Constant.WEB_LINK, Api.API_BASE_URL + HttpContent.fund_detail);
        bundle.putString(Constant.FUND_DETAIL_CODE, model.getFund_code());
        bundle.putString(Constant.FUND_DETAIL_NAME, model.getFund_name());
        startActivity(FundDetailWebActivity.class, bundle);
    }


    public void checkVersionSuccess(UpdateVersionResp data) {

        if (!"0".equals(data.getUpdradeCode())){

            Intent intent = new Intent();
            intent.setClass(getActivity(), UpdateAppActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("data",data);
            startActivity(intent);
            getActivity().overridePendingTransition(0, 0);
        }
    }
}
