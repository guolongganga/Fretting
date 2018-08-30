//package com.zhsoft.fretting.ui.widget;
//
//import android.app.Activity;
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.drawable.ColorDrawable;
//import android.text.Editable;
//import android.text.TextUtils;
//import android.text.TextWatcher;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.WindowManager;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.PopupWindow;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//
//import cn.com.buyforyou.fund.R;
//import cn.com.buyforyou.fund.model.user.ImageResp;
//import cn.com.buyforyou.fund.present.user.ISuccessDataLoadView;
//import cn.com.buyforyou.fund.present.user.ImgCodePersenter;
//import cn.com.buyforyou.fund.utils.Base64ImageUtil;
//
//import cn.droidlover.xdroidmvp.mvp.XActivity;
//
///**
// * <p>Description: 图形验证码校验</p>
// */
//public class ImgCodePopupWindow extends PopupWindow {
//    private EditText code;
//    private ImageView imageCode;
//    private TextView error;
//    private ProgressBar progressBar;
//    private XActivity mContext;
//
//    private String phone;
//    /** 图片验证码id */
//    private String image_code_id;
//    private CallBack callBack;
//    private final ImgCodePersenter imgCodePersenter;
//    private final ISuccessDataLoadView dataLoadView = new ISuccessDataLoadView<ImageResp>() {
//        @Override
//        public void callData(ImageResp data) {
//            //获取 图片Base64 字符串
//            String strimage = data.getBase64Image();
//            image_code_id = data.getImageCodeId();
//            if (!TextUtils.isEmpty(strimage)) {
//                //将Base64图片串转换成Bitmap
//                Bitmap bitmap = Base64ImageUtil.base64ToBitmap(strimage);
//                imageCode.setImageBitmap(bitmap);
//            }
//        }
//
//        @Override
//        public void onSuccessful() {
//            ImgCodePopupWindow.this.dismiss();
//            if (callBack != null) {
//                callBack.checkSuccess();
//            }
//        }
//
//
//        @Override
//        public void showLoading() {
//            progressBar.setVisibility(View.VISIBLE);
//        }
//
//        @Override
//        public void hideLoading() {
//            progressBar.setVisibility(View.GONE);
//        }
//
//        @Override
//        public void showRetry() {
//
//        }
//
//        @Override
//        public void hideRetry() {
//            error.setText("");
//        }
//
//    };
//
//    public ImgCodePopupWindow(XActivity context) {
//        super(context);
//        this.mContext = context;
//        this.imgCodePersenter = new ImgCodePersenter(context);
//
//
//        LayoutInflater inflater = (LayoutInflater) context
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View inflate = inflater.inflate(R.layout.layout_imgcode, null);
//        code = (EditText) inflate.findViewById(R.id.code);
//        code.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                error.setText("");
//            }
//        });
//        error = (TextView) inflate.findViewById(R.id.error);
//        imageCode = (ImageView) inflate.findViewById(R.id.image_code);
//        imageCode.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ImgCodePopupWindow.this.imgCodePersenter.getImgCode();
//            }
//        });
//        progressBar = (ProgressBar) inflate.findViewById(R.id.progress_bar);
//        inflate.findViewById(R.id.commit_button)
//                .setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        //验证
//                        String codeNum = code.getText().toString();
//                        if (TextUtils.isEmpty(codeNum)) {
//                            error.setText("验证码不能为空");
//                        } else {
//                            ImgCodePopupWindow.this.imgCodePersenter.checkImgCode(phone, codeNum, dataLoadView);
//                        }
//
//                    }
//                });
//
//
//        //设置SelectPicPopupWindow的View
//        this.setContentView(inflate);
//        //设置SelectPicPopupWindow弹出窗体的宽
//        this.setWidth(ViewGroup.LayoutParams.FILL_PARENT);
//        //设置SelectPicPopupWindow弹出窗体的高
//        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
//        //设置SelectPicPopupWindow弹出窗体可点击
//        this.setFocusable(true);
//        //设置SelectPicPopupWindow弹出窗体动画效果
////        this.setAnimationStyle(R.style.AnimBottom);
//        //实例化一个ColorDrawable颜色为半透明
//        ColorDrawable dw = new ColorDrawable(0x00000000);
//        //设置SelectPicPopupWindow弹出窗体的背景
//        this.setBackgroundDrawable(dw);
//        backgroundAlpha(mContext, 0.3f);//0.0-1.0
//        //设置跟随软键盘上移
//        //控制弹出窗口如何与输入法进行操作
//        this.setInputMethodMode(INPUT_METHOD_NEEDED);
//        //设置软输入区域的操作模式
//        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//
//        this.setOnDismissListener(new OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                backgroundAlpha(mContext, 1f);
//            }
//        });
//
//    }
//
//
//    public void check(String phone, CallBack callBack) {
//        this.phone = phone;
//        this.callBack = callBack;
//        //点击图片获取验证码
//        imageCode.performClick();
//    }
//
//    /**
//     * 设置添加屏幕的背景透明度
//     *
//     * @param bgAlpha
//     */
//    public void backgroundAlpha(Activity context, float bgAlpha) {
//        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
//        lp.alpha = bgAlpha;
//        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//        context.getWindow().setAttributes(lp);
//    }
//
//    public interface CallBack {
//        void checkSuccess();
//    }
//}
