package cn.com.buyforyou.fund.utils;

import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import cn.com.buyforyou.fund.R;

/**
 * 作者：sunnyzeng on 2017/12/5
 * 描述：ImageLoader显示选项
 */

public class ImageOptions {

    private static DisplayImageOptions defaultOptions;

    /**
     * 默认图片加载选项
     *
     * @return
     */
    public static DisplayImageOptions getDefaultOptions() {
        if (defaultOptions == null) {
            defaultOptions = getDisplayOptions(R.mipmap.ic_launcher);
        }
        return defaultOptions;
    }


    /**
     * 获取图片加载选项
     *
     * @param res 加载默认图片
     * @return DisplayImageOptions
     * @see DisplayImageOptions
     */
    private static DisplayImageOptions getDisplayOptions(@DrawableRes Integer res) {
        DisplayImageOptions.Builder builder = new DisplayImageOptions.Builder();
        // 设置默认图片
        if (res != null) {
            builder.showImageForEmptyUri(res)       // url为空显示该图片
                    .showImageOnFail(res)          // 加载失败显示图片
                    .showImageOnLoading(res);        // 正在加载中显示图片
        }
        // 设置其它属性
        builder.resetViewBeforeLoading(false)           // 设置图片在下载前是否重置，复位
                .imageScaleType(ImageScaleType.EXACTLY) // 设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.ARGB_8888)  // 设置图片的解码类型
                .cacheInMemory(true)
                .cacheOnDisk(true);
        return builder.build();
    }

    /**
     * 根据阿里云图片处理服务器生成正确的图片进行裁剪
     *
     * @param url
     * @param w
     * @param h
     * @return
     */
    public static String buildUrl(String url, int w, int h) {
        return url + "@" + w + "w_" + h + "h_" + "1e_1c_80q.png";
    }

    /**
     * 根据阿里云图片处理显示原图指定宽高
     *
     * @param url
     * @return
     */
    public static String buildUrl(String url, int length) {
        return url + "@" + length + "w_" + length + "h_" + "80q.png";
    }
}
