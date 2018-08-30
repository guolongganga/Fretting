package cn.com.buyforyou.fund.ui.activity.user;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.com.buyforyou.fund.R;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.mvp.XActivity;

/**
 * 作者：sunnyzeng on 2017/12/13 13:53
 * 描述：变更密码
 */

public class ChangePwdActivity extends XActivity {
    /** 返回按钮 */
    @BindView(R.id.head_back) ImageButton headBack;
    /** 标题 */
    @BindView(R.id.head_title) TextView headTitle;
    /** 变更登录密码 */
    @BindView(R.id.ll_changepwd_login) LinearLayout llChangepwdLogin;
    /** 变更交易密码 */
    @BindView(R.id.ll_changepwd_trade) LinearLayout llChangepwdTrade;

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_changepwd;
    }

    @Override
    public Object newP() {
        return null;
    }

    @Override
    public void initData(Bundle bundle) {
        //设置标题
        headTitle.setText("变更密码");
    }

    @Override
    public void initEvents() {
        headBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        llChangepwdLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(ChangeLoginPwdActivity.class);
            }
        });
        llChangepwdTrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(ChangeTradePwdActivity.class);
            }
        });

    }

}
