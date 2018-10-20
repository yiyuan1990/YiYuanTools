package com.sjr.yiyuantools.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sjr.yiyuantools.R;
import com.sjr.yiyuantools.entity.TabBean;

import java.util.List;

public class AdMineTwo extends BaseQuickAdapter<TabBean, BaseViewHolder> {

    public AdMineTwo(int layoutResId, @Nullable List<TabBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TabBean item) {
        helper.setText(R.id.tvMineTwo, item.tabName);
        helper.setImageResource(R.id.ivIcon, item.iconId);
    }
}
