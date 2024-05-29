package com.wzr.food.ui.activity;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.wzr.food.databinding.ActivityHistoryBinding;
import com.wzr.food.logic.dao.MessageDao;
import com.wzr.food.ui.adapter.FollowArticleAdapter;
import com.wzr.food.ui.base.BaseActivity;
import com.wzr.food.ui.fragment.FollowViewModel;

public class HistoryActivity extends BaseActivity {
    private ActivityHistoryBinding binding;
    private FollowViewModel mViewModel;

    private ImageView ivback;
    private RecyclerView rvClick;

    private FollowArticleAdapter followArticleAdapter;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FollowViewModel.class);

        ivback.setOnClickListener(v -> onBackPressed());

        mViewModel.getPostActionListLiveData("click", MessageDao.getSaveLoginBean().getObj().getId(), 1).observe(this, postActionListBean -> {
            staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
            rvClick.setLayoutManager(staggeredGridLayoutManager);
            followArticleAdapter = new FollowArticleAdapter(this, postActionListBean.getObj());
            rvClick.setAdapter(followArticleAdapter);
        });
    }

    @Override
    protected void initLayout() {
        binding = ActivityHistoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }


    @Override
    protected void initView() {
        ivback = binding.ivBack;
        rvClick = binding.rvClick;
    }


}