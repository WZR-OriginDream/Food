package com.wzr.food.ui.activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.wzr.food.databinding.ActivityEditProfileBinding;
import com.wzr.food.ui.base.BaseActivity;

public class EditProfileActivity extends BaseActivity {
    private ActivityEditProfileBinding binding;
    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ivBack.setOnClickListener(v -> onBackPressed());
    }

    @Override
    protected void initLayout() {
        binding = ActivityEditProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void initView() {
        ivBack = binding.ivBack;
    }
}