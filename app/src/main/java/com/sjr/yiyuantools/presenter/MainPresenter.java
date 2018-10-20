package com.sjr.yiyuantools.presenter;

import android.content.Context;

import com.sjr.yiyuantools.contract.MainContract;
import com.sjr.yiyuantools.model.MainModle;

import java.util.HashMap;

public class MainPresenter extends MainContract.Presenter {
    private Context context;
    private MainModle modle;

    public MainPresenter(Context context) {
        this.modle = new MainModle();
        this.context = context;
    }

    @Override
    public void GetData(HashMap<String, String> map, boolean isDialog, boolean cancelable) {


    }
}
