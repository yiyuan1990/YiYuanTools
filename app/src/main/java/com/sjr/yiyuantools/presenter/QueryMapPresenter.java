package com.sjr.yiyuantools.presenter;

import android.content.Context;

import com.sjr.yiyuantools.base.BaseResponse;
import com.sjr.yiyuantools.contract.QueryMapPictureContract;
import com.sjr.yiyuantools.entity.QueryMapPicture;
import com.sjr.yiyuantools.model.QueryMapModel;
import com.sjr.yiyuantools.progress.ObserverResponseListener;
import com.sjr.yiyuantools.utils.ExceptionHandle;
import com.sjr.yiyuantools.utils.ToastUtil;

import java.util.HashMap;

public class QueryMapPresenter extends QueryMapPictureContract.Presenter {

    private QueryMapModel model;
    private Context context;

    public QueryMapPresenter(Context context) {
        this.model = new QueryMapModel();
        this.context = context;
    }

    @Override
    public void query(HashMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.query(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                if (getView() != null) {
                    getView().result((BaseResponse<QueryMapPicture>) o);
                    getView().setMsg("请求成功！");
                }
            }

            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                if (getView()!=null){
                    //处理异常
                    ToastUtil.showShortToast(ExceptionHandle.handleException(e).message);
                }
            }
        });
    }
}
