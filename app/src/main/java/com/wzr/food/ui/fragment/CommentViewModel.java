package com.wzr.food.ui.fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.wzr.food.logic.model.MyCommentBean;
import com.wzr.food.repository.Repository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentViewModel extends ViewModel {
    private MutableLiveData<MyCommentBean> myCommentBeanMutableLiveData;

    public CommentViewModel() {
        myCommentBeanMutableLiveData = new MutableLiveData<>();
    }

    public LiveData<MyCommentBean> getMyCommentBeanMutableLiveData(int userId) {
        getMyComment(userId);
        return myCommentBeanMutableLiveData;
    }

    private void getMyComment(int userId) {
        Repository.myCommentService().getMyComment(userId).enqueue(new Callback<MyCommentBean>() {
            @Override
            public void onResponse(Call<MyCommentBean> call, Response<MyCommentBean> response) {
                MyCommentBean body = response.body();
                myCommentBeanMutableLiveData.postValue(body);
            }

            @Override
            public void onFailure(Call<MyCommentBean> call, Throwable t) {

            }
        });
    }

}