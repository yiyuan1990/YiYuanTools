package com.sjr.yiyuantools.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

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
public class GiveFragment extends BaseFragment {

    @BindView(R.id.top)
    LinearLayout top;
    Unbinder unbinder;
    @BindView(R.id.btnOk)
    Button btnOk;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_give_need;
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
        int statusBarHeight = BSUtils.getStatusBarHeight(getActivity());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(top.getLayoutParams());
        layoutParams.setMargins(0, statusBarHeight, 0, 0);
        top.setLayoutParams(layoutParams);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(btnOk.getLayoutParams());
        layoutParams2.setMargins(0, 0, 0, statusBarHeight);
        btnOk.setLayoutParams(layoutParams2);
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
