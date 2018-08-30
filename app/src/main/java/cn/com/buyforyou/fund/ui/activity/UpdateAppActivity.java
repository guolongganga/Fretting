package cn.com.buyforyou.fund.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.com.buyforyou.fund.App;
import cn.com.buyforyou.fund.BuildConfig;
import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.model.index.UpdateVersionResp;
import cn.com.buyforyou.fund.ui.DownloadProgressButton;
import cn.com.buyforyou.fund.ui.download.DownloadUtil;
import cn.droidlover.xdroidmvp.widget.ToastUtils;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by limxing on 2018/5/9.
 */

public class UpdateAppActivity extends AppCompatActivity implements DownloadUtil.OnDownloadListener {
    @BindView(R.id.uodrade_tv_curVersion)
    TextView cvText;

    @BindView(R.id.uodrade_tv_newVersion)
    TextView nvText;

    @BindView(R.id.uodrade_tv_content)
    TextView desText;

    @BindView(R.id.upgrade_download)
    DownloadProgressButton progressButton;

    @BindView(R.id.upgrade_tv_install)
    TextView toInstallText;

    @BindView(R.id.upgrade_close)
    ImageView closeImage;


    private RxPermissions permisson;

    String url = "http://imtt.dd.qq.com/16891/920269AF35C71FC5D0EAF6905DC1BA00.apk";
    File file = new File(Environment.getExternalStorageDirectory(),"Download");
    private UpdateVersionResp update;
    private File f;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        permisson = new RxPermissions(this);
        setContentView(R.layout.activity_update_app);
        ButterKnife.bind(this);
        file.mkdir();
        update = (UpdateVersionResp)getIntent().getParcelableExtra("data");
        url = update.getDownloadAdd();
        desText.setText("更新内容："+update.getVersionDesc());

        if ("2".equals(update.getUpdradeCode())){
            closeImage.setVisibility(View.GONE);
        }else{
            closeImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
        try {
            cvText.setText("当前版本："+this.getPackageManager().getPackageInfo(this.getPackageName(),0).versionName);
        } catch (PackageManager.NameNotFoundException e) {

        }
        nvText.setText("最新版本："+update.getVersionNumber());

         f = new File(file,url.substring(url.lastIndexOf("/")+1));



        progressButton.setOnDownLoadClickListener(new DownloadProgressButton.OnDownLoadClickListener() {
            @Override
            public void clickDownload() {

            }

            @Override
            public void clickPause() {

            }

            @Override
            public void clickResume() {

            }

            @Override
            public void clickFinish() {
                if (f.exists()){
                    install(f.getAbsolutePath());
                }
            }
        });
        toInstallText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                permisson.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Boolean value) {

                            if (value){
                                if (f.exists()){
                                    install(f.getAbsolutePath());

                                }else {
                                    toDownload();

                                }
                            }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });



            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }


    private void toDownload() {
        toInstallText.setVisibility(View.GONE);
        DownloadUtil.getInstance().download(url, file.getAbsolutePath(), this);
    }

    @Override
    public void onDownloadSuccess(String path) {

        install(path);

    }

    @Override
    public void onDownloading(int progress) {
        progressButton.setProgress(progress);
    }

    @Override
    public void onDownloadFailed() {
        ToastUtils.show(this,"下载失败");
        Logger.i("onDownloadFailed");
        finish();
    }

    private void install(String path){
        File f = new File(path);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        Uri data;
        // 判断版本大于等于7.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            data = FileProvider.getUriForFile(this, "cn.com.buyforyou.fund.fileprovider", f);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            data = Uri.fromFile(f);
        }

        intent.setDataAndType(data,"application/vnd.android.package-archive");
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (closeImage.getVisibility() == View.VISIBLE){
            super.onBackPressed();
        }
    }
}
