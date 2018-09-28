package com.sjr.yiyuantools.activity;

import com.sjr.yiyuantools.base.BaseActivity;
import com.sjr.yiyuantools.base.BaseResponse;
import com.sjr.yiyuantools.contract.LoginContract;
import com.sjr.yiyuantools.entity.Login;
import com.sjr.yiyuantools.presenter.LoginPresenter;

import java.util.List;

import io.reactivex.ObservableTransformer;

public class LoginAct extends BaseActivity<LoginContract.View, LoginPresenter> implements LoginContract.View {

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public LoginPresenter createPresenter() {
        return null;
    }

    @Override
    public LoginContract.View createView() {
        return null;
    }

    @Override
    public void init() {

    }

    @Override
    public void result(BaseResponse<List<Login>> data) {

    }

    @Override
    public void logoutResult(BaseResponse<List<Login>> data) {

    }

    @Override
    public void setMsg(String msg) {

    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return null;
    }
}
