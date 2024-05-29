package com.wzr.food.ui.fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.wzr.food.logic.model.AttentionsBean;
import com.wzr.food.logic.model.UserActionListBean;
import com.wzr.food.repository.Repository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FansViewModel extends ViewModel {
    private MutableLiveData<AttentionsBean> fansLiveData;
    private MutableLiveData<UserActionListBean> userAction2ListLiveData;

    public FansViewModel() {

        fansLiveData = new MutableLiveData<>();
        userAction2ListLiveData = new MutableLiveData<>();
    }

    public LiveData<UserActionListBean> getUserAction2ListLiveData(String action,  int entityType,int entityId) {
        getFollower(action,entityType,entityId);
        return userAction2ListLiveData;
    }

    private void getFollower(String action, int entityType,int entityId) {
        Repository.userAction2ListService().userAction2List(action,entityType,entityId).enqueue(new Callback<UserActionListBean>() {
            @Override
            public void onResponse(Call<UserActionListBean> call, Response<UserActionListBean> response) {
                UserActionListBean body = response.body();
                userAction2ListLiveData.postValue(body);
            }

            @Override
            public void onFailure(Call<UserActionListBean> call, Throwable t) {

            }
        });
    }



    public LiveData<AttentionsBean> getFansLiveData(int id) {
        getFans(id);
        return fansLiveData;
    }

    public void getFans(int id) {
        Repository.getFansService().getFans(id).enqueue(new Callback<AttentionsBean>() {
            @Override
            public void onResponse(Call<AttentionsBean> call, Response<AttentionsBean> response) {
                AttentionsBean attentionsBean= response.body();
                fansLiveData.postValue(attentionsBean);
            }

            @Override
            public void onFailure(Call<AttentionsBean> call, Throwable t) {

            }
        });
    }
}
