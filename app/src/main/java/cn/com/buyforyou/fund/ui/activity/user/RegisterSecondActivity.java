package cn.com.buyforyou.fund.ui.activity.user;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.com.buyforyou.fund.App;
import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.constant.Constant;
import cn.com.buyforyou.fund.event.RefreshUserDataEvent;
import cn.com.buyforyou.fund.model.user.BankResp;
import cn.com.buyforyou.fund.net.Api;
import cn.com.buyforyou.fund.net.HttpContent;
import cn.com.buyforyou.fund.present.user.RegisterSecondPresent;
import cn.com.buyforyou.fund.ui.activity.boot.WebPublicActivity;

import com.zhsoft.fretting.ui.widget.ChenJingET;
import com.zhsoft.fretting.ui.widget.MyClickText;
import com.zhsoft.fretting.ui.widget.OnTvClick;

import cn.com.buyforyou.fund.utils.RuntimeHelper;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.dialog.httploadingdialog.HttpLoadingDialog;
import cn.droidlover.xdroidmvp.mvp.XActivity;

/**
 * 作者：sunnyzeng on 2017/12/14 13:54
 * <p>
 * 描述：基金开户 第二步 绑定银行卡
 */

public class RegisterSecondActivity extends XActivity<RegisterSecondPresent> {

    /**
     * 返回按钮
     */
    @BindView(R.id.head_back)
    ImageButton headBack;
    /**
     * 标题
     */
    @BindView(R.id.head_title)
    TextView headTitle;
    /**
     * 用户名
     */
    @BindView(R.id.user_name)
    EditText userName;
    /**
     * 身份证号
     */
    @BindView(R.id.identity)
    EditText identity;
    /**
     * 邮件
     */
    @BindView(R.id.email)
    EditText email;
    /**
     * 银行名称
     */
    @BindView(R.id.banck_name)
    TextView banckName;
    /**
     * 选择银行
     */
    @BindView(R.id.ll_choose_bank)
    LinearLayout llChooseBank;
    /**
     * 银行卡号
     */
    @BindView(R.id.banknumber)
    EditText banknumber;
    /**
     * 手机号码
     */
    @BindView(R.id.phone)
    EditText phone;
    //    /** 短信验证码 */
//    @BindView(R.id.msg_code) EditText msgCode;
//    /** 获取验证码 */
//    @BindView(R.id.get_verify_code) CountdownButton getVerifyCode;
    /**
     * 交易密码
     */
    @BindView(R.id.password)
    EditText password;
    /**
     * 确认交易密码
     */
    @BindView(R.id.password_again)
    EditText passwordAgain;
    /**
     * 勾选框
     */
    @BindView(R.id.register_service_select)
    ImageView registerServiceSelect;
    /**
     * 注册协议
     */
    @BindView(R.id.register_service)
    TextView registerService;
    /**
     * 下一步按钮
     */
    @BindView(R.id.btn_save)
    Button btnSave;

    /**
     * 加载框
     */
    private HttpLoadingDialog httpLoadingDialog;
    /**
     * 已选择的银行
     */
    private BankResp bankResp;
    /**
     * 用户编号
     */
    private String userId;
    /**
     * 登录标识
     */
    private String token;
    /**
     * 注册的手机号
     */
    private String strPhone;
    private boolean shouldStopChange = false;

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_register_bindcard;
    }

    @Override
    public RegisterSecondPresent newP() {
        return new RegisterSecondPresent();
    }

    @Override
    public void initData(Bundle bundle) {
        //解决键盘弹出遮挡不滚动问题
        ChenJingET.assistActivity(context);
        httpLoadingDialog = new HttpLoadingDialog(context);
        //设置标题
        headTitle.setText("基金开户");
        registerServiceSelect.setSelected(true);
        registerServiceText();
        //获取用户缓存的userid 和 token
        userId = App.getSharedPref().getString(Constant.USERID, "");
        token = App.getSharedPref().getString(Constant.TOKEN, "");
        strPhone = App.getSharedPref().getString(Constant.USER_PHONE, "");
        phone.setText(strPhone);

    }

    @Override
    public void initEvents() {
        identity.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    getP().checkIDExist(getText(identity));
                }
            }
        });
        headBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backDeal();
            }
        });
//        getVerifyCode.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String strPhone = getText(phone);
//                //表单验证
//                if (noNetWork()) {
//                    showNetWorkError();
//                    return;
//                }
//                //表单验证
//                if (noNetWork()) {
//                    showNetWorkError();
//                    return;
//                }
//                if (!isNotEmpty(strPhone)) {
//                    showToast("手机号不能为空");
//                    return;
//                }
//                if (strPhone.length() < 11) {
//                    showToast("请输入正确的手机号码");
//                    return;
//                }
//                //短信验证码
//                getVerifyCode.start();
//
//            }
//        });
        banknumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (shouldStopChange) {
                    shouldStopChange = false;
                    return;
                }

                shouldStopChange = true;

                String str = s.toString().trim().replaceAll(" ", "");
                int len = str.length();
                int courPos;

                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < len; i++) {
                    builder.append(str.charAt(i));
                    if (i == 3 || i == 7 || i == 11 || i == 15) {
                        if (i != len - 1)
                            builder.append(" ");
                    }
                }
                courPos = builder.length();
                banknumber.setText(builder.toString());
                banknumber.setSelection(courPos);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strUsername = getText(userName);
                String strIdentity = getText(identity);
                String strBankName = getText(banckName);
                String strBanknumber = getText(banknumber);
                String strPhone = getText(phone);
                String strpwd = getText(password);
                String strpwdAgain = getText(passwordAgain);
//                String strMsgCode = getText(msgCode);
                //表单验证
                if (noNetWork()) {
                    showNetWorkError();
                    return;
                }
                if (!isNotEmpty(strUsername)) {
                    showToast("姓名不能为空");
                    return;
                }
                if (!isNotEmpty(strIdentity)) {
                    showToast("身份证号不能为空");
                    return;
                }
                if (!isNotEmpty(strBankName)) {
                    showToast("请选择银行名称");
                    return;
                }
                if (!isNotEmpty(strBanknumber)) {
                    showToast("银行卡号不能为空");
                    return;
                }
                if (!isNotEmpty(strPhone)) {
                    showToast("手机号不能为空");
                    return;
                }
                if (strPhone.length() < 11) {
                    showToast("请输入正确的手机号码");
                    return;
                }
                if (!isNotEmpty(strpwd)) {
                    showToast("交易密码不能为空");
                    return;
                }
                if (strpwd.length() < 6) {
                    showToast("请输入6位纯数字");
                    return;
                }
                if (!isNotEmpty(strpwdAgain)) {
                    showToast("确认交易密码不能为空");
                    return;
                }
                if (!strpwd.equals(strpwdAgain)) {
                    showToast("两次密码不一致");
                    return;
                }
//                if (!isNotEmpty(strMsgCode)) {
//                    showToast("验证码不能为空");
//                }
                if (!registerServiceSelect.isSelected()) {
                    showToast("请阅读并同意协议");
                    return;
                }
                //下一步 请求注册接口
                httpLoadingDialog.visible("开户中...");
                httpLoadingDialog.setCanceledOnKeyBack();
                getP().openAccount(userId, token, strUsername, strIdentity, getText(email), bankResp, strBanknumber, strPhone, strpwd);

//                Bundle bundle = new Bundle();
//                //姓名
//                bundle.putString(Constant.NAME, getText(userName));
//                //身份证号
//                bundle.putString(Constant.CERT_NO, getText(identity));
//                startActivity(RegisterSuccessActivity.class, bundle);
//                finish();
            }
        });
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

        llChooseBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //选择银行
                startActivity(BankListActivity.class, Constant.BANKLIST_ACTIVITY);

            }
        });

//        registerService.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Bundle bundle = new Bundle();
//                bundle.putInt(Constant.WEB_TITLE, R.string.user_register_service);
//                bundle.putString(Constant.WEB_LINK, "https://www.baidu.com/?tn=96928074_hao_pg");
//                startActivity(WebPublicActivity.class, bundle);
//            }
//        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Constant.BANKLIST_RESULT_CODE && requestCode == Constant.BANKLIST_ACTIVITY) {
            bankResp = data.getParcelableExtra(Constant.CHOOSE_BANCK);
            banckName.setText(bankResp.getBank_name());
        }
    }

    /**
     * 开户失败
     *
     * @param message
     */
    public void requestOpenAccountFail(String message) {
        httpLoadingDialog.dismiss();

        new AlertDialog.Builder(context).setTitle("开户失败").setMessage(message).setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).setCancelable(false).create().show();
    }

    /**
     * 开户成功
     */
    public void requestOpenAccountSuccess() {
        httpLoadingDialog.dismiss();
//        showToast(data);
        //更新开户状态 改成已开户
        App.getSharedPref().putString(Constant.IS_OPEN_ACCOUNT, Constant.ALREADY_OPEN_ACCOUNT);
        EventBus.getDefault().post(new RefreshUserDataEvent());

        Bundle bundle = new Bundle();
        //姓名
        bundle.putString(Constant.NAME, getText(userName));
        //身份证号
        bundle.putString(Constant.CERT_NO, getText(identity));
        startActivity(RegisterSuccessActivity.class, bundle);
        finish();
    }

    private void backDeal() {
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        backDeal();
    }

    /**
     * 协议设置下划线和点击事件
     *
     * @return
     */
    public void registerServiceText() {

        SpannableString spannableString = new SpannableString("我已阅读并同意《北京微动利基金销售有限公司基金电子直销服务协议》与《投资人权益须知协议》");

        MyClickText click1 = new MyClickText(this);
        click1.setOnTvClick(new OnTvClick() {
            @Override
            public void onClick(View widget) {
                Bundle bundle = new Bundle();
//                bundle.putInt(Constant.WEB_TITLE, R.string.user_register_service1);
                bundle.putString(Constant.WEB_LINK, Api.API_BASE_URL + HttpContent.openaccount_instructions);
                startActivity(WebPublicActivity.class, bundle);
            }
        });

        MyClickText click2 = new MyClickText(this);
        click2.setOnTvClick(new OnTvClick() {
            @Override
            public void onClick(View widget) {
                Bundle bundle = new Bundle();
//                bundle.putInt(Constant.WEB_TITLE, R.string.user_register_service2);
                bundle.putString(Constant.WEB_LINK, Api.API_BASE_URL + HttpContent.openaccount_agreement);
                startActivity(WebPublicActivity.class, bundle);
            }
        });
        //设置下划线
        spannableString.setSpan(click1, 7, 32, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        spannableString.setSpan(click2, 33, 44, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        //当然这里也可以通过setSpan来设置哪些位置的文本哪些颜色
        registerService.setText(spannableString);
        registerService.setMovementMethod(LinkMovementMethod.getInstance());
        registerService.setHighlightColor(Color.TRANSPARENT); //设置点击后的颜色为透明

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

    public void checkIDExistFail(boolean b) {
        if (b) {
            identity.setText("");
            identity.requestFocus();
        }
    }

    public void checkIDExistSuccess() {

    }
}
