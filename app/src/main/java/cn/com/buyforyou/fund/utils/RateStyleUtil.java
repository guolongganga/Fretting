package cn.com.buyforyou.fund.utils;

import android.content.Context;
import android.widget.TextView;

import cn.com.buyforyou.fund.R;

import java.math.BigDecimal;

/**
 * 作者：sunnyzeng on 2018/2/2 10:33
 * 描述：样式
 */

public class RateStyleUtil {

    /**
     * 利率的样式
     *
     * @param context
     * @param textView
     * @param rate
     */
    public static void rateStyle(Context context, TextView textView, BigDecimal rate) {
        if (rate != null) {
            int i = rate.compareTo(BigDecimal.ZERO);
            String strRate = BigDecimalUtil.bigdecimalToString(rate);
            StringBuffer stringBuffer = new StringBuffer(strRate);
            if (i == -1) {
                //rate小于0 绿色
                stringBuffer.append("%");
                textView.setTextColor(context.getResources().getColor(R.color.x_green));
            } else {
                //rate大于或等于0
                stringBuffer.insert(0, "+");
                stringBuffer.append("%");
                textView.setTextColor(context.getResources().getColor(R.color.color_main));
            }
            textView.setText(stringBuffer.toString());
        }else{
            textView.setText("");
        }
    }

    /**
     * 利率的样式 不需要加%
     *
     * @param context
     * @param textView
     * @param rate
     */
    public static void rateStyleNoPer(Context context, TextView textView, BigDecimal rate) {
        if (rate != null) {
            int i = rate.compareTo(BigDecimal.ZERO);
            String strRate = BigDecimalUtil.bigdecimalToString(rate);
            StringBuffer stringBuffer = new StringBuffer(strRate);
            if (i == -1) {
                //rate小于0 绿色
                textView.setTextColor(context.getResources().getColor(R.color.x_green));
            } else {
                //rate大于或等于0
                stringBuffer.insert(0, "+");
                textView.setTextColor(context.getResources().getColor(R.color.color_main));
            }
            textView.setText(stringBuffer.toString());
        }else{
            textView.setText("");
        }
    }

    /**
     * 金额的样式
     *
     * @param context
     * @param textView
     * @param amount
     */
    public static void amountStyle(Context context, TextView textView, BigDecimal amount) {
        if (amount != null) {
            int i = amount.compareTo(BigDecimal.ZERO);
            String strAmount = BigDecimalUtil.bigdecimalToString(amount);
            StringBuffer stringBuffer = new StringBuffer(strAmount);
            if (i == -1) {
                //rate小于0 绿色
                textView.setTextColor(context.getResources().getColor(R.color.x_green));
            } else {
                //rate大于或等于0
                stringBuffer.insert(0, "+");
                textView.setTextColor(context.getResources().getColor(R.color.color_main));
            }
            textView.setText(stringBuffer.toString());
        }else{
            textView.setText("");
        }
    }

}
