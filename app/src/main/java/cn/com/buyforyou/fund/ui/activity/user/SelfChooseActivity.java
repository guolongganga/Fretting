package cn.com.buyforyou.fund.ui.activity.user;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import cn.com.buyforyou.fund.App;
import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.constant.Constant;
import cn.com.buyforyou.fund.event.InvalidTokenEvent;
import cn.com.buyforyou.fund.model.user.SelfChooseResp;
import cn.com.buyforyou.fund.net.Api;
import cn.com.buyforyou.fund.net.HttpContent;
import cn.com.buyforyou.fund.present.user.SelfChoosePresent;
import cn.com.buyforyou.fund.ui.activity.boot.FundDetailWebActivity;
import cn.com.buyforyou.fund.ui.adapter.user.SelfChooseRecycleAdapter;
import cn.com.buyforyou.fund.utils.RuntimeHelper;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.dialog.httploadingdialog.HttpLoadingDialog;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xrecyclerview.RecyclerAdapter;
import cn.droidlover.xrecyclerview.RecyclerItemCallback;
import cn.droidlover.xrecyclerview.XRecyclerView;

/**
 * Created by ${sunny}
 * data: 2017/12/19
 * 自选基金
 */

public class SelfChooseActivity extends XActivity<SelfChoosePresent> {
    /** 净值 */
    @BindView(R.id.ck_networth) CheckBox ckNetWorth;
    /** 涨跌幅 */
    @BindView(R.id.ck_range) CheckBox ckRange;
    /** 列表 */
    @BindView(R.id.xrv_my_recyler) XRecyclerView xrvMyRecyler;
    /** 返回 */
    @BindView(R.id.head_back) ImageButton headBack;
    /** 标题 */
    @BindView(R.id.head_title) TextView headTitle;
    /** 暂无数据 */
    @BindView(R.id.tv_empty) TextView tvEmpty;

    /** 净值排序 */
    private String sortJz = "1";
    /** 涨跌幅排序 */
    private String sortZdf = "1";
    /** 用户编号 */
    private String userId;
    /** 登录标识 */
    private String token;
    /** 加载圈 */
    private HttpLoadingDialog httpLoadingDialog;

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_self_choose;
    }

    @Override
    public SelfChoosePresent newP() {
        return new SelfChoosePresent();
    }

    @Override
    public void initData(Bundle bundle) {
        headTitle.setText("自选基金");
        xrvMyRecyler.verticalLayoutManager(context);//设置RecycleView类型 - 不设置RecycleView不显示
        httpLoadingDialog = new HttpLoadingDialog(context);
        token = App.getSharedPref().getString(Constant.TOKEN, "");
        userId = App.getSharedPref().getString(Constant.USERID, "");

        httpLoadingDialog.visible();
        getP().selfChooseFund(sortJz, sortZdf, token, userId);
    }

    @Override
    public void initEvents() {
        //返回
        headBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //涨幅
        ckRange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO 排序
                if (ckRange.isChecked()) {
                    //如果选中就是降序
                    sortZdf = "0";
                } else {
                    //未选中升序
                    sortZdf = "1";
                }
                httpLoadingDialog.visible();
                getP().selfChooseFund(sortJz, sortZdf, token, userId);
            }
        });
        //净值
        ckNetWorth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO 排序
                if (ckNetWorth.isChecked()) {
                    //如果选中就是降序
                    sortJz = "0";
                } else {
                    //未选中升序
                    sortJz = "1";
                }
                httpLoadingDialog.visible();
                getP().selfChooseFund(sortJz, sortZdf, token, userId);
            }
        });

    }

    /**
     * 初始化adapter
     *
     * @return
     */
    public RecyclerAdapter getAdapter() {
        SelfChooseRecycleAdapter adapter = new SelfChooseRecycleAdapter(context);
        xrvMyRecyler.setAdapter(adapter);
        adapter.setRecItemClick(new RecyclerItemCallback<SelfChooseResp, SelfChooseRecycleAdapter.ViewHolder>() {
            @Override
            public void onItemClick(int position, SelfChooseResp model, int tag, SelfChooseRecycleAdapter.ViewHolder holder) {
                super.onItemClick(position, model, tag, holder);
                switch (tag) {
                    //点击
                    case SelfChooseRecycleAdapter.ITEM_CLICK:
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
        return adapter;
    }

    /**
     * 数据展示
     *
     * @param item
     */
    public void showData(List<SelfChooseResp> item) {
        httpLoadingDialog.dismiss();
        if (item != null && item.size() > 0) {
            tvEmpty.setVisibility(View.GONE);
            getAdapter().addData(item);
        } else {
            tvEmpty.setVisibility(View.VISIBLE);
        }
    }

    public void requestFundFail() {
        httpLoadingDialog.dismiss();
    }

    /**
     * 已经登出系统，请重新登录
     */
    public void areadyLogout() {
        httpLoadingDialog.dismiss();
        //清除本地缓存，设置成未登录
        RuntimeHelper.getInstance().isInvalidToken();
        //跳转登录界面
        Bundle bundle = new Bundle();
        bundle.putString(Constant.SKIP_SIGN, Constant.SKIP_INDEX_ACTIVITY);
        startActivity(LoginActivity.class, bundle);
    }
}
