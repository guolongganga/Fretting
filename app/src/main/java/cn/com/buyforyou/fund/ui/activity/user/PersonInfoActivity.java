package cn.com.buyforyou.fund.ui.activity.user;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.com.buyforyou.fund.App;
import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.constant.Constant;
import cn.com.buyforyou.fund.event.InvalidTokenEvent;
import cn.com.buyforyou.fund.model.ApplyBaseInfo;
import cn.com.buyforyou.fund.model.user.OccupationResp;
import cn.com.buyforyou.fund.model.user.PersonInfoResp;
import cn.com.buyforyou.fund.present.user.PersonInfoPresent;

import com.zhsoft.fretting.ui.widget.CustomDialog;
import com.zhsoft.fretting.ui.widget.FundBuyDialog;
import com.zhsoft.fretting.ui.widget.PopShow;
import com.zhsoft.fretting.ui.widget.CitySelectPopupWindow;

import cn.com.buyforyou.fund.utils.KeyBoardUtils;

import com.zhsoft.fretting.ui.widget.ChenJingET;
import com.zhsoft.fretting.ui.widget.SelectPopupWindow;

import cn.com.buyforyou.fund.utils.RuntimeHelper;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.dialog.httploadingdialog.HttpLoadingDialog;
import cn.droidlover.xdroidmvp.mvp.XActivity;

/**
 * 作者：sunnyzeng on 2017/12/13 10:13
 * 描述：个人信息
 */

public class PersonInfoActivity extends XActivity<PersonInfoPresent> {
    //时间类
    private Calendar calendar = Calendar.getInstance();
    //时间选择器
    private DatePickerDialog timeDialog;
    /**
     * 返回按钮
     */
    @BindView(R.id.head_back)
    ImageButton headBack;
    /**
     * 标题
     */
    @BindView(R.id.head_title)
    TextView headTitle;
    /**
     * 用户名
     */
    @BindView(R.id.name)
    TextView name;
    /**
     * 身份证号
     */
    @BindView(R.id.identity)
    TextView identity;
    /**
     * 账户编号
     */
    @BindView(R.id.accountid)
    TextView accountid;
    /**
     * 邮箱
     */
    @BindView(R.id.email)
    EditText email;
    /**
     * 性别
     */
    @BindView(R.id.sex)
    TextView sex;
    /**
     * 身份证期限
     */
    @BindView(R.id.limit_time)
    TextView limitTime;
    /**
     * 选择 永久有效
     */
    @BindView(R.id.iv_selector)
    ImageView ivSelector;
    /**
     * 国籍
     */
    @BindView(R.id.nationality)
    TextView nationality;
    /**
     * 选择职业
     */
    @BindView(R.id.linearlayout_duty)
    LinearLayout linearlayout_duty;
    /**
     * 职业
     */
    @BindView(R.id.duty)
    TextView duty;
    /**
     * 居民税收选择框
     */
    @BindView(R.id.residents_tax)
    TextView residentsTax;
    /**
     * 居民涉税信息
     */
    @BindView(R.id.residents_tax_info)
    TextView residentsTaxInfo;
//    /** 选择地址 */
//    @BindView(R.id.linearlayout_area) LinearLayout linearlayoutArea;
//    /** 地址 */
//    @BindView(R.id.address) TextView address;
    /**
     * 详细地址
     */
    @BindView(R.id.address_detail)
    TextView addressDetail;
    /**
     * 保存按钮
     */
    @BindView(R.id.btn_save)
    Button btnSave;

//    /** 设置按钮 */
//    @BindView(R.id.head_right) Button headRight;
//    /** 编辑按钮 */
//    @BindView(R.id.click_update) TextView clickUpdate;

    /**
     * 用户编号
     */
    private String userId;
    /**
     * 登录标识
     */
    private String token;
//    /** 地址弹出框 */
//    private CitySelectPopupWindow popupWindow;
    /**
     * 加载框
     */
    private HttpLoadingDialog httpLoadingDialog;
    /**
     * 职业集合
     */
    private ArrayList<OccupationResp> listOccupation;
    /**
     * 选择的职业
     */
    private OccupationResp selectOccupationResp;
    /**
     * 输入密码弹框
     */
    private FundBuyDialog fundBuyDialog;
    /**
     * 密码错误弹框
     */
    private CustomDialog errorDialog;

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_personinfo;
    }

    @Override
    public PersonInfoPresent newP() {
        return new PersonInfoPresent();
    }

    @Override
    public void initData(Bundle bundle) {
        //解决键盘弹出遮挡不滚动问题
        ChenJingET.assistActivity(context);
        //设置标题
        headTitle.setText("个人信息");
        ivSelector.setSelected(false);
        httpLoadingDialog = new HttpLoadingDialog(context);

        //获取用户缓存的userid 和 token
        userId = App.getSharedPref().getString(Constant.USERID, "");
        token = App.getSharedPref().getString(Constant.TOKEN, "");

        //请求职业集合接口
        httpLoadingDialog.visible();
        getP().getOccupation(token, userId);

        //请求个人信息接口
        getP().getUserInfo(token, userId);

    }

    @Override
    public void initEvents() {
//        headRight.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if ("完成".equals(headRight.getText().toString())) {
//                    //保存邮箱信息
//                    showToast("保存该信息");
//                    //然后设置为不可编辑状态
//                    email.setFocusable(false);
//                    email.setFocusableInTouchMode(false);
//                    headRight.setText("编辑");
//                    clickUpdate.setVisibility(View.GONE);
//                } else if ("编辑".equals(headRight.getText().toString())) {
//                    clickUpdate.setVisibility(View.VISIBLE);
//                    clickUpdate.setText("修改");
//
//                }
//
//            }
//        });

//        clickUpdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                headRight.setText("完成");
//                email.setSelection(email.getText().toString().trim().length());//将光标移至文字末尾
//                email.setFocusableInTouchMode(true);
//                email.setFocusable(true);
//                email.requestFocus();
//            }
//        });
        headBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        limitTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //如果未勾选永久有效 就可以选择时间
                if (!ivSelector.isSelected()) {

                    timeDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            //月份未满10 前面添加0
                            String strMonth;
                            int month = monthOfYear + 1;
                            if (month < 10) {
                                strMonth = "0" + month;
                            } else {
                                strMonth = "" + month;
                            }
                            //天数未满10 前面添加0
                            String strDay;
                            if (dayOfMonth < 10) {
                                strDay = "0" + dayOfMonth;
                            } else {
                                strDay = "" + dayOfMonth;
                            }
                            limitTime.setText(year + strMonth + strDay);
                        }
                    }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                    timeDialog.show();
                }

            }
        });

        ivSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //如果未勾选永久有效 就可以选择时间
                if (ivSelector.isSelected()) {
                    ivSelector.setSelected(false);
                    limitTime.setHint("请选择");
                } else {
                    ivSelector.setSelected(true);
                    limitTime.setHint("");
                    limitTime.setText("");
                }
            }
        });


        linearlayout_duty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                showToast("选择职业");
                //关闭当前输入框
                KeyBoardUtils.closeKeybord(PersonInfoActivity.this);
                final List<String> list = new ArrayList<>();
                for (OccupationResp occupation : listOccupation) {
                    list.add(occupation.getCaption());
                }
                PopShow popShow = new PopShow(context, linearlayout_duty);
                popShow.showText(list);
                popShow.setOnClickPop(new PopShow.OnClickPop() {
                    @Override
                    public void setRange(int position) {
                        duty.setText(list.get(position));
                        selectOccupationResp = listOccupation.get(position);
                    }
                });
            }
        });

//        linearlayoutArea.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //关闭当前输入框
//                KeyBoardUtils.closeKeybord(PersonInfoActivity.this);
//                //显示窗口
//                popupWindow.showAtLocation(linearlayoutArea, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
//            }
//        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //表单验证通过才弹出pop
                if (noNetWork()) {
                    showNetWorkError();
                    return;
                }
                if (!ivSelector.isSelected() && !isNotEmpty(getText(limitTime))) {
                    showToast("身份证有效期不能为空");
                    return;
                }
                if (!isNotEmpty(getText(duty))) {
                    showToast("职业不能为空");
                    return;
                }
//                if (!isNotEmpty(getText(address))) {
//                    showToast("联系地址不能为空");
//                    return;
//                }
                if (!isNotEmpty(getText(addressDetail))) {
                    showToast("详细地址不能为空");
                    return;
                }
                if (!isNotEmpty(getText(email))) {
                    showToast("邮箱不能为空");
                    return;
                }
                //身份证有限期
                final String id_enddate;
                if (ivSelector.isSelected()) {
                    id_enddate = "1";
                } else {
                    id_enddate = getText(limitTime);
                }
//                httpLoadingDialog.visible("保存中...");
                //弹出交易密码框
                fundBuyDialog = new FundBuyDialog
                        .Builder(context)
                        .setOnTextFinishListener(new FundBuyDialog.OnTextFinishListener() {
                            @Override
                            public void onFinish(String str) {
                            }
                        }).setPositiveButton("确定", new FundBuyDialog.OnPositiveButtonListener() {
                            @Override
                            public void onButtonClick(DialogInterface dialog, String str) {
                                //验证是否符合更换条件
                                dialog.dismiss();
                                httpLoadingDialog.visible("保存中...");
                                getP().changeMyInformation(token, userId, id_enddate, null, getText(addressDetail), getText(email), selectOccupationResp, str, currentItemTax);


                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create();

                fundBuyDialog.show();


            }
        });


    }

    /**
     * 请求用户信息成功
     *
     * @param personInfoResp
     */
    public void requestUserInfoSuccess(PersonInfoResp personInfoResp) {
        httpLoadingDialog.dismiss();
        if (personInfoResp != null) {
            //姓名
            name.setText(personInfoResp.getUserName());
            //性别
            sex.setText(personInfoResp.getSex());
            //身份证号
            identity.setText(personInfoResp.getCertNo());
            //身份证有效期 如果是0说明永久有效
            if ("1".equals(personInfoResp.getId_enddate())) {
                ivSelector.setSelected(true);
                limitTime.setHint("");
                limitTime.setText("");
            } else {
                ivSelector.setSelected(false);
                if (isNotEmpty(personInfoResp.getId_enddate())) {
                    limitTime.setText(personInfoResp.getId_enddate());
                } else {
                    limitTime.setHint("请选择");
                }
            }
            //国籍
            nationality.setText(personInfoResp.getFund_nationality());
            //职业
            if (personInfoResp.getOccupation() != null) {
                selectOccupationResp = personInfoResp.getOccupation();
                duty.setText(personInfoResp.getOccupation().getCaption());
            } else {
                duty.setHint("请选择");
            }
//            //地址
//            if (isNotEmpty(personInfoResp.getAddress())) {
//                address.setText(personInfoResp.getAddress());
//            } else {
//                address.setHint("请选择");
//            }
            //详细地址
            addressDetail.setText(personInfoResp.getDetaile_address());
            //基金账号
            accountid.setText(personInfoResp.getTa_acco());
            //邮箱
            email.setText(personInfoResp.getEmail());

            //显示居民税收类型
            if (null != personInfoResp.getCust_flag_info()) {

                isShowTax(personInfoResp.getCust_flag_info().getKeyvalue(), personInfoResp.getCust_flag_info().getCaption());
            }

            initPopWindow(personInfoResp.getTaxflag());

            residentsTaxInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Bundle bundle = new Bundle();
                    bundle.putString("caption", residentsTax.getText().toString());
                    bundle.putString("keyvalue", currentItemTax);
                    startActivityDelay(ResidentsTaxInfoActivity.class, bundle);
                }
            });

        }
    }

    /**
     * 是否显示居民涉税信息
     *
     * @param keyvalue key值
     * @param caption  内容
     */
    private void isShowTax(String keyvalue, String caption) {

        if (null == keyvalue||keyvalue.equals("null")) {
            keyvalue = "0";
        }
        if (null == caption || caption.equals("null")) {
            caption = "请选择";
        }

        residentsTax.setText(caption);
        currentItemTax = keyvalue + "";

        residentsTaxInfo.setVisibility(View.GONE);
        switch (keyvalue) {
            case "0":
                residentsTaxInfo.setVisibility(View.GONE);
                break;
            case "1":
                residentsTaxInfo.setVisibility(View.VISIBLE);
                break;
            case "2":
                residentsTaxInfo.setVisibility(View.VISIBLE);
                break;
        }


    }

    /**
     * 居民税收弹框
     */
    private SelectPopupWindow cyclePopupWindow;
    /**
     * 选择完的涉税信息
     */
    private String currentItemTax = "0";

    /**
     * 初始化弹出框
     *
     * @param list 居民税收类型集合
     */
    private void initPopWindow(List<OccupationResp> list) {

        if (null == list || list.size() == 0) {
            return;
        }

        ArrayList<ApplyBaseInfo> cycleList = new ArrayList<>();
        for (OccupationResp occupationResp : list) {
            cycleList.add(new ApplyBaseInfo(occupationResp.getKeyvalue(), occupationResp.getCaption()));
        }
        //初始化周期弹出框
        cyclePopupWindow = new SelectPopupWindow(this, cycleList);
        cyclePopupWindow.setCallBack(new SelectPopupWindow.CallBack() {
            @Override
            public void onSelectChange(int currentItem, String name) {
                isShowTax(currentItem + "", name);
            }
        });

        residentsTax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //设置layout在PopupWindow中显示的位置
                cyclePopupWindow.showAtLocation(residentsTax, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
            }
        });

    }


    /**
     * 请求用户信息失败
     */
    public void requestUserInfoFail() {
        httpLoadingDialog.dismiss();
    }

    /**
     * 获取职业集合成功
     *
     * @param data
     */
    public void getOccupationSuccess(ArrayList<OccupationResp> data) {
        httpLoadingDialog.dismiss();
        listOccupation = data;
    }

    /**
     * 获取职业集合失败
     */
    public void getOccupationFail() {
        httpLoadingDialog.dismiss();
    }

    /**
     * 保存我的信息成功
     */
    public void changeMyInformationSuccess() {
        httpLoadingDialog.dismiss();
        showToast("保存成功");
    }

    /**
     * 保存我的信息失败
     */
    public void changeMyInformationFail() {
        httpLoadingDialog.dismiss();
    }

    /**
     * 更换银行卡 密码错误
     *
     * @param message
     */
    public void passwordError(String message) {
        httpLoadingDialog.dismiss();
        if (errorDialog == null) {
            errorDialog = new CustomDialog.Builder(context)
                    .setMessage(message)
                    .setNegativeButton("忘记密码", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            errorDialog.dismiss();
                            startActivity(FindPwdTradeFirstActivity.class);
                        }
                    }).setPositiveButton("再试一次", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            errorDialog.dismiss();
                            fundBuyDialog.show();
                        }
                    }).create();
        }
        errorDialog.show();
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

    @Override
    protected void onDestroy() {
        if (errorDialog != null) {
            errorDialog.dismiss();
            errorDialog = null;
        }
        if (fundBuyDialog != null) {
            fundBuyDialog.dismiss();
            fundBuyDialog = null;
        }
        super.onDestroy();
    }
}
