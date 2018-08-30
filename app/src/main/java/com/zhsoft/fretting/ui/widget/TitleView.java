package com.zhsoft.fretting.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;


import cn.com.buyforyou.fund.R;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * Created by ${Yis}
 * data: 2017/12/4
 */

public class TitleView extends RelativeLayout {

    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.title_name)
    TextView titleName;

    public TitleView(Context context) {
        super(context);
        setupView(context);
    }

    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupView(context);
    }

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupView(context);
    }


    private void setupView(final Context context) {
        inflate(context, R.layout.public_title, this);
        KnifeKit.bind(this);
    }

    public void setTitle(final Activity activity, int titleResouce) {
        //设置标题
        if (titleResouce != 0) {
            titleName.setText(titleResouce);
        }
        //关闭Activity页面
        rlBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.finish();
            }
        });
    }
}
