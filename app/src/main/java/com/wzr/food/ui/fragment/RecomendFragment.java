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
import com.wzr.food.databinding.RecomendFragmentBinding;
import com.wzr.food.logic.dao.MessageDao;
import com.wzr.food.ui.adapter.FoodAdapter;
import com.wzr.food.ui.base.BaseFragment;

public class RecomendFragment extends BaseFragment {
    private RecomendFragmentBinding binding;
    private RecomendViewModel recomendViewModel;

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private LottieAnimationView lavRecommend;
    private LinearLayout flRecommend;

    private FoodAdapter foodAdapter;
    private LinearLayoutManager linearLayoutManager;

    public static RecomendFragment newInstance() {
        return new RecomendFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recomendViewModel = new ViewModelProvider(this).get(RecomendViewModel.class);
    }

    @Override
    protected View initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = RecomendFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    protected void initView() {
        flRecommend = binding.flRecommend;
        lavRecommend = binding.lavRecommend;
        recyclerView = binding.recyclerView;
        swipeRefreshLayout = binding.swipeRefresh;
        swipeRefreshLayout.setColorSchemeResources(R.color.light_red);
    }

    @Override
    protected void initEvent() {
        lavRecommend.setVisibility(View.VISIBLE);
        showDefaultPage(lavRecommend);
        flRecommend.setBackgroundResource(R.color.white);

        show();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                show();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    public void show() {
        recomendViewModel.getBriefsLiveData(MessageDao.getSaveLoginBean().getObj().getId(), 8).observe(this, briefsBean -> {
            linearLayoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            foodAdapter = new FoodAdapter(getContext(), briefsBean.getObj());
            recyclerView.setAdapter(foodAdapter);

            if (briefsBean.getObj().size() > 0) {
                lavRecommend.setVisibility(View.INVISIBLE);
                flRecommend.setBackgroundResource(R.color.light_gray);
            } else {
                lavRecommend.setVisibility(View.VISIBLE);
                showDefaultPage(lavRecommend);
                flRecommend.setBackgroundResource(R.color.white);
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