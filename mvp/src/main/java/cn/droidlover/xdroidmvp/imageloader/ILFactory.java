package cn.droidlover.xdroidmvp.imageloader;

/**
 * Created by wanglei on 2016/11/28.
 */
public class ILFactory {
    /**
     * double check   双重检查锁单例模式
     */

    private static ILoader loader;

    public static ILoader getLoader() {
        if (loader == null) {
            synchronized (ILFactory.class) {
                if (loader == null) {
                    loader = new GlideLoader();
                }
            }
        }
        return loader;
    }


}
