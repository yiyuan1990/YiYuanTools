package com.sjr.yiyuantools.contract;


import com.sjr.yiyuantools.base.BasePresenter;
import com.sjr.yiyuantools.base.BaseResponse;
import com.sjr.yiyuantools.base.BaseView;
import com.sjr.yiyuantools.entity.Login;

import java.util.HashMap;

import io.reactivex.ObservableTransformer;

public interface MainContract {
    interface View extends BaseView {

        void Result(BaseResponse<Login> data);

        void Error(String msg);

        <T> ObservableTransformer<T, T> bindLifecycle();
    }

    abstract class Presenter extends BasePresenter<View> {

        public abstract void GetData(HashMap<String, String> map, boolean isDialog, boolean cancelable);


    }
}
