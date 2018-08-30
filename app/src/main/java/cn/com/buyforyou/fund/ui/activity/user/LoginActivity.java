package cn.com.buyforyou.fund.ui.activity.user;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import cn.com.buyforyou.fund.App;
import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.constant.Constant;
import cn.com.buyforyou.fund.event.ChangeTabEvent;
import cn.com.buyforyou.fund.event.RefreshUserDataEvent;
import cn.com.buyforyou.fund.model.LoginResp;
import cn.com.buyforyou.fund.present.user.LoginPresent;
import cn.com.buyforyou.fund.ui.activity.MainActivity;
import cn.com.buyforyou.fund.utils.RuntimeHelper;
import com.zhsoft.fretting.ui.widget.ChenJingET;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.dialog.httploadingdialog.HttpLoadingDialog;
import cn.droidlover.xdroidmvp.mvp.XActivity;

/**
 * 作者：sunnyzeng on 2017/12/14 11:46
 * 描述：登录界面
 */

public class LoginActivity extends XActivity<LoginPresent> {
    /** 返回按钮 */
    @BindView(R.id.head_back) ImageButton headBack;
    /** 标题 */
    @BindView(R.id.head_title) TextView headTitle;
    /** 用户名 */
    @BindView(R.id.username) EditText username;
    /** 密码 */
    @BindView(R.id.password) EditText password;
    /** 10秒开户 */
    @BindView(R.id.register) TextView register;
    /** 找回登录密码 */
    @BindView(R.id.find_password) TextView findPassword;
    /** 登录按钮 */
    @BindView(R.id.btn_next) Button btnNext;

    /** 加载框 */
    private HttpLoadingDialog httpLoadingDialog;
    /** 从哪个页面跳转过来的请求码 */
    private String mRequestCode;
    private String phone;

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_login;
    }

    @Override
    public LoginPresent newP() {
        return new LoginPresent();
    }

    @Override
    public void initData(Bundle bundle) {
        //解决键盘弹出遮挡不滚动问题
        ChenJingET.assistActivity(context);
        //设置标题
        headTitle.setText("登录");
        httpLoadingDialog = new HttpLoadingDialog(context);
        phone = App.getSharedPref().getString(Constant.USER_PHONE, "");

        if (isNotEmpty(phone)) {
            username.setText(phone);
            username.setSelection(phone.length());
        }

        if (bundle != null) {
            mRequestCode = bundle.getString(Constant.SKIP_SIGN);
        } else {
            mRequestCode = "";
        }

    }

    @Override
    public void initEvents() {
        headBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                skipTarget();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(RegisterFirstActivity.class);
                finish();
            }
        });
        findPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(FindPwdLoginFirstActivity.class);
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strname = getText(username);
                String strpwd = getText(password);

                //表单验证
                if (noNetWork()) {
                    showNetWorkError();
                    return;
                }
                if (!isNotEmpty(strname)) {
                    showToast("用户名不能为空");
                    return;
                }
                if (!isNotEmpty(strpwd)) {
                    showToast("密码不能为空");
                    return;
                }
                //请求登录接口
                httpLoadingDialog.visible("登录中...");
                getP().login(strname, strpwd);

            }
        });
    }

    /**
     * 登录成功
     */
    public void showData(LoginResp model) {
        httpLoadingDialog.dismiss();

        //缓存用户userId,token,username,is_open_account
        App.getSharedPref().putString(Constant.USERID, model.getUserId());
        App.getSharedPref().putString(Constant.TOKEN, model.getToken());
        //如果位数是11位，说明是手机号，否则是身份证号
        if (getText(username).length() == 11) {
            App.getSharedPref().putString(Constant.USER_PHONE, getText(username));
        } else {
            App.getSharedPref().putString(Constant.USER_CERTNO, getText(username));
        }
        App.getSharedPref().putString(Constant.IS_OPEN_ACCOUNT, model.getIsOpenAccount());

        EventBus.getDefault().post(new RefreshUserDataEvent());

        //全局变量设置为登录状态
        RuntimeHelper.getInstance().setLogin(true);

        if (Constant.WEB_ACTIVITY.equals(mRequestCode)) {
            finish();
        }else if (Constant.SKIP_INDEX_ACTIVITY.equals(mRequestCode)) {
            EventBus.getDefault().post(new ChangeTabEvent(Constant.MAIN_INDEX));
            startActivity(MainActivity.class);
            finish();
        } else {
            startActivity(MainActivity.class);
            finish();
        }


    }

    /**
     * 登录失败 关闭掉dialog
     */
    public void loginFail() {
        httpLoadingDialog.dismiss();
    }

    /**
     * 跳转的目标
     */
    private void skipTarget() {
        //如果是修改登录密码，修改交易密码，重置交易密码页面过来返回按钮都返回到主页面
        if (isNotEmpty(mRequestCode)) {
            if (Constant.SKIP_MAIN_ACTIVITY.equals(mRequestCode)) {
                startActivity(MainActivity.class);
            } else if (Constant.SKIP_INDEX_ACTIVITY.equals(mRequestCode)) {
                EventBus.getDefault().post(new ChangeTabEvent(Constant.MAIN_INDEX));
                startActivity(MainActivity.class);
            }
        }
        finish();
    }

    /**
     * 返回按键
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        skipTarget();
    }

}
