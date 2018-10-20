package com.sjr.yiyuantools.activity;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sjr.yiyuantools.R;
import com.sjr.yiyuantools.api.Constant;
import com.sjr.yiyuantools.base.BaseActivity;
import com.sjr.yiyuantools.base.BaseResponse;
import com.sjr.yiyuantools.common.SPUtil;
import com.sjr.yiyuantools.common.assist.Check;
import com.sjr.yiyuantools.common.statusbar.BSUtils;
import com.sjr.yiyuantools.contract.LoginContract;
import com.sjr.yiyuantools.customview.CountDownView;
import com.sjr.yiyuantools.entity.Login;
import com.sjr.yiyuantools.presenter.LoginPresenter;
import com.sjr.yiyuantools.utils.ToastUtil;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.bgabanner.BGABanner;
import cn.bingoogolapple.bgabanner.BGALocalImageSize;
import io.reactivex.ObservableTransformer;


public class GuidanceAct extends BaseActivity<LoginContract.View, LoginContract.Presenter> implements LoginContract.View {


    @BindView(R.id.banner_guide_background)
    BGABanner bannerGuideBackground;
    @BindView(R.id.banner_guide_foreground)
    BGABanner bannerGuideForeground;
    @BindView(R.id.tv_guide_skip)
    TextView tvGuideSkip;
    @BindView(R.id.btn_guide_enter)
    TextView btnGuideEnter;
    @BindView(R.id.ivWel)
    ImageView ivWel;
    @BindView(R.id.rl_enter)
    RelativeLayout rlEnter;
    @BindView(R.id.progress)
    CountDownView progress;

    @Override
    public int getLayoutId() {
        return R.layout.act_guidance;
    }

    @Override
    public LoginContract.Presenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    public LoginContract.View createView() {
        return this;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void init() {
        BSUtils.setStatusBar(this, R.color.tran, false);
        String isFirst = (String) SPUtil.getString(this, "start", "first");
        Log.i("SSSS", "SPUtil:" + isFirst);
        if (!Check.isEmpty(isFirst)) {
            if ("first".equals(isFirst)) {
                SPUtil.putString(this, "start", "other");
                firstEnter();
            } else {
                followEnter();
            }
        }


    }

    /**
     * 后续启动app
     */
    private void followEnter() {
        Log.i("SSSS", "SPUtil: 后续启动app");
        tvGuideSkip.setVisibility(View.GONE);
        btnGuideEnter.setVisibility(View.GONE);
        ivWel.setVisibility(View.VISIBLE);
        rlEnter.setVisibility(View.VISIBLE);
        progress.setVisibility(View.VISIBLE);
        String imUrl = "https://thumbs.dreamstime.com/b/%E5%86%9C%E4%B8%9A%E9%A3%8E%E6%99%AF-39846236.jpg";
//        ImageLoaderOptions op = new ImageLoaderOptions.Builder(ivWel, imUrl)
//                .isSkipMemoryCache(false)
//                .isCrossFade(true)
//                .diskCacheStrategy(ImageLoaderOptions.DiskCacheStrategy.DEFAULT)
//                .build();
//        ImageLoaderManager.getInstance().showImage(op);
        ivWel.setBackgroundResource(R.mipmap.wel_a);
        rlEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                outGuidanceAct();
            }
        });
        progress.setPaintColor("#3F51B5");
        progress.startDownTime(1000, new CountDownView.OnFinishListener() {
            @Override
            public void onFinish() {
                outGuidanceAct();
            }
        });


    }

    /**
     * 首次启动app
     */
    private void firstEnter() {
        /**
         * 设置进入按钮和跳过按钮控件资源 id 及其点击事件
         * 如果进入按钮和跳过按钮有一个不存在的话就传 0
         * 在 BGABanner 里已经帮开发者处理了防止重复点击事件
         * 在 BGABanner 里已经帮开发者处理了「跳过按钮」和「进入按钮」的显示与隐藏
         */
        bannerGuideBackground.setEnterSkipViewIdAndDelegate(R.id.btn_guide_enter, R.id.tv_guide_skip, new BGABanner.GuideDelegate() {
            @Override
            public void onClickEnterOrSkip() {
                outGuidanceAct();
            }
        });
        // Bitmap 的宽高在 maxWidth maxHeight 和 minWidth minHeight 之间
        BGALocalImageSize localImageSize = new BGALocalImageSize(1080, 1920, 320, 640);
        // 设置数据源
        bannerGuideBackground.setData(localImageSize, ImageView.ScaleType.CENTER_CROP,
                R.mipmap.wel_a,
                R.mipmap.wel_b,
                R.mipmap.wel_c);

        bannerGuideForeground.setData(localImageSize, ImageView.ScaleType.CENTER_CROP,
                R.mipmap.w_a,
                R.mipmap.w_b,
                R.mipmap.w_c);
    }

    /**
     * 离开本页面
     */
    private void outGuidanceAct() {
        // TODO: 2018/9/30 判断是否登录过，如果已保存登陆过直接进入主页面，未保存登陆过则进入登录页面
//        startActivity(new Intent(GuidanceAct.this, MainActivity.class));

        String loginState = SPUtil.getString(this, "loginState", "");
        if ("1".equals(loginState)) {//之前登陆过，token是一天后过期 故要重新登录一下

//            SPUtil.putString(this, "token", token);
//            SPUtil.putString(this, "uuid", uuid);
//            SPUtil.putString(this, "loginState", "1");
//            SPUtil.putString(this, "userName", userName);
//            SPUtil.putString(this, "userPw", userPw);
//            SPUtil.putString(this, "md5Pw", md5Pw);

            String userName = SPUtil.getString(this, "userName", "");
            String md5Pw = SPUtil.getString(this, "md5Pw", "");
            if (!Check.isEmpty(userName) && !Check.isEmpty(md5Pw)) {
                HashMap<String, String> params = new HashMap<>();
                params.put("app_key", Constant.APP_KEY);
                params.put("username", userName);
                params.put("password", md5Pw);
                getPresenter().login(params, false, false);
            } else {
                ToastUtil.showShortToast("登录已失效，请重新登录");
                startActivity(new Intent(GuidanceAct.this, LoginAct.class));
                finish();
            }

        } else {
            startActivity(new Intent(GuidanceAct.this, LoginAct.class));
            finish();
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    public void result(BaseResponse<Login> data) {
        Login data1 = data.getData();
        int err_code = data1.getErr_code();
        if (err_code == 0) {
            keepUserData(data1);//保存登录用户资料
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            ToastUtil.showLongToast(data1.getErr_msg());
            startActivity(new Intent(GuidanceAct.this, LoginAct.class));
            finish();
        }


    }

    @Override
    public void err(int err) {
        if (err == 999) {
            startActivity(new Intent(GuidanceAct.this, LoginAct.class));
            finish();
        }
    }

    private void keepUserData(Login data) {
        String token = data.getToken();
        SPUtil.putString(this, "token", token);
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindUntilEvent(ActivityEvent.STOP);
    }
}
