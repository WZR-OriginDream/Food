package com.wzr.food.logic.network.service;

import com.wzr.food.logic.model.BriefsBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface SearchService {
    @Headers("Accept: application/json")
    @GET("post/search/{title}")
    Call<BriefsBean> getSearch(@Path("title") String title);
}
