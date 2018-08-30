package cn.com.buyforyou.fund.ui.fragment.user;

import android.os.Bundle;
import android.view.View;

import cn.com.buyforyou.fund.App;
import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.constant.Constant;
import cn.com.buyforyou.fund.event.InvalidTokenEvent;
import cn.com.buyforyou.fund.model.user.MyBonusResp;
import cn.com.buyforyou.fund.present.user.MyBonusPresent;
import cn.com.buyforyou.fund.model.user.TransactionResp;
import cn.com.buyforyou.fund.ui.activity.user.LoginActivity;
import cn.com.buyforyou.fund.ui.adapter.user.MyBonusRecycleAdapter;
import cn.com.buyforyou.fund.ui.adapter.user.TransactionContentRecycleAdapter;
import com.zhsoft.fretting.ui.widget.StateView;
import cn.com.buyforyou.fund.utils.RuntimeHelper;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.dialog.httploadingdialog.HttpLoadingDialog;
import cn.droidlover.xdroidmvp.mvp.XFragment;
import cn.droidlover.xrecyclerview.RecyclerAdapter;
import cn.droidlover.xrecyclerview.RecyclerItemCallback;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import cn.droidlover.xrecyclerview.XRecyclerView;

/**
 * 作者：sunnyzeng on 2018/1/24 15:46
 * 描述：交易查询
 */

public class MyBonusFragment extends XFragment<MyBonusPresent> {

    @BindView(R.id.content_layout) XRecyclerContentLayout contentLayout;
    private MyBonusRecycleAdapter adapter;
    private final int pageSize = 40;
    /** 基金类型 */
    private String tabType;
    private String fundTabName;
    /** 登录标识 */
    private String token;
    /** 用户编号 */
    private String userId;
//    private StateView errorView;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_user_transaction_content;
    }

    @Override
    public MyBonusPresent newP() {
        return new MyBonusPresent();
    }

    @Override
    public void initData(Bundle bundle) {
        token = App.getSharedPref().getString(Constant.TOKEN, "");
        userId = App.getSharedPref().getString(Constant.USERID, "");

        contentLayout.getSwipeRefreshLayout().setColorSchemeResources(
                R.color.color_main,
                cn.droidlover.xrecyclerview.R.color.x_blue,
                cn.droidlover.xrecyclerview.R.color.x_yellow,
                cn.droidlover.xrecyclerview.R.color.x_green
        );

        contentLayout.getRecyclerView().verticalLayoutManager(context);
        contentLayout.getRecyclerView().setAdapter(getAdapter());
        contentLayout.getRecyclerView().horizontalDivider(R.color.color_F9F9F9, R.dimen.dimen_1);  //设置divider
        //0表示tab的类型
        getP().loadMyBonusData(token, userId, 1, pageSize);

        contentLayout.getRecyclerView()
                .setOnRefreshAndLoadMoreListener(new XRecyclerView.OnRefreshAndLoadMoreListener() {
                    @Override
                    public void onRefresh() {
                        getP().loadMyBonusData(token, userId, 1, pageSize);
                    }

                    @Override
                    public void onLoadMore(int page) {
                        getP().loadMyBonusData(token, userId, page, pageSize);
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



    @Override
    public void initEvents() {
        adapter.setRecItemClick(new RecyclerItemCallback<MyBonusResp, MyBonusRecycleAdapter.ViewHolder>() {
            @Override
            public void onItemClick(int position, MyBonusResp model, int tag, MyBonusRecycleAdapter.ViewHolder holder) {
                super.onItemClick(position, model, tag, holder);
                switch (tag) {
                    //点击
                    case MyBonusRecycleAdapter.ITEM_CLICK:
//                        //跳转 结果页
//                        Bundle bundle = new Bundle();
//                        //交易流水号
//                        bundle.putString(Constant.INVEST_PROTOCOL_ID, model.getAllot_no());
//                        //TODO 得写动态的
//                        bundle.putString(Constant.INVEST_RECORD_STATUS, "定投成功");
//                        startActivity(ResultDetailOneActivity.class, bundle);
                        break;
                }
            }
        });
//        errorView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getP().loadMyBonusData(token, userId, 1, pageSize);
//            }
//        });
    }


    /**
     * 初始化adapter
     *
     * @return
     */
    public RecyclerAdapter getAdapter() {
        if (adapter == null) {
            adapter = new MyBonusRecycleAdapter(context);
        }
        return adapter;
    }

    /**
     * 数据展示
     *
     * @param pageno
     * @param item
     */
    public void showData(int pageno, List<MyBonusResp> item) {

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

    public void showError() {
        contentLayout.showError();
    }

    /**
     * 已经登出系统，请重新登录
     */
    public void areadyLogout() {
//        httpLoadingDialog.dismiss();
//        EventBus.getDefault().post(new InvalidTokenEvent());
        //清除本地缓存，设置成未登录
        RuntimeHelper.getInstance().isInvalidToken();
        //跳转登录界面
        Bundle bundle = new Bundle();
        bundle.putString(Constant.SKIP_SIGN, Constant.SKIP_INDEX_ACTIVITY);
        startActivity(LoginActivity.class, bundle);
    }

}
