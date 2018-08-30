package cn.com.buyforyou.fund.ui.activity.boot;

import android.os.Bundle;

import cn.com.buyforyou.fund.ui.activity.base.BaseWebActivity;

/**
 * 作者：sunnyzeng on 2018/2/1 14:13
 * 描述：不带token userid 公共H5页面
 */

public class WebPublicActivity extends BaseWebActivity {
    @Override
    protected void myLoadUrl(Bundle bundle) {
        mWeb.loadUrl(link);
    }

    @Override
    public Object newP() {
        return null;
    }
}
