package com.wzr.food.ui.fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.wzr.food.logic.model.CreateLiveBean;
import com.wzr.food.repository.Repository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LiveViewModel extends ViewModel {
    private MutableLiveData<CreateLiveBean> liveLiveData;
    public LiveViewModel(){
        liveLiveData=new MutableLiveData<>();
    }
    public LiveData<CreateLiveBean> getLiveLiveData(String liveName){
        createLive(liveName);
        return liveLiveData;
    }

    private void createLive(String liveName) {
        Repository.getCreateLiveService().createLive(liveName).enqueue(new Callback<CreateLiveBean>() {
            @Override
            public void onResponse(Call<CreateLiveBean> call, Response<CreateLiveBean> response) {
                CreateLiveBean createLiveBean= response.body();
                liveLiveData.postValue(createLiveBean);

            }

            @Override
            public void onFailure(Call<CreateLiveBean> call, Throwable t) {

            }
        });
    }

}