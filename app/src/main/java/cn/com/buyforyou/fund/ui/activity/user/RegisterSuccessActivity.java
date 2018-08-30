package cn.com.buyforyou.fund.ui.activity.user;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.constant.Constant;
import cn.com.buyforyou.fund.event.ChangeTabEvent;
import cn.com.buyforyou.fund.net.Api;
import cn.com.buyforyou.fund.net.HttpContent;
import cn.com.buyforyou.fund.ui.activity.MainActivity;
import cn.com.buyforyou.fund.ui.activity.boot.WebRiskActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.mvp.XActivity;

/**
 * 作者：sunnyzeng on 2017/12/14 15:06
 * 描述：注册成功页面
 */

public class RegisterSuccessActivity extends XActivity {

    /** 返回按钮 */
    @BindView(R.id.head_back) ImageButton headBack;
    /** 标题 */
    @BindView(R.id.head_title) TextView headTitle;
    /** 姓名 */
    @BindView(R.id.name) TextView name;
    /** 身份证号 */
    @BindView(R.id.identity) TextView identity;
    /** 完成按钮 */
    @BindView(R.id.btn_finish) Button btnFinish;
    /** 风险测评 */
    @BindView(R.id.risk_test) Button riskTest;
    /** 用户姓名 */
    private String username;
    /** 用户身份证号 */
    private String certNo;

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_register_success;
    }

    @Override
    public Object newP() {
        return null;
    }

    @Override
    public void initData(Bundle bundle) {
        headTitle.setText("基金开户");
        // 获取用户名和身份证号
        username = bundle.getString(Constant.NAME, "");
        certNo = bundle.getString(Constant.CERT_NO, "");

        name.setText(username);
        identity.setText(certNo);

    }

    @Override
    public void initEvents() {
        headBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backDeal();
            }
        });
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backDeal();
            }
        });
        riskTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 跳转风险测评
                Bundle bundle = new Bundle();
                bundle.putInt(Constant.WEB_TITLE, R.string.user_risk_test);
                bundle.putString(Constant.WEB_LINK, Api.API_BASE_URL + HttpContent.risk_question);
                startActivity(WebRiskActivity.class, bundle);
            }
        });
    }

    private void backDeal() {
        EventBus.getDefault().post(new ChangeTabEvent(Constant.MAIN_MY));
        startActivity(MainActivity.class);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        backDeal();
    }
}
