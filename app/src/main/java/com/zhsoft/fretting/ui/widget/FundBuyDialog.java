package com.zhsoft.fretting.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.com.buyforyou.fund.R;


/**
 * @author ZengSuWa
 * @Description：
 * @Company：中融百汇
 * @Created time：2016/7/14 13:37
 */
public class FundBuyDialog extends Dialog {

    public FundBuyDialog(Context context) {
        super(context);
    }

    public FundBuyDialog(Context context, int theme) {
        super(context, theme);
    }

    public interface OnTextFinishListener {
        void onFinish(String str);
    }

    public interface OnPositiveButtonListener {
        void onButtonClick(DialogInterface dialog, String str);
    }

    private static PayPwdEditText ppePwd;

    public static class Builder {
        private Context context;
        private String fundName;
        private String fundAmount;
        private String hintText;
        private String positiveButtonText;
        private String negativeButtonText;
        private FundBuyDialog.OnTextFinishListener onTextFinishListener;
        private OnPositiveButtonListener positiveButtonClickListener;
        private OnClickListener negativeButtonClickListener;

        public Builder(Context context) {
            this.context = context;
        }

        /**
         * 设置基金名称
         *
         * @param fundName
         * @return
         */
        public Builder setFundName(String fundName) {
            this.fundName = fundName;
            return this;
        }

        /**
         * 设置基金金额
         *
         * @param fundAmount
         * @return
         */
        public Builder setFundAmount(String fundAmount) {
            this.fundAmount = fundAmount;
            return this;
        }

        /**
         * 设置密码输入完成监听
         *
         * @param onTextFinishListener
         * @return
         */
        public Builder setOnTextFinishListener(FundBuyDialog.OnTextFinishListener onTextFinishListener) {
            this.onTextFinishListener = onTextFinishListener;
            return this;
        }

        /**
         * 设置提示
         *
         * @param hintText
         * @return
         */
        public Builder setHintText(String hintText) {
            this.hintText = hintText;
            return this;
        }

        /**
         * Set the positive button resource and it's listener
         *
         * @param positiveButtonText
         * @return
         */
        public Builder setPositiveButton(int positiveButtonText,
                                         OnPositiveButtonListener listener) {
            this.positiveButtonText = (String) context
                    .getText(positiveButtonText);
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setPositiveButton(String positiveButtonText,
                                         OnPositiveButtonListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            return this;
        }

        /**
         * Set the negative button resource and it's listener
         *
         * @param negativeButtonText
         * @return
         */
        public Builder setNegativeButton(int negativeButtonText,
                                         OnClickListener listener) {
            this.negativeButtonText = (String) context
                    .getText(negativeButtonText);
            this.negativeButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(String negativeButtonText,
                                         OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }

        public FundBuyDialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            final FundBuyDialog dialog = new FundBuyDialog(context, R.style.Dialog);
            View layout = inflater.inflate(R.layout.dialog_fund_buy_layout, null);
            dialog.addContentView(layout, new LayoutParams(
                    LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));

            //购买的基金名称
            if (TextUtils.isEmpty(fundName)) {
                layout.findViewById(R.id.tv_fund_name).setVisibility(View.GONE);
            } else {
                layout.findViewById(R.id.tv_fund_name).setVisibility(View.VISIBLE);
                ((TextView) layout.findViewById(R.id.tv_fund_name)).setText(fundName);
            }

            //购买的基金金额
            if (TextUtils.isEmpty(fundAmount)) {
                layout.findViewById(R.id.tv_fund_amount).setVisibility(View.GONE);
            } else {
                layout.findViewById(R.id.tv_fund_amount).setVisibility(View.VISIBLE);
                ((TextView) layout.findViewById(R.id.tv_fund_amount)).setText(fundAmount);
            }

            //提示
            if (TextUtils.isEmpty(hintText)) {
                layout.findViewById(R.id.tv_hint).setVisibility(View.GONE);
            } else {
                layout.findViewById(R.id.tv_hint).setVisibility(View.VISIBLE);
                ((TextView) layout.findViewById(R.id.tv_hint)).setText(hintText);
            }

            layout.findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            ppePwd = (PayPwdEditText) layout.findViewById(R.id.ppe_pwd);
            //初始化交易密码输入框的样式
            ppePwd.initStyle(R.drawable.edit_num_bg, 6, 1f, R.color.color_EFEFEF, R.color.color_000000, 20);
            ppePwd.setOnTextFinishListener(new PayPwdEditText.OnTextFinishListener() {
                @Override
                public void onFinish(String str) {//密码输入完后的回调
                    onTextFinishListener.onFinish(str);
                }
            });

            if (positiveButtonText != null) {
                ((Button) layout.findViewById(R.id.positive_button))
                        .setText(positiveButtonText);

                if (positiveButtonClickListener != null) {
                    ((Button) layout.findViewById(R.id.positive_button))
                            .setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    positiveButtonClickListener.onButtonClick(dialog, ppePwd.getPwdText().toString());
                                }
                            });
                }
            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.positive_button).setVisibility(
                        View.GONE);
            }
            // set the cancel button
            if (negativeButtonText != null) {
                ((Button) layout.findViewById(R.id.negative_button))
                        .setText(negativeButtonText);
                if (negativeButtonClickListener != null) {
                    ((Button) layout.findViewById(R.id.negative_button))
                            .setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    negativeButtonClickListener.onClick(dialog,
                                            DialogInterface.BUTTON_NEGATIVE);
                                }
                            });
                }
            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.negative_button).setVisibility(
                        View.GONE);
                layout.findViewById(R.id.iv_line).setVisibility(View.GONE);
            }
            if (positiveButtonText == null && negativeButtonText == null) {
                layout.findViewById(R.id.view_btn_line).setVisibility(View.GONE);
                layout.findViewById(R.id.ll_button).setVisibility(View.GONE);
            } else {
                layout.findViewById(R.id.view_btn_line).setVisibility(View.VISIBLE);
                layout.findViewById(R.id.ll_button).setVisibility(View.VISIBLE);
            }
            dialog.setContentView(layout);
            WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
            lp.dimAmount = 0.7f;
            return dialog;
        }

    }

    /**
     * dialog重新显示时，清空上次输入的密码
     */
    @Override
    public void show() {
        super.show();
        ppePwd.clearText();
    }
}
