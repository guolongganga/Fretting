package cn.com.buyforyou.fund.ui.activity.fund;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.com.buyforyou.fund.App;
import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.constant.Constant;
import cn.com.buyforyou.fund.event.InvalidTokenEvent;
import cn.com.buyforyou.fund.event.RefreshUserDataEvent;
import cn.com.buyforyou.fund.model.ApplyBaseInfo;
import cn.com.buyforyou.fund.model.fund.GetNextTimeResp;
import cn.com.buyforyou.fund.model.fund.InvestResp;
import cn.com.buyforyou.fund.model.fund.InvestSureResp;
import cn.com.buyforyou.fund.model.user.BankCardResp;
import cn.com.buyforyou.fund.net.Api;
import cn.com.buyforyou.fund.net.HttpContent;
import cn.com.buyforyou.fund.present.fund.InvestPersent;
import cn.com.buyforyou.fund.ui.activity.boot.WebPublicActivity;
import cn.com.buyforyou.fund.ui.activity.user.BankCardActivity;
import cn.com.buyforyou.fund.ui.activity.user.FindPwdTradeFirstActivity;
import cn.com.buyforyou.fund.ui.activity.user.LoginActivity;
import com.zhsoft.fretting.ui.widget.CustomDialog;
import com.zhsoft.fretting.ui.widget.FundBuyDialog;
import com.zhsoft.fretting.ui.widget.MyClickText;
import com.zhsoft.fretting.ui.widget.OnTvClick;
import com.zhsoft.fretting.ui.widget.SelectPopupWindow;
import cn.com.buyforyou.fund.utils.Base64ImageUtil;
import cn.com.buyforyou.fund.utils.BigDecimalUtil;
import cn.com.buyforyou.fund.utils.KeyBoardUtils;
import cn.com.buyforyou.fund.utils.RuntimeHelper;

import org.greenrobot.eventbus.EventBus;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.dialog.httploadingdialog.HttpLoadingDialog;
import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.mvp.XActivity;

/**
 * 作者：sunnyzeng on 2018/1/9 10:57
 * 描述：定投页面
 */

public class InvestActivity extends XActivity<InvestPersent> {
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
     * 银行名称
     */
    @BindView(R.id.bank_name)
    TextView bankName;
    /**
     * 银行限额
     */
    @BindView(R.id.bank_limit)
    TextView bankLimit;
    /**
     * 修改银行卡
     */
    @BindView(R.id.rl_change)
    RelativeLayout rlChange;
    /**
     * 购买金额
     */
    @BindView(R.id.et_amount)
    EditText etAmount;
    /**
     * 定投周期
     */
    @BindView(R.id.tv_invest_week)
    TextView etInvestWeek;
    /**
     * 定投日
     */
    @BindView(R.id.tv_invest_day)
    TextView etInvestDay;
    /**
     * 下次扣款时间
     */
    @BindView(R.id.tv_next_time)
    TextView tvNextTime;
    /**
     * 确定按钮
     */
    @BindView(R.id.sure)
    Button sure;
    /**
     * 勾选框
     */
    @BindView(R.id.register_service_select)
    ImageView registerServiceSelect;
    /**
     * 注册协议
     */
    @BindView(R.id.tv_agreement)
    TextView tvAgreement;
    /**
     * 定投日
     */
    @BindView(R.id.rl_invest_day)
    RelativeLayout rlInvestDay;
    /**
     * 定投周期
     */
    @BindView(R.id.rl_invest_week)
    RelativeLayout rlInvestWeek;

    /**
     * 定投周期选择
     */
    private int cycleSelector = 1;
    /**
     * 定投周期选择编号
     */
    private String cycleSelectorCode;
    /**
     * 定投日选择编号
     */
    private String daySelectorCode;
    /**
     * 周期集合
     */
    private ArrayList<ApplyBaseInfo> cycleList;
    /**
     * 周期对应的定投日总集合
     */
    private Map<String, ArrayList<ApplyBaseInfo>> dayMap;
    /**
     * 周期弹出框
     */
    private SelectPopupWindow cyclePopupWindow;
    /**
     * 交易密码弹出框
     */
    private FundBuyDialog fundBuyDialog;
    /**
     * 结果弹出框
     */
    private CustomDialog customDialog;
    /**
     * 投资页的类型，是定投，还是定投修改
     */
    private String type;
    /**
     * 加载框
     */
    private HttpLoadingDialog httpLoadingDialog;
    /**
     * 登录标识
     */
    private String token;
    /**
     * 用户编号
     */
    private String userId;
    /**
     * 得到的用户购买准备数据
     */
    private InvestResp investResp;
    /**
     * 基金代码
     */
    private String fundCode;
    /**
     * 基金名称
     */
    private String fundName;
    /**
     * 首次交易月
     */
    private String first_trade_month;
    /**
     * 协议编号
     */
    private String protocol_id;
    private float limitPerPay;

    @Override
    public int getLayoutId() {
        return R.layout.activity_fund_invest_update;
    }

    @Override
    public InvestPersent newP() {
        return new InvestPersent();
    }

    @Override
    public void initData(Bundle bundle) {
        //设置标题
        headTitle.setText("定投");
        //初始化加载框
        httpLoadingDialog = new HttpLoadingDialog(context);
        registerServiceSelect.setSelected(false);
        agreementText();

        //获取缓存数据
        token = App.getSharedPref().getString(Constant.TOKEN, "");
        userId = App.getSharedPref().getString(Constant.USERID, "");

        //获取页面类型 定投购买/定投修改
        type = bundle.getString(Constant.INVEST_ACTIVITY_TYPE);
        //如果是定投页面
        if (Constant.INVEST_ACTIVITY.equals(type)) {
            sure.setText("确定定投");
        } else if (Constant.INVEST_ACTIVITY_UPDATE.equals(type)) {
            sure.setText("确定修改");
        }
        //初始化选择器数据
        initWeeklyList();
        //初始化选择器
        initPopWindow();

        if (bundle != null) {
            //基金代码
            fundCode = bundle.getString(Constant.FUND_DETAIL_CODE);
            //基金名称
            fundName = bundle.getString(Constant.FUND_DETAIL_NAME);
            //协议编号
            protocol_id = bundle.getString(Constant.INVEST_PROTOCOL_ID);
            //显示数据
            investResp = (InvestResp) bundle.getParcelable(Constant.INVEST_FUND_OBJECT);
            //获取到基金数据
            //获取 图片Base64 字符串
            if (investResp != null) {
                //刷新银行卡信息
                refreshBankView(investResp.getBankCardPageEntity());

                //最小投资金额
                etAmount.setHint("最低购买金额" + BigDecimalUtil.bigdecimalToString(investResp.getMinPurchaseAmt()) + "元");
                //首次交易月
                first_trade_month = investResp.getFirst_trade_month();
                //下次扣款日
                tvNextTime.setText("注：下次扣款日：" + investResp.getExchdate() + " " + investResp.getExchWeek());
                //定投周期
                cycleSelectorCode = investResp.getProtocol_period_unit();
                for (int i = 0; i < cycleList.size(); i++) {
                    if (cycleList.get(i).getCode().equals(investResp.getProtocol_period_unit())) {
                        cycleSelector = i;
                        etInvestWeek.setText(cycleList.get(i).getContent());
                        break;
                    }
                }
                //定投日
                daySelectorCode = investResp.getFirst_exchdate();
                ArrayList<ApplyBaseInfo> selectList = dayMap.get(cycleList.get(cycleSelector).getContent());
                for (int j = 0; j < selectList.size(); j++) {
                    if (selectList.get(j).getCode().equals(investResp.getFirst_exchdate())) {
                        etInvestDay.setText(selectList.get(j).getContent());
                        break;
                    }
                }

            }
        }

    }

    /**
     * 初始化周期集合
     */
    public void initWeeklyList() {
        //定投周期分类 集合
        cycleList = new ArrayList<>();
        ApplyBaseInfo per_week = new ApplyBaseInfo("1", "每周");
        ApplyBaseInfo per_month = new ApplyBaseInfo("0", "每月");
        cycleList.add(per_week);
        cycleList.add(per_month);
        //周集合
        ArrayList<ApplyBaseInfo> weekList = new ArrayList<>();
        ApplyBaseInfo monday = new ApplyBaseInfo("02", "星期一");
        ApplyBaseInfo tuesday = new ApplyBaseInfo("03", "星期二");
        ApplyBaseInfo wednesday = new ApplyBaseInfo("04", "星期三");
        ApplyBaseInfo thursday = new ApplyBaseInfo("05", "星期四");
        ApplyBaseInfo friday = new ApplyBaseInfo("06", "星期五");
        weekList.add(monday);
        weekList.add(tuesday);
        weekList.add(wednesday);
        weekList.add(thursday);
        weekList.add(friday);

        //日集合
        ArrayList<ApplyBaseInfo> dayList = new ArrayList<>();
        for (int i = 1; i < 29; i++) {
            String stri;
            if (i < 10) {
                stri = "0" + i;
            } else {
                stri = "" + i;
            }
            ApplyBaseInfo dayInfo = new ApplyBaseInfo(stri, stri);
            dayList.add(dayInfo);
        }

        dayMap = new HashMap<>();
        dayMap.put(cycleList.get(0).getContent(), weekList);
        dayMap.put(cycleList.get(1).getContent(), dayList);
    }

    /**
     * 初始化弹出框
     */
    private void initPopWindow() {
        //初始化周期弹出框
        cyclePopupWindow = new SelectPopupWindow(this, cycleList);
        cyclePopupWindow.setCallBack(new SelectPopupWindow.CallBack() {
            @Override
            public void onSelectChange(int currentItem, String name) {
                //上一次选择的周期
                String lastChoose = getText(etInvestWeek);
                //设置选择的内容
                etInvestWeek.setText(name);
                //选择的位置（周期类型）
                cycleSelector = currentItem;
                //获得选择的周期编码
                cycleSelectorCode = cycleList.get(currentItem).getCode();
                //如果周期选择发生改变，则日期选择置空
                if (!getText(etInvestWeek).equals(lastChoose)) {
                    etInvestDay.setText("");
                }
            }
        });
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
        /*定投周期选择*/
        rlInvestWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //关闭当前输入框
                KeyBoardUtils.closeKeybord(InvestActivity.this);
                //显示窗口
                cyclePopupWindow.showAtLocation(etInvestWeek, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
            }
        });
        /*服务协议勾选*/
        registerServiceSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (registerServiceSelect.isSelected()) {
                    registerServiceSelect.setSelected(false);
                } else {
                    registerServiceSelect.setSelected(true);
                }
            }
        });
        /*定投日选择*/
        rlInvestDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //关闭当前输入框
                KeyBoardUtils.closeKeybord(InvestActivity.this);
                if (!isNotEmpty(getText(etInvestWeek))) {
                    showToast("请选择定投周期");
                    return;
                }
                //上一次选择的定投日
                final String lastDayChoose = getText(etInvestDay);
                final ArrayList<ApplyBaseInfo> selectList = dayMap.get(cycleList.get(cycleSelector).getContent());

                //初始化 日弹出框
                SelectPopupWindow dayPopupWindow = new SelectPopupWindow(context, selectList);
                dayPopupWindow.setCallBack(new SelectPopupWindow.CallBack() {
                    @Override
                    public void onSelectChange(int currentItem, String name) {
                        etInvestDay.setText(name);
                        daySelectorCode = selectList.get(currentItem).getCode();
                        if (isNotEmpty(getText(etInvestDay)) && !getText(etInvestDay).equals(lastDayChoose)) {
                            //请求接口得到扣款日期 下次扣款时间：2017-12-18，遇非交易日顺延
                            getP().nextDeductTime(token, userId, fundCode, cycleSelectorCode, daySelectorCode);
                        }
                    }
                });
                //显示窗口
                dayPopupWindow.showAtLocation(etInvestDay, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置

            }
        });
        /*确定*/
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String strAmount = getText(etAmount);

                //表单验证通过才弹出Dialog
                if (noNetWork()) {
                    showNetWorkError();
                    return;
                }
                if (!isNotEmpty(strAmount)) {
                    showToast("请输入购买金额");
                    return;
                }
                BigDecimal amount = BigDecimal.valueOf(Double.parseDouble(strAmount));
                //如果amount小于0，重新填写购买金额
                if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                    showToast("请输入正确的投资金额");
                    return;
                }
                if (Float.parseFloat(strAmount) / 10000 > limitPerPay) {
                    showToast("超出单笔限额");
                    return;
                }
                //如果amount小于最小购买金额，重新填写购买金额

//                if (amount.compareTo(investResp.getMinPurchaseAmt()) < 0) {
                if (Float.parseFloat(strAmount) < investResp.getMinPurchaseAmt().floatValue()) {
                    showToast("最小投资金额为" + BigDecimalUtil.bigdecimalToString(investResp.getMinPurchaseAmt()) + "元");
                    return;
                }
                if (!isNotEmpty(getText(etInvestWeek))) {
                    showToast("请选择定投周期");
                    return;
                }
                if (!isNotEmpty(getText(etInvestDay))) {
                    showToast("请选择定投日");
                    return;
                }
                if (!registerServiceSelect.isSelected()) {
                    showToast("请阅读并同意协议");
                    return;
                }
                //格式化输入金额
//                DecimalFormat df = new DecimalFormat(",###,##0.00"); //保留两位小数
//                String dealAmount = df.format(amount);
                fundBuyDialog = new FundBuyDialog
                        .Builder(context)
                        .setFundName(fundName)
                        .setFundAmount("￥" + amount)
                        .setOnTextFinishListener(new FundBuyDialog.OnTextFinishListener() {
                            @Override
                            public void onFinish(String str) {
                                fundBuyDialog.dismiss();
                                //请求接口 跳转到定投成功
                                //如果是定投页面
                                if (Constant.INVEST_ACTIVITY.equals(type)) {
                                    //确定购买
                                    httpLoadingDialog.visible();
                                    getP().sureInvest(token, userId, fundCode, fundName, strAmount,
                                            first_trade_month, cycleSelectorCode, daySelectorCode, str, null);
                                } else if (Constant.INVEST_ACTIVITY_UPDATE.equals(type)) {
                                    //确定修改 增加protocol_id
                                    httpLoadingDialog.visible();
                                    getP().sureInvest(token, userId, fundCode, fundName, strAmount,
                                            first_trade_month, cycleSelectorCode, daySelectorCode, str, protocol_id);
                                }
                            }
                        }).create();
                fundBuyDialog.show();
            }
        });
        /*更换银行卡*/
        rlChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(BankCardActivity.class, Constant.INVEST_BANK_ACTIVITY);
            }
        });
        /*金额输入监控*/
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
     * 请求我的银行卡信息成功 数据显示
     */
    public void requestInvestSuccess(BankCardResp resp) {
        httpLoadingDialog.dismiss();
        //刷新银行卡数据
        refreshBankView(resp);
    }

    /**
     * 请求我的银行卡信息失败
     */
    public void requestInvestFail() {
        httpLoadingDialog.dismiss();
    }


    /**
     * 请求扣款时间成功 返回扣款时间
     */
    public void requestDeductTimeSuccess(GetNextTimeResp timeResp) {
        first_trade_month = timeResp.getFirst_trade_month();
        tvNextTime.setText("注：下次扣款日：" + timeResp.getExchdate() + " " + timeResp.getExchWeek());
    }

    /**
     * 请求扣款时间失败 返回扣款时间
     */
    public void requestDeductTimeFail() {

    }

    /**
     * 确认定投成功
     */
    public void requestSureInvestSuccess(InvestSureResp sureResp) {
        httpLoadingDialog.dismiss();
        EventBus.getDefault().post(new RefreshUserDataEvent());
        //传值定投成功
        Bundle bundle = new Bundle();
        bundle.putString(Constant.FUND_DETAIL_CODE, fundCode);
        bundle.putParcelable(Constant.INVEST_SUCCESS_OBJECT, sureResp);
        startActivity(InvestSuccessActivity.class, bundle);
    }

    /**
     * 确认购买失败
     */
    public void requestSureInvestFail() {
        httpLoadingDialog.dismiss();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.INVEST_BANK_ACTIVITY && resultCode == Constant.SUCCESS_BACK_BANK) {
            String isChange = data.getStringExtra(Constant.CHANGE_BANK);
            //如果修改了银行卡就刷新本页面数据
            if (Constant.CHANGE_BANK_SUCCESS.equals(isChange)) {
                //获取银行卡数据 判断是否能够定投
                httpLoadingDialog.visible();
                getP().investTime(token, userId, fundCode, fundName);
            }
        }
    }

    /**
     * 刷新银行卡视图
     *
     * @param bankCardResp
     */
    public void refreshBankView(BankCardResp bankCardResp) {
        String strimage = bankCardResp.getBankLogo();
        if (!TextUtils.isEmpty(strimage)) {
            //将Base64图片串转换成Bitmap
            Bitmap bitmap = Base64ImageUtil.base64ToBitmap(strimage);
            bankImage.setImageBitmap(bitmap);
        }
        //银行卡名称和尾号
        bankName.setText(bankCardResp.getBankName() + "（" + bankCardResp.getBankNoTail() + "）");
        //银行限额
        limitPerPay = Float.parseFloat(bankCardResp.getLimit_per_payment());
        bankLimit.setText("单笔限额" + bankCardResp.getLimit_per_payment() + "万，单日限额" + bankCardResp.getLimit_per_day() + "万");

    }

    /**
     * 提交定投数据，返回输入密码错误
     *
     * @param message
     */
    public void passwordError(String message) {
        httpLoadingDialog.dismiss();
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
     * 协议设置下划线和点击事件
     *
     * @return
     */
    public void agreementText() {

        SpannableString spannableString = new SpannableString("我已阅读并同意《定期定额申购协议》");

        MyClickText click1 = new MyClickText(this);
        click1.setOnTvClick(new OnTvClick() {
            @Override
            public void onClick(View widget) {
                Bundle bundle = new Bundle();
//                bundle.putInt(Constant.WEB_TITLE, R.string.user_register_service1);
                bundle.putString(Constant.WEB_LINK, Api.API_BASE_URL + HttpContent.agreement_fdtimesbuy);
                startActivity(WebPublicActivity.class, bundle);
            }
        });

        //设置下划线
        spannableString.setSpan(click1, 7, 17, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//        spannableString.setSpan(click2, 24, 35, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        //当然这里也可以通过setSpan来设置哪些位置的文本哪些颜色
        tvAgreement.setText(spannableString);
        tvAgreement.setMovementMethod(LinkMovementMethod.getInstance());
        tvAgreement.setHighlightColor(Color.TRANSPARENT); //设置点击后的颜色为透明

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
