package cn.com.buyforyou.fund.ui.activity.fund;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Editable;
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
import android.widget.Toast;

import cn.com.buyforyou.fund.App;
import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.constant.Constant;
import cn.com.buyforyou.fund.event.InvalidTokenEvent;
import cn.com.buyforyou.fund.model.ApplyBaseInfo;
import cn.com.buyforyou.fund.model.fund.BuyFundResp;
import cn.com.buyforyou.fund.model.fund.FundStatusResp;
import cn.com.buyforyou.fund.model.fund.SellResp;
import cn.com.buyforyou.fund.model.user.BankCardResp;
import cn.com.buyforyou.fund.present.fund.SellPresent;
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
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.dialog.httploadingdialog.HttpLoadingDialog;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.widget.ToastUtils;

/**
 * 作者：sunnyzeng on 2018/1/8 11:55
 * 描述：基金详情页-赎回页面
 */

public class SellActivity extends XActivity<SellPresent> {
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
     * 可用份额
     */
    @BindView(R.id.available_share)
    TextView availableShare;
    /**
     * 全部份额
     */
    @BindView(R.id.btn_all_share)
    Button btnAllShare;
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
     * 巨额赎回处理类型
     */
    @BindView(R.id.tv_deal_type)
    TextView tvDealType;
    /**
     * 最低赎回份额
     */
    @BindView(R.id.tv_min_sell)
    TextView tvMinSell;
    /**
     * 巨额赎回处理
     */
    @BindView(R.id.ll_big_deal)
    LinearLayout llBigDeal;
    /**
     * 横线
     */
    @BindView(R.id.view_line)
    View viewLine;
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
    private SellResp sellResp;
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
     * 巨额赎回处理类型 选中
     */
    private int isSelector = 0;
    /**
     * 巨额赎回处理类型 集合
     */
    private List<ApplyBaseInfo> list;

    /**
     * 绑定UI布局
     *
     * @return
     */
    @Override
    public int getLayoutId() {
        return R.layout.activity_fund_sell;
    }

    /**
     * 绑定控制器
     *
     * @return
     */
    @Override
    public SellPresent newP() {
        return new SellPresent();
    }

    /**
     * 初始化数据及资源
     *
     * @param bundle
     */
    @Override
    public void initData(Bundle bundle) {
        //标题
        headTitle.setText("赎回");
        //加载框
        httpLoadingDialog = new HttpLoadingDialog(context);
        //获取缓存数据
        token = App.getSharedPref().getString(Constant.TOKEN, "");
        userId = App.getSharedPref().getString(Constant.USERID, "");

        if (bundle != null) {
            //基金编号
            fundCode = bundle.getString(Constant.FUND_DETAIL_CODE);
            //基金名称
            fundName = bundle.getString(Constant.FUND_DETAIL_NAME);
            //请求赎回基金的展示页数据
            httpLoadingDialog.visible();
            getP().sellFundPre(token, userId, fundCode);
        }

    }

    /**
     * 刷新银行卡数据
     *
     * @param cardResp
     */
    public void refreshBankView(BankCardResp cardResp) {
        String strimage = cardResp.getBankLogo();
        if (!TextUtils.isEmpty(strimage)) {
            //将Base64图片串转换成Bitmap
            Bitmap bitmap = Base64ImageUtil.base64ToBitmap(strimage);
            bankImage.setImageBitmap(bitmap);
        }
        //银行卡名称和尾号
        bankName.setText(cardResp.getBankName() + "（" + cardResp.getBankNoTail() + "）");
        //银行卡限额
        bankLimit.setText("单笔上限" + cardResp.getLimit_per_payment() + "万，单日限额" + cardResp.getLimit_per_day() + "万");
    }

    /**
     * 监听事件
     */
    @Override
    public void initEvents() {
        //返回
        headBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //修改银行卡
        rlChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转更换银行卡
                startActivity(BankCardActivity.class, Constant.INVEST_BANK_ACTIVITY);
            }
        });

        //全部份额
        btnAllShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //输入框变成全部份额
                etAmount.setText(BigDecimalUtil.bigdecimalToString(sellResp.getEnable_shares()) + "");
            }
        });

        //巨额赎回处理
        llBigDeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //弹出选择器
                PopShow popShow = new PopShow(context, viewLine);
                popShow.showRangeSelector(list, isSelector);
                popShow.setOnClickPop(new PopShow.OnClickPop() {
                    @Override
                    public void setRange(int position) {
                        isSelector = position;
                        tvDealType.setText(list.get(position).getContent());
                    }
                });
            }
        });

        //确认赎回
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String strAmount = getText(etAmount);
                BigDecimal amount = BigDecimal.valueOf(Double.parseDouble(strAmount));
                //表单验证通过才弹出Dialog
                if (noNetWork()) {
                    showNetWorkError();
                    return;
                }
                if (!isNotEmpty(getText(etAmount))) {
                    showToast("请输入赎回份额");
                    return;
                }
                //如果amount小于0，重新填写购买金额
                if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                    showToast("请输入正确的赎回份额");
                    return;
                }
                // 如果amount小于份额，重新填写购买金额
                if (amount.compareTo(sellResp.getMinVar()) < 0) {
                    showToast("最低赎回" + BigDecimalUtil.bigdecimalToString(sellResp.getMinVar()) + "份");
                    return;
                }
                if (amount.compareTo(sellResp.getEnable_shares()) > 0) {
                    showToast("最高赎回" + BigDecimalUtil.bigdecimalToString(sellResp.getEnable_shares()) + "份");
                    return;
                }
                if (sellResp.getEnable_shares().floatValue() < sellResp.getMinVar().floatValue()) {
                    showToast("可用份额低于" + BigDecimalUtil.bigdecimalToString(sellResp.getMinVar()) + "份,请全部赎回");
                    return;
                }


//                int comValue = amount.compareTo(sellResp.getEnable_shares());

                //表示bigdemical大于bigdemical2 输入金额大于可赎回份额
//                if (comValue == 1) {
//                    ToastUtils.show(SellActivity.this, "可赎回份额为" + BigDecimalUtil.bigdecimalToString(sellResp.getEnable_shares()) + "份", Toast.LENGTH_LONG);
//                    return;
//                }

                BigDecimal subValue = sellResp.getEnable_shares().subtract(sellResp.getRemainVar());

//                int comValueTwo = amount.compareTo(subValue);

//                if (comValue != 0 && comValueTwo == 1) {
//                    ToastUtils.show(SellActivity.this, "剩余份额大于" + BigDecimalUtil.bigdecimalToString(subValue) + "份，且不等于" + BigDecimalUtil.bigdecimalToString(sellResp.getEnable_shares()) + "份，请全部赎回", Toast.LENGTH_LONG);
//                    return;
//                }
                DecimalFormat df = new DecimalFormat(",###,##0.00"); //保留两位小数
                String dealAmount = df.format(amount);
                //TODO 弹出框
                fundBuyDialog = new FundBuyDialog
                        .Builder(context)
                        .setFundName(fundName)
                        .setFundAmount("￥" + dealAmount)
                        .setOnTextFinishListener(new FundBuyDialog.OnTextFinishListener() {
                            @Override
                            public void onFinish(String str) {
                                fundBuyDialog.dismiss();
//                                    // 卖出接口
                                getP().sellFund(token, userId, fundCode, sellResp.getTrade_acco(),
                                        str, fundName, strAmount, sellResp.getShare_type(), list.get(isSelector).getCode());
//                                    startActivity(SellSuccessActivity.class);
                            }
                        }).create();
                fundBuyDialog.show();

            }
        });
        etAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (!"".equals(charSequence.toString())) {
                    //如果输入的内容不为空，确认按钮可点击
                    sure.setBackgroundColor(getResources().getColor(R.color.color_4D7BFE));
                    sure.setClickable(true);
                } else {
                    //如果输入的内容为空，按钮不可点击
                    sure.setBackgroundColor(getResources().getColor(R.color.color_B9D1F8));
                    sure.setClickable(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

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
                //TODO 获取银行卡数据
                httpLoadingDialog.visible();
                getP().sellFundPre(token, userId, fundCode);
            }
        }
    }

    /**
     * 刷新银行卡信息 失败
     */
    public void requestFundFail() {
        httpLoadingDialog.dismiss();
    }

    /**
     * 刷新银行卡信息 成功
     */
    public void requestFundSuccess(SellResp data) {
        httpLoadingDialog.dismiss();
        sellResp = data;
        refreshBankView(data.getBankCardPageEntity());
        tvMinSell.setText("（最低赎回" + data.getMinVar() + "份）");
        availableShare.setText(BigDecimalUtil.bigdecimalToString(data.getEnable_shares()) + "份");
        list = data.getFundExceedFlagList();
        tvDealType.setText(list.get(isSelector).getContent());

    }

    /**
     * 立即卖出请求失败
     */
    public void requestSellFail() {

    }

    /**
     * 立即卖出成功
     */
    public void requestSellSuccess(FundStatusResp resp) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constant.ACTIVITY_OBJECT, resp);
//        bundle.putString("12",resp.getFundCode());
        startActivity(SellSuccessActivity.class, bundle);
        finish();

    }

    /**
     * 卖出 密码错误
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
