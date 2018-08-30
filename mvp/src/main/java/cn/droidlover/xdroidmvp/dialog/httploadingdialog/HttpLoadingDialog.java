package cn.droidlover.xdroidmvp.dialog.httploadingdialog;

/**
 *自定义dialog
 * Created by ${Yis}
 * data: 2017/12/6
 */

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import cn.droidlover.xdroidmvp.R.id;
import cn.droidlover.xdroidmvp.R.layout;
import cn.droidlover.xdroidmvp.R.string;
import cn.droidlover.xdroidmvp.R.style;
import cn.droidlover.xdroidmvp.dialog.base.BaseDialog;

public class HttpLoadingDialog extends BaseDialog {
    private TextView tvMessage;
    public static final int DIALOG_HTTP_LOADING_UPLOAD = 1;
    public static final int DIALOG_HTTP_LOADING_WORKING = 2;

    public HttpLoadingDialog(Context context) {
        super(context, style.Dialog_Style);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, layout.dialog_http_loading);
    }

    public void initBoots() {
        this.setCanceledOnTouchOutside(false);
//        this.setCanceledOnKeyBack(false);
    }

    public void initViews() {
        this.tvMessage = (TextView) this.findViewById(id.tv_dialog_http_loading);
    }

    public void initData() {
        this.tvMessage.setText(this.dialogMessage);
    }

    public void initEvents() {
    }

    public void visible() {
        this.setDialogMessage(this.getRes().getString(string.dialog_http_loading_message_default));
        this.setViewMessage();
        this.show();
    }

    public void visible(String message) {
        this.setDialogMessage(message);
        this.setViewMessage();
        this.show();
    }

    public void visible(int type) {
        int res = string.dialog_http_loading_message_default;
        switch (type) {
            case 1:
                res = string.dialog_http_loading_message_upload;
                break;
            case 2:
                res = string.dialog_http_loading_message_working;
        }

        this.setDialogMessage(this.getRes().getString(res));
        this.setViewMessage();
        this.show();
    }

    private void setViewMessage() {
        if (this.tvMessage != null) {
            this.tvMessage.setText(this.dialogMessage);
        }

    }
}