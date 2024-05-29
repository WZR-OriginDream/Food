package com.wzr.food.logic.network.service;

import com.wzr.food.logic.model.BriefsBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface RecommendService {
    @Headers("Accept: application/json")
    @GET("post/recommend/{userId}")
    Call<BriefsBean> recommend(@Path("userId") int userId);
}
