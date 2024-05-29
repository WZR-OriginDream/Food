package com.wzr.food.ui.fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.wzr.food.logic.model.CallBackBean;
import com.wzr.food.logic.model.TypeArticleBean;
import com.wzr.food.repository.Repository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CookBookViewModel extends ViewModel {
    private MutableLiveData<TypeArticleBean> userArticleLiveData;
    private MutableLiveData<CallBackBean> callBackLiveData;

    public CookBookViewModel() {
        userArticleLiveData = new MutableLiveData<>();
        callBackLiveData = new MutableLiveData<>();
    }

    public LiveData<TypeArticleBean> getUserArticleLiveData(int id) {
        getUserArticle(id);
        return userArticleLiveData;
    }

    public LiveData<CallBackBean> getCallBackLiveData(int id) {
        deleteArticle(id);
        return callBackLiveData;
    }

    public void getUserArticle(int id) {
        Repository.getUserArticleService().getUserArticle(id).enqueue(new Callback<TypeArticleBean>() {
            @Override
            public void onResponse(Call<TypeArticleBean> call, Response<TypeArticleBean> response) {
                TypeArticleBean userArticleBean = response.body();
                userArticleLiveData.postValue(userArticleBean);
            }

            @Override
            public void onFailure(Call<TypeArticleBean> call, Throwable t) {

            }
        });
    }

    public void deleteArticle(int id) {
        Repository.getDeleteArticleService().deleteArticle(id).enqueue(new Callback<CallBackBean>() {
            @Override
            public void onResponse(Call<CallBackBean> call, Response<CallBackBean> response) {
                CallBackBean callBackBean = response.body();
                callBackLiveData.postValue(callBackBean);
            }

            @Override
            public void onFailure(Call<CallBackBean> call, Throwable t) {

            }
        });
    }
}