package com.sjr.yiyuantools.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.sjr.yiyuantools.R;
import com.sjr.yiyuantools.base.BaseFragment;
import com.sjr.yiyuantools.base.BasePresenter;
import com.sjr.yiyuantools.base.BaseView;
import com.sjr.yiyuantools.common.statusbar.BSUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 首页
 */
public class NearFragment extends BaseFragment {

    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.top)
    LinearLayout top;
    Unbinder unbinder;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_near;
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public BaseView createView() {
        return null;
    }

    @Override
    public void init() {
        String one = "https://h5.m.jd.com/dev/2vQWcFpeGVxMqGFiUbGAM3CzqvJS/index.html";
        String Two = "https://pro.jd.com/mall/active/3Ha4WUgHnz6JvkhjsGNLAjLzNVfu/index.html";
        int statusBarHeight = BSUtils.getStatusBarHeight(getActivity());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(top.getLayoutParams());
        layoutParams.setMargins(0, statusBarHeight, 0, 0);
        top.setLayoutParams(layoutParams);

        initWebView(webView);
        webView.loadUrl(one);
//        webView.loadData(asd,"text/html", "UTF-8");
    }

    private void initWebView(WebView webView) {
        WebSettings webSettings = webView.getSettings();
        //添加客户端支持
        webView.setWebChromeClient(new WebChromeClient());
        //获取触摸焦点
        webView.requestFocusFromTouch();

        //支持缩放，默认为true。
        webSettings.setSupportZoom(false);
        //调整图片至适合webview的大小
        webSettings.setUseWideViewPort(true);
        // 缩放至屏幕的大小
        webSettings.setLoadWithOverviewMode(true);
        //设置默认编码
        webSettings.setDefaultTextEncodingName("utf-8");
        //设置自动加载图片
        webSettings.setLoadsImagesAutomatically(true);
        //多窗口
        webSettings.supportMultipleWindows();
        //允许访问文件
        webSettings.setAllowFileAccess(true);
        //开启javascript
        webSettings.setJavaScriptEnabled(true);
        //支持通过JS打开新窗口
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        //提高渲染的优先级
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        //支持内容重新布局
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //关闭webview中缓存
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
