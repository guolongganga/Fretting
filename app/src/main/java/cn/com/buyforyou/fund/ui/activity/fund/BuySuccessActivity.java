package cn.com.buyforyou.fund.ui.activity.fund;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.constant.Constant;
import cn.com.buyforyou.fund.event.ChangeTabEvent;
import cn.com.buyforyou.fund.model.fund.FundStatusResp;
import cn.com.buyforyou.fund.model.user.StepResp;
import cn.com.buyforyou.fund.ui.activity.MainActivity;
import com.zhsoft.fretting.ui.widget.ChenJingET;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.mvp.XActivity;

/**
 * 作者：sunnyzeng on 2018/1/8 17:59
 * 描述：购买成功页
 */

public class BuySuccessActivity extends XActivity {
    /*返回*/
    @BindView(R.id.head_back) ImageButton headBack;
    /*标题*/
    @BindView(R.id.head_title) TextView headTitle;
    /*关闭*/
    @BindView(R.id.head_right) Button headRight;
    /*基金名称*/
    @BindView(R.id.tv_fund_name) TextView tvFundName;
    /*金额*/
    @BindView(R.id.tv_fund_amount) TextView tvFundAmount;
    /*银行信息*/
    @BindView(R.id.tv_bank_name) TextView tvBankName;
    /*支付成功 图标*/
    @BindView(R.id.iv_pay_success) ImageView ivPaySuccess;
    /*支付成功 时间*/
    @BindView(R.id.tv_pay_success) TextView tvPaySuccess;
    /*支付成功*/
    @BindView(R.id.font_pay_success) TextView fontPaySuccess;
    /*基金公司确认份额 图标*/
    @BindView(R.id.iv_sure_number) ImageView ivSureNumber;
    /*基金公司确认份额 时间*/
    @BindView(R.id.tv_sure_number) TextView tvSureNumber;
    /*基金公司确认份额*/
    @BindView(R.id.font_sure_number) TextView fontSureNumber;
    /*查询收益 图标*/
    @BindView(R.id.iv_query_income) ImageView ivQueryIncome;
    /*查询收益 时间*/
    @BindView(R.id.tv_query_income) TextView tvQueryIncome;
    /*查询收益*/
    @BindView(R.id.font_query_income) TextView fontQueryIncome;
    /*ScrollView滑动*/
    @BindView(R.id.scroll_view) ScrollView scrollView;
    /*进度线一*/
    @BindView(R.id.line_one) View lineOne;
    /*进度线二*/
    @BindView(R.id.line_two) View lineTwo;
    /** 购买成功传递过来的数据 */
    private FundStatusResp statusResp;

    @Override
    public int getLayoutId() {
        return R.layout.activity_fund_buy_success;
    }

    @Override
    public Object newP() {
        return null;
    }

    @Override
    public void initData(Bundle bundle) {
        //解决键盘弹出遮挡不滚动问题
        ChenJingET.assistActivity(context);
        //初始化标题栏
        headBack.setVisibility(View.GONE);
        headTitle.setText("购买结果");
        headRight.setText("关闭");
        headRight.setVisibility(View.VISIBLE);
        //获取传递过来的值
        if (bundle != null) {
            statusResp = (FundStatusResp) bundle.getParcelable(Constant.BUY_SUCCESS_OBJECT);
            if (statusResp != null) {
                //基金名称
                tvFundName.setText(statusResp.getFundName());
                //金额
                tvFundAmount.setText(statusResp.getShares());
                //银行卡信息
                tvBankName.setText(statusResp.getBankCardPageEntity().getBankName() + "   储蓄卡 （" + statusResp.getBankCardPageEntity().getBankNoTail() + "）");
                //进度
                ArrayList<StepResp> stepList = statusResp.getStepList();

                //第一步
                if ("1".equals(stepList.get(0).getIscpl())) {
                    //选中
                    ivPaySuccess.setImageResource(R.drawable.icon_progress_choose);
                } else {
                    //未选中
                    ivPaySuccess.setImageResource(R.drawable.icon_progress_unchoose);
                }
                fontPaySuccess.setText(stepList.get(0).getName());
                tvPaySuccess.setText(stepList.get(0).getTime());

                //第二步
                if ("1".equals(stepList.get(1).getIscpl())) {
                    //选中
                    ivSureNumber.setImageResource(R.drawable.icon_progress_choose);
                    lineOne.setBackgroundColor(getResources().getColor(R.color.color_DC6F5A));
                } else {
                    ivSureNumber.setImageResource(R.drawable.icon_progress_unchoose);
                    lineOne.setBackgroundColor(getResources().getColor(R.color.color_D8D8D8));
                }
                fontSureNumber.setText(stepList.get(1).getName());
                tvSureNumber.setText(stepList.get(1).getTime());

                //第三步
                if ("1".equals(stepList.get(2).getIscpl())) {
                    //选中
                    lineTwo.setBackgroundColor(getResources().getColor(R.color.color_DC6F5A));
                    ivQueryIncome.setImageResource(R.drawable.icon_progress_choose);
                } else {
                    lineTwo.setBackgroundColor(getResources().getColor(R.color.color_D8D8D8));
                    ivQueryIncome.setImageResource(R.drawable.icon_progress_unchoose);
                }
                fontQueryIncome.setText(stepList.get(2).getName());
                tvQueryIncome.setText(stepList.get(2).getTime());
            }
        }
    }

    @Override
    public void initEvents() {
        /*返回*/
        headRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backDeal();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        backDeal();
    }

    /**
     * 返回到我的界面
     */
    private void backDeal() {
        EventBus.getDefault().post(new ChangeTabEvent(Constant.MAIN_MY));
        startActivity(MainActivity.class);
        finish();
    }

}
