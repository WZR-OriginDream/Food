package com.wzr.food.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.wzr.food.R;
import com.wzr.food.databinding.LiveFragmentBinding;
import com.wzr.food.logic.model.LivesBean;
import com.wzr.food.logic.model.PullBean;
import com.wzr.food.repository.Repository;
import com.wzr.food.ui.adapter.LiveAdapter;
import com.wzr.food.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LiveFragment extends BaseFragment {
    private LiveFragmentBinding binding;

    private String cid;
    private RecyclerView rvlives;
    private SwipeRefreshLayout swipeRefreshLayout;
    private LiveAdapter liveAdapter;
    private GridLayoutManager gridLayoutManager;

    private List<String> pullUrl=new ArrayList<>();

    public static LiveFragment newInstance() {
        return new LiveFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected View initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = LiveFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    protected void initView() {
        rvlives= binding.rvLives;
        swipeRefreshLayout= binding.swipeRefresh;
        swipeRefreshLayout.setColorSchemeResources(R.color.light_red);
    }

    @Override
    protected void initEvent() {

        show();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                show();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void show(){
        pullUrl.clear();
        Repository.getLivesService().getLives().enqueue(new Callback<LivesBean>() {
            @Override
            public void onResponse(Call<LivesBean> call, Response<LivesBean> response) {
                LivesBean livesBean= response.body();
                gridLayoutManager = new GridLayoutManager(getContext(),2);
                rvlives.setLayoutManager(gridLayoutManager);
                for (int i=0;i<livesBean.getObj().getRet().getList().size();i++){
                    cid=livesBean.getObj().getRet().getList().get(i).getCid();
                    Repository.getPullService().getPull(cid).enqueue(new Callback<PullBean>() {
                        @Override
                        public void onResponse(Call<PullBean> call, Response<PullBean> response) {
                            PullBean pullBean=response.body();
                            Log.d("温宗荣path1",pullBean.getObj().getRet().getHttpPullUrl());
                            pullUrl.add(pullBean.getObj().getRet().getHttpPullUrl());
                        }

                        @Override
                        public void onFailure(Call<PullBean> call, Throwable t) {

                        }
                    });

                }

                liveAdapter = new LiveAdapter(getContext(), livesBean.getObj().getRet().getList(),pullUrl);
                rvlives.setAdapter(liveAdapter);



            }

            @Override
            public void onFailure(Call<LivesBean> call, Throwable t) {

            }
        });
    }
}