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
import com.zhsoft.fretting.ui.widget.wheel.OnWheelChangedListener;
import com.zhsoft.fretting.ui.widget.wheel.WheelView;
import com.zhsoft.fretting.ui.widget.wheel.adapters.ArrayWheelAdapter;
import com.zhsoft.fretting.ui.widget.wheel.model.CityModel;
import com.zhsoft.fretting.ui.widget.wheel.model.DistrictModel;
import com.zhsoft.fretting.ui.widget.wheel.model.ProvinceModel;
import com.zhsoft.fretting.ui.widget.wheel.service.XmlParserHandler;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * <p>Description: 地区选择菜单</p>
 *
 * @author sunnyzeng
 */
public class CitySelectPopupWindow extends PopupWindow implements OnWheelChangedListener {
    private View mMenuView;
    private WheelView mViewProvince;
    private WheelView mViewCity;
    private WheelView mViewDistrict;
    private Context mContext;
    private CallBack callBack;

    public interface CallBack {
        void onSelectChange(String name);
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    public CitySelectPopupWindow(Context context) {
        super(context);
        this.mContext = context;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.layout_city_select, null);
        Button onCancle = (Button) mMenuView.findViewById(R.id.btn_cancle);
        Button onComplete = (Button) mMenuView.findViewById(R.id.btn_complete);
        mViewProvince = (WheelView) mMenuView.findViewById(R.id.id_province);
        mViewCity = (WheelView) mMenuView.findViewById(R.id.id_city);
        mViewDistrict = (WheelView) mMenuView.findViewById(R.id.id_district);
        mViewProvince.addChangingListener(this);
        mViewCity.addChangingListener(this);
        mViewDistrict.addChangingListener(this);
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

                int currentItem = mViewDistrict.getCurrentItem();
                String[] strings = mDistrictDatasMap.get(mCurrentCityName);
                if (strings.length > 0) {
                    mCurrentDistrictName = strings[currentItem];
                } else {
                    mCurrentDistrictName = "";
                }
                if (callBack != null) {
                    callBack.onSelectChange(mCurrentProviceName + mCurrentCityName + mCurrentDistrictName);
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

        //加载地区信息
        initProvinceDatas();
        mViewProvince.setViewAdapter(new ArrayWheelAdapter<String>(mContext, mProvinceDatas));
        // 设置可见条目数量
        mViewProvince.setVisibleItems(7);
        mViewCity.setVisibleItems(7);
        mViewDistrict.setVisibleItems(7);
        updateCities();
        updateAreas();
    }

    /** 根据当前的市，更新区WheelView的信息 */
    private void updateAreas() {
        int pCurrent = mViewCity.getCurrentItem();
        mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[pCurrent];
        String[] areas = mDistrictDatasMap.get(mCurrentCityName);

        if (areas == null) {
            areas = new String[]{""};
        }
        mViewDistrict.setViewAdapter(new ArrayWheelAdapter<String>(mContext, areas));
        mViewDistrict.setCurrentItem(0);
    }

    /** 根据当前的省，更新市WheelView的信息 */
    private void updateCities() {
        int pCurrent = mViewProvince.getCurrentItem();
        mCurrentProviceName = mProvinceDatas[pCurrent];
        String[] cities = mCitisDatasMap.get(mCurrentProviceName);
        if (cities == null) {
            cities = new String[]{""};
        }
        mViewCity.setViewAdapter(new ArrayWheelAdapter<String>(mContext, cities));
        mViewCity.setCurrentItem(0);
        updateAreas();
    }

    /** 所有省 */
    private String[] mProvinceDatas;
    /** key - 省 value - 市 */
    private Map<String, String[]> mCitisDatasMap = new HashMap<String, String[]>();
    /** key - 市 values - 区 */
    private Map<String, String[]> mDistrictDatasMap = new HashMap<String, String[]>();


    /** 当前省的名称 */
    private String mCurrentProviceName;
    /** 当前市的名称 */
    private String mCurrentCityName;
    /** 当前区的名称 */
    private String mCurrentDistrictName;

    /** 当前区域Id */
    private int mCurrentZipCode = 0;

    /** 解析省市区的XML数据 */
    protected void initProvinceDatas() {
        List<ProvinceModel> provinceList = null;
        AssetManager asset = mContext.getAssets();
        try {
            InputStream input = asset.open("region.xml");
            // 创建一个解析xml的工厂对象
            SAXParserFactory spf = SAXParserFactory.newInstance();
            // 解析xml
            SAXParser parser = spf.newSAXParser();
            XmlParserHandler handler = new XmlParserHandler();
            parser.parse(input, handler);
            input.close();
            // 获取解析出来的数据
            provinceList = handler.getDataList();
            //*/ 初始化默认选中的省、市、区
            if (provinceList != null && !provinceList.isEmpty()) {
                mCurrentProviceName = provinceList.get(0).getName();
                List<CityModel> cityList = provinceList.get(0).getCityList();
                if (cityList != null && !cityList.isEmpty()) {
                    mCurrentCityName = cityList.get(0).getName();
                    List<DistrictModel> districtList = cityList.get(0).getDistrictList();
                    mCurrentDistrictName = districtList.get(0).getName();
                    mCurrentZipCode = districtList.get(0).getId();
                }
            }
            //*/
            mProvinceDatas = new String[provinceList.size()];
            for (int i = 0; i < provinceList.size(); i++) {
                // 遍历所有省的数据
                mProvinceDatas[i] = provinceList.get(i).getName();
                List<CityModel> cityList = provinceList.get(i).getCityList();
                String[] cityNames = new String[cityList.size()];
                for (int j = 0; j < cityList.size(); j++) {
                    // 遍历省下面的所有市的数据
                    cityNames[j] = cityList.get(j).getName();
                    List<DistrictModel> districtList = cityList.get(j).getDistrictList();
                    String[] distrinctNameArray = new String[districtList.size()];
                    DistrictModel[] distrinctArray = new DistrictModel[districtList.size()];
                    for (int k = 0; k < districtList.size(); k++) {
                        // 遍历市下面所有区/县的数据
                        DistrictModel districtModel = new DistrictModel(districtList.get(k).getId(), districtList.get(k).getName());
                        // 区/县对于的邮编，保存到mZipcodeDatasMap
                        distrinctArray[k] = districtModel;
                        distrinctNameArray[k] = districtModel.getName();
                    }
                    // 市-区/县的数据，保存到mDistrictDatasMap
                    mDistrictDatasMap.put(cityNames[j], distrinctNameArray);
                }
                // 省-市的数据，保存到mCitisDatasMap
                mCitisDatasMap.put(provinceList.get(i).getName(), cityNames);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        finally {

        }
    }


    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        if (wheel == mViewProvince) {
            updateCities();
        } else if (wheel == mViewCity) {
            updateAreas();
        } else if (wheel == mViewDistrict) {
            mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[newValue];
        }
    }
}
