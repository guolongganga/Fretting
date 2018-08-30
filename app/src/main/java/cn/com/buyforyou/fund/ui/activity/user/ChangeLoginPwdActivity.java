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
import cn.com.buyforyou.fund.event.InvalidTokenEvent;
import cn.com.buyforyou.fund.present.user.ChangeLoginPwdPresent;
import cn.com.buyforyou.fund.utils.RuntimeHelper;
import com.zhsoft.fretting.ui.widget.ChenJingET;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.dialog.httploadingdialog.HttpLoadingDialog;
import cn.droidlover.xdroidmvp.mvp.XActivity;

/**
 * 作者：sunnyzeng on 2017/12/13 13:58
 * 描述：变更登录密码
 */

public class ChangeLoginPwdActivity extends XActivity<ChangeLoginPwdPresent> {
    /** 返回按钮 */
    @BindView(R.id.head_back) ImageButton headBack;
    /** 标题 */
    @BindView(R.id.head_title) TextView headTitle;
    /** 新登录密码 */
    @BindView(R.id.password) EditText password;
    /** 再次输入登录密码 */
    @BindView(R.id.password_again) EditText passwordAgain;
    /** 保存 */
    @BindView(R.id.btn_save) Button btnSave;

    /** 加载框 */
    private HttpLoadingDialog httpLoadingDialog;
    /** 登录标识 */
    private String token;
    /** 用户编号 */
    private String userId;

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_changepwd_login;
    }

    @Override
    public ChangeLoginPwdPresent newP() {
        return new ChangeLoginPwdPresent();
    }

    @Override
    public void initData(Bundle bundle) {
        //解决键盘弹出遮挡不滚动问题
        ChenJingET.assistActivity(context);
        //设置标题
        headTitle.setText("变更登录密码");
        httpLoadingDialog = new HttpLoadingDialog(context);

        //获取本地缓存
        token = App.getSharedPref().getString(Constant.TOKEN, "");
        userId = App.getSharedPref().getString(Constant.USERID, "");

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
                //请求修改登录密码接口
                httpLoadingDialog.visible();
                getP().changePassword(token, userId, pwdnumbe, pwdAgainnumbe);
            }
        });
    }

    /**
     * 修改登录密码失败
     */
    public void requestFail() {
        httpLoadingDialog.dismiss();
        showToast("修改登录密码失败");
    }

    /**
     * 修改登录密码成功
     */
    public void requestSuccess() {
        httpLoadingDialog.dismiss();
        showToast("修改登录密码成功");

        //清空缓存数据
        App.getSharedPref().putString(Constant.USERID, "");
        App.getSharedPref().putString(Constant.TOKEN, "");
        App.getSharedPref().putString(Constant.USER_PHONE, "");
        App.getSharedPref().putString(Constant.USER_CERTNO, "");
        App.getSharedPref().putString(Constant.IS_OPEN_ACCOUNT, "");
//                EventBus.getDefault().post(new RefreshUserDataEvent());

        RuntimeHelper.getInstance().setLogin(false);
        //跳转回登录界面
        Bundle bundle = new Bundle();
        bundle.putString(Constant.SKIP_SIGN, Constant.SKIP_MAIN_ACTIVITY);
        startActivity(LoginActivity.class, bundle);
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
