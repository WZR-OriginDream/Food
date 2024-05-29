package com.wzr.food.logic.network.service;

import com.wzr.food.logic.model.ProfileBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface ProfileService {
    @Headers("Accept: application/json")
    @GET("user/profile/{userId}")
    Call<ProfileBean> profile(@Path("userId") int userId);
}
