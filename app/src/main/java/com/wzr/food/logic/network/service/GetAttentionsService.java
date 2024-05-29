package com.wzr.food.logic.network.service;

import com.wzr.food.logic.model.AttentionsBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface GetAttentionsService {
    @Headers("Accept: application/json")
    @GET("attention/getAttentions/{id}")
    Call<AttentionsBean> getAttentions(@Path("id") int id);
}
