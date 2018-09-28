package com.sjr.yiyuantools.model;

import android.content.Context;

import com.sjr.yiyuantools.api.Api;
import com.sjr.yiyuantools.base.BaseModel;
import com.sjr.yiyuantools.progress.ObserverResponseListener;

import java.util.HashMap;

import io.reactivex.ObservableTransformer;

public class QueryMapModel<T> extends BaseModel {
    public void query(Context context, HashMap<String, String> map, boolean isDialog, boolean cancelable,
                      ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
//        subscribe(context, Api.getApiService().login(map), observerListener);
        subscribe(context, Api.getApiService().query(map), observerListener, transformer, isDialog, cancelable);
    }


}
