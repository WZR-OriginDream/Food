package com.wzr.food.logic.network.service;

import com.wzr.food.logic.model.ArticleBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface ArticleService {
    @Headers("Accept: application/json")
    @GET("post/getArticle/{id}")
    Call<ArticleBean> getArticle(@Path("id") int id);
}
