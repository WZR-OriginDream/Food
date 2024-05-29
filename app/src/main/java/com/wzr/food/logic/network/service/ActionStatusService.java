package com.wzr.food.logic.network.service;

import com.wzr.food.logic.model.ActionStatusBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface ActionStatusService {
    @Headers("Accept: application/json")
    @GET("action/status/{entityType}/{entityId}/{entityUserId}")
    Call<ActionStatusBean> actionStatus(@Path("entityType") int entityType,
                                  @Path("entityId") int entityId,
                                  @Path("entityUserId") int entityUserId);
}
