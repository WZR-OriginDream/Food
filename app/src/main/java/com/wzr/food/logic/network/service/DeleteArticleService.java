package com.wzr.food.logic.network.service;

import com.wzr.food.logic.model.CallBackBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface DeleteArticleService {
    @Headers("Accept: application/json")
    @GET("post/deleteArticle/{id}")
    Call<CallBackBean> deleteArticle(@Path("id") int id);
}
