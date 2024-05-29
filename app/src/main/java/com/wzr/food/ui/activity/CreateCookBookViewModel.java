package com.wzr.food.ui.activity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.wzr.food.logic.model.CreateArticleBean;
import com.wzr.food.logic.model.CreateResponseBean;
import com.wzr.food.repository.Repository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateCookBookViewModel extends ViewModel {
    private MutableLiveData<CreateResponseBean> createResponseLiveData;

    public CreateCookBookViewModel() {
        createResponseLiveData = new MutableLiveData<>();
    }

    public LiveData<CreateResponseBean> getCreateResponseLiveData(CreateArticleBean createArticleBean) {
        createArticle(createArticleBean);
        return createResponseLiveData;
    }

    public void createArticle(CreateArticleBean createArticleBean) {
        Repository.getCreateArticleServive().createArticle(createArticleBean).enqueue(new Callback<CreateResponseBean>() {
            @Override
            public void onResponse(Call<CreateResponseBean> call, Response<CreateResponseBean> response) {
                CreateResponseBean createResponseBean = response.body();
                createResponseLiveData.postValue(createResponseBean);
            }

            @Override
            public void onFailure(Call<CreateResponseBean> call, Throwable t) {

            }
        });
    }


}
