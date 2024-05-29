package com.wzr.food.logic.network.service;

import com.wzr.food.logic.model.CommentBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface CommentService {
    @Headers("Accept: application/json")
    @GET("comment/getPostComment/{id}")
    Call<CommentBean> getComment(@Path("id") int id);
}
