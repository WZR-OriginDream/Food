package com.wzr.food.logic.network.service;

import com.wzr.food.logic.model.ImageBean;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface PhotoService {
    @Multipart
//    @Headers({"Content-Type: multipart/form-data","Accept: text/plain"})
    @POST("oss/upload")
    Call<ImageBean> uploadPhoto(@Part MultipartBody.Part file);
}
