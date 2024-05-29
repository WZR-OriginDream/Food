package com.wzr.food;

import android.app.Application;
import android.content.Context;

public class FoodApplication extends Application {

    private static FoodApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static FoodApplication getInstance() {
        return instance;
    }

    public static Context getContext() {
        return instance.getApplicationContext();
    }
}
