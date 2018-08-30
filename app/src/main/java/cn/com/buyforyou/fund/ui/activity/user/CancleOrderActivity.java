package cn.com.buyforyou.fund.ui.activity.user;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import cn.com.buyforyou.fund.App;
import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.constant.Constant;
import cn.com.buyforyou.fund.event.InvalidTokenEvent;
import cn.com.buyforyou.fund.event.RefreshUserDataEvent;
import cn.com.buyforyou.fund.model.user.CancleOrderResp;
import cn.com.buyforyou.fund.present.user.CancleOrderPresent;
import cn.com.buyforyou.fund.ui.adapter.user.CancleOrderRecyleAdapter;
import com.zhsoft.fretting.ui.widget.CustomDialog;
import com.zhsoft.fretting.ui.widget.FundBuyDialog;
import cn.com.buyforyou.fund.utils.RuntimeHelper;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.dialog.httploadingdialog.HttpLoadingDialog;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xrecyclerview.RecyclerItemCallback;
import cn.droidlover.xrecyclerview.XRecyclerView;

/**
 * 作者：sunnyzeng on 2018/1/25 11:39
 * 描述：撤单
 */

public class CancleOrderActivity extends XActivity<CancleOrderPresent> {
    @BindView(R.id.head_back) ImageButton headBack;
    @BindView(R.id.head_title) TextView headTitle;
    @BindView(R.id.xrv_my_invest) XRecyclerView xrvMyInvest;
    @BindView(R.id.tv_empty) TextView tvEmpty;
    /** 登录标识 */
    private String token;
    /** 用户编号 */
    private String userId;
    /** 加载圈 */
    private HttpLoadingDialog httpLoadingDialog;
    /** 输入密码弹框 */
    private FundBuyDialog fundBuyDialog;
    /** 密码错误弹框 */
    private CustomDialog errorDialog;

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_cancle_order;
    }

    @Override
    public CancleOrderPresent newP() {
        return new CancleOrderPresent();
    }

    @Override
    public void initData(Bundle bundle) {
        headTitle.setText("撤单");
        //获取用户缓存的userid 和 token
        httpLoadingDialog = new HttpLoadingDialog(context);
        //获取本地缓存
        userId = App.getSharedPref().getString(Constant.USERID, "");
        token = App.getSharedPref().getString(Constant.TOKEN, "");
        xrvMyInvest.verticalLayoutManager(context);//设置RecycleView类型 - 不设置RecycleView不显示

        //请求撤单列表接口
        httpLoadingDialog.visible();
        getP().cancleOrderListData(token, userId);
    }

    @Override
    public void initEvents() {
        headBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    /**
     * 初始化定投计划adapter
     *
     * @return
     */
    public SimpleRecAdapter getCancleOrderAdapter() {
        CancleOrderRecyleAdapter adapter = new CancleOrderRecyleAdapter(context);
        xrvMyInvest.setAdapter(adapter);
        adapter.setRecItemClick(new RecyclerItemCallback<CancleOrderResp, CancleOrderRecyleAdapter.ViewHolder>() {
            @Override
            public void onItemClick(int position, final CancleOrderResp model, int tag, CancleOrderRecyleAdapter.ViewHolder holder) {
                super.onItemClick(position, model, tag, holder);
                switch (tag) {
                    //点击
                    case CancleOrderRecyleAdapter.ITEM_CLICK:
//                        String title;
//                        //（1、买入 0、卖出）
//                        if ("1".equals(model.getBuyType())) {
//                            title = getString(R.string.result_title_buy);
//                        } else {
//                            title = getString(R.string.result_title_sell);
//                        }
//                        Bundle bundle = new Bundle();
//                        //交易流水号
//                        bundle.putString(Constant.INVEST_PROTOCOL_ID, model.getAllot_no());
//                        bundle.putString(Constant.ACTIVITY_TITLE, title);
//                        startActivity(ResultDetailOneActivity.class, bundle);
                        fundBuyDialog = new FundBuyDialog
                                .Builder(context)
                                .setHintText("注意：撤单不可以恢复")
                                .setOnTextFinishListener(new FundBuyDialog.OnTextFinishListener() {
                                    @Override
                                    public void onFinish(String str) {
                                    }
                                }).setPositiveButton("确定", new FundBuyDialog.OnPositiveButtonListener() {
                                    @Override
                                    public void onButtonClick(DialogInterface dialog, String str) {
                                        dialog.dismiss();
                                        httpLoadingDialog.visible();
                                        getP().withdrawApplyOperate(model.getAllot_no(), str, model.getFund_code(), token, userId);

                                    }
                                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                }).create();

                        fundBuyDialog.show();


                        break;
                }


            }
        });
        return adapter;
    }

    /**
     * 请求撤单列表成功
     */
    public void requestOrderSuccess(ArrayList<CancleOrderResp> list) {
        httpLoadingDialog.dismiss();
        if (list != null && list.size() > 0) {
            getCancleOrderAdapter().addData(list);
            xrvMyInvest.setVisibility(View.VISIBLE);
            tvEmpty.setVisibility(View.GONE);
        } else {
            xrvMyInvest.setVisibility(View.GONE);
            tvEmpty.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 请求撤单列表失败
     */
    public void requestOrderFail() {
        httpLoadingDialog.dismiss();
    }

    /**
     * 销毁
     */
    @Override
    protected void onDestroy() {
        if (fundBuyDialog != null) {
            fundBuyDialog.dismiss();
            fundBuyDialog = null;
        }
        if (errorDialog != null) {
            errorDialog.dismiss();
            errorDialog = null;
        }
        super.onDestroy();
    }

//    /**
//     * 刷新撤单列表
//     *
//     * @param event
//     */
//    @Subscribe(threadMode = ThreadMode.POSTING)
//    public void refreshCancleData(CancleDataEvent event) {
//        //请求撤单列表接口
//        httpLoadingDialog.visible();
//        getP().cancleOrderData(token, userId);
//    }

    /**
     * 撤单 密码错误
     * @param message
     */
    public void passwordError(String message) {
        httpLoadingDialog.dismiss();
        if (errorDialog == null) {
            errorDialog = new CustomDialog.Builder(context)
                    .setMessage(message)
                    .setNegativeButton("忘记密码", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            errorDialog.dismiss();
                            startActivity(FindPwdTradeFirstActivity.class);
                        }
                    }).setPositiveButton("再试一次", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            errorDialog.dismiss();
                            fundBuyDialog.show();
                        }
                    }).create();
        }
        errorDialog.show();
    }

    /**
     * 撤单失败
     */
    public void requestCancleFail() {
        httpLoadingDialog.dismiss();
    }

    /**
     * 撤单成功
     */
    public void requestCancleSuccess() {
        httpLoadingDialog.dismiss();
        showToast("撤单成功");
        EventBus.getDefault().post(new RefreshUserDataEvent());
        httpLoadingDialog.visible();
        getP().cancleOrderListData(token, userId);
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
