package com.wzr.food.ui.activity;

import android.animation.Animator;
import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;
import com.wzr.food.databinding.ActivityAboutBinding;
import com.wzr.food.ui.base.BaseActivity;

public class AboutActivity extends BaseActivity {
    private ActivityAboutBinding binding;
    private LottieAnimationView lavAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initLayout() {
        binding = ActivityAboutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void initView() {
        lavAbout = binding.lavAbout;
        showDefaultPage(lavAbout);
    }

    private void showDefaultPage(LottieAnimationView lav){
        lav.setAnimation("default_page_currently_plant.json"); // 设置动画文件名
        lav.loop(true); // 设置是否循环播放
        lav.playAnimation(); // 开始播放动画
        lav.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                // 动画开始
            }
            @Override
            public void onAnimationEnd(Animator animation) {
                // 动画结束
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
            // 其他事件处理...
        });
    }
}