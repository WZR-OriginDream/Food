package com.wzr.food.logic.network.service;

import com.wzr.food.logic.model.MyCommentBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface MyCommentService {
    @Headers("Accept: application/json")
    @GET("comment/getMyComment/{userId}")
    Call<MyCommentBean> getMyComment(@Path("userId") int userId);
}
