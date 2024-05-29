package com.wzr.food.ui.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =initLayout(inflater, container, savedInstanceState);
        initView();
        initEvent();
        return view;
    }
    protected abstract View initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);
    protected abstract void initView();
    protected abstract void initEvent();

}