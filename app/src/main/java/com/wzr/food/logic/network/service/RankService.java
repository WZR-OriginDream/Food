package com.wzr.food.logic.network.service;

import com.wzr.food.logic.model.BriefsBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface RankService {
    @Headers("Accept: application/json")
    @GET("post/rank")
    Call<BriefsBean> rank();
}
