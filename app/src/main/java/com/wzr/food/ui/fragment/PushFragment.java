package com.wzr.food.ui.fragment;

import android.animation.Animator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.wzr.food.R;
import com.wzr.food.databinding.FragmentPushBinding;
import com.wzr.food.logic.dao.MessageDao;
import com.wzr.food.ui.adapter.FoodAdapter;
import com.wzr.food.ui.base.BaseFragment;

public class PushFragment extends BaseFragment {
    private FragmentPushBinding binding;
    private PushViewModel pushViewModel;

    private SwipeRefreshLayout srlPush;
    private RecyclerView rvPush;
    private LottieAnimationView lavPush;
    private LinearLayout flPush;

    private FoodAdapter foodAdapter;
    private LinearLayoutManager linearLayoutManager;

    public static PushFragment newInstance() {
        return new PushFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pushViewModel = new ViewModelProvider(this).get(PushViewModel.class);
    }

    @Override
    protected View initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPushBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    protected void initView() {
        flPush = binding.flPush;
        lavPush = binding.lavPush;
        rvPush = binding.rvPush;
        srlPush = binding.srlPush;
        srlPush.setColorSchemeResources(R.color.light_red);
    }

    @Override
    protected void initEvent() {
        lavPush.setVisibility(View.VISIBLE);
        showDefaultPage(lavPush);
        flPush.setBackgroundResource(R.color.white);

        show();
        srlPush.setOnRefreshListener(() -> {
            show();
            srlPush.setRefreshing(false);
        });
    }

    public void show() {
        pushViewModel.getRecommendLiveData(MessageDao.getSaveLoginBean().getObj().getId()).observe(this, briefsBean -> {
            linearLayoutManager = new LinearLayoutManager(getContext());
            rvPush.setLayoutManager(linearLayoutManager);
            foodAdapter = new FoodAdapter(getContext(), briefsBean.getObj());
            rvPush.setAdapter(foodAdapter);

            if (briefsBean.getObj().size() > 0) {
                lavPush.setVisibility(View.INVISIBLE);
                flPush.setBackgroundResource(R.color.light_gray);
            } else {
                lavPush.setVisibility(View.VISIBLE);
                showDefaultPage(lavPush);
                flPush.setBackgroundResource(R.color.white);
            }

        });
    }

    private void showDefaultPage(LottieAnimationView lav){
        lav.setAnimation("default_page_currently_no_network.json"); // 设置动画文件名
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