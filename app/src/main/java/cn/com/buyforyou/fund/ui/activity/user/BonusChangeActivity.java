package cn.com.buyforyou.fund.ui.activity.user;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import cn.com.buyforyou.fund.App;
import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.constant.Constant;
import cn.com.buyforyou.fund.event.InvalidTokenEvent;
import cn.com.buyforyou.fund.model.user.UpdateBonusResp;
import cn.com.buyforyou.fund.model.user.WebBonusResp;
import cn.com.buyforyou.fund.present.user.BonusChangePresent;
import com.zhsoft.fretting.ui.widget.ChenJingET;
import cn.com.buyforyou.fund.utils.RuntimeHelper;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.dialog.httploadingdialog.HttpLoadingDialog;
import cn.droidlover.xdroidmvp.mvp.XActivity;

/**
 * 作者：sunnyzeng on 2018/1/23 17:32
 * 描述：分红方式
 */

public class BonusChangeActivity extends XActivity<BonusChangePresent> {
    @BindView(R.id.head_back)
    ImageButton headBack;
    @BindView(R.id.head_title)
    TextView headTitle;
    @BindView(R.id.tv_fund_name)
    TextView tvFundName;
    @BindView(R.id.tv_fund_code)
    TextView tvFundCode;
    @BindView(R.id.rb_again)
    RadioButton rbAgain;
    @BindView(R.id.rb_carsh)
    RadioButton rbCarsh;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.btn_save)
    Button btnSave;

    private HttpLoadingDialog httpLoadingDialog;
    private String chooseStyle;
    private UpdateBonusResp updateBonusResp;
    /**
     * 登录标识
     */
    private String token;
    /**
     * 用户编号
     */
    private String userId;

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_bonus_change;
    }

    @Override
    public BonusChangePresent newP() {
        return new BonusChangePresent();
    }

    @Override
    public void initData(Bundle bundle) {
        //解决键盘弹出遮挡不滚动问题
        ChenJingET.assistActivity(context);
        headTitle.setText("分红方式变更");
        httpLoadingDialog = new HttpLoadingDialog(context);
        token = App.getSharedPref().getString(Constant.TOKEN, "");
        userId = App.getSharedPref().getString(Constant.USERID, "");

        //获得基金代码
        if (bundle != null) {
            if (bundle.getBoolean("isFromWeb")){
                WebBonusResp webBonusResp = bundle.getParcelable(Constant.ACTIVITY_OBJECT);
                updateBonusResp = new UpdateBonusResp();
                updateBonusResp.setFundcode(webBonusResp.getFundcode());
                updateBonusResp.setFundName(webBonusResp.getFundName());
                updateBonusResp.setAutoBuy(webBonusResp.getAutoBuy());
                updateBonusResp.setSharetype(webBonusResp.getSharetype());
                updateBonusResp.setTradeacco(webBonusResp.getTradeacco());

            }else {

                updateBonusResp = bundle.getParcelable(Constant.ACTIVITY_OBJECT);
            }
            tvFundName.setText(updateBonusResp.getFundName());
            tvFundCode.setText(updateBonusResp.getFundcode());
            //如果是现金红利
            if ("1".equals(updateBonusResp.getAutoBuy())) {
                rbAgain.setChecked(false);
                rbCarsh.setChecked(true);
            } else {
                rbAgain.setChecked(true);
                rbCarsh.setChecked(false);
            }
        }

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
                //表单验证
                if (noNetWork()) {
                    showNetWorkError();
                    return;
                }
                if (!isNotEmpty(getText(password))) {
                    showToast("交易密码不能为空");
                    return;
                }
                if (rbAgain.isChecked()) {
                    chooseStyle = "0";
                } else {
                    chooseStyle = "1";
                }
                if (updateBonusResp.getAutoBuy().equals(chooseStyle)) {
                    showToast("并未修改分红方式");
                    return;
                }
//                showToast("保存，分红方式为：" + chooseStyle);
                httpLoadingDialog.visible();
                getP().loadBonusXgDeatilData(updateBonusResp.getFundcode(), chooseStyle,
                        updateBonusResp.getSharetype(), updateBonusResp.getTradeacco(),
                        getText(password), token, userId);

            }
        });
    }

    public void showData() {
        //跳转到修改成功界面，通知分红方式改变
        //EventBus.getDefault().post(new RefreshBonusEvent(chooseStyle));
//        EventBus.getDefault().post(new RefreshUserDataEvent());
        httpLoadingDialog.dismiss();
        startActivity(BonusChangeSuccessActivity.class);
        finish();
    }

    public void showError() {
        httpLoadingDialog.dismiss();
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
