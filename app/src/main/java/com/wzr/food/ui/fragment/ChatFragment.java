package com.wzr.food.ui.fragment;

import android.animation.Animator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;

import com.airbnb.lottie.LottieAnimationView;
import com.wzr.food.databinding.ChatFragmentBinding;
import com.wzr.food.ui.base.BaseFragment;

public class ChatFragment extends BaseFragment {
    private ChatFragmentBinding binding;
    private ChatViewModel mViewModel;

    private LottieAnimationView lavInfo;

    public static ChatFragment newInstance() {
        return new ChatFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ChatViewModel.class);
    }

    @Override
    protected View initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = ChatFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    protected void initView() {
        lavInfo = binding.lavInfo;
    }

    @Override
    protected void initEvent() {
        lavInfo.setAnimation("default_page_currently_no_info.json"); // 设置动画文件名
        lavInfo.loop(true); // 设置是否循环播放
        lavInfo.playAnimation(); // 开始播放动画
        lavInfo.addAnimatorListener(new Animator.AnimatorListener() {
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