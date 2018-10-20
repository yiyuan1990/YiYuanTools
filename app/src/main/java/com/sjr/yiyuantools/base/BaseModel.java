package com.sjr.yiyuantools.base;

import android.content.Context;

import com.sjr.yiyuantools.progress.ObserverResponseListener;
import com.sjr.yiyuantools.progress.ProObserver;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class BaseModel<T> {
    /**
     * 封装线程管理和订阅的过程
     */
    public void subscribe(Context context, final Observable observable, ObserverResponseListener<T> listener,
                          ObservableTransformer<T, T> transformer, boolean isDialog, boolean cancelable) {
        final Observer<T> observer = new ProObserver(context, listener, isDialog, cancelable);
        observable.compose(transformer)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void subscribe(Context context, final Observable observable, ObserverResponseListener<T> listener,
                          ObservableTransformer<T, T> transformer) {
        subscribe(context, observable, listener, transformer, true, true);
    }
}
