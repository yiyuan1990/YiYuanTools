package com.sjr.yiyuantools.common.myglide;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class GlideUtils {
    /**
     * 加载网络图片
     *
     * @param mContext
     * @param path
     * @param imageview
     */
    public static void LoadImage(Context mContext, String path, ImageView imageview) {
        Glide.with(mContext).load(path).into(imageview);
    }


    /**
     * 加载本地图片
     *
     * @param mContext
     * @param path
     * @param imageview
     */
    public static void LoadLocation(Context mContext, Integer path, ImageView imageview) {
        Glide.with(mContext).load(path).into(imageview);
    }

}
