package com.wzr.food.ui.activity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.wzr.food.logic.model.ActionBean;
import com.wzr.food.logic.model.ActionStatusBean;
import com.wzr.food.logic.model.AddCommentBean;
import com.wzr.food.logic.model.ArticleBean;
import com.wzr.food.logic.model.CommentBean;
import com.wzr.food.logic.model.MyCommentBean;
import com.wzr.food.repository.Repository;
import com.wzr.food.ui.custom.SingleLiveEvent;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodViewModel extends ViewModel {
    private MutableLiveData<CommentBean> commentLiveData;
    private MutableLiveData<MyCommentBean> myCommentLiveData;
    private MutableLiveData<ArticleBean> articleLiveData;
    private SingleLiveEvent<ActionBean> actionLiveData;
    private MutableLiveData<ActionStatusBean> actionStatusLiveData;

    public FoodViewModel() {
        commentLiveData = new MutableLiveData<>();
        articleLiveData = new MutableLiveData<>();
        myCommentLiveData = new MutableLiveData<>();
        actionLiveData = new SingleLiveEvent<>();
        actionStatusLiveData = new MutableLiveData<>();
    }

    public LiveData<CommentBean> getCommentLiveData(int id) {
        getComment(id);
        return commentLiveData;
    }

    public LiveData<ArticleBean> getArticleLiveData(int id) {
        getArticle(id);
        return articleLiveData;
    }

    public SingleLiveEvent<ActionBean> getActionLiveData(String action, int entityType, int entityId, int entityUserId) {
        getAction(action, entityType, entityId, entityUserId);
        return actionLiveData;
    }

    public LiveData<ActionStatusBean> getActionStatusLiveData(int entityType, int entityId, int entityUserId) {
        getActionStatus(entityType, entityId, entityUserId);
        return actionStatusLiveData;
    }

    private void getActionStatus(int entityType, int entityId, int entityUserId) {
        Repository.getActionStatusService().actionStatus(entityType,entityId,entityUserId).enqueue(new Callback<ActionStatusBean>() {
            @Override
            public void onResponse(Call<ActionStatusBean> call, Response<ActionStatusBean> response) {
                ActionStatusBean actionStatusBean = response.body();
                actionStatusLiveData.postValue(actionStatusBean);
            }

            @Override
            public void onFailure(Call<ActionStatusBean> call, Throwable t) {

            }
        });
    }


    public LiveData<MyCommentBean> addCommentLiveData(AddCommentBean addCommentBean) {
        addComment(addCommentBean);
        return myCommentLiveData;
    }

    public void getComment(int id) {
        Repository.getCommentService().getComment(id).enqueue(new Callback<CommentBean>() {
            @Override
            public void onResponse(Call<CommentBean> call, Response<CommentBean> response) {
                CommentBean commentBean = response.body();
                commentLiveData.postValue(commentBean);
            }

            @Override
            public void onFailure(Call<CommentBean> call, Throwable t) {

            }
        });
    }

    public void getAction(String action, int entityType, int entityId, int entityUserId) {
        Repository.getActionService().action(action, entityType, entityId, entityUserId).enqueue(new Callback<ActionBean>() {
            @Override
            public void onResponse(Call<ActionBean> call, Response<ActionBean> response) {
                ActionBean actionBean = response.body();
                actionLiveData.postValue(actionBean);
            }

            @Override
            public void onFailure(Call<ActionBean> call, Throwable t) {

            }
        });
    }

    public void getArticle(int id) {
        Repository.getArticleService().getArticle(id).enqueue(new Callback<ArticleBean>() {
            @Override
            public void onResponse(Call<ArticleBean> call, Response<ArticleBean> response) {
                ArticleBean articleBean = response.body();
                articleLiveData.postValue(articleBean);
            }

            @Override
            public void onFailure(Call<ArticleBean> call, Throwable t) {

            }
        });
    }

    public void addComment(AddCommentBean addCommentBean) {
        Repository.getAddCommentService().addComment(addCommentBean).enqueue(new Callback<MyCommentBean>() {
            @Override
            public void onResponse(Call<MyCommentBean> call, Response<MyCommentBean> response) {
                MyCommentBean myCommentBean = response.body();
                myCommentLiveData.postValue(myCommentBean);
            }

            @Override
            public void onFailure(Call<MyCommentBean> call, Throwable t) {

            }
        });
    }
}
