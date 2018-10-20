package com.sjr.yiyuantools.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sjr.yiyuantools.R;
import com.sjr.yiyuantools.entity.HomeBean;
import com.sjr.yiyuantools.fragment.HomeFragment;

import java.util.List;

public class AdHomeRv extends BaseQuickAdapter<HomeBean, BaseViewHolder> {
    private HomeFragment mContext;

    public AdHomeRv(int layoutResId, @Nullable List<HomeBean> data, HomeFragment context) {
        super(layoutResId, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean item) {
        ImageView ivBgLeftOne = helper.getView(R.id.ivBgLeftOne);
        ImageView ivBgLeftTwo = helper.getView(R.id.ivBgLeftTwo);
        ImageView ivBgRightOne = helper.getView(R.id.ivBgRightOne);
        ImageView ivBgRightTwo = helper.getView(R.id.ivBgRightTwo);
        String imageUrl = item.getImageUrl();
        String[] split = imageUrl.split("yiyuan");
        Glide.with(mContext).load(split[0]).into(ivBgLeftOne);
        Glide.with(mContext).load(split[1]).into(ivBgLeftTwo);
        Glide.with(mContext).load(split[2]).into(ivBgRightOne);
        Glide.with(mContext).load(split[3]).into(ivBgRightTwo);
//        ivBg.setBackgroundResource(item.getImageId());
    }
}
