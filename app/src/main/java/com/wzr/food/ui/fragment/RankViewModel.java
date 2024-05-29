package com.wzr.food.ui.fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.wzr.food.logic.model.BriefsBean;
import com.wzr.food.repository.Repository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RankViewModel extends ViewModel {
    private MutableLiveData<BriefsBean> rankLiveData;

    public RankViewModel() {
        rankLiveData = new MutableLiveData<>();
    }

    public LiveData<BriefsBean> getRecommendLiveData() {
        getData();
        return rankLiveData;
    }

    public void getData() {
        Repository.rankService().rank().enqueue(new Callback<BriefsBean>() {
            @Override
            public void onResponse(Call<BriefsBean> call, Response<BriefsBean> response) {
                BriefsBean briefsBean = response.body();
                rankLiveData.postValue(briefsBean);
            }

            @Override
            public void onFailure(Call<BriefsBean> call, Throwable t) {

            }
        });
    }
}