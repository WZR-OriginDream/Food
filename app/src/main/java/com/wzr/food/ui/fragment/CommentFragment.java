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

import com.airbnb.lottie.LottieAnimationView;
import com.wzr.food.R;
import com.wzr.food.databinding.CommentFragmentBinding;
import com.wzr.food.logic.dao.MessageDao;
import com.wzr.food.ui.adapter.MyCommentAdapter;
import com.wzr.food.ui.base.BaseFragment;

public class CommentFragment extends BaseFragment {

    private CommentFragmentBinding binding;
    private CommentViewModel mViewModel;

    private LottieAnimationView lavDirection;
    private RecyclerView rvMyComent;
    private LinearLayout llComment;
    private MyCommentAdapter myCommentAdapter;
    private LinearLayoutManager linearLayoutManager;

    public static CommentFragment newInstance() {
        return new CommentFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CommentViewModel.class);
    }

    @Override
    protected View initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = CommentFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    protected void initView() {
        lavDirection = binding.lavDirection;
        rvMyComent=  binding.rvComment;
        llComment = binding.llComment;
    }

    @Override
    protected void initEvent() {

        mViewModel.getMyCommentBeanMutableLiveData(MessageDao.getSaveLoginBean().getObj().getId()).observe(getViewLifecycleOwner(),myCommentBean -> {
            linearLayoutManager = new LinearLayoutManager(getContext());
            rvMyComent.setLayoutManager(linearLayoutManager);
            myCommentAdapter = new MyCommentAdapter(getContext(), myCommentBean.getObj());
            rvMyComent.setAdapter(myCommentAdapter);

            if (myCommentBean.getObj().size() > 0) {
                lavDirection.setVisibility(View.INVISIBLE);
                llComment.setBackgroundResource(R.color.light_gray);
            } else {
                lavDirection.setVisibility(View.VISIBLE);
                showDefaultPage(lavDirection);
                llComment.setBackgroundResource(R.color.white);
            }
        });

    }
    private void showDefaultPage(LottieAnimationView lav){
        lav.setAnimation("default_page_currently_no_direction.json"); // 设置动画文件名
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