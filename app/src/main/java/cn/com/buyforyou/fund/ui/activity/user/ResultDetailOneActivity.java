package cn.com.buyforyou.fund.ui.activity.user;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.com.buyforyou.fund.App;
import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.constant.Constant;
import cn.com.buyforyou.fund.event.InvalidTokenEvent;
import cn.com.buyforyou.fund.model.user.ResultDetailResp;
import cn.com.buyforyou.fund.model.user.StepResp;
import cn.com.buyforyou.fund.present.user.ResultDetailOnePresent;
import com.zhsoft.fretting.ui.widget.CustomDialog;
import com.zhsoft.fretting.ui.widget.FundBuyDialog;
import cn.com.buyforyou.fund.utils.BigDecimalUtil;
import cn.com.buyforyou.fund.utils.RuntimeHelper;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.dialog.httploadingdialog.HttpLoadingDialog;
import cn.droidlover.xdroidmvp.mvp.XActivity;

/**
 * 作者：sunnyzeng on 2018/1/23 11:21
 * 描述：结果页 交易详情 只有 进度（第一步，第二步，第三步）
 */

public class ResultDetailOneActivity extends XActivity<ResultDetailOnePresent> {
    /** 返回 */
    @BindView(R.id.head_back) ImageButton headBack;
    /** 标题 */
    @BindView(R.id.head_title) TextView headTitle;
    /** 右侧按钮 */
    @BindView(R.id.head_right) Button headRight;
    /** 基金名称 */
    @BindView(R.id.tv_fund_name) TextView tvFundName;
    /** 购买金额 */
    @BindView(R.id.tv_fund_amount) TextView tvFundAmount;
    /** 银行信息 */
    @BindView(R.id.tv_bank_name) TextView tvBankName;
    /** 第一步 图标 */
    @BindView(R.id.iv_pay_success) ImageView ivPaySuccess;
    /** 第一步 状态 */
    @BindView(R.id.font_pay_success) TextView fontPaySuccess;
    /** 第一步 时间 */
    @BindView(R.id.tv_pay_success) TextView tvPaySuccess;
    /** 第二步 图标 */
    @BindView(R.id.iv_sure_number) ImageView ivSureNumber;
    /** 第二步 时间 */
    @BindView(R.id.tv_sure_number) TextView tvSureNumber;
    /** 第二步 状态 */
    @BindView(R.id.font_sure_number) TextView fontSureNumber;
    /** 第三步 图标 */
    @BindView(R.id.iv_query_income) ImageView ivQueryIncome;
    /** 第三步 时间 */
    @BindView(R.id.tv_query_income) TextView tvQueryIncome;
    /** 第三步 状态 */
    @BindView(R.id.font_query_income) TextView fontQueryIncome;
    /** 申请编号 */
    @BindView(R.id.tv_allot_no) TextView tvAllotNo;
//    /** 交易记录的状态 */
//    private String recordStatus;
    /** 终止弹框 */
    private CustomDialog customDialog;
    /** 交易流水号 */
    private String allot_no;
    /** 登录标识 */
    private String token;
    /** 用户编号 */
    private String userId;
    /** 输入密码弹框 */
    private FundBuyDialog fundBuyDialog;
    /** 密码错误弹框 */
    private CustomDialog errorDialog;
    /** 加载框 */
    private HttpLoadingDialog httpLoadingDialog;
    /** 标题 */
    private String title;


    @Override
    public int getLayoutId() {
        return R.layout.activity_user_result_one;
    }

    @Override
    public ResultDetailOnePresent newP() {
        return new ResultDetailOnePresent();
    }

    @Override
    public void initData(Bundle bundle) {
        httpLoadingDialog = new HttpLoadingDialog(context);
        if (bundle != null) {
            allot_no = bundle.getString(Constant.INVEST_PROTOCOL_ID);
            title = bundle.getString(Constant.ACTIVITY_TITLE);
        }
        headTitle.setText(title);
        //获取用户缓存的userid 和 token
        userId = App.getSharedPref().getString(Constant.USERID, "");
        token = App.getSharedPref().getString(Constant.TOKEN, "");

        httpLoadingDialog.visible();
        getP().withdrawApplyDetail(allot_no, token, userId);

    }


    @Override
    public void initEvents() {
        headBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        headRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (customDialog == null) {
                    customDialog = new CustomDialog.Builder(context)
                            .setMessage("撤单不可以恢复，确认要撤单吗？")
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    customDialog.dismiss();
                                }
                            }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    customDialog.dismiss();
                                    if (fundBuyDialog == null) {
                                        fundBuyDialog = new FundBuyDialog
                                                .Builder(context)
                                                .setOnTextFinishListener(new FundBuyDialog.OnTextFinishListener() {
                                                    @Override
                                                    public void onFinish(String str) {
                                                        fundBuyDialog.dismiss();
                                                        httpLoadingDialog.visible();
                                                        getP().withdrawApplyOperate(allot_no, str, token, userId);

                                                    }
                                                }).create();
                                    }

                                    fundBuyDialog.show();


                                }
                            }).create();
                }
                customDialog.show();
            }
        });
    }

    /**
     * 请求结果详情成功
     */
    public void requestDetailSuccess(ResultDetailResp resp) {
        httpLoadingDialog.dismiss();
        //头部交易信息
        tvFundName.setText(resp.getRecord().getFund_name());
        tvFundAmount.setText(BigDecimalUtil.bigdecimalToString(resp.getRecord().getFund_amount()) + "元");
        tvBankName.setText(resp.getRecord().getJywater());
        tvAllotNo.setText(resp.getRecord().getAllot_no());

        //进度
        ArrayList<StepResp> stepList = resp.getStepList();

        //第一步
        if ("1".equals(stepList.get(0).getIscpl())) {
            //选中
            ivPaySuccess.setSelected(true);
        }
        fontPaySuccess.setText(stepList.get(0).getName());
        tvPaySuccess.setText(stepList.get(0).getTime());

        //第二步
        if ("1".equals(stepList.get(1).getIscpl())) {
            //选中
            ivSureNumber.setSelected(true);
        }
        fontSureNumber.setText(stepList.get(1).getName());
        tvSureNumber.setText(stepList.get(1).getTime());

        //第三步
        if ("1".equals(stepList.get(2).getIscpl())) {
            //选中
            ivQueryIncome.setSelected(true);
        }
        fontQueryIncome.setText(stepList.get(2).getName());
        tvQueryIncome.setText(stepList.get(2).getTime());


        //买入交易成功和卖出交易成功才有撤单
        if ("9".equals(resp.getRecord().getTrade_status()) || "卖出成功".equals(resp.getRecord().getTrade_status())) {          //交易成功，待确认份额
            //右侧撤单按钮
            headRight.setVisibility(View.VISIBLE);
            headRight.setText("撤单");
        } else {
            //右侧撤单按钮
            headRight.setVisibility(View.GONE);
        }

    }

    /**
     * 请求结果详情失败
     */
    public void requestDetailFail() {
        httpLoadingDialog.dismiss();
    }

    /**
     * 撤单成功
     */
    public void requestCancleSuccess() {
        httpLoadingDialog.dismiss();
        //撤单成功
        Bundle bundle = new Bundle();
        //标题
        bundle.putString(Constant.ACTIVITY_TITLE, title);
        //交易流水号
        bundle.putString(Constant.INVEST_PROTOCOL_ID, allot_no);
        startActivity(ResultDetailTwoActivity.class, bundle);
        finish();
    }

    /**
     * 撤单失败
     */
    public void requestCancleFail() {
        httpLoadingDialog.dismiss();
    }

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

    @Override
    protected void onDestroy() {
        if (customDialog != null) {
            customDialog.dismiss();
            customDialog = null;
        }
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
