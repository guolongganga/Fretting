package cn.com.buyforyou.fund.ui.activity.user;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import cn.com.buyforyou.fund.App;
import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.constant.Constant;
import cn.com.buyforyou.fund.event.CancleDataEvent;
import cn.com.buyforyou.fund.event.InvalidTokenEvent;
import cn.com.buyforyou.fund.model.user.ResultDetailResp;
import cn.com.buyforyou.fund.model.user.StepResp;
import cn.com.buyforyou.fund.present.user.ResultDetailTwoPresent;
import cn.com.buyforyou.fund.utils.BigDecimalUtil;
import cn.com.buyforyou.fund.utils.RuntimeHelper;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.dialog.httploadingdialog.HttpLoadingDialog;
import cn.droidlover.xdroidmvp.mvp.XActivity;

/**
 * 作者：sunnyzeng on 2018/1/23 11:21
 * 描述：结果页 交易详情
 */

public class ResultDetailTwoActivity extends XActivity<ResultDetailTwoPresent> {
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
    /** 状态 */
    @BindView(R.id.tv_transaction_status) TextView tvTransactionStatus;
    /** 原因 */
    @BindView(R.id.tv_transaction_cause) TextView tvTransactionCause;
    /** 申请编号 */
    @BindView(R.id.tv_allot_no) TextView tvAllotNo;
    /** 交易记录的状态 */
    private String recordStatus;
    /** 标题 */
    private String title;
    /** 交易流水号 */
    private String allot_no;
    /** 登录标识 */
    private String token;
    /** 用户编号 */
    private String userId;
    /** 加载框 */
    private HttpLoadingDialog httpLoadingDialog;


    @Override
    public int getLayoutId() {
        return R.layout.activity_user_result_two;
    }

    @Override
    public ResultDetailTwoPresent newP() {
        return new ResultDetailTwoPresent();
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
        getP().succDetailData(allot_no, token, userId);

    }

    @Override
    public void initEvents() {
        headBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    /**
     * 请求结果详情成功
     */
    public void requestDetailSuccess(ResultDetailResp resp) {
        httpLoadingDialog.dismiss();

        recordStatus = resp.getRecord().getTrade_status();
        tvFundName.setText(resp.getRecord().getFund_name());
        tvFundAmount.setText(BigDecimalUtil.bigdecimalToString(resp.getRecord().getFund_amount()) + "元");
        tvBankName.setText(resp.getRecord().getJywater());
        tvAllotNo.setText(resp.getRecord().getAllot_no());

        //进度
        ArrayList<StepResp> stepList = resp.getStepList();

        //交易状态
        tvTransactionStatus.setText(stepList.get(0).getName());
        //原因字体颜色
//        tvTransactionCause.setTextColor(getResources().getColor(R.color.color_696969));
        //资金返回时间
        tvTransactionCause.setText(stepList.get(0).getTime());
    }

    /**
     * 请求结果详情失败
     */
    public void requestDetailFail() {
        httpLoadingDialog.dismiss();
    }

    @Override
    public void onBackPressed() {
        //todo 如果交易状态是 买入撤单成功 或 卖出撤单成功
//        if("".equals(recordStatus))
        EventBus.getDefault().post(new CancleDataEvent());
        super.onBackPressed();
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
