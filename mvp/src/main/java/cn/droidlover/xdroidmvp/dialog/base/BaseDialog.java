package cn.droidlover.xdroidmvp.dialog.base;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.KeyEvent;

/**
 * Created by ${Yis}
 * data: 2017/12/6
 */

public abstract class BaseDialog extends Dialog {
    private Context context;
    public DialogInterface dialogInterface;
    public String dialogTitle;
    public String dialogMessage;
    public boolean canceledOnKeyBack = false;

    public BaseDialog(Context context) {
        super(context);
        this.context = context;
    }

    public BaseDialog(Context context, int theme) {
        super(context, theme);
        this.context = context;
    }

    protected void onCreate(Bundle savedInstanceState, int layout) {
        super.onCreate(savedInstanceState);
        this.setContentView(layout);
        this.initBoots();
        this.initViews();
        this.initData();
        this.initEvents();
    }

    public abstract void initBoots();

    public abstract void initViews();

    public abstract void initData();

    public abstract void initEvents();

    public void setDialogTitle(String dialogTitle) {
        this.dialogTitle = dialogTitle;
    }

    public void setDialogMessage(String dialogMessage) {
        this.dialogMessage = dialogMessage;
    }

    public void setDialogInterface(DialogInterface dialogInterface) {
        this.dialogInterface = dialogInterface;
    }

    public void setCanceledOnKeyBack() {
        this.canceledOnKeyBack = true;
    }

    public Resources getRes() {
        return this.context.getResources();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        return keyCode == 4 ? this.canceledOnKeyBack : super.onKeyDown(keyCode, event);
        if (keyCode == 4 && !this.canceledOnKeyBack) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}