package com.sjr.yiyuantools.contract;

import com.sjr.yiyuantools.base.BasePresenter;
import com.sjr.yiyuantools.base.BaseResponse;
import com.sjr.yiyuantools.base.BaseView;
import com.sjr.yiyuantools.entity.QueryMapPicture;

import java.util.HashMap;

import io.reactivex.ObservableTransformer;

/**
 * 查询定位地图块图片
 */
public interface QueryMapPictureContract {
    interface View extends BaseView {
        void result(BaseResponse<QueryMapPicture> data);

        void setMsg(String msg);

        <T> ObservableTransformer<T, T> bindLifecycle();
    }

    abstract class Presenter extends BasePresenter<View> {

        public abstract void query(HashMap<String, String> map, boolean isDialog, boolean cancelable);

    }
}
