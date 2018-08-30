package cn.com.buyforyou.fund.utils;

import android.os.Looper;
import android.os.Message;

/**
 * 作者：sunnyzeng on 2018/1/11 10:56
 * 描述：
 */

public class OptionSearch implements CommonHandler.MessageHandler {
    private String currentWord;
    private IFinishListener mListener;

    private MyRunnable myRunnable = new MyRunnable();

    private CommonHandler mHandler;

    /**
     * 点击按钮响应间隔时间－毫秒
     */
    private int INTERVAL_TIME = 500;

    public OptionSearch(Looper looper) {
        mHandler = new CommonHandler(looper, this);
    }

    /**
     * 这一步就是实时搜索优化的关键代码了，当EditText中的文字发生改变时，我们先会将handle中的Callback移除掉。然后使用Handle发一个延时的消息。最后通过回调getKeyword，让Activity开始搜索
     */
    public void optionSearch(String keyword) {
        this.currentWord = keyword;
        if (myRunnable != null) {
            mHandler.removeCallbacks(myRunnable);
        }
        mHandler.postDelayed(myRunnable, INTERVAL_TIME);
    }

    public void setListener(IFinishListener listener) {
        this.mListener = listener;
    }

    @Override
    public void handleMessage(Message msg) {
        if(mListener!=null){
            mListener.getKeyword(currentWord);
        }
    }


    public interface IFinishListener {
        void getKeyword(String keyword);
    }

    private class MyRunnable implements Runnable {

        @Override
        public void run() {
            mHandler.sendEmptyMessage(1);
        }
    }


}
