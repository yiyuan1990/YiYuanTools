package com.sjr.yiyuantools.contract;


import com.sjr.yiyuantools.base.BasePresenter;
import com.sjr.yiyuantools.base.BaseResponse;
import com.sjr.yiyuantools.base.BaseView;
import com.sjr.yiyuantools.entity.Login;

import java.util.HashMap;
import java.util.List;

import io.reactivex.ObservableTransformer;

/**
 * LoginContract  V 、P契约类
 */
public interface LoginContract {

    interface View extends BaseView {

        void result(BaseResponse<Login> data);

        void err(int err);

        <T> ObservableTransformer<T, T> bindLifecycle();

    }

    abstract class Presenter extends BasePresenter<View> {

        public abstract void login(HashMap<String, String> map, boolean isDialog, boolean cancelable);

    }
}
