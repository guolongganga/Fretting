package cn.com.buyforyou.fund.ui.activity.fund;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import cn.com.buyforyou.fund.App;
import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.constant.Constant;
import cn.com.buyforyou.fund.event.ChangeTabEvent;
import cn.com.buyforyou.fund.model.fund.InvestResp;
import cn.com.buyforyou.fund.model.fund.InvestSureResp;
import cn.com.buyforyou.fund.present.fund.InvestSuccessPresent;
import cn.com.buyforyou.fund.ui.activity.MainActivity;
import cn.com.buyforyou.fund.ui.activity.user.LoginActivity;
import com.zhsoft.fretting.ui.widget.ChenJingET;
import cn.com.buyforyou.fund.utils.RuntimeHelper;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.dialog.httploadingdialog.HttpLoadingDialog;
import cn.droidlover.xdroidmvp.mvp.XActivity;

/**
 * 作者：sunnyzeng on 2018/1/9 10:57
 * 描述：定投页面
 */

public class InvestSuccessActivity extends XActivity<InvestSuccessPresent> {
    /** 返回 */
    @BindView(R.id.head_back) ImageButton headBack;
    /** 标题 */
    @BindView(R.id.head_title) TextView headTitle;
    /** 基金名称 */
    @BindView(R.id.tv_fund_name) TextView tvFundName;
    /** 基金代码 */
    @BindView(R.id.tv_fund_code) TextView tvFundCode;
    /** 定投日 */
    @BindView(R.id.tv_day) TextView tvDay;
    /** 购买金额 */
    @BindView(R.id.tv_fund_amount) TextView tvFundAmount;
    /** 银行名称 */
    @BindView(R.id.tv_bank_name) TextView tvBankName;
    /** 银行尾号 */
    @BindView(R.id.tv_last_number) TextView tvLastNumber;
    /** 扣款日期和星期 */
    @BindView(R.id.tv_day_week) TextView tvDayWeek;
    /** 完成按钮 */
    @BindView(R.id.sure) Button sure;
    /** 返回数据 */
    private InvestSureResp sureResp;
    /** 登录标识 */
    private String token;
    /** 用户编号 */
    private String userId;
    /** 基金代码 */
    private String fundCode;
    /** 加载圈 */
    private HttpLoadingDialog httpLoadingDialog;

    @Override
    public int getLayoutId() {
        return R.layout.activity_fund_invest_success;
    }

    @Override
    public InvestSuccessPresent newP() {
        return new InvestSuccessPresent();
    }

    @Override
    public void initData(Bundle bundle) {
        //解决键盘弹出遮挡不滚动问题
        ChenJingET.assistActivity(context);
        //初始化加载圈
        httpLoadingDialog = new HttpLoadingDialog(context);
        //标题设置
        headBack.setVisibility(View.GONE);
        headTitle.setText("定投详情");
        //获取缓存数据
        token = App.getSharedPref().getString(Constant.TOKEN, "");
        userId = App.getSharedPref().getString(Constant.USERID, "");
        //获取页面参数
        if (bundle != null) {
            fundCode = bundle.getString(Constant.FUND_DETAIL_CODE);
            sureResp = bundle.getParcelable(Constant.INVEST_SUCCESS_OBJECT);
        }
        //请求定投详情数据
        httpLoadingDialog.visible();
        getP().investSuccessData(token, userId, fundCode, sureResp.getScheduled_protocol_id());

    }

    @Override
    public void initEvents() {
        /*完成*/
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backDetal();
            }
        });
    }

    /**
     * 请求失败
     */
    public void requestFail() {
        httpLoadingDialog.dismiss();
    }

    /**
     * 请求成功
     *
     * @param investResp
     */
    public void requestSuccess(InvestResp investResp) {
        httpLoadingDialog.dismiss();
        if (investResp != null) {
            //基金名称
            tvFundName.setText(investResp.getFund_name());
            //基金代码
            tvFundCode.setText("(" + investResp.getFundCode() + ")");
            //定投日 日
            tvDay.setText(investResp.getFixDateDetails());
            //定投金额
            tvFundAmount.setText(investResp.getApply_sum()+"");
            //支付方式 银行名称
            tvBankName.setText(investResp.getBankCardPageEntity().getBankName());
            //银行尾号
            tvLastNumber.setText("(" + investResp.getBankCardPageEntity().getBankNoTail() + ")");
            //定投扣款 日期 星期
            tvDayWeek.setText(investResp.getExchdate() + "  " + investResp.getExchWeek());

        }

    }

    /**
     * 返回按键
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        backDetal();
    }

    /**
     * 返回键和完成按钮处理
     */
    private void backDetal() {
        EventBus.getDefault().post(new ChangeTabEvent(Constant.MAIN_MY));
        startActivity(MainActivity.class);
        finish();
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
