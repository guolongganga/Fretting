package com.zhsoft.fretting.ui.widget;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.model.ApplyBaseInfo;

import com.zhsoft.fretting.ui.widget.wheel.OnWheelChangedListener;
import com.zhsoft.fretting.ui.widget.wheel.WheelView;
import com.zhsoft.fretting.ui.widget.wheel.adapters.ArrayWheelAdapter;
import com.zhsoft.fretting.ui.widget.wheel.model.CityModel;
import com.zhsoft.fretting.ui.widget.wheel.model.DistrictModel;
import com.zhsoft.fretting.ui.widget.wheel.model.ProvinceModel;
import com.zhsoft.fretting.ui.widget.wheel.service.XmlParserHandler;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import cn.droidlover.xdroidmvp.log.XLog;

/**
 * <p>Description: 地区选择菜单</p>
 *
 * @author sunnyzeng
 */
public class SelectPopupWindow extends PopupWindow implements OnWheelChangedListener {
    private View mMenuView;
    private WheelView mViewContent;
    private Context mContext;
    private CallBack callBack;
    private CallBackCode callBackCode;
    private ArrayList<ApplyBaseInfo> list;

    public interface CallBack {
        void onSelectChange(int currentItem, String name);
    }

    public interface CallBackCode {
        void onSelectChange(int currentItem, String name, String Code);
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    public void setCallBackCode(CallBackCode callBackCode) {
        this.callBackCode = callBackCode;
    }

    public SelectPopupWindow(Context context, final ArrayList<ApplyBaseInfo> list) {
        super(context);
        this.mContext = context;
        this.list = list;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.layout_select_pop, null);
        Button onCancle = (Button) mMenuView.findViewById(R.id.btn_cancle);
        Button onComplete = (Button) mMenuView.findViewById(R.id.btn_complete);
        mViewContent = (WheelView) mMenuView.findViewById(R.id.id_content);
        mViewContent.addChangingListener(this);
        onCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        onComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();

                int currentItem = mViewContent.getCurrentItem();
                if (callBack != null) {
                    callBack.onSelectChange(currentItem, list.get(currentItem).getContent());
                }
                if (callBackCode != null) {
                    callBackCode.onSelectChange(currentItem, list.get(currentItem).getContent(), list.get(currentItem).getCode());
                }
            }
        });

        //设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.FILL_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimBottom);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);

        //加载信息
        initDatas();
        mViewContent.setViewAdapter(new ArrayWheelAdapter<String>(mContext, mDatas));
        mViewContent.setVisibleItems(7);
    }

    /**
     * 所有信息
     */
    private String[] mDatas;

    protected void initDatas() {
        mDatas = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            mDatas[i] = list.get(i).getContent();
        }
    }


    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        if (wheel == mViewContent) {
//            updateCities();
//            XLog.e("oldValue=" + oldValue + ",newValue=" + newValue);
        }
    }
}
