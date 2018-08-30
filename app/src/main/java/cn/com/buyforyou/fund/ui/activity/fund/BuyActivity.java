package cn.com.buyforyou.fund.ui.activity.fund;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.com.buyforyou.fund.App;
import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.constant.Constant;
import cn.com.buyforyou.fund.event.InvalidTokenEvent;
import cn.com.buyforyou.fund.event.RefreshUserDataEvent;
import cn.com.buyforyou.fund.model.ApplyBaseInfo;
import cn.com.buyforyou.fund.model.fund.BuyFundResp;
import cn.com.buyforyou.fund.model.fund.BuyNowResp;
import cn.com.buyforyou.fund.model.fund.CalculationResp;
import cn.com.buyforyou.fund.model.fund.FundStatusResp;
import cn.com.buyforyou.fund.present.fund.BuyPresent;
import cn.com.buyforyou.fund.ui.activity.user.BankCardActivity;
import cn.com.buyforyou.fund.ui.activity.user.FindPwdTradeFirstActivity;
import cn.com.buyforyou.fund.ui.activity.user.LoginActivity;
import com.zhsoft.fretting.ui.widget.CustomDialog;
import com.zhsoft.fretting.ui.widget.FundBuyDialog;
import com.zhsoft.fretting.ui.widget.PopShow;
import cn.com.buyforyou.fund.utils.Base64ImageUtil;
import cn.com.buyforyou.fund.utils.BigDecimalUtil;
import cn.com.buyforyou.fund.utils.RuntimeHelper;

import org.greenrobot.eventbus.EventBus;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.logging.Logger;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.dialog.httploadingdialog.HttpLoadingDialog;
import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.net.NetError;

/**
 * 作者：sunnyzeng on 2018/1/8 11:55
 * 描述：基金详情页-购买页面
 */

public class BuyActivity extends XActivity<BuyPresent> {
    /**
     * 返回
     */
    @BindView(R.id.head_back)
    ImageButton headBack;
    /**
     * 标题
     */
    @BindView(R.id.head_title)
    TextView headTitle;
    /**
     * 银行logo
     */
    @BindView(R.id.bank_image)
    ImageView bankImage;
    /**
     * 银行卡名称和尾号
     */
    @BindView(R.id.bank_name)
    TextView bankName;
    /**
     * 银行卡限额
     */
    @BindView(R.id.bank_limit)
    TextView bankLimit;
    /**
     * 更换银行卡
     */
    @BindView(R.id.rl_change)
    RelativeLayout rlChange;
    /**
     * 申购费
     */
    @BindView(R.id.tv_apply_fee)
    TextView tvApplyFee;
    /**
     * 确认份额时间
     */
    @BindView(R.id.tv_sure_time)
    TextView tvSureTime;
    /**
     * 查看收益时间
     */
    @BindView(R.id.tv_look_time)
    TextView tvLookTime;
    /**
     * 购买金额
     */
    @BindView(R.id.et_amount)
    EditText etAmount;
    /**
     * 确认购买
     */
    @BindView(R.id.sure)
    Button sure;
    /**
     * 分红方式
     */
    @BindView(R.id.ll_bonus)
    LinearLayout llBonus;
    /**
     * 分红方式
     */
    @BindView(R.id.tv_bonus_type)
    TextView tvBonusType;
    /**
     * 手续费
     */
    @BindView(R.id.tv_poundage)
    TextView tvPoundage;
    /**
     * 费率
     */
    @BindView(R.id.tv_rate)
    TextView tvRate;
    /**
     * 输入密码弹框
     */
    private FundBuyDialog fundBuyDialog;
    /**
     * 密码错误弹框
     */
    private CustomDialog customDialog;
    /**
     * 得到的用户购买准备数据
     */
    private BuyFundResp buyFundResp;
    /**
     * 基金代码
     */
    private String fundCode;
    /**
     * 基金名称
     */
    private String fundName;
    /**
     * 登录标识
     */
    private String token;
    /**
     * 用户编号
     */
    private String userId;
    /**
     * 加载框
     */
    private HttpLoadingDialog httpLoadingDialog;
    /**
     * 分红方式 选中选项
     */
    private int isSelector = 0;
    /**
     * 分红方式
     */
    private List<ApplyBaseInfo> list;


    @Override
    public int getLayoutId() {
        return R.layout.activity_fund_buy;
    }

    @Override
    public BuyPresent newP() {
        return new BuyPresent();
    }

    @Override
    public void initData(Bundle bundle) {
        headTitle.setText(R.string.fund_buy);
        httpLoadingDialog = new HttpLoadingDialog(context);
        //获取缓存数据
        token = App.getSharedPref().getString(Constant.TOKEN, "");
        userId = App.getSharedPref().getString(Constant.USERID, "");
        //中间横线
        tvApplyFee.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        // 抗锯齿
        tvApplyFee.getPaint().setAntiAlias(true);
        tvPoundage.setText("申购费  0.00元");

        if (bundle != null) {
            fundCode = bundle.getString(Constant.FUND_DETAIL_CODE);
            fundName = bundle.getString(Constant.FUND_DETAIL_NAME);
            buyFundResp = (BuyFundResp) bundle.getParcelable(Constant.BUY_FUND_OBJECT);
            //获取到基金数据
            //获取 图片Base64 字符串
            if (buyFundResp != null) {
                refreshBankView(buyFundResp);
                list = buyFundResp.getDefault_auto_buy();
                if (list != null && list.size() > 0) {
                    tvBonusType.setText(list.get(isSelector).getContent());
                }
                //确认时间
                tvSureTime.setText("·" + buyFundResp.getInfo1());
                //查看收益时间
                tvLookTime.setText("·" + buyFundResp.getInfo2());
                tvRate.setText(buyFundResp.getCurr_rate() + "%");
                tvApplyFee.setText(buyFundResp.getSource_rate() + "%");
                String minValue;
                if (buyFundResp.getLow_value() != null) {
                    minValue = BigDecimalUtil.bigdecimalToString(buyFundResp.getLow_value());
                } else {
                    minValue = "0.00";
                }

                etAmount.setHint("最低购买金额" + minValue + "元");
            }
        }


    }

    /**
     * 刷新银行卡视图
     *
     * @param fundResp
     */
    public void refreshBankView(BuyFundResp fundResp) {
        String strimage = fundResp.getLogo();
        if (!TextUtils.isEmpty(strimage)) {
            //将Base64图片串转换成Bitmap
            Bitmap bitmap = Base64ImageUtil.base64ToBitmap(strimage);
            bankImage.setImageBitmap(bitmap);
        }
        //银行卡名称和尾号
        bankName.setText(fundResp.getName());
        //银行卡限额
        bankLimit.setText(fundResp.getInfo());
    }

    @Override
    public void initEvents() {
        etAmount.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(2)});
        /*返回*/
        headBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        /*确定购买*/
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String strAmount = getText(etAmount);

                //表单验证通过才弹出Dialog
                if (noNetWork()) {
                    showNetWorkError();
                    return;
                }
                if (!isNotEmpty(getText(etAmount))) {
                    showToast("请输入购买金额");
                    return;
                }
                BigDecimal amount = BigDecimal.valueOf(Double.parseDouble(strAmount));
                //如果amount小于0，重新填写购买金额
                if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                    showToast("请输入正确的投资金额");
                    return;
                }
                //如果amount小于最小购买金额，重新填写购买金额
                if (amount.compareTo(buyFundResp.getLow_value()) < 0) {
                    showToast("最小投资金额为" + BigDecimalUtil.bigdecimalToString(buyFundResp.getLow_value()) + "元");
                    return;
                }
                //如果amount大于最大购买金额，重新填写购买金额
                XLog.d(buyFundResp.getHigh_value()+"");
                if (amount.compareTo(buyFundResp.getHigh_value()) > 0) {
                    showToast("最大投资金额为" + BigDecimalUtil.bigdecimalToString(buyFundResp.getHigh_value()) + "元");
                    return;
                }
                //格式化输入金额
//                DecimalFormat df = new DecimalFormat(",###,##0.00"); //保留两位小数
//                String dealAmount = df.format(amount);
                //弹出框
                fundBuyDialog = new FundBuyDialog
                        .Builder(context)
                        .setFundName(fundName)
                        .setFundAmount("￥" + amount)
                        .setOnTextFinishListener(new FundBuyDialog.OnTextFinishListener() {
                            @Override
                            public void onFinish(String str) {
                                fundBuyDialog.dismiss();
                                httpLoadingDialog.visible();
                                getP().purchase(token, userId, fundCode, strAmount, str, list.get(isSelector).getCode());

                            }
                        }).create();
                fundBuyDialog.show();

            }
        });
        /*变更银行卡*/
        rlChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(BankCardActivity.class, Constant.INVEST_BANK_ACTIVITY);
            }
        });
        /*选择分红方式*/
        llBonus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopShow popShow = new PopShow(context, llBonus);
                popShow.showRangeSelector(list, isSelector);
                popShow.setOnClickPop(new PopShow.OnClickPop() {
                    @Override
                    public void setRange(int position) {
                        isSelector = position;
                        tvBonusType.setText(list.get(position).getContent());
                    }
                });
            }
        });
        /*金额输入监控*/
        etAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!"".equals(s.toString())) {
                    //如果输入的内容不为空，查询费率,确认按钮可点击
                    sure.setBackgroundColor(getResources().getColor(R.color.color_4D7BFE));
                    getP().buyFundCalculation(token, userId, fundCode, getText(etAmount));
                    sure.setClickable(true);
                } else {
                    tvPoundage.setText("申购费  0.00元");
                    tvRate.setText(buyFundResp.getCurr_rate() + "%");
                    tvApplyFee.setText(buyFundResp.getSource_rate() + "%");
                    //如果输入的内容为空，则不显示申购费，按钮不可点击
                    sure.setBackgroundColor(getResources().getColor(R.color.color_B9D1F8));
                    sure.setClickable(false);

                }
            }
        });
    }

    /**
     * 修改了银行卡就刷新本页面数据
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.INVEST_BANK_ACTIVITY && resultCode == Constant.SUCCESS_BACK_BANK) {
            String isChange = data.getStringExtra(Constant.CHANGE_BANK);
            //如果修改了银行卡就刷新本页面数据
            if (Constant.CHANGE_BANK_SUCCESS.equals(isChange)) {
                //获取银行卡数据
                httpLoadingDialog.visible();
                getP().buyFund(token, userId, fundCode);
            }
        }
    }

    /**
     * 刷新银行卡信息 失败
     */
    public void requestBuyFundFail() {
        httpLoadingDialog.dismiss();
    }

    /**
     * 刷新银行卡信息 成功
     */
    public void requestBuyFundSuccess(BuyFundResp data) {
        httpLoadingDialog.dismiss();
        refreshBankView(data);
    }

    /**
     * 立即购买请求失败
     */
    public void requestBuyNowFail() {
        httpLoadingDialog.dismiss();
    }

    /**
     * 立即购买成功
     *
     * @param data
     */
    public void requestBuyNowSuccess(FundStatusResp data) {
        httpLoadingDialog.dismiss();
        EventBus.getDefault().post(new RefreshUserDataEvent());
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constant.BUY_SUCCESS_OBJECT, data);
        startActivity(BuySuccessActivity.class, bundle);
        finish();

    }

    /**
     * 立即购买 密码错误
     *
     * @param message
     */
    public void passwordError(String message) {
        httpLoadingDialog.dismiss();
        if (customDialog == null) {
            customDialog = new CustomDialog.Builder(context)
                    .setMessage(message)
                    .setNegativeButton("忘记密码", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            customDialog.dismiss();
                            startActivity(FindPwdTradeFirstActivity.class);
                        }
                    }).setPositiveButton("再试一次", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            customDialog.dismiss();
                            fundBuyDialog.show();
                        }
                    }).create();
        }
        customDialog.show();
    }

    /**
     * 销毁
     */
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
        super.onDestroy();
    }

    /**
     * 计算申购费 失败
     */
    public void requestCalculationFail() {
    }

    /**
     * 计算申购费 成功
     *
     * @param calculationResp
     */
    public void requestCalculationSuccess(CalculationResp calculationResp) {
        if (sure.isClickable()) {
            //申购费
            String rate = calculationResp.getFare_sx();
            if (rate.equals("null")) {
                rate = "0.00";
            }
            tvPoundage.setText("申购费  " + rate + "元");
            tvRate.setText(calculationResp.getCurr_rate() + "%");
            tvApplyFee.setText(calculationResp.getSource_rate() + "%");
        }else {
            tvRate.setText(buyFundResp.getCurr_rate() + "%");
            tvApplyFee.setText(buyFundResp.getSource_rate() + "%");
        }
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
