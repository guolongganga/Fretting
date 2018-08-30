package cn.com.buyforyou.fund.ui.activity.user;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import cn.com.buyforyou.fund.App;
import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.constant.Constant;
import cn.com.buyforyou.fund.event.ChangeBankCardEvent;
import cn.com.buyforyou.fund.model.user.BankCardResp;
import cn.com.buyforyou.fund.present.user.BankCardPresent;
import com.zhsoft.fretting.ui.widget.CustomDialog;
import com.zhsoft.fretting.ui.widget.FundBuyDialog;
import cn.com.buyforyou.fund.utils.Base64ImageUtil;
import cn.com.buyforyou.fund.utils.RuntimeHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.dialog.httploadingdialog.HttpLoadingDialog;
import cn.droidlover.xdroidmvp.mvp.XActivity;

/**
 * 作者：sunnyzeng on 2017/12/14 10:07
 * 描述：我的银行卡页面
 */

public class BankCardActivity extends XActivity<BankCardPresent> {
    /** 返回按钮 */
    @BindView(R.id.head_back) ImageButton headBack;
    /** 标题 */
    @BindView(R.id.head_title) TextView headTitle;
    /** 银行图标 */
    @BindView(R.id.image_banck) ImageView imageBanck;
    /** 银行名称 */
    @BindView(R.id.banck_name) TextView banckName;
    /** 交易账号 */
    @BindView(R.id.trade_number) TextView tradeNumber;
    /** 变更银行卡 */
    @BindView(R.id.btn_change) Button btnChange;

    /** 加载图片 */
    private ImageLoader imageLoader = ImageLoader.getInstance();
    /** 加载圈 */
    private HttpLoadingDialog httpLoadingDialog;
    /** 银行卡信息 */
    private BankCardResp bankCardResp;
    /** 用户编号 */
    private String userId;
    /** 登录标识 */
    private String token;
    /** 是否修改了银行卡 */
    private String isChange = "1";
    /** 输入密码弹框 */
    private FundBuyDialog fundBuyDialog;
    /** 密码错误弹框 */
    private CustomDialog errorDialog;
    /** 交易密码 */
    private String trade_password;

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_bankcard;
    }

    @Override
    public BankCardPresent newP() {
        return new BankCardPresent();
    }

    @Override
    public void initData(Bundle bundle) {
        headTitle.setText("我的银行卡");
        httpLoadingDialog = new HttpLoadingDialog(context);
        //获取用户缓存的userid 和 token
        userId = App.getSharedPref().getString(Constant.USERID, "");
        token = App.getSharedPref().getString(Constant.TOKEN, "");

        //注册eventbus
        EventBus.getDefault().register(this);

        //获取我的银行卡信息
        httpLoadingDialog.visible();
        getP().getBankCardInfo(token, userId);
    }

    @Override
    public void initEvents() {
        headBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backDeal();
            }
        });
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //弹出交易密码框
                fundBuyDialog = new FundBuyDialog
                        .Builder(context)
                        .setOnTextFinishListener(new FundBuyDialog.OnTextFinishListener() {
                            @Override
                            public void onFinish(String str) {
                            }
                        }).setPositiveButton("确定", new FundBuyDialog.OnPositiveButtonListener() {
                            @Override
                            public void onButtonClick(DialogInterface dialog, String str) {
                                //验证是否符合更换条件
                                dialog.dismiss();
                                httpLoadingDialog.visible();
                                trade_password = str;
                                getP().changeBankCardCheck(token, userId, str);

                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create();

                fundBuyDialog.show();

            }
        });

    }

    /**
     * 获得用户银行卡信息
     *
     * @param resp
     */
    public void showBankCardData(BankCardResp resp) {
        httpLoadingDialog.dismiss();
        bankCardResp = resp;
        //获取 图片Base64 字符串
        String strimage = resp.getBankLogo();
        if (!TextUtils.isEmpty(strimage)) {
            //将Base64图片串转换成Bitmap
            Bitmap bitmap = Base64ImageUtil.base64ToBitmap(strimage);
            imageBanck.setImageBitmap(bitmap);
        }
        banckName.setText(resp.getBankName() + "（尾号" + resp.getBankNoTail() + "）");
        tradeNumber.setText(resp.getTa_acco());
    }

    /**
     * 更换银行卡 密码错误
     * @param message
     */
    public void passwordError(String message) {
        httpLoadingDialog.dismiss();
        if (errorDialog == null) {
            errorDialog = new CustomDialog.Builder(context)
                    .setMessage(message)
                    .setNegativeButton("忘记密码", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            errorDialog.dismiss();
                            startActivity(FindPwdTradeFirstActivity.class);
                        }
                    }).setPositiveButton("再试一次", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            errorDialog.dismiss();
                            fundBuyDialog.show();
                        }
                    }).create();
        }
        errorDialog.show();
    }


    /**
     * 获取用户银行卡信息失败
     */
    public void requestFail() {
        httpLoadingDialog.dismiss();
    }


    /**
     * 修改银行卡事件
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void changeBankEvent(ChangeBankCardEvent event) {
        //重新获取我的银行卡信息
        httpLoadingDialog.visible();
        //修改了银行卡
        isChange = "0";
        getP().getBankCardInfo(token, userId);
//        showToast("修改啦");
    }

    /**
     * 是否能修改银行卡
     *
     */
    public void isCanChange() {
        httpLoadingDialog.dismiss();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.TRADE_PASSWORD, trade_password);
        startActivity(BankCardChangeActivity.class, bundle);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        backDeal();
    }

    /**
     * 返回键处理
     */
    private void backDeal() {
        Intent intent = new Intent();
        intent.putExtra(Constant.CHANGE_BANK, isChange);
        setResult(Constant.SUCCESS_BACK_BANK, intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        if (errorDialog != null) {
            errorDialog.dismiss();
            errorDialog = null;
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
//        httpLoadingDialog.dismiss();
//        EventBus.getDefault().post(new InvalidTokenEvent());
        //清除本地缓存，设置成未登录
        RuntimeHelper.getInstance().isInvalidToken();
        //跳转登录界面
        Bundle bundle = new Bundle();
        bundle.putString(Constant.SKIP_SIGN, Constant.SKIP_INDEX_ACTIVITY);
        startActivity(LoginActivity.class, bundle);
    }
}
