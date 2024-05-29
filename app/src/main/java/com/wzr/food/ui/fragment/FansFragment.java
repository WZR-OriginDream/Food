package com.wzr.food.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wzr.food.databinding.FansFragmentBinding;
import com.wzr.food.logic.dao.MessageDao;
import com.wzr.food.ui.adapter.AttentionsAdapter;
import com.wzr.food.ui.base.BaseFragment;

public class FansFragment extends BaseFragment {
    private FansFragmentBinding binding;
    private FansViewModel mViewModel;

    private AttentionsAdapter attentionsAdapter;
    private LinearLayoutManager linearLayoutManager;

    private RecyclerView rvFans;

    public static FansFragment newInstance() {
        return new FansFragment();
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FansViewModel.class);
    }

    @Override
    protected View initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FansFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    protected void initView() {
        rvFans= binding.rvFans;
    }

    @Override
    protected void initEvent() {
//        mViewModel.getFansLiveData( MessageDao.getSaveLoginBean().getObj().getId()).observe(this, new Observer<AttentionsBean>() {
//            @Override
//            public void onChanged(AttentionsBean attentionsBean) {
//                linearLayoutManager = new LinearLayoutManager(getContext());
//                rvFans.setLayoutManager(linearLayoutManager);
//                attentionsAdapter = new AttentionsAdapter(getContext(), attentionsBean.getObj());
//                rvFans.setAdapter(attentionsAdapter);
//            }
//        });

        mViewModel.getUserAction2ListLiveData("follow",0, MessageDao.getSaveLoginBean().getObj().getId()).observe(this,userActionListBean -> {
            linearLayoutManager = new LinearLayoutManager(getContext());
            rvFans.setLayoutManager(linearLayoutManager);
            attentionsAdapter = new AttentionsAdapter(getContext(), userActionListBean.getObj());
            rvFans.setAdapter(attentionsAdapter);
        });
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

}