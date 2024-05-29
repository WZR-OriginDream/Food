package com.wzr.food.ui.fragment;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.wzr.food.R;
import com.wzr.food.databinding.CookbookFragmentBinding;
import com.wzr.food.ui.activity.CreateCookBookActivity;
import com.wzr.food.ui.adapter.UserArticleAdapter;
import com.wzr.food.ui.base.BaseFragment;

public class CookBookFragment extends BaseFragment {
    private static final int REQUEST_CODE = 1;
    private CookbookFragmentBinding binding;
    private CookBookViewModel cookBookViewModel;

    private FloatingActionButton postcookbook, deletecookbook;

    private RelativeLayout rlCookBook;
    private RecyclerView rvUserArticle;
    private LottieAnimationView lavEmpty;
    private UserArticleAdapter smallArticleAdapter;
    private LinearLayoutManager linearLayoutManager;

    private TextView tvtip;

    private Boolean isDelete = false;

    private static int userId;

    public static CookBookFragment newInstance(int id) {
        userId = id;
        return new CookBookFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cookBookViewModel = new ViewModelProvider(this).get(CookBookViewModel.class);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                String value = data.getStringExtra("key"); // 获取传递的数据
                // 使用返回的数据进行操作
                show(isDelete);
            }
        }
    }

    @Override
    protected View initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = CookbookFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    protected void initView() {
        postcookbook = binding.actionA;
        deletecookbook = binding.actionB;
        rvUserArticle = binding.rvUserArticle;
        tvtip = binding.tvTip;
        lavEmpty = binding.lavEmpty;
        rlCookBook=binding.rlCookbook;
    }

    @Override
    protected void initEvent() {

        lavEmpty.setVisibility(View.VISIBLE);
        showDefaultPage(lavEmpty);
        rlCookBook.setBackgroundResource(R.color.white);
        tvtip.setText("发布菜谱给食友们分享经验吧");

        show(isDelete);

        //上传食谱
        postcookbook.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), CreateCookBookActivity.class);
            startActivityForResult(intent,REQUEST_CODE);
        });


        //删除食谱
        deletecookbook.setOnClickListener(v -> {
            isDelete = !isDelete;
            if (isDelete) {
                deletecookbook.setColorNormal(R.color.light_red);
                deletecookbook.setIcon(R.drawable.delete_white);
            } else {
                deletecookbook.setColorNormal(R.color.white);
                deletecookbook.setIcon(R.drawable.delete);
            }
            show(isDelete);
        });
    }

    @SuppressLint("ResourceAsColor")
    private void show(Boolean isDelete) {
        //获取食谱
        cookBookViewModel.getUserArticleLiveData(userId).observe(this, typeArticleBean -> {
            linearLayoutManager = new LinearLayoutManager(getContext());
            rvUserArticle.setLayoutManager(linearLayoutManager);
            smallArticleAdapter = new UserArticleAdapter(getContext(), typeArticleBean.getObj(), isDelete);
            rvUserArticle.setAdapter(smallArticleAdapter);

            if (typeArticleBean.getObj().size() > 0) {
                lavEmpty.setVisibility(View.GONE);
                rlCookBook.setBackgroundResource(R.color.light_gray);
                tvtip.setText("没有更多菜谱了");
            } else {
                lavEmpty.setVisibility(View.VISIBLE);
                showDefaultPage(lavEmpty);
                rlCookBook.setBackgroundResource(R.color.white);
                tvtip.setText("发布菜谱给食友们分享经验吧");
            }
        });
    }

    private void showDefaultPage(LottieAnimationView lav){
        lav.setAnimation("default_page_currently_empty.json"); // 设置动画文件名
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