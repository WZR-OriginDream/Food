package com.wzr.food.logic.dao;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.wzr.food.FoodApplication;
import com.wzr.food.logic.model.LoginBean;

public class MessageDao {

    private MessageDao() {

    }

    public static MessageDao getInstance() {
        return new MessageDao();
    }

    public static Boolean isLoginBeanSaved() {
        return getSharedPreferences().contains("login");
    }

    public static Boolean isBgPathSaved() {
        return getSharedPreferences().contains("bgpath");
    }

    public static void saveBgPath(String bgPath) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString("bgpath", bgPath);
        editor.apply();
    }

    public static String getSaveBgPath() {
        return getSharedPreferences().getString("bgpath", "");
    }

    public static void saveLoginBean(LoginBean loginBean) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString("login", new Gson().toJson(loginBean));
        editor.apply();
    }

    public static LoginBean getSaveLoginBean() {
        return new Gson().fromJson(getSharedPreferences().getString("login", ""), LoginBean.class);
    }

    public static void removeLoginBean() {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.remove("login"); // "loginBean"是您存储登录信息时使用的键
        editor.apply(); // 提交更改
    }


    static SharedPreferences getSharedPreferences() {
        return FoodApplication.getContext().getSharedPreferences("food", Context.MODE_PRIVATE);
    }
}
