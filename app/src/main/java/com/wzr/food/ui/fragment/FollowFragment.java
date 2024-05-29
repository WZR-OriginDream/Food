package com.wzr.food.ui.fragment;

import android.animation.Animator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.airbnb.lottie.LottieAnimationView;
import com.wzr.food.R;
import com.wzr.food.databinding.FollowFragmentBinding;
import com.wzr.food.ui.adapter.FollowArticleAdapter;
import com.wzr.food.ui.base.BaseFragment;

public class FollowFragment extends BaseFragment {
    private FollowFragmentBinding binding;
    private FollowViewModel mViewModel;

    private LinearLayout llFollow;
    private LottieAnimationView lavCollect;

    private RecyclerView rvfollow;
    private FollowArticleAdapter followArticleAdapter;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;

    private static int userId;

    public static FollowFragment newInstance(int id) {
        userId = id;
        return new FollowFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FollowViewModel.class);
    }

    @Override
    protected View initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FollowFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    protected void initView() {
        rvfollow = binding.rvFollow;
        llFollow = binding.llFollow;
        lavCollect = binding.lavCollect;
    }

    @Override
    protected void initEvent() {
        lavCollect.setVisibility(View.VISIBLE);
        showDefaultPage(lavCollect);
        llFollow.setBackgroundResource(R.color.white);
        show();
    }

    private void show() {
        mViewModel.getPostActionListLiveData("collect", userId, 1).observe(this, postActionListBean -> {
            staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            rvfollow.setLayoutManager(staggeredGridLayoutManager);
            followArticleAdapter = new FollowArticleAdapter(getContext(), postActionListBean.getObj());
            rvfollow.setAdapter(followArticleAdapter);

            if (postActionListBean.getObj().size() > 0) {
                lavCollect.setVisibility(View.GONE);
                llFollow.setBackgroundResource(R.color.light_gray);
            } else {
                lavCollect.setVisibility(View.VISIBLE);
                showDefaultPage(lavCollect);
                llFollow.setBackgroundResource(R.color.white);
            }
        });

    }

    private void showDefaultPage(LottieAnimationView lav) {
        lav.setAnimation("default_page_currently_no_collect.json"); // 设置动画文件名
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