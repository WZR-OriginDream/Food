package com.wzr.food.logic.network.service;

import com.wzr.food.logic.model.BriefsBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface BriefsService {
    @Headers("Accept: application/json")
    @GET("post/getBriefs/{userId}/{limit}")
    Call<BriefsBean> getBriefs(@Path("userId") int userId, @Path("limit") int limit);
}
