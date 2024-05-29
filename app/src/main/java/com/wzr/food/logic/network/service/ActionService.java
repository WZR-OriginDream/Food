package com.wzr.food.logic.network.service;

import com.wzr.food.logic.model.ActionBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface ActionService {
    @Headers("Accept: application/json")
    @GET("action/{action}/{entityType}/{entityId}/{entityUserId}")
    Call<ActionBean> action(@Path("action") String action,
                            @Path("entityType") int entityType,
                            @Path("entityId") int entityId,
                            @Path("entityUserId") int entityUserId);
}
