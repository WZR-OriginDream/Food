package com.wzr.food.logic.network.service;

import com.wzr.food.logic.model.CreateArticleBean;
import com.wzr.food.logic.model.CreateResponseBean;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface CreateArticleService {
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("post/postArticle")
    Call<CreateResponseBean> createArticle(@Body CreateArticleBean createArticleBean);
}
