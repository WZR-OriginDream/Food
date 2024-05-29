package com.wzr.food.logic.network.service;

import com.wzr.food.logic.model.AddCommentBean;
import com.wzr.food.logic.model.MyCommentBean;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface AddCommentService {
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("comment/add")
    Call<MyCommentBean> addComment(@Body AddCommentBean addCommentBean);
}
