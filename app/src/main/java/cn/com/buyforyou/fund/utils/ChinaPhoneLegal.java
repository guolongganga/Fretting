package cn.com.buyforyou.fund.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by limxing on 2018/4/20.
 */

public class ChinaPhoneLegal {
    //正则表达式  手机号
    public static boolean isChinaPhoneLegal(String str) {

        String regExp = "^((13[0-9])|(15[^4])|(166)|(17[0-8])|(18[0-9])|(19[8-9])|(147,145))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }
}
