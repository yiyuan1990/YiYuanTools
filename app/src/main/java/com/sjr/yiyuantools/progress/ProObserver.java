package com.sjr.yiyuantools.progress;

import android.content.Context;
import android.util.Log;

import com.sjr.yiyuantools.utils.ExceptionHandle;
import com.sjr.yiyuantools.utils.ToastUtil;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * 观察者
 */
public class ProObserver<T> implements Observer<T>, ProCancelListener {
    private static final String TAG = "ProObserver--- ";
    private ObserverResponseListener listener;
    private ProDialogHandler mProDialogHandler;
    private Context context;
    private Disposable d;

    public ProObserver(Context context, ObserverResponseListener listener, boolean isDialog, boolean cancelable) {
        this.listener = listener;
        this.context = context;
        if(isDialog){
            mProDialogHandler = new ProDialogHandler(context, this, cancelable);
        }
    }

    private void showProgressDialog() {
        if (mProDialogHandler != null) {
            mProDialogHandler.obtainMessage(ProDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }

    private void dismissProgressDialog() {
        if (mProDialogHandler != null) {
            mProDialogHandler.obtainMessage(ProDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            mProDialogHandler = null;
        }
    }

    @Override
    public void onSubscribe(Disposable d) {
        this.d = d;
        Log.e(TAG, "onSubscribe: ");
        showProgressDialog();
    }

    @Override
    public void onNext(T t) {
        listener.onNext(t);//可定制接口，通过code回调处理不同的业务
    }

    @Override
    public void onError(Throwable e) {
        dismissProgressDialog();
        Log.e(TAG, "onError: ", e);
        //自定义异常处理
        if(e instanceof ExceptionHandle.ResponeThrowable){
            listener.onError((ExceptionHandle.ResponeThrowable)e);
        } else {
            listener.onError(new ExceptionHandle.ResponeThrowable(e, ExceptionHandle.ERROR.UNKNOWN));
        }

        if (e instanceof UnknownHostException) {
            ToastUtil.showLongToast("网络连接异常，请检查网络状态！");
        } else if (e instanceof SocketTimeoutException) {
            ToastUtil.showLongToast("请求超时");
        } else if (e instanceof ConnectException) {
            ToastUtil.showLongToast("连接失败");
        } else if (e instanceof HttpException) {
            ToastUtil.showLongToast("请求超时");
        }else {
            ToastUtil.showLongToast("请求失败");
        }
    }

    @Override
    public void onComplete() {
        dismissProgressDialog();
        Log.e(TAG, "onComplete: ");
    }

    @Override
    public void onCancelProgress() {
        Log.e(TAG, "requestCancel: ");
        //如果处于订阅状态，则取消订阅
        if (!d.isDisposed()) {
            d.dispose();
        }
    }
}
