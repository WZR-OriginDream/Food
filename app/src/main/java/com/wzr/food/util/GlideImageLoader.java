package com.wzr.food.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wzr.food.R;
import com.youth.banner.loader.ImageLoader;

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        // 使用Glide的load方法加载图片，并设置占位符
        Glide.with(context)
                .load(path)
                .placeholder(R.drawable.ic_default_page_load_66) // 设置加载中的占位图
                .error(R.drawable.ic_default_page_load_66) // 设置加载失败时的占位图
                .into(imageView);
    }
}
