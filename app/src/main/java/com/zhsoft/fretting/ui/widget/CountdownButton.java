package com.zhsoft.fretting.ui.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

/**
 * <p>Description: 倒计时1分按钮 </p>
 * <p>Company: 投资啦</p>
 * <p>Create Time:2016/11/5 10:38</p>
 *
 * @author MiaoWenHai
 */
public class CountdownButton extends Button {
    /** 开始倒计时120秒从新发送短信 */
    private final int COUNT_DOWN_START = 1;
    private int countdown = 119;
    private boolean isCancel;
    private Timer timer;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case COUNT_DOWN_START:
                    onCountDown();
                    break;
            }
        }
    };

    private void onCountDown() {
        if (!isCancel) {
            this.setText(countdown + "秒后重新发送");
            countdown--;
            if (countdown == 0) {
                isCancel = true;
            }
        } else {
            countdown = 119;
            //恢复按钮状态
            this.setEnabled(true);
            this.setText("重新获取验证码");
            //取消任务
            if (timer != null) {
                timer.cancel();
            }
        }
    }

    public CountdownButton(Context context) {
        super(context);
    }

    public CountdownButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CountdownButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void start() {
        isCancel = false;

        //将重新发送按钮置为不可用
        this.setEnabled(false);

        //定时任务
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Message message = Message.obtain();
                message.what = COUNT_DOWN_START;
                handler.sendMessage(message);
            }
        };
        timer.schedule(task, 0, 1000);
    }

    public void cancel() {
        isCancel = true;
    }
}
