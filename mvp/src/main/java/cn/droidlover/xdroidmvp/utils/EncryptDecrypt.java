package cn.droidlover.xdroidmvp.utils;

import android.util.Base64;

/**
 * 作者：sunnyzeng on 2017/12/6 10:46
 * 描述：加密解密工具
 */

public class EncryptDecrypt {

    public static final String AESKEY = "0e441613d8a611e784ef6c92bf314e43";


    /**
     * 进行aes加密
     * 先加密，再编码
     *
     * @param data 加密数据
     * @return 加密结果
     */
    public static String encryptByAES(String data) {

        byte[] encryptByte = AESUtils.encrypt(AESKEY, data);
        String afterencrypt = Base64.encodeToString(encryptByte, Base64.DEFAULT);
        return afterencrypt;

    }

    /**
     * 进行aes解密
     * 先解码，再解密
     *
     * @param data
     * @return
     */
    public static String decrptByAES(String data) {
        //AES 解密
        String afterdecrypt = AESUtils.decrypt(AESKEY, Base64.decode(data, Base64.DEFAULT));
        return afterdecrypt;
    }
}
