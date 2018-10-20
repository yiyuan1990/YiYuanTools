package com.sjr.yiyuantools.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sjr.yiyuantools.R;
import com.sjr.yiyuantools.entity.TabBean;

import java.util.List;

public class AdTabRv extends BaseQuickAdapter<TabBean, BaseViewHolder> {
    public AdTabRv(int layoutResId, @Nullable List<TabBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TabBean item) {
        helper.setImageResource(R.id.iv, item.iconId);
        helper.setText(R.id.tv, item.tabName);
    }
}
