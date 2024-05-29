package com.wzr.food.logic.network.service;

import com.wzr.food.logic.model.CallBackBean;
import com.wzr.food.logic.model.RegisterBean;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RegisterService {
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("user/register")
    Call<CallBackBean> register(@Body RegisterBean registerBean);
}
