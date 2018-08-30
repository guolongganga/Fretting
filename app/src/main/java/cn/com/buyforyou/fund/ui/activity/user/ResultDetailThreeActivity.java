package cn.com.buyforyou.fund.ui.activity.user;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.com.buyforyou.fund.App;
import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.constant.Constant;
import cn.com.buyforyou.fund.event.InvalidTokenEvent;
import cn.com.buyforyou.fund.model.user.ResultDetailResp;
import cn.com.buyforyou.fund.model.user.StepResp;
import cn.com.buyforyou.fund.present.user.ResultDetailThreePresent;
import cn.com.buyforyou.fund.utils.BigDecimalUtil;
import cn.com.buyforyou.fund.utils.RuntimeHelper;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.dialog.httploadingdialog.HttpLoadingDialog;
import cn.droidlover.xdroidmvp.mvp.XActivity;

/**
 * 作者：sunnyzeng on 2018/1/23 11:21
 * 描述：结果页 买入确认成功 卖出确认成功
 * 标题：交易详情/卖出详情
 */

public class ResultDetailThreeActivity extends XActivity<ResultDetailThreePresent> {
    /** 返回 */
    @BindView(R.id.head_back) ImageButton headBack;
    /** 标题 */
    @BindView(R.id.head_title) TextView headTitle;
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
    /** 进度 */
    @BindView(R.id.ll_progress_info) LinearLayout llProgressInfo;
    /** 确认金额 */
    @BindView(R.id.tv_sure_amount) TextView tvSureAmount;
    /** 确认份额 */
    @BindView(R.id.tv_sure_share) TextView tvSureShare;
    /** 确认净值 */
    @BindView(R.id.tv_sure_rate) TextView tvSureRate;
    /** 确认日期 */
    @BindView(R.id.tv_sure_date) TextView tvSureDate;
    /** 手续费 */
    @BindView(R.id.tv_fee_sx) TextView tvFeeSx;
    /** 确认信息 */
    @BindView(R.id.ll_sure_info) LinearLayout llSureInfo;
    /** 申请编号 */
    @BindView(R.id.tv_allot_no) TextView tvAllotNo;
//    /** 交易记录的状态 */
//    private String recordStatus;
    /** 交易流水号 */
    private String allot_no;
    /** 登录标识 */
    private String token;
    /** 用户编号 */
    private String userId;
    /** 加载框 */
    private HttpLoadingDialog httpLoadingDialog;
    private String title;


    @Override
    public int getLayoutId() {
        return R.layout.activity_user_result_three;
    }

    @Override
    public ResultDetailThreePresent newP() {
        return new ResultDetailThreePresent();
    }

    @Override
    public void initData(Bundle bundle) {
        httpLoadingDialog = new HttpLoadingDialog(context);
        if (bundle != null) {
//            recordStatus = bundle.getString(Constant.INVEST_RECORD_STATUS);
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

        //确认金额
        tvSureAmount.setText(BigDecimalUtil.bigdecimalToString(resp.getRecord().getTrade_confirm_balance()));
        //确认份额
        tvSureNumber.setText(BigDecimalUtil.bigdecimalToString(resp.getRecord().getTrade_confirm_shares()));
        //确认净值
        tvSureRate.setText(resp.getRecord().getNet_value());
        //确认日期
        tvSureDate.setText(resp.getRecord().getAffirm_date());
        //手续费
        tvFeeSx.setText(BigDecimalUtil.bigdecimalToString(resp.getRecord().getFare_sx()));

    }

    /**
     * 请求结果详情失败
     */
    public void requestDetailFail() {
        httpLoadingDialog.dismiss();
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
