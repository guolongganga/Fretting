package cn.com.buyforyou.fund.ui.activity.boot;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import cn.com.buyforyou.fund.App;
import cn.com.buyforyou.fund.ui.activity.MainActivity;
import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.constant.Constant;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者：sunnyzeng on 2017/12/5
 * 描述：启动页
 */

public class StartActivity extends XActivity {

    @BindView(R.id.iv_jump)
    TextView ivJump;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    public boolean canJump = false;

    @BindView(R.id.iv_start)
    ImageView ivStart;//启动图
    private ValueAnimator animator;//倒计时


    @Override
    public int getLayoutId() {
        return R.layout.activity_start;
    }

    @Override
    public Object newP() {
        return null;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        //解决Android 8.0 上每次回到桌面都会启动 启动界面
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) > 0) {
            /**为了防止重复启动多个闪屏页面**/
            finish();
            return;
        }
        setTranslucentStatus(false);

        animator = ValueAnimator.ofInt(3, 0);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                ivJump.setText("跳过" + valueAnimator.getAnimatedValue().toString());
            }
        });
        animator.setDuration(3000);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                boolean firstUse = App.getSharedPref().getBoolean(Constant.FIRST_USE, true);
                if (firstUse) {
                    /*
                      如果第一次是true的话 就进入到加载引导页  如果不是第一次的话 就进入到启动页界面
                     */
                    //加载引导页面
                    WelcomeActivity.launch(context);
                } else {
                    //启动主页
                    startActivity(MainActivity.class);
                }
                finish();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animator.start();


//            Observable.timer(3, TimeUnit.SECONDS)
//                    .subscribe(new Observer<Long>() {
//
//                @Override
//                public void onSubscribe(Disposable d) {
//
//                }
//
//                @Override
//                public void onNext(Long value) {
////                    Logger.global.info("跳过"+value.intValue());
////                    ivJump.setText("跳过"+value.intValue());
//                }
//
//                @Override
//                public void onError(Throwable e) {
//
//                }
//
//                @Override
//                public void onComplete() {
//                    startActivity(MainActivity.class);
//                    finish();
//                }
//            });

    }

    @Override
    public void initEvents() {
        ivJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (animator != null) {
                    animator.cancel();
                    animator = null;
                }
                ivJump.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
//                finish();
            }
        });
    }
}
