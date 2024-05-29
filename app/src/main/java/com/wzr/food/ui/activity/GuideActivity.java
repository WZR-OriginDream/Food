package com.wzr.food.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.wzr.food.R;
import com.wzr.food.ui.adapter.MyFragmentStateAdapter;
import com.wzr.food.ui.base.BaseActivity;
import com.wzr.food.ui.fragment.GuideFragment1;
import com.wzr.food.ui.fragment.GuideFragment2;
import com.wzr.food.ui.fragment.GuideFragment3;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends BaseActivity {

    private ViewPager2 viewPager;
    private List<Fragment> fragments;
    private int lastPageIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        viewPager = findViewById(R.id.view_pager);
        fragments = new ArrayList<>();
        lastPageIndex = 2;

        // 初始化引导页Fragment
        fragments.add(new GuideFragment1());
        fragments.add(new GuideFragment2());
        fragments.add(new GuideFragment3());

        viewPager.setAdapter(new MyFragmentStateAdapter(this));
        viewPager.setOffscreenPageLimit(3);

        // 初始化指示点
//        initDots();

        findViewById(R.id.tv_skip).setOnClickListener(view -> {
            startActivity(new Intent(this, LoginActivity.class));
            finish(); // 跳过进入登录界面
        });

        findViewById(R.id.btn_next).setOnClickListener(view -> {
            if (viewPager.getCurrentItem() != lastPageIndex) {
                // 滑动到下一张图
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
            } else {
                // 最后一张图，进入登录界面
                startActivity(new Intent(this, LoginActivity.class));
                finish();
            }
        });
    }

    @Override
    protected void initLayout() {

    }

    @Override
    protected void initView() {

    }

}