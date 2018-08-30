package cn.com.buyforyou.fund.ui.activity.user;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.com.buyforyou.fund.App;
import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.constant.Constant;
import cn.com.buyforyou.fund.event.InvalidTokenEvent;
import cn.com.buyforyou.fund.model.user.RiskInfoResp;
import cn.com.buyforyou.fund.net.Api;
import cn.com.buyforyou.fund.net.HttpContent;
import cn.com.buyforyou.fund.present.user.SettingPresent;
import cn.com.buyforyou.fund.ui.activity.boot.WebPublicActivity;
import cn.com.buyforyou.fund.ui.activity.boot.WebRiskActivity;

import com.zhsoft.fretting.ui.widget.CustomDialog;

import cn.com.buyforyou.fund.utils.RuntimeHelper;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.dialog.httploadingdialog.HttpLoadingDialog;
import cn.droidlover.xdroidmvp.mvp.XActivity;

/**
 * 作者：sunnyzeng on 2017/12/12 19:50
 * 描述：设置页面
 */

public class SettingActivity extends XActivity<SettingPresent> {
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
     * 个人信息
     */
    @BindView(R.id.personinfo)
    TextView personinfo;
    /**
     * 手机号码
     */
    @BindView(R.id.phone)
    TextView phone;
    /**
     * 是否做过风险测评 风险等级
     */
    @BindView(R.id.tv_risk_grade)
    TextView tvRiskGrade;
    /**
     * 密码管理
     */
    @BindView(R.id.password_manager)
    TextView passwordManager;
    /**
     * 银行卡号
     */
    @BindView(R.id.bankcard)
    TextView bankcard;
    /**
     * 风险等级测评
     */
    @BindView(R.id.risk_test)
    LinearLayout riskTest;
    /**
     * 客服电话
     */
    @BindView(R.id.call_agent)
    TextView callAgent;
    /**
     * 关于我们
     */
    @BindView(R.id.about_us)
    TextView aboutUs;
//    /** 切换账户 */
//    @BindView(R.id.change_user) TextView changeUser;
    /**
     * 安全退出
     */
    @BindView(R.id.exit)
    Button exit;
    /**
     * 邀请码
     */
    @BindView(R.id.invite_code)
    TextView inviteCode;

    /**
     * 针对即使获取了拨打电话的权限依然报错问题的解决方案
     */
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    /**
     * 客服电话
     */
    private String serviceTel;
    /**
     * 登录标识
     */
    private String token;
    /**
     * 用户编号
     */
    private String userId;
    /**
     * 是否风险测评
     */
    private String riskEvaluteStatus;
    /**
     * 加载圈
     */
    private HttpLoadingDialog httpLoadingDialog;
    /**
     * 是否开户
     */
    private String isOpenAccount;
    private CustomDialog dialog;

    @Override

    public int getLayoutId() {
        return R.layout.activity_user_setting;
    }

    @Override
    public SettingPresent newP() {
        return new SettingPresent();
    }

    @Override
    public void initData(Bundle bundle) {
        //设置标题
        headTitle.setText("设置");
        //初始化加载圈
        httpLoadingDialog = new HttpLoadingDialog(context);
        //获取本地缓存
        token = App.getSharedPref().getString(Constant.TOKEN, "");
        userId = App.getSharedPref().getString(Constant.USERID, "");
        //获得本地缓存的开户标识
        isOpenAccount = App.getSharedPref().getString(Constant.IS_OPEN_ACCOUNT, "");
        //获得本地缓存的服务号码
        serviceTel = App.getSharedPref().getString(Constant.SERVICE_TEL, "");
        //请求是否风险等级或是否做过风险等级
        httpLoadingDialog.visible();
        getP().riskGrade(token, userId);

        setRippBac(personinfo);
        setRippBac(phone);
        setRippBac(passwordManager);
        setRippBac(bankcard);

        setRippBac(aboutUs);
    }


    @Override
    public void initEvents() {

        personinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Constant.ALREADY_OPEN_ACCOUNT.equals(isOpenAccount)) {
                    showToast("您还未开户，请先去开户");
                    return;
                }
                startActivityDelay(PersonInfoActivity.class);
            }
        });
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Constant.ALREADY_OPEN_ACCOUNT.equals(isOpenAccount)) {
                    showToast("您还未开户，请先去开户");
                    return;
                }
                startActivityDelay(PhoneActivity.class);
            }
        });
        passwordManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Constant.ALREADY_OPEN_ACCOUNT.equals(isOpenAccount)) {
                    showToast("您还未开户，请先去开户");
                    return;
                }
                startActivityDelay(ChangePwdActivity.class);
            }
        });
        bankcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Constant.ALREADY_OPEN_ACCOUNT.equals(isOpenAccount)) {
                    showToast("您还未开户，请先去开户");
                    return;
                }
                startActivityDelay(BankCardActivity.class);
            }
        });
        riskTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Constant.ALREADY_OPEN_ACCOUNT.equals(isOpenAccount)) {
                    showToast("您还未开户，请先去开户");
                    return;
                }
                if (riskEvaluteStatus != null) {
                    if ("0".equals(riskEvaluteStatus)) {
                        //未测
                        Bundle bundle = new Bundle();
                        bundle.putString(Constant.WEB_LINK, Api.API_BASE_URL + HttpContent.risk_question);
                        startActivity(WebRiskActivity.class, bundle);
                    } else {
                        Bundle bundle = new Bundle();
                        bundle.putString(Constant.WEB_LINK, Api.API_BASE_URL + HttpContent.risk_dengji);
                        startActivity(WebRiskActivity.class, bundle, Constant.WEB_RISK_ACTIVITY);
                    }
                }

            }
        });
        callAgent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callPhonePermission();

            }
        });
        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString(Constant.WEB_LINK, Api.API_BASE_URL + HttpContent.about_us);
                startActivityDelay(WebPublicActivity.class, bundle);
            }
        });
//        changeUser.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(SwitchAccountActivity.class);
//            }
//        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                //清空缓存数据
//                App.getSharedPref().putString(Constant.USERID, "");
//                App.getSharedPref().putString(Constant.TOKEN, "");
//                App.getSharedPref().putString(Constant.USER_CERTNO, "");
//                App.getSharedPref().putString(Constant.IS_OPEN_ACCOUNT, "");
////                EventBus.getDefault().post(new RefreshUserDataEvent());
//                //更新登录状态
//                RuntimeHelper.getInstance().setLogin(false);

                if (dialog == null) {
                    dialog = new CustomDialog.Builder(context)
                            .setMessage("您确定要退出吗？")
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialog.dismiss();
                                }
                            }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialog.dismiss();
                                    RuntimeHelper.getInstance().isInvalidToken();

                                    showToast("安全退出");
                                    finish();
                                }
                            }).create();
                }
                dialog.show();
            }
        });
        headBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    /**
     * 风险等级或是否做了风险测评 成功
     *
     * @param data
     */
    public void requestRiskSuccess(RiskInfoResp data) {
        httpLoadingDialog.dismiss();
        riskEvaluteStatus = data.getRiskEvaluteStatus();
        //服务号码
        serviceTel = data.getServiceTel();
        //缓存本地
        App.getSharedPref().putString(Constant.SERVICE_TEL, serviceTel);

        if ("0".equals(riskEvaluteStatus)) {
            tvRiskGrade.setText("未测");
        } else {
            tvRiskGrade.setText(data.getRiskEvaluteVal());
        }

        if (!Constant.ALREADY_OPEN_ACCOUNT.equals(isOpenAccount)) {
            inviteCode.setText("未生成");
            inviteCode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showToast("您还未开户成功，暂不能生成邀请码");
                }
            });
        } else {
            inviteCode.setText(data.getInvite_code());
        }

    }

    /**
     * 风险等级或是否做了风险测评 失败
     */
    public void requestRiskFail() {
        httpLoadingDialog.dismiss();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //从风险测评回来后刷新数据
        if (requestCode == Constant.WEB_RISK_ACTIVITY && resultCode == Constant.RISK_BACK_ACTIVITY) {
            getP().riskGrade(token, userId);
        }
    }

    public void callPhonePermission() {
        // 检查是否获得了权限（Android6.0运行时权限）
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // 没有获得授权，申请授权
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
                // 返回值：
                //如果app之前请求过该权限,被用户拒绝, 这个方法就会返回true.
                //如果用户之前拒绝权限的时候勾选了对话框中”Don’t ask again”的选项,那么这个方法会返回false.
                //如果设备策略禁止应用拥有这条权限, 这个方法也返回false.
                // 弹窗需要解释为何需要该权限，再次请求授权
                showToast("请授权拨号！");
                // 帮跳转到该应用的设置界面，让用户手动授权
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
            } else {
                // 不需要解释为何需要该权限，直接请求授权
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_CALL_PHONE);
            }
        } else {
            // 已经获得授权，可以打电话
            CallPhone();
        }
    }

    private void CallPhone() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        //url:统一资源定位符
        //uri:统一资源标示符（更广）
        intent.setData(Uri.parse("tel:" + serviceTel));
        //开启系统拨号器
        startActivity(intent);
    }


    /**
     * 已经登出系统，请重新登录
     */
    public void areadyLogout() {
        httpLoadingDialog.dismiss();
        //清除本地缓存，设置成未登录
        RuntimeHelper.getInstance().isInvalidToken();
        //跳转登录界面
        Bundle bundle = new Bundle();
        bundle.putString(Constant.SKIP_SIGN, Constant.SKIP_INDEX_ACTIVITY);
        startActivity(LoginActivity.class, bundle);
    }

    /**
     * 设置水波纹点击背景
     *
     * @param itemView
     */
    private void setRippBac(View itemView) {
        if (itemView.getBackground() == null) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme theme = itemView.getContext().getTheme();
            int top = itemView.getPaddingTop();
            int bottom = itemView.getPaddingBottom();
            int left = itemView.getPaddingLeft();
            int right = itemView.getPaddingRight();
            if (theme.resolveAttribute(android.R.attr.selectableItemBackground, typedValue, true)) {
                itemView.setBackgroundResource(typedValue.resourceId);
            }
            itemView.setPadding(left, top, right, bottom);
        }
    }
}
