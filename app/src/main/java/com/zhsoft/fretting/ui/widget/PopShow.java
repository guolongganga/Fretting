package com.zhsoft.fretting.ui.widget;

import android.app.Activity;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import cn.com.buyforyou.fund.App;
import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.model.ApplyBaseInfo;
import cn.com.buyforyou.fund.model.fund.NewestFundResp;
import cn.com.buyforyou.fund.ui.adapter.boot.PopBottomSelectorRecycleAdapter;
import cn.com.buyforyou.fund.ui.adapter.boot.PopBottomSelectorRecycleAdapter2;
import cn.com.buyforyou.fund.ui.adapter.boot.SearchRecycleAdapter;
import cn.com.buyforyou.fund.ui.adapter.boot.PopDropSelectorRecycleAdapter;

import java.util.List;
import java.util.Map;

import cn.droidlover.xrecyclerview.RecyclerItemCallback;
import cn.droidlover.xrecyclerview.XRecyclerView;

/**
 * PoP展示
 * Created by ${sunny}
 * data: 2017/12/19
 */

public class PopShow {

    private Activity activity;
    private View view;

    public PopShow(Activity activity, View view) {
        this.activity = activity;
        this.view = view;
    }

    /**
     * 涨幅选择
     */
    public void showRangeSelector(final List<ApplyBaseInfo> list, int isSelector) {
        //设置contentView
        View contentView = LayoutInflater.from(activity).inflate(R.layout.pop_drop_selector, null);
        final PopupWindow mPopWindow = new PopupWindow(contentView,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        mPopWindow.setContentView(contentView);
        mPopWindow.setFocusable(true);
        //外部是否可以点击
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopWindow.setOutsideTouchable(true);

        FrameLayout flPopContent = contentView.findViewById(R.id.fl_content);
        flPopContent.getBackground().setAlpha(150);

        XRecyclerView xrvDropSelector = contentView.findViewById(R.id.xrv_drop_selector);
        xrvDropSelector.verticalLayoutManager(activity);
        final PopDropSelectorRecycleAdapter popAdapter = new PopDropSelectorRecycleAdapter(activity);
        xrvDropSelector.setAdapter(popAdapter);
        popAdapter.setData(list);
        popAdapter.setIsSelector(isSelector);//默认选中项

        popAdapter.setRecItemClick(new RecyclerItemCallback<ApplyBaseInfo, PopDropSelectorRecycleAdapter.ViewHolder>() {
            @Override
            public void onItemClick(int position, ApplyBaseInfo model, int tag, PopDropSelectorRecycleAdapter.ViewHolder holder) {
                switch (tag) {
                    case PopDropSelectorRecycleAdapter.ITEM_CLICK:
                        popAdapter.setIsSelector(position);
                        popAdapter.notifyDataSetChanged();
                        onClickPop.setRange(position);
                        mPopWindow.dismiss();
                        break;
                }
            }
        });
        selfDropDown(mPopWindow, view);
    }

    /**
     * 纯字符显示 在底部
     *
     * @param list
     */
    public void showText(final List<String> list) {
        //设置contentView
        View contentView = LayoutInflater.from(activity).inflate(R.layout.pop_bottom_selector, null);
        final PopupWindow mPopWindow = new PopupWindow(contentView,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        mPopWindow.setContentView(contentView);
        mPopWindow.setFocusable(true);
        //外部是否可以点击
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopWindow.setOutsideTouchable(true);
        //设置PopupWindow弹出窗体动画效果
        mPopWindow.setAnimationStyle(R.style.AnimBottom);

//        FrameLayout flPopContent = contentView.findViewById(R.id.fl_content);
//        flPopContent.getBackground().setAlpha(150);
        //取消按钮
        Button cancle = contentView.findViewById(R.id.cancle);
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopWindow.dismiss();
            }
        });

        XRecyclerView xrvDropSelector = contentView.findViewById(R.id.xrv_drop_selector);
        xrvDropSelector.verticalLayoutManager(activity);
        final PopBottomSelectorRecycleAdapter popAdapter = new PopBottomSelectorRecycleAdapter(activity);
        xrvDropSelector.setAdapter(popAdapter);
        popAdapter.setData(list);

        popAdapter.setRecItemClick(new RecyclerItemCallback<String, PopBottomSelectorRecycleAdapter.ViewHolder>() {
            @Override
            public void onItemClick(int position, String model, int tag, PopBottomSelectorRecycleAdapter.ViewHolder holder) {
                switch (tag) {
                    case PopBottomSelectorRecycleAdapter.ITEM_CLICK:
                        popAdapter.notifyDataSetChanged();
                        onClickPop.setRange(position);
                        mPopWindow.dismiss();
                        break;
                }
            }
        });
//        mPopWindow.showAtLocation(view, Gravity.CENTER | Gravity.BOTTOM, 0, 0);
        selfDropDown(mPopWindow, view);
    }

    /**
     * 纯字符显示 在底部，带编码
     *
     * @param list
     */
    public void showTextWithCode(final List<ApplyBaseInfo> list) {
        //设置contentView
        View contentView = LayoutInflater.from(activity).inflate(R.layout.pop_bottom_selector, null);
        final PopupWindow mPopWindow = new PopupWindow(contentView,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        mPopWindow.setContentView(contentView);
        mPopWindow.setFocusable(true);
        //外部是否可以点击
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopWindow.setOutsideTouchable(true);
        //设置PopupWindow弹出窗体动画效果
        mPopWindow.setAnimationStyle(R.style.AnimBottom);

//        FrameLayout flPopContent = contentView.findViewById(R.id.fl_content);
//        flPopContent.getBackground().setAlpha(150);
        //取消按钮
        Button cancle = contentView.findViewById(R.id.cancle);
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopWindow.dismiss();
            }
        });

        XRecyclerView xrvDropSelector = contentView.findViewById(R.id.xrv_drop_selector);
        xrvDropSelector.verticalLayoutManager(activity);
        final PopBottomSelectorRecycleAdapter2 popAdapter = new PopBottomSelectorRecycleAdapter2(activity);
        xrvDropSelector.setAdapter(popAdapter);
        popAdapter.setData(list);

        popAdapter.setRecItemClick(new RecyclerItemCallback<ApplyBaseInfo, PopBottomSelectorRecycleAdapter2.ViewHolder>() {
            @Override
            public void onItemClick(int position, ApplyBaseInfo model, int tag, PopBottomSelectorRecycleAdapter2.ViewHolder holder) {
                switch (tag) {
                    case PopBottomSelectorRecycleAdapter.ITEM_CLICK:
                        popAdapter.notifyDataSetChanged();
                        onClickPop.setRange(position);
                        mPopWindow.dismiss();
                        break;
                }
            }
        });
//        mPopWindow.showAtLocation(view, Gravity.CENTER | Gravity.BOTTOM, 0, 0);
        selfDropDown(mPopWindow, view);
    }

    /**
     * 搜索下拉 未使用（用pop弹出输入框的焦点获取有问题）
     */
    public void showSearch(final List<NewestFundResp> list) {
        //设置contentView
        View contentView = LayoutInflater.from(activity).inflate(R.layout.pop_drop_selector_bg_white, null);
        final PopupWindow mPopWindow = new PopupWindow(contentView,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        mPopWindow.setContentView(contentView);
        mPopWindow.setFocusable(true);
        //外部是否可以点击
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopWindow.setOutsideTouchable(true);

        FrameLayout flPopContent = contentView.findViewById(R.id.fl_content);
//        flPopContent.getBackground().setAlpha(150);

        XRecyclerView xrvDropSelector = contentView.findViewById(R.id.xrv_drop_selector);
        xrvDropSelector.verticalLayoutManager(activity);
        final SearchRecycleAdapter popAdapter = new SearchRecycleAdapter(activity);
        xrvDropSelector.setAdapter(popAdapter);
        popAdapter.setData(list);

        popAdapter.setRecItemClick(new RecyclerItemCallback<NewestFundResp, SearchRecycleAdapter.ViewHolder>() {
            @Override
            public void onItemClick(int position, NewestFundResp model, int tag, SearchRecycleAdapter.ViewHolder holder) {
                switch (tag) {
                    case SearchRecycleAdapter.ITEM_CLICK:
                        popAdapter.notifyDataSetChanged();
                        onClickPop.setRange(position);
                        mPopWindow.dismiss();
                        break;
                }
            }
        });
//        mPopWindow.showAsDropDown(view, 0, 0);
        selfDropDown(mPopWindow, view);
    }


    public OnClickPop onClickPop;

    public void setOnClickPop(OnClickPop onClickPop) {
        this.onClickPop = onClickPop;
    }

    public interface OnClickPop {
        void setRange(int position);
    }

    /**
     * 显示PopupWindow 适配android7。0+
     *
     * @param mPopWindow
     * @param view
     */
    public void selfDropDown(PopupWindow mPopWindow, View view) {
        //显示PopupWindow 适配android7。0+
        if (Build.VERSION.SDK_INT >= 24) {
            Rect visibleFrame = new Rect();
            view.getGlobalVisibleRect(visibleFrame);
            int height = view.getResources().getDisplayMetrics().heightPixels - visibleFrame.bottom;
            mPopWindow.setHeight(height);
            mPopWindow.showAsDropDown(view);
        } else {
            mPopWindow.showAsDropDown(view);
        }
    }
}
