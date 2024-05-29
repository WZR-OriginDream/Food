package com.wzr.food.logic.network.service;

import com.wzr.food.logic.model.TypeArticleBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface UserArticleService {
    @Headers("Accept: application/json")
    @GET("post/getUserArticle/{id}")
    Call<TypeArticleBean> getUserArticle(@Path("id") int id);
}
