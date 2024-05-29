package com.wzr.food.ui.activity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.wzr.food.logic.model.BriefsBean;
import com.wzr.food.logic.model.TypeArticleBean;
import com.wzr.food.repository.Repository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TypeFoodViewModel extends ViewModel {
    private MutableLiveData<TypeArticleBean> typeArticleLiveData;
    private MutableLiveData<BriefsBean> briefsLiveData;

    public TypeFoodViewModel(){
        typeArticleLiveData=new MutableLiveData<>();
        briefsLiveData=new MutableLiveData<>();
    }
    public LiveData<TypeArticleBean> getTypeArticleLiveData(int id){
        getTypeArticle(id);
        return typeArticleLiveData;
    }
    public LiveData<BriefsBean> getSearchLiveData(String title){
        getSearch(title);
        return briefsLiveData;
    }
    public void getTypeArticle(int id){
        Repository.getTypeArticleServie().getTypeArticle(id).enqueue(new Callback<TypeArticleBean>() {
            @Override
            public void onResponse(Call<TypeArticleBean> call, Response<TypeArticleBean> response) {
                TypeArticleBean typeArticleBean= response.body();
                typeArticleLiveData.postValue(typeArticleBean);
            }

            @Override
            public void onFailure(Call<TypeArticleBean> call, Throwable t) {

            }
        });
    }
    public void getSearch(String title){
        Repository.getSearchService().getSearch(title).enqueue(new Callback<BriefsBean>() {
            @Override
            public void onResponse(Call<BriefsBean> call, Response<BriefsBean> response) {
                BriefsBean briefsBean= response.body();
                briefsLiveData.postValue(briefsBean);
            }

            @Override
            public void onFailure(Call<BriefsBean> call, Throwable t) {

            }
        });
    }

}
