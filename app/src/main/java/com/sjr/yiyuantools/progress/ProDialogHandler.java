package com.sjr.yiyuantools.progress;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;

import com.cazaea.sweetalert.SweetAlertDialog;

/**
 * Dialog的进度控制
 */

public class ProDialogHandler extends Handler {
    public static final int SHOW_PROGRESS_DIALOG = 1;
    public static final int DISMISS_PROGRESS_DIALOG = 2;

    private SweetAlertDialog sad;
    private Context context;
    private boolean cancelable;
    private ProCancelListener mProCancelListener;

    public ProDialogHandler(Context context, ProCancelListener mProCancelListener, boolean cancelable) {
        super();
        this.context = context;
        this.mProCancelListener = mProCancelListener;
        this.cancelable = cancelable;
    }

    private void initProgressDialog() {
        if (sad == null) {
            sad = new SweetAlertDialog(context);
            sad.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
            sad.setTitleText("正在加载...");
            sad.setCancelable(cancelable);
            if (cancelable) {
                sad.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        mProCancelListener.onCancelProgress();
                    }
                });
            }

            if (!sad.isShowing()) {
                sad.show();
            }
        }
    }

    private void dismissProgressDialog() {
        if (sad != null) {
            sad.dismiss();
            sad = null;
        }
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case SHOW_PROGRESS_DIALOG:
                initProgressDialog();
                break;
            case DISMISS_PROGRESS_DIALOG:
                dismissProgressDialog();
                break;
        }
    }
}
