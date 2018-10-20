package com.sjr.yiyuantools.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sjr.yiyuantools.R;
import com.sjr.yiyuantools.entity.TabBean;
import com.sjr.yiyuantools.fragment.MyFragment;

import java.util.List;

public class AdMineOne extends BaseQuickAdapter<TabBean, BaseViewHolder> {
    MyFragment mContext;

    public AdMineOne(int layoutResId, @Nullable List<TabBean> data, MyFragment context) {
        super(layoutResId, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, TabBean item) {
        helper.setText(R.id.tvMine, item.tabName);
        helper.setImageResource(R.id.ivMine, item.iconId);


    }
}
