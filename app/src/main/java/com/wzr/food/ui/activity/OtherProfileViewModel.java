package com.wzr.food.ui.activity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.wzr.food.logic.model.ProfileBean;
import com.wzr.food.repository.Repository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtherProfileViewModel extends ViewModel {
    private MutableLiveData<ProfileBean> profileLiveData;

    public OtherProfileViewModel(){
        profileLiveData=new MutableLiveData<>();
    }

    public LiveData<ProfileBean> getProfileLiveData(int userId){
        getProfile(userId);
        return profileLiveData;
    }

    private void getProfile(int userId) {
        Repository.profileService().profile(userId).enqueue(new Callback<ProfileBean>() {
            @Override
            public void onResponse(Call<ProfileBean> call, Response<ProfileBean> response) {
                ProfileBean body = response.body();
                profileLiveData.postValue(body);
            }

            @Override
            public void onFailure(Call<ProfileBean> call, Throwable t) {

            }
        });
    }

}
