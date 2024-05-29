package com.wzr.food.logic.network.service;

import com.wzr.food.logic.model.UserActionListBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface UserActionListService {
    @Headers("Accept: application/json")
    @GET("action/user/{action}/{userId}/{entityType}")
    Call<UserActionListBean> userActionList(@Path("action") String action,
                                            @Path("userId") int userId,
                                            @Path("entityType") int entityType);
}
