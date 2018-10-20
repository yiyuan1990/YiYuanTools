package com.sjr.yiyuantools.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sjr.yiyuantools.R;
import com.sjr.yiyuantools.adapter.AdHomeRv;
import com.sjr.yiyuantools.adapter.AdTabRv;
import com.sjr.yiyuantools.base.BaseFragment;
import com.sjr.yiyuantools.base.BasePresenter;
import com.sjr.yiyuantools.base.BaseView;
import com.sjr.yiyuantools.common.statusbar.BSUtils;
import com.sjr.yiyuantools.customview.TextViewVerticalMore;
import com.sjr.yiyuantools.entity.HomeBean;
import com.sjr.yiyuantools.entity.TabBean;
import com.sjr.yiyuantools.utils.ToastUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * 首页
 */
public class HomeFragment extends BaseFragment {


    @BindView(R.id.ivScan)
    ImageView ivScan;
    @BindView(R.id.ivMes)
    ImageView ivMes;
    Unbinder unbinder;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;
    @BindView(R.id.rvHome)
    RecyclerView rvHome;


    BGABanner banner;
    RecyclerView rvTab;
    TextViewVerticalMore tvm;
    @BindView(R.id.top)
    LinearLayout top;

    private int[] imageIds = new int[]
            {R.mipmap.tab_a, R.mipmap.tab_b, R.mipmap.tab_c, R.mipmap.tab_d, R.mipmap.tab_e,
                    R.mipmap.tab_f, R.mipmap.tab_g, R.mipmap.tab_h, R.mipmap.tab_i, R.mipmap.tab_j};
    private String[] imageNames = new String[]
            {"农业气象", "产品资讯", "养种植课堂", "水产市场", "热门供应",
                    "致富经验", "农机租用", "政策法规", "招商合作", "精彩活动"};

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
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
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(top.getLayoutParams());
        layoutParams.setMargins(0, statusBarHeight, 0, 0);
        top.setLayoutParams(layoutParams);

        //------测试数据--------
        List<HomeBean> mList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            HomeBean bean = new HomeBean();
            if (i == 1 || i == 3 || i == 5 || i == 9 || i == 12 || i == 15 || i == 17) {
                bean.setImageUrl("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1818860636,3711917293&fm=27&gp=0.jpg" +
                        "yiyuanhttps://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=753404856,317136831&fm=27&gp=0.jpg" +
                        "yiyuanhttps://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3426713988,950570123&fm=27&gp=0.jpg" +
                        "yiyuanhttps://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=595912802,3969363459&fm=200&gp=0.jpg");
                bean.setImageId(R.mipmap.wel_a);
            } else if (i == 2 || i == 4 || i == 8 || i == 10 || i == 11 || i == 13 || i == 18) {
                bean.setImageUrl("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2159746133,1721702531&fm=27&gp=0.jpg" +
                        "yiyuanhttps://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3426713988,950570123&fm=27&gp=0.jpg" +
                        "yiyuanhttps://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1257432960,504006486&fm=27&gp=0.jpg" +
                        "yiyuanhttps://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1222266479,3800419976&fm=26&gp=0.jpg");
                bean.setImageId(R.mipmap.b);
            } else {
                bean.setImageUrl("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2640324039,3350491369&fm=26&gp=0.jpg" +
                        "yiyuanhttps://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1326232956,83996827&fm=26&gp=0.jpg" +
                        "yiyuanhttps://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1751784589,1219598480&fm=26&gp=0.jpg" +
                        "yiyuanhttps://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=207666846,4227354390&fm=200&gp=0.jpg");
                bean.setImageId(R.mipmap.app_update_top);
            }
            mList.add(bean);
        }
        List<String> titleList = new ArrayList<>();
        titleList.add("9月21日，河北省首届“中国农民丰收节”在沙河市红石沟生态农场隆重举行。来自全省11个地市的2000多名新型农业经营主体代表、种植大户、农民代表齐聚河北省沙河市共庆丰收年");
        titleList.add("华盛绿能肥城园区联合入驻企业，创新“企业支部党员+创客+贫困户”的模式对周边贫困户进行帮扶。");
        titleList.add("中国农民丰收节专题：100个乡村美食名单都有哪些？到中国乡村，都有哪些经典美食？");
        titleList.add("北京传统特色小吃，起源于北京城南的南横街。据说光绪年间因为用五花肉煮制的苏造肉价格昂贵，所以人们就用猪头肉和猪下水代替，经过民间烹饪高手的传播，久而久之，造就了卤煮火烧。");
        titleList.add("一道传统的中式面食，由菜码、炸酱拌面条而成，流行于北京 、河北，天津等地。");
        titleList.add("农产品网据大河客户端9月11日消息，因所卖的长豆角农药残留超标，河南新郑市龙湖镇一菜贩杨传有被罚51000元，没收违法所得120元。新郑市食品药品监督管理局责令其整改。");
        titleList.add("吃瓜群众为什么这么尴尬？一个小南瓜，背后有大学问。笔者最近特别留意了这个很不起眼的农产品，也注意到农产品品质与价格背后，相关各方的尴尬。");
        titleList.add("我们种植的“爱上沙漠”小南瓜产量一般在每亩2000斤左右,如果超过2000斤的产量，有的产量在6、7千斤，实际都是不合格的.");
        titleList.add("什么属于“越老越吃香”？要想越老越吃香，只有不断的学习和进步。也就是说要和年轻人一样不落伍，发挥自身职业经验丰富的优势，这样才能“显摆”。否则，“老”就意味着，你该休息了。");

        //------测试数据--------

        rvHome.setLayoutManager(new LinearLayoutManager(getActivity()));
        AdHomeRv adHomeRv = new AdHomeRv(R.layout.item_home_rv, mList, this);
        View headerView = LayoutInflater.from(getActivity()).inflate(R.layout.header_home, null);
        adHomeRv.addHeaderView(headerView);
        rvHome.setAdapter(adHomeRv);

//        setUPMarqueeView
        //Header
        banner = headerView.findViewById(R.id.banner);
        tvm = headerView.findViewById(R.id.tvm);
        rvTab = headerView.findViewById(R.id.rvTab);
        //TextViewVerticalMore
        List<View> views = setUPMarqueeView(titleList);
        tvm.setViews(views);
        //RecycleView
        rvTab.setLayoutManager(new GridLayoutManager(getActivity(), 5));
        List<TabBean> beanList = new ArrayList<>();
        for (int i = 0; i < imageIds.length; i++) {
            TabBean bean = new TabBean(imageIds[i], imageNames[i]);
            beanList.add(bean);
        }
        AdTabRv adTabRv = new AdTabRv(R.layout.item_tab_rv, beanList);
        rvTab.setAdapter(adTabRv);
        //banner
        banner.setAdapter(new BGABanner.Adapter<ImageView, String>() {
            @Override
            public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {
                Glide.with(HomeFragment.this)
                        .load(model)
                        .into(itemView);
            }
        });
        banner.setData(Arrays.asList("http://cdn7.okayapi.com/20181002204951_537e5dbfb22ae4fcfd1ebe1a614e30a8.jpeg",
                "http://cdn7.okayapi.com/20181002205000_d1aed15729c27078b25111d177cc8774.jpeg",
                "http://cdn7.okayapi.com/20181002205006_46548a827e97c282d5a5ee84440016d6.jpeg",
                "http://cdn7.okayapi.com/20181002205015_053143b9e5f288e90ea4ec82e43af41f.jpeg",
                "http://cdn7.okayapi.com/20181002205022_7fe1b8dc7fa022dd0ffd148691686338.jpeg"),
                Arrays.asList("0", "1", "2", "3", "4"));

        banner.setDelegate(new BGABanner.Delegate<ImageView, String>() {
            @Override
            public void onBannerItemClick(BGABanner banner, ImageView itemView, String model, int position) {
                ToastUtil.showShortToast(position + "");
            }
        });

        srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

            }
        });
        srl.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

            }
        });
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

    @OnClick({R.id.ivScan, R.id.ivMes})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivScan:

                break;
            case R.id.ivMes:

                break;
        }
    }

    private List<View> setUPMarqueeView(List<String> list) {
        List<View> views = new ArrayList<>();
        for (int i = 0; i < list.size(); i = i + 2) {
            final int position = i;
            //设置滚动的单个布局
            LinearLayout moreView = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.item_view, null);
            //初始化布局的控件
            TextView tvOne = moreView.findViewById(R.id.tvOne);
            TextView tvTwo = moreView.findViewById(R.id.tvTwo);

            /**
             * 设置监听
             */
            moreView.findViewById(R.id.ll1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            /**
             * 设置监听
             */
            moreView.findViewById(R.id.ll2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            //进行对控件赋值
            tvOne.setText(list.get(i));
            if (list.size() > i + 1) {
                //因为淘宝那儿是两条数据，但是当数据是奇数时就不需要赋值第二个，所以加了一个判断，还应该把第二个布局给隐藏掉
                tvTwo.setText(list.get((i + 1)));
            } else {
                moreView.findViewById(R.id.ll2).setVisibility(View.GONE);
            }

            //添加到循环滚动数组里面去
            views.add(moreView);
        }
        return views;
    }
}