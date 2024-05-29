package com.wzr.food.ui.custom;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.airbnb.lottie.LottieAnimationView;
import com.wzr.food.R;

public class LottieDialog extends Dialog {

    public LottieDialog(@NonNull Context context) {
        super(context);
    }

    public LottieDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    // 重写onCreate方法来设置对话框内容
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置布局
        setContentView(R.layout.dialog_lottie_animation);
        // 初始化LottieAnimationView
        LottieAnimationView lottieAnimationView = findViewById(R.id.lottieAnimationView);
        // 加载动画文件
        lottieAnimationView.setAnimation("four_action.json");
        // 设置自动播放等属性
        lottieAnimationView.loop(true);
        lottieAnimationView.playAnimation();
    }
}
