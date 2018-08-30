package cn.com.buyforyou.fund.ui.activity.user;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.present.user.FindPwdLoginSecondPresent;
import com.zhsoft.fretting.ui.widget.ChenJingET;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.dialog.httploadingdialog.HttpLoadingDialog;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * 作者：sunnyzeng on 2017/12/13 18:18
 * 描述：找回登录密码 第二步
 */

public class FindPwdLoginSecondActivity extends XActivity<FindPwdLoginSecondPresent> {
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
        return R.layout.activity_user_findpwd_second_login;
    }

    @Override
    public FindPwdLoginSecondPresent newP() {
        return new FindPwdLoginSecondPresent();
    }

    @Override
    public void initData(Bundle bundle) {
        //解决键盘弹出遮挡不滚动问题
        ChenJingET.assistActivity(context);
        httpLoadingDialog = new HttpLoadingDialog(context);
        //设置标题
        headTitle.setText("找回登录密码");
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
                String pwdnumbe = getText(password);
                String pwdAgainnumbe = getText(passwordAgain);
                //表单验证
                if (noNetWork()) {
                    showNetWorkError();
                    return;
                }
                if (!isNotEmpty(pwdnumbe)) {
                    showToast("新登录密码不能为空");
                    return;
                }
                if (pwdnumbe.length() < 8) {
                    showToast("登录密码为8-16位数字、字母、特殊字符等");
                    return;
                }
                if (!isNotEmpty(pwdAgainnumbe)) {
                    showToast("再次输入登录密码不能为空");
                    return;
                }
                if (!pwdnumbe.equals(pwdAgainnumbe)) {
                    showToast("两次密码不一致");
                    return;
                }
                //找回登录密码接口
                httpLoadingDialog.visible();
                getP().findPassword(mPhone, pwdnumbe, pwdAgainnumbe);
            }
        });
    }

    public static void launch(Activity activity, String phone) {
        Router.newIntent(activity)
                .to(FindPwdLoginSecondActivity.class)
                .putString(PHONE, phone)
                .launch();
    }

    /**
     * 找回登录密码失败
     */
    public void requestFail() {
        httpLoadingDialog.dismiss();
        showToast("找回登录密码失败");
    }

    /**
     * 找回登录密码成功
     *
     * @param data
     */
    public void requestSuccess(Object data) {
        httpLoadingDialog.dismiss();
        showToast("找回登录密码成功");
        finish();
    }
}
