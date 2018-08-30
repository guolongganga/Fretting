package cn.droidlover.xdroidmvp.net.converter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.utils.EncryptDecrypt;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Converter;

/**
 * 处理请求数据
 * Created by ${Yis}
 * data: 2017/12/7
 */

public class DecodeRequestBodyConverter<T> implements Converter<T, RequestBody> {

    public static final String TAG = "http";

    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    private final Gson gson;
    private final TypeAdapter<T> adapter;

    DecodeRequestBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public RequestBody convert(T value) throws IOException {

        Buffer buffer = new Buffer();
        Writer writer = new OutputStreamWriter(buffer.outputStream(), UTF_8);
        JsonWriter jsonWriter = gson.newJsonWriter(writer);
        adapter.write(jsonWriter, value);
        jsonWriter.flush();
        //获得需要加密的字符串
        String strData = gson.toJson(value);
        XLog.d(TAG, "加密前 : " + strData);
//        //加密请求数据
      //  String encrypt = EncryptDecrypt.encryptByAES(strData);
        Map<String, String> map = new HashMap<>();
        map.put("reqData", strData);
        return RequestBody.create(MEDIA_TYPE, gson.toJson(map));
    }

}


