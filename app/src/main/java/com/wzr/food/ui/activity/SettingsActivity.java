package com.wzr.food.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wzr.food.databinding.ActivitySettingBinding;
import com.wzr.food.logic.dao.MessageDao;
import com.wzr.food.ui.base.BaseActivity;

public class SettingsActivity extends BaseActivity {
    private ActivitySettingBinding binding;
    private ImageView ivback;
    private RelativeLayout rtinfo,rtphone,rtprofit,rthelp,rtabout,rtswitch;
    private TextView tvlogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ivback.setOnClickListener(v -> onBackPressed());
        tvlogout.setOnClickListener(v -> logout());

        rtinfo.setOnClickListener(v->{
            Intent intent = new Intent(SettingsActivity.this, EditProfileActivity.class);
            startActivity(intent);
        });

        rtabout.setOnClickListener(v->{
            Intent intent = new Intent(SettingsActivity.this, AboutActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void initLayout() {
        binding = ActivitySettingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }


    @Override
    protected void initView() {
        ivback = binding.ivBack;
        rtinfo= binding.rtUserInfo;
        tvlogout= binding.tvLogout;
        rtabout = binding.rtUserAbout;
    }

    /**
     * 注销
     */
    private void logout() {
        MessageDao.removeLoginBean();
        Intent intent = new Intent(SettingsActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }


}