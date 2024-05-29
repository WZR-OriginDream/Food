package com.wzr.food.ui.fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.wzr.food.logic.model.BriefsBean;
import com.wzr.food.repository.Repository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecomendViewModel extends ViewModel {
    private MutableLiveData<BriefsBean> briefsLiveData;

    public RecomendViewModel() {
        briefsLiveData = new MutableLiveData<>();
    }

    public LiveData<BriefsBean> getBriefsLiveData(int userId, int limit) {
        getData(userId, limit);
        return briefsLiveData;
    }

    public void getData(int userId, int limit) {
        Repository.getBriefsService().getBriefs(userId, limit).enqueue(new Callback<BriefsBean>() {
            @Override
            public void onResponse(Call<BriefsBean> call, Response<BriefsBean> response) {
                BriefsBean briefsBean = response.body();
                briefsLiveData.postValue(briefsBean);
            }

            @Override
            public void onFailure(Call<BriefsBean> call, Throwable t) {

            }
        });
    }
}
