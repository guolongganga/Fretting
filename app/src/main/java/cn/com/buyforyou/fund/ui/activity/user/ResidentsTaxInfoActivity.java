package cn.com.buyforyou.fund.ui.activity.user;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.zhsoft.fretting.ui.widget.SelectPopupWindow;
import com.zhsoft.fretting.ui.widget.wheel.ScrollListView;

import org.apache.http.util.TextUtils;

import java.util.ArrayList;

import butterknife.BindView;
import cn.com.buyforyou.fund.App;
import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.constant.Constant;
import cn.com.buyforyou.fund.model.ApplyBaseInfo;
import cn.com.buyforyou.fund.model.user.OccupationResp;
import cn.com.buyforyou.fund.model.user.ResidentsTaxInfoResp;
import cn.com.buyforyou.fund.model.user.TaxInfoEResp;
import cn.com.buyforyou.fund.present.user.ResidentsTaxInfoPresent;
import cn.com.buyforyou.fund.ui.adapter.user.TaxInfoAdapter;
import cn.com.buyforyou.fund.utils.RuntimeHelper;
import cn.droidlover.xdroidmvp.dialog.httploadingdialog.HttpLoadingDialog;
import cn.droidlover.xdroidmvp.mvp.XActivity;

/**
 * 居民涉税信息
 */
public class ResidentsTaxInfoActivity extends XActivity<ResidentsTaxInfoPresent> {
    //返回按钮
    @BindView(R.id.head_back)
    ImageButton headBack;
    //标题
    @BindView(R.id.head_title)
    TextView headTitle;
    //title信息
    @BindView(R.id.tax_tiletop)
    TextView tileTop;
    //现居住国家
    @BindView(R.id.countries)
    TextView countries;
    //出生国家
    @BindView(R.id.born_countries)
    TextView born_countries;
    //现居住国家（英文）
    @BindView(R.id.now_living_state)
    TextView now_living_state;
    //出生国家（英文）
    @BindView(R.id.country_birth)
    TextView country_birth;


    //现居住详细地址
    @BindView(R.id.address)
    EditText address;
    //出生省份/城市
    @BindView(R.id.address_provinces)
    EditText address_provinces;
    //姓（英文）
    @BindView(R.id.surname_e)
    EditText surname_e;
    //名（英文）
    @BindView(R.id.name_e)
    EditText name_e;
    //现居住地址（英文）
    @BindView(R.id.now_address_state)
    EditText now_address_state;
    //现居住详细地址（英文）
    @BindView(R.id.now_address_detailed)
    EditText now_address_detailed;
    //出生省份/城市（英文）
    @BindView(R.id.born_provinces)
    EditText born_provinces;

    @BindView(R.id.listView)
    ScrollListView listView;

    //增加其他税收居民国信息
    @BindView(R.id.add_tax_info)
    TextView add_tax_info;


    @BindView(R.id.btn_save)
    Button btn_save;

    //个人信息传递过来的选项
    private String caption = "";
    private String keyvalue = "";
    //用户编号
    private String userId;
    //登录标识
    private String token;
    //加载框
    private HttpLoadingDialog httpLoadingDialog;

    //纳税信息
    private ResidentsTaxInfoResp residentsTaxInfoResp;

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_taxinfo;
    }

    @Override
    public ResidentsTaxInfoPresent newP() {
        return new ResidentsTaxInfoPresent();
    }


    @Override
    public void initData(Bundle bundle) {
        //设置标题
        headTitle.setText("居民涉税信息");
        headBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        httpLoadingDialog = new HttpLoadingDialog(context);

        //获取用户缓存的userid 和 token
        userId = App.getSharedPref().getString(Constant.USERID, "");
        token = App.getSharedPref().getString(Constant.TOKEN, "");

        caption = bundle.getString("caption");
        keyvalue = bundle.getString("keyvalue");

        tileTop.setText("您的居民税收类型为:" + caption + "，请补充填写居民涉税信息");


        //请求职业集合接口
        httpLoadingDialog.visible();
        getP().getResidentsTax(token, userId);

    }

    @Override
    public void initEvents() {

    }


    /**
     * 请求用户信息成功
     *
     * @param residentsTaxInfoResp 返回的对象
     */
    public void requestUserInfoSuccess(final ResidentsTaxInfoResp residentsTaxInfoResp) {
        httpLoadingDialog.dismiss();
        this.residentsTaxInfoResp = residentsTaxInfoResp;

        for (OccupationResp occupationResp : residentsTaxInfoResp.getNationList()) {
            //现居住国家
            if (occupationResp.getKeyvalue().equals(residentsTaxInfoResp.getCrsmain().getCurrent_work_nation())) {
                countries.setText(occupationResp.getCaption());
            }
            //出生国家
            if (occupationResp.getKeyvalue().equals(residentsTaxInfoResp.getCrsmain().getBorn_nation())) {
                born_countries.setText(occupationResp.getCaption());
            }
        }

        for (OccupationResp occupationResp : residentsTaxInfoResp.getNationList_en()) {
            //现居住国家 英文
            if (occupationResp.getKeyvalue().equals(residentsTaxInfoResp.getCrsmain().getEng_current_work_nation())) {
                now_living_state.setText(occupationResp.getCaption());
            }

            //出生国家 英文
            if (occupationResp.getKeyvalue().equals(residentsTaxInfoResp.getCrsmain().getEng_born_nation())) {
                country_birth.setText(occupationResp.getCaption());
            }
        }


        address.setText(residentsTaxInfoResp.getCrsmain().getCurrent_work_addr());
        address_provinces.setText(residentsTaxInfoResp.getCrsmain().getHome_place());
        surname_e.setText(residentsTaxInfoResp.getCrsmain().getEng_name());
        name_e.setText(residentsTaxInfoResp.getCrsmain().getEng_last_name());
        now_address_state.setText(residentsTaxInfoResp.getCrsmain().getEng_current_addr());
        now_address_detailed.setText(residentsTaxInfoResp.getCrsmain().getCurrent_addr());
        born_provinces.setText(residentsTaxInfoResp.getCrsmain().getEng_home_place());

        initPopWindow();

        if (null != residentsTaxInfoResp.getCrsmain().getTaxinfo() && residentsTaxInfoResp.getCrsmain().getTaxinfo().size() == 0) {
            TaxInfoEResp taxInfoEResp = new TaxInfoEResp();
            taxInfoEResp.setIs_main_tax("1");
            taxInfoEResp.setNo_tax_no_reason("A");
            residentsTaxInfoResp.getCrsmain().getTaxinfo().add(taxInfoEResp);
        }

        final TaxInfoAdapter taxInfoAdapter = new TaxInfoAdapter(this, residentsTaxInfoResp.getCrsmain().getTaxinfo(), residentsTaxInfoResp.getNationList(), residentsTaxInfoResp.getNoTaxReasonList());
        listView.setAdapter(taxInfoAdapter);

        add_tax_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TaxInfoEResp taxInfoEResp = new TaxInfoEResp();
                taxInfoEResp.setIs_main_tax("0");
                taxInfoEResp.setNo_tax_no_reason("A");
                residentsTaxInfoResp.getCrsmain().getTaxinfo().add(taxInfoEResp);
                taxInfoAdapter.getTaxInfoEResps_e().add(taxInfoEResp);
                taxInfoAdapter.notifyDataSetChanged();

            }
        });


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (residentsTaxInfoResp.getCrsmain().getCurrent_work_nation().equals("")) {
                    showToast("请选择现居住国家");
                    return;
                }

                if (residentsTaxInfoResp.getCrsmain().getBorn_nation().equals("")) {
                    showToast("请选择出生国家");
                    return;
                }

                if (address.getText().toString().trim().equals("")) {
                    showToast("请输入现居住详细地址");
                    return;
                }
                if (address_provinces.getText().toString().trim().equals("")) {
                    showToast("请输入出生省份/城市");
                    return;
                }
                if (surname_e.getText().toString().trim().equals("")) {
                    showToast("请输入姓（英文）");
                    return;
                }
                if (name_e.getText().toString().trim().equals("")) {
                    showToast("请输入名（英文）");
                    return;
                }
                if (now_address_state.getText().toString().trim().equals("")) {
                    showToast("请输入现居住地址（英文）");
                    return;
                }
                if (now_address_detailed.getText().toString().trim().equals("")) {
                    showToast("请输入现居住详细地址（英文）");
                    return;
                }
                if (born_provinces.getText().toString().trim().equals("")) {
                    showToast("请输入出生省份/城市（英文）");
                    return;
                }

                String type = "0";

                for (TaxInfoEResp taxInfoEResp : residentsTaxInfoResp.getCrsmain().getTaxinfo()) {
                    if (null == taxInfoEResp.getTax_nation() || taxInfoEResp.getTax_nation().equals("")) {
                        showToast("请选择税收居民国（地区）");
                        type = "1";
                        break;
                    }
                }

                if (type.equals("1")) {
                    return;
                }

                for (TaxInfoEResp taxInfoEResp : taxInfoAdapter.getTaxInfoEResps_e()) {
                    if (null == taxInfoEResp.getTax_no() || taxInfoEResp.getTax_no().equals("")) {
                        showToast("请输入可用的纳税识别号码");
                        type = "1";
                        break;
                    }
                }

                if (type.equals("1")) {
                    return;
                }

                for (TaxInfoEResp taxInfoEResp : residentsTaxInfoResp.getCrsmain().getTaxinfo()) {
                    if (null == taxInfoEResp.getNo_tax_no_reason() || taxInfoEResp.getNo_tax_no_reason().equals("B")) {
                        if (TextUtils.isEmpty(taxInfoEResp.getTax_explain())) {
                            showToast("请输入未能取得纳税人识别号的具体原因");
                            type = "1";
                            break;
                        }
                    }
                }

                if (type.equals("1")) {
                    return;
                }

                for (int i = 0; i < residentsTaxInfoResp.getCrsmain().getTaxinfo().size(); i++) {
                    TaxInfoEResp taxInfoEResp = residentsTaxInfoResp.getCrsmain().getTaxinfo().get(i);
                    taxInfoEResp.setTax_no(taxInfoAdapter.getTaxInfoEResps_e().get(i).getTax_no());
                    taxInfoEResp.setTax_explain(taxInfoAdapter.getTaxInfoEResps_e().get(i).getTax_explain());
                }

                residentsTaxInfoResp.getCrsmain().setCurrent_work_addr(address.getText().toString());
                residentsTaxInfoResp.getCrsmain().setHome_place(address_provinces.getText().toString());
                residentsTaxInfoResp.getCrsmain().setEng_name(surname_e.getText().toString());
                residentsTaxInfoResp.getCrsmain().setEng_last_name(name_e.getText().toString());
                residentsTaxInfoResp.getCrsmain().setEng_current_addr(now_address_state.getText().toString());
                residentsTaxInfoResp.getCrsmain().setCurrent_addr(now_address_detailed.getText().toString());
                residentsTaxInfoResp.getCrsmain().setEng_home_place(born_provinces.getText().toString());

                residentsTaxInfoResp.getCrsmain().setCust_flag(keyvalue);
                httpLoadingDialog.visible("保存中...");
                getP().saveTaxInfo(token, userId, residentsTaxInfoResp);

            }
        });
    }


    /**
     * 初始化弹出框
     */
    private void initPopWindow() {

        ArrayList<ApplyBaseInfo> cycleList = new ArrayList<>();
        for (OccupationResp occupationResp : residentsTaxInfoResp.getNationList()) {
            cycleList.add(new ApplyBaseInfo(occupationResp.getKeyvalue(), occupationResp.getCaption()));
        }
        ArrayList<ApplyBaseInfo> cycleList_en = new ArrayList<>();
        for (OccupationResp occupationResp : residentsTaxInfoResp.getNationList_en()) {
            cycleList_en.add(new ApplyBaseInfo(occupationResp.getKeyvalue(), occupationResp.getCaption()));
        }
        //现居住国家弹框
        final SelectPopupWindow cyclePopupWindow1 = new SelectPopupWindow(this, cycleList);
        cyclePopupWindow1.setCallBackCode(new SelectPopupWindow.CallBackCode() {
            @Override
            public void onSelectChange(int currentI11tem, String name, String code) {

                residentsTaxInfoResp.getCrsmain().setCurrent_work_nation(code);
                residentsTaxInfoResp.getCrsmain().setEng_current_work_nation(code);
                countries.setText(name);
                for (OccupationResp occupationResp : residentsTaxInfoResp.getNationList_en()) {
                    //现居住国家 英文
                    if (occupationResp.getKeyvalue().equals(code)) {
                        now_living_state.setText(occupationResp.getCaption());
                    }
                }
            }
        });

        countries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //设置layout在PopupWindow中显示的位置
                cyclePopupWindow1.showAtLocation(countries, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
            }
        });

        //现居住国家英文弹框
        final SelectPopupWindow cyclePopupWindow2 = new SelectPopupWindow(this, cycleList_en);

        cyclePopupWindow2.setCallBackCode(new SelectPopupWindow.CallBackCode() {
            @Override
            public void onSelectChange(int current11Item, String name, String code) {

                residentsTaxInfoResp.getCrsmain().setCurrent_work_nation(code);
                residentsTaxInfoResp.getCrsmain().setEng_current_work_nation(code);
                now_living_state.setText(name);

                for (OccupationResp occupationResp : residentsTaxInfoResp.getNationList()) {
                    //现居住国家 英文
                    if (occupationResp.getKeyvalue().equals(code)) {
                        countries.setText(occupationResp.getCaption());
                    }
                }
            }
        });

        now_living_state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //设置layout在PopupWindow中显示的位置
                cyclePopupWindow2.showAtLocation(now_living_state, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
            }
        });


        //出生国家
        final SelectPopupWindow cyclePopupWindow3 = new SelectPopupWindow(this, cycleList);
        cyclePopupWindow3.setCallBackCode(new SelectPopupWindow.CallBackCode() {
            @Override
            public void onSelectChange(int currentItem, String name, String code) {

                residentsTaxInfoResp.getCrsmain().setBorn_nation(code);
                residentsTaxInfoResp.getCrsmain().setEng_born_nation(code);
                born_countries.setText(name);

                for (OccupationResp occupationResp : residentsTaxInfoResp.getNationList_en()) {
                    //现居住国家 英文
                    if (occupationResp.getKeyvalue().equals(code)) {
                        country_birth.setText(occupationResp.getCaption());
                    }
                }
            }
        });

        born_countries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //设置layout在PopupWindow中显示的位置
                cyclePopupWindow3.showAtLocation(born_countries, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
            }
        });

        //出生国家英文
        final SelectPopupWindow cyclePopupWindow4 = new SelectPopupWindow(this, cycleList_en);
        cyclePopupWindow4.setCallBackCode(new SelectPopupWindow.CallBackCode() {
            @Override
            public void onSelectChange(int currentItem, String name, String code) {

                residentsTaxInfoResp.getCrsmain().setBorn_nation(code);
                residentsTaxInfoResp.getCrsmain().setEng_born_nation(code);
                country_birth.setText(name);

                for (OccupationResp occupationResp : residentsTaxInfoResp.getNationList()) {
                    //现居住国家 英文
                    if (occupationResp.getKeyvalue().equals(code)) {
                        born_countries.setText(occupationResp.getCaption());
                    }
                }
            }
        });

        country_birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //设置layout在PopupWindow中显示的位置
                cyclePopupWindow4.showAtLocation(born_countries, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
            }
        });

    }


    /**
     * 保存我的信息成功
     */
    public void changeMyInformationSuccess() {
        httpLoadingDialog.dismiss();
        showToast("保存成功");
        finish();
    }

    public void requestUserInfoFail() {
        httpLoadingDialog.dismiss();
    }

    public void areadyLogout() {
        httpLoadingDialog.dismiss();
        //清除本地缓存，设置成未登录
        RuntimeHelper.getInstance().isInvalidToken();
        //跳转登录界面
        Bundle bundle = new Bundle();
        bundle.putString(Constant.SKIP_SIGN, Constant.SKIP_INDEX_ACTIVITY);
        startActivity(LoginActivity.class, bundle);
    }

    public void passwordError(String message) {
        httpLoadingDialog.dismiss();

    }

    /**
     * 保存我的信息失败
     */
    public void changeMyInformationFail() {
        httpLoadingDialog.dismiss();
    }
}
