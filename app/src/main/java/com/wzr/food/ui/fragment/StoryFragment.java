package com.wzr.food.ui.fragment;

import android.animation.Animator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.wzr.food.databinding.FragmentStoryBinding;
import com.wzr.food.ui.base.BaseFragment;


public class StoryFragment extends BaseFragment {
    private FragmentStoryBinding binding;
    private TextView tvStory, tvCreateTime;
    private String story, createTime;
    private LottieAnimationView lavPlant;

    private StoryFragment(String text, String createTime) {
        this.story = text;
        this.createTime = createTime;
    }

    public static StoryFragment newInstance(String text, String createTime) {
        return new StoryFragment(text, createTime);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected View initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentStoryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    protected void initView() {
        tvStory = binding.tvStory;
        tvCreateTime = binding.tvCreatetime;
        lavPlant = binding.lavPlant;
    }

    @Override
    protected void initEvent() {
        tvStory.setText(story);
        tvCreateTime.setText(createTime);
        showDefaultPage(lavPlant);
    }

    private void showDefaultPage(LottieAnimationView lav) {
        lav.setAnimation("default_page_currently_book.json"); // 设置动画文件名
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