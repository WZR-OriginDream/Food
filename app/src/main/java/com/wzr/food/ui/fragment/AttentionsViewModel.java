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

public class AttentionsViewModel extends ViewModel {
    private MutableLiveData<AttentionsBean> attentionsLiveData;
    private MutableLiveData<UserActionListBean> userActionListLiveData;

    public AttentionsViewModel() {
        attentionsLiveData = new MutableLiveData<>();
        userActionListLiveData = new MutableLiveData<>();
    }

    public LiveData<AttentionsBean> getAttentionsLiveData(int id) {
        getAttentions(id);
        return attentionsLiveData;
    }

    public LiveData<UserActionListBean> getUserActionListLiveData(String action, int userId, int entityType) {
        getFollowee(action, userId, entityType);
        return userActionListLiveData;
    }

    private void getFollowee(String action, int userId, int entityType) {
        Repository.userActionListService().userActionList(action, userId, entityType).enqueue(new Callback<UserActionListBean>() {
            @Override
            public void onResponse(Call<UserActionListBean> call, Response<UserActionListBean> response) {
                UserActionListBean body = response.body();
                userActionListLiveData.postValue(body);
            }

            @Override
            public void onFailure(Call<UserActionListBean> call, Throwable t) {

            }
        });
    }

    public void getAttentions(int id) {
        Repository.getAttentionsService().getAttentions(id).enqueue(new Callback<AttentionsBean>() {
            @Override
            public void onResponse(Call<AttentionsBean> call, Response<AttentionsBean> response) {
                AttentionsBean attentionsBean= response.body();
                attentionsLiveData.postValue(attentionsBean);
            }

            @Override
            public void onFailure(Call<AttentionsBean> call, Throwable t) {

            }
        });
    }
}
