package com.wzr.food.logic.network.service;

import com.wzr.food.logic.model.PullBean;

import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GetPullService {
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("live/getPull")
    Call<PullBean> getPull(@Query("cid") String cid);
}
