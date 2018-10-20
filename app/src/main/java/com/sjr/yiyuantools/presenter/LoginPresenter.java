package com.sjr.yiyuantools.presenter;

import android.content.Context;

import com.sjr.yiyuantools.base.BaseResponse;
import com.sjr.yiyuantools.contract.LoginContract;
import com.sjr.yiyuantools.entity.Login;
import com.sjr.yiyuantools.model.LoginModel;
import com.sjr.yiyuantools.progress.ObserverResponseListener;
import com.sjr.yiyuantools.utils.ExceptionHandle;
import com.sjr.yiyuantools.utils.ToastUtil;

import java.util.HashMap;
import java.util.List;

import retrofit2.http.GET;

/**
 * 登录 退出
 */
public class LoginPresenter extends LoginContract.Presenter {

    private LoginModel model;
    private Context context;

    public LoginPresenter(Context context) {
        this.model = new LoginModel();
        this.context = context;
    }

    @Override
    public void login(HashMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.login(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {

                    getView().result((BaseResponse<Login>) o);
                }
            }

            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                if (getView() != null) {
                    //// TODO: 2017/12/28 自定义处理异常
                    ToastUtil.showShortToast(ExceptionHandle.handleException(e).message);
                    getView().err(999);
                }
            }
        });
    }

}
