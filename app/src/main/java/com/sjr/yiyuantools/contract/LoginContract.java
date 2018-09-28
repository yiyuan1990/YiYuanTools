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

        void result(BaseResponse<List<Login>> data);

        void logoutResult(BaseResponse<List<Login>> data);

        void setMsg(String msg);

        <T> ObservableTransformer<T, T> bindLifecycle();

    }

    abstract class Presenter extends BasePresenter<View> {

        //请求1
        public abstract void login(HashMap<String, String> map, boolean isDialog, boolean cancelable);

        //请求2
        public abstract void logout(HashMap<String, String> map, boolean isDialog, boolean cancelable);
    }
}
