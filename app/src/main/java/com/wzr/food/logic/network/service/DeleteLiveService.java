package com.wzr.food.logic.network.service;

import com.wzr.food.logic.model.CallBackBean;

import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface DeleteLiveService {
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("live/deleteLive")
    Call<CallBackBean> deleteLive(@Query("cid") String cid);
}
