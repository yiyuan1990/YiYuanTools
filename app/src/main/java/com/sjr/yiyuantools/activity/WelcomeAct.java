package com.sjr.yiyuantools.activity;

import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sjr.yiyuantools.R;
import com.sjr.yiyuantools.api.Constant;
import com.sjr.yiyuantools.base.BaseActivity;
import com.sjr.yiyuantools.base.BaseResponse;
import com.sjr.yiyuantools.contract.QueryMapPictureContract;
import com.sjr.yiyuantools.entity.QueryMapPicture;
import com.sjr.yiyuantools.presenter.QueryMapPresenter;
import com.sjr.yiyuantools.utils.ToastUtil;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

public class WelcomeAct extends BaseActivity<QueryMapPictureContract.View, QueryMapPresenter> implements QueryMapPictureContract.View {
    @BindView(R.id.btnQuery)
    Button btnQuery;
    @BindView(R.id.ivMapPicture)
    ImageView ivMapPicture;
    @BindView(R.id.tvMapUrl)
    TextView tvMapUrl;

    @Override
    public int getLayoutId() {
        return R.layout.act_welcome;
    }

    @Override
    public QueryMapPresenter createPresenter() {
        return new QueryMapPresenter(this);
    }

    @Override
    public QueryMapPictureContract.View createView() {
        return this;
    }

    @Override
    public void init() {

    }

    @Override
    public void result(BaseResponse<QueryMapPicture> data) {

        Log.i("SSSS", data.getShowapi_res_body().getMap_path());
        tvMapUrl.setText(data.getShowapi_res_body().getMap_path());
    }

    @Override
    public void setMsg(String msg) {
        ToastUtil.showShortToast(msg);
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindUntilEvent(ActivityEvent.STOP);//绑定到Activity的pause生命周期（在pause销毁请求）
//        return this.bindLifecycle();//绑定当前Activity（保持和Activity生命周期一致）
    }

    @OnClick(R.id.btnQuery)
    public void onViewClicked() {
        HashMap<String, String> map = new HashMap<>();
//        showapi_appid	String	是	易源应用id
//        showapi_sign	String	是	为了验证用户身份，以及确保参数不被中间人篡改，需要传递调用者的数字签名。
//        showapi_timestamp	String	否	客户端时间。格式yyyyMMddHHmmss,如20141114142239
//                                  为了在一定程度上防止“重放攻击”，平台只接受在10分钟之内的请求。如果不传或传空串，则系统不再做此字段的检测。
//        showapi_res_gzip     0或1     否 返回值是否用gzip方式压缩。此值为1时将压缩，其他值不压缩。
        map.put("showapi_appid", Constant.SHOWAPI_APPID);
        map.put("showapi_sign", Constant.SHOWAPI_SIGN);
        map.put("showapi_res_gzip", Constant.SHOWAPI_RES_GZIP);
        map.put("lons", "116.37359,116.47359");
        map.put("lats", "39.92437,39.92437");
        map.put("zoom", "");
        map.put("width", "");
        map.put("height", "");
        map.put("type", "");
        map.put("needTraffic", "");
        map.put("specs", "");
        map.put("color", "");
        map.put("marker", "");
        map.put("title", "");
        map.put("font", "");
        map.put("bold", "");
        map.put("fontSize", "");
        map.put("fontColor", "");
        map.put("backgroundColor", "");
        map.put("weight", "");
        map.put("pathsColor", "");
        map.put("transparency", "");
        map.put("fillColor", "");
        map.put("fillTransparency", "");
        getPresenter().query(map, true, true);


    }

}
