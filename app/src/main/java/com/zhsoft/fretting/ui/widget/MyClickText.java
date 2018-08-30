package com.zhsoft.fretting.ui.widget;

import android.content.Context;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import cn.com.buyforyou.fund.R;


/**
 * text点击事件
 * Created by end on 2016/8/30.
 */
public class MyClickText extends ClickableSpan {


    private OnTvClick onTvClick;

    private Context context;

    public void setOnTvClick(OnTvClick onTvClick) {
        this.onTvClick = onTvClick;
    }

    public MyClickText(Context context) {
        this.context = context;
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
        //设置文本的颜色
        ds.setColor(context.getResources().getColor(R.color.color_4D7BFE));
        ds.setUnderlineText(false);
    }

    @Override
    public void onClick(View widget) {

        if (onTvClick != null) {
            onTvClick.onClick(widget);
        }

    }

}


