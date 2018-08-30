package cn.droidlover.xdroidmvp.net.converter;

import com.google.gson.TypeAdapter;
import java.io.IOException;

import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.utils.EncryptDecrypt;
import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * 处理返回数据
 * Created by ${Yis}
 * data: 2017/12/7
 */

public class DecodeResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    public static final String TAG = "http";
    private final TypeAdapter<T> adapter;

    DecodeResponseBodyConverter(TypeAdapter<T> adapter) {
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        //.string()调用2次就会引来错误,在第一次调用之后就关闭了资源 所以只能调用一次
        String resp = value.string();
        //解密返回数据
     //  String decResp = EncryptDecrypt.decrptByAES(resp);

        XLog.d(TAG, "解密数据 : " + resp);

        return adapter.fromJson(resp);
    }
}

