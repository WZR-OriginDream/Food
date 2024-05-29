package com.wzr.food.logic.network.service;

import com.wzr.food.logic.model.UserActionListBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface UserAction2ListService {
    @Headers("Accept: application/json")
    @GET("action/entity/{action}/{entityType}/{entityId}")
    Call<UserActionListBean> userAction2List(@Path("action") String action,
                                            @Path("entityType") int entityType,
                                            @Path("entityId") int entityId);
}
