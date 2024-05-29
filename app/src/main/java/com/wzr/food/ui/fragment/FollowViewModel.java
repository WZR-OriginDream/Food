package com.wzr.food.ui.fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.wzr.food.logic.model.PostActionListBean;
import com.wzr.food.logic.model.TypeArticleBean;
import com.wzr.food.repository.Repository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FollowViewModel extends ViewModel {
    private MutableLiveData<TypeArticleBean> typeArticleLiveData;
    private MutableLiveData<PostActionListBean> postActionListLiveData;

    public FollowViewModel() {
        typeArticleLiveData = new MutableLiveData<>();
        postActionListLiveData = new MutableLiveData<>();
    }

    public LiveData<TypeArticleBean> getTypeArticleLiveData(int id) {
        getTypeArticle(id);
        return typeArticleLiveData;
    }

    public LiveData<PostActionListBean> getPostActionListLiveData(String action, int userId, int entityType) {
        getCollectArticle(action, userId, entityType);
        return postActionListLiveData;
    }

    private void getCollectArticle(String action, int userId, int entityType) {
        Repository.postActionListService().postActionList(action, userId, entityType).enqueue(new Callback<PostActionListBean>() {
            @Override
            public void onResponse(Call<PostActionListBean> call, Response<PostActionListBean> response) {
                PostActionListBean body = response.body();
                postActionListLiveData.postValue(body);
            }

            @Override
            public void onFailure(Call<PostActionListBean> call, Throwable t) {

            }
        });
    }

    public void getTypeArticle(int id) {
        Repository.getTypeArticleServie().getTypeArticle(id).enqueue(new Callback<TypeArticleBean>() {
            @Override
            public void onResponse(Call<TypeArticleBean> call, Response<TypeArticleBean> response) {
                TypeArticleBean typeArticleBean = response.body();
                typeArticleLiveData.postValue(typeArticleBean);
            }

            @Override
            public void onFailure(Call<TypeArticleBean> call, Throwable t) {
            }
        });
    }
}