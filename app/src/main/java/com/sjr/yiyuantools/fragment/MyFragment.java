package com.sjr.yiyuantools.fragment;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sjr.yiyuantools.R;
import com.sjr.yiyuantools.adapter.AdMineOne;
import com.sjr.yiyuantools.adapter.AdMineTwo;
import com.sjr.yiyuantools.base.BaseFragment;
import com.sjr.yiyuantools.base.BasePresenter;
import com.sjr.yiyuantools.base.BaseView;
import com.sjr.yiyuantools.common.statusbar.BSUtils;
import com.sjr.yiyuantools.customview.RoundAngleImageView;
import com.sjr.yiyuantools.entity.TabBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 首页
 */
public class MyFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.rivUserHeader)
    RoundAngleImageView rivUserHeader;
    @BindView(R.id.tvUserName)
    TextView tvUserName;
    @BindView(R.id.rvMine)
    RecyclerView rvMine;
    @BindView(R.id.rvMineTwo)
    RecyclerView rvMineTwo;
    @BindView(R.id.nestedScrollView)
    NestedScrollView nestedScrollView;
    @BindView(R.id.vv)
    View vv;
    private int[] iconIds = new int[]{R.mipmap.mine_a, R.mipmap.mine_b, R.mipmap.mine_c,
            R.mipmap.mine_d, R.mipmap.mine_e, R.mipmap.mine_f};
    private String[] iconNames = new String[]{"每日签到", "我的订单", "收藏记录", "关注店铺", "会员中心", "开店致富"};
    private int[] iconIds2 = new int[]{R.mipmap.bot_a, R.mipmap.bot_b, R.mipmap.bot_c, R.mipmap.bot_d,
            R.mipmap.bot_e,R.mipmap.bot_h, R.mipmap.bot_f, R.mipmap.bot_g};
    private String[] iconNames2 = new String[]{"我的积分详情", "专家技术论坛", "人工客服", "意见反馈", "分享邀请给小伙伴", "检查更新", "关于我们", "退出登录"};

    @Override
    public int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public BaseView createView() {
        return null;
    }

    boolean isTag = true;// true 黄色  false 白色
    boolean isFirst = true;// true 黄色  false 白色
    int heightVv;

    @Override
    public void init() {

        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                Log.i("AAAA", "onScrollChange: " + scrollX + "-" + scrollY + "\n" + oldScrollX + "-" + oldScrollY);
                if (isFirst) {
                    heightVv = vv.getBottom();
                    isFirst = false;
                }

                if (scrollY >= heightVv) {
                    if (isTag) {
                        BSUtils.setStatusBar(getActivity(), R.color.tran, true);
                        isTag = false;
                    }
                } else {
                    if (!isTag) {
                        BSUtils.setStatusBar(getActivity(), R.color.tran, false);
                        isTag = true;
                    }


                }

            }
        });
        //-------------------------测试数据------------------------

        //-------------------------测试数据------------------------
        List<TabBean> beans = new ArrayList<>();
        List<TabBean> beans2 = new ArrayList<>();
        for (int i = 0; i < iconIds.length; i++) {
            TabBean b = new TabBean(iconIds[i], iconNames[i]);
            beans.add(b);

        }
        for (int i = 0; i < iconNames2.length; i++) {
            TabBean b2 = new TabBean(iconIds2[i], iconNames2[i]);
            beans2.add(b2);

        }
        rvMine.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        AdMineOne adMineOne = new AdMineOne(R.layout.item_mine_tab_one, beans, this);
        rvMine.setAdapter(adMineOne);


        rvMineTwo.setLayoutManager(new LinearLayoutManager(getActivity()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        AdMineTwo adMineTwo = new AdMineTwo(R.layout.item_mine_tab_two, beans2);
        rvMineTwo.setAdapter(adMineTwo);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
