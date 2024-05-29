package com.wzr.food.logic.network.service;

import com.wzr.food.logic.model.CreateLiveBean;

import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CreateLiveService {
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("live/createLive")
    Call<CreateLiveBean> createLive(@Query("liveName") String liveName);
}
