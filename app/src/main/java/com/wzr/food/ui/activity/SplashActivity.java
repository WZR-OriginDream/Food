package com.wzr.food.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.wzr.food.MainActivity;
import com.wzr.food.R;
import com.wzr.food.logic.dao.MessageDao;
import com.wzr.food.ui.base.BaseActivity;

public class SplashActivity extends BaseActivity {

    private static final int SPLASH_DISPLAY_LENGTH = 1000; // 2秒

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 检查是否是第一次启动应用
                boolean isFirstStart = getSharedPreferences("PREFS", MODE_PRIVATE)
                        .getBoolean("is_first_start", true);

                if (isFirstStart) {
                    // 如果是第一次启动，跳转到引导页
                    redirectToGuidePage();
                } else {
                    // 否则，跳转到主页面
                    redirectToHomePage();
                }

                // 将is_first_start标记为false
                getSharedPreferences("PREFS", MODE_PRIVATE).edit()
                        .putBoolean("is_first_start", false).apply();

                // 完成启动界面的工作后，finish这个Activity
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    @Override
    protected void initLayout() {

    }

    @Override
    protected void initView() {

    }

    private void redirectToGuidePage() {
        Intent intent = new Intent(SplashActivity.this, GuideActivity.class);
        startActivity(intent);
    }

    private void redirectToHomePage() {
        if (MessageDao.isLoginBeanSaved()){
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
        }else {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }
}