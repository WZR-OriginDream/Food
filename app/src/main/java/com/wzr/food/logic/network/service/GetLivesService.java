package com.wzr.food.logic.network.service;

import com.wzr.food.logic.model.LivesBean;

import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface GetLivesService {
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("live/getLives")
    Call<LivesBean> getLives();
}
