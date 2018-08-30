package cn.com.buyforyou.fund.ui.activity.user;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import cn.com.buyforyou.fund.App;
import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.constant.Constant;
import cn.com.buyforyou.fund.present.user.FindPwdTradeSecondPresent;
import cn.com.buyforyou.fund.utils.RuntimeHelper;
import com.zhsoft.fretting.ui.widget.ChenJingET;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.dialog.httploadingdialog.HttpLoadingDialog;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * 作者：sunnyzeng on 2017/12/13 18:18
 * 描述：找回交易密码 第二步
 */

public class FindPwdTradeSecondActivity extends XActivity<FindPwdTradeSecondPresent> {
    /** 传递phone */
    private static final String PHONE = "phone";
    /** 返回按钮 */
    @BindView(R.id.head_back) ImageButton headBack;
    /** 标题 */
    @BindView(R.id.head_title) TextView headTitle;
    /** 密码 */
    @BindView(R.id.password) EditText password;
    /** 再次输入密码 */
    @BindView(R.id.password_again) EditText passwordAgain;
    /** 保存按钮 */
    @BindView(R.id.btn_save) Button btnSave;

    /** 加载框 */
    private HttpLoadingDialog httpLoadingDialog;
    /** 开户手机号 */
    private String mPhone;

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_findpwd_second_trade;
    }

    @Override
    public FindPwdTradeSecondPresent newP() {
        return new FindPwdTradeSecondPresent();
    }

    @Override
    public void initData(Bundle bundle) {
        initView();
    }

    private void initView() {
        //解决键盘弹出遮挡不滚动问题
        ChenJingET.assistActivity(context);
        httpLoadingDialog = new HttpLoadingDialog(context);
        //设置标题
        headTitle.setText("找回交易密码");
        mPhone = getIntent().getStringExtra(PHONE);
    }

    @Override
    public void initEvents() {

        headBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pwd = getText(password);
                String againpwd = getText(passwordAgain);
                //表单验证
                if (noNetWork()) {
                    showNetWorkError();
                    return;
                }
                if (!isNotEmpty(pwd)) {
                    showToast("新交易密码不能为空");
                    return;
                }
                if (pwd.length() < 6) {
                    showToast("请输入正确的新交易密码");
                    return;
                }
                if (!isNotEmpty(againpwd)) {
                    showToast("再次输入交易密码不能为空");
                    return;
                }
                if (!pwd.equals(againpwd)) {
                    showToast("两次密码不一致");
                    return;
                }
                //请求修改交易密码接口
                httpLoadingDialog.visible();
                getP().findPassword(mPhone, pwd, againpwd);
            }
        });
    }

    public static void launch(Activity activity, String phone) {
        Router.newIntent(activity)
                .to(FindPwdTradeSecondActivity.class)
                .putString(PHONE, phone)
                .launch();
    }

    /**
     * 找回登录密码失败
     */
    public void requestFail() {
        httpLoadingDialog.dismiss();
        showToast("找回交易密码失败");
    }

    /**
     * 找回登录密码成功
     *
     * @param data
     */
    public void requestSuccess(Object data) {
        httpLoadingDialog.dismiss();
        showToast("找回交易密码成功");
        //清空缓存数据
        App.getSharedPref().putString(Constant.USERID, "");
        App.getSharedPref().putString(Constant.TOKEN, "");
        App.getSharedPref().putString(Constant.USER_PHONE, "");
        App.getSharedPref().putString(Constant.USER_CERTNO, "");
        App.getSharedPref().putString(Constant.IS_OPEN_ACCOUNT, "");
//                EventBus.getDefault().post(new RefreshUserDataEvent());
        //改变登录状态
        RuntimeHelper.getInstance().setLogin(false);
        //跳转登录页面
        Bundle bundle = new Bundle();
        bundle.putString(Constant.SKIP_SIGN, Constant.SKIP_MAIN_ACTIVITY);
        startActivity(LoginActivity.class,bundle);
        finish();
    }
}
