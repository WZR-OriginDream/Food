package com.wzr.food.logic.network.service;

import com.wzr.food.logic.model.TypeArticleBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface TypeArticleService {
    @Headers("Accept: application/json")
    @GET("post/getTypeArticle/{type}")
    Call<TypeArticleBean> getTypeArticle(@Path("type") int type);
}
