package com.wzr.food.logic.network.service;

import com.wzr.food.logic.model.PostActionListBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface PostActionListService {
    @Headers("Accept: application/json")
    @GET("action/user/{action}/{userId}/{entityType}")
    Call<PostActionListBean> postActionList(@Path("action") String action,
                                            @Path("userId") int userId,
                                            @Path("entityType") int entityType);
}
