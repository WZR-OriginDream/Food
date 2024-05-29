package com.wzr.food.ui.fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.wzr.food.logic.model.BriefsBean;
import com.wzr.food.repository.Repository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PushViewModel extends ViewModel {
    private MutableLiveData<BriefsBean> recommendLiveData;

    public PushViewModel() {
        recommendLiveData = new MutableLiveData<>();
    }

    public LiveData<BriefsBean> getRecommendLiveData(int userId) {
        getData(userId);
        return recommendLiveData;
    }

    public void getData(int userId) {
        Repository.recommendService().recommend(userId).enqueue(new Callback<BriefsBean>() {
            @Override
            public void onResponse(Call<BriefsBean> call, Response<BriefsBean> response) {
                BriefsBean briefsBean = response.body();
                recommendLiveData.postValue(briefsBean);
            }

            @Override
            public void onFailure(Call<BriefsBean> call, Throwable t) {

            }
        });
    }
}