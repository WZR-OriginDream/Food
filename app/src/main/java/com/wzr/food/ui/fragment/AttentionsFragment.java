package com.wzr.food.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wzr.food.databinding.AttentionsFragmentBinding;
import com.wzr.food.logic.dao.MessageDao;
import com.wzr.food.ui.adapter.AttentionsAdapter;
import com.wzr.food.ui.base.BaseFragment;

public class AttentionsFragment extends BaseFragment {
    private AttentionsFragmentBinding binding;
    private AttentionsViewModel mViewModel;

    private AttentionsAdapter attentionsAdapter;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView rvAttentions;

    public static AttentionsFragment newInstance() {
        return new AttentionsFragment();
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AttentionsViewModel.class);
    }

    @Override
    protected View initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = AttentionsFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    protected void initView() {
        rvAttentions = binding.rvAttentions;
    }

    @Override
    protected void initEvent() {
//        mViewModel.getAttentionsLiveData(MessageDao.getSaveLoginBean().getObj().getId()).observe(this, attentionsBean -> {
//            linearLayoutManager = new LinearLayoutManager(getContext());
//            rvAttentions.setLayoutManager(linearLayoutManager);
//            attentionsAdapter = new AttentionsAdapter(getContext(), attentionsBean.getObj());
//            rvAttentions.setAdapter(attentionsAdapter);
//        });

        mViewModel.getUserActionListLiveData("follow",MessageDao.getSaveLoginBean().getObj().getId(),0).observe(this,userActionListBean -> {
            linearLayoutManager = new LinearLayoutManager(getContext());
            rvAttentions.setLayoutManager(linearLayoutManager);
            attentionsAdapter = new AttentionsAdapter(getContext(), userActionListBean.getObj());
            rvAttentions.setAdapter(attentionsAdapter);
        });
    }

}