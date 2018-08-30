package cn.com.buyforyou.fund.utils;

import java.math.BigDecimal;

/**
 * 作者：sunnyzeng on 2017/12/29 11:26
 * 描述：
 */

public class BigDecimalUtil {

    public static String bigdecimalToString(BigDecimal bigDecimal) {
        if (bigDecimal != null) {
            return bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        } else {
            return "--.--";
        }
    }

}
