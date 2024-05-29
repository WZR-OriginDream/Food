package com.wzr.food.logic.network.service;

import com.wzr.food.logic.model.LoginBean;

import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LoginService {
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("user/login")
    Call<LoginBean> login(@Query("username") String username, @Query("password") String password);
}
