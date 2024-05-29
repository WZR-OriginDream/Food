package com.wzr.food.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wzr.food.FoodApplication;
import com.wzr.food.R;
import com.wzr.food.logic.model.CallBackBean;
import com.wzr.food.logic.model.TypeArticleBean;
import com.wzr.food.repository.Repository;
import com.wzr.food.ui.activity.FoodActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserArticleAdapter extends RecyclerView.Adapter<UserArticleAdapter.ViewHolder> {

    private List<TypeArticleBean.Obj> mfoodList;
    private Context mcontext;
    private Boolean misDelete;

    public UserArticleAdapter(Context context, List<TypeArticleBean.Obj> foodList, Boolean isDelete) {
        mfoodList = foodList;
        mcontext = context;
        misDelete = isDelete;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView foodName, foodContent;
        ImageView foodImage, delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.item_foodname);
            foodImage = itemView.findViewById(R.id.item_foodimage);
            delete = itemView.findViewById(R.id.iv_delete);
            foodContent = itemView.findViewById(R.id.item_content);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.userarticle_item, parent, false);
        ViewHolder holder = new ViewHolder(view);

        //跳转食谱详情页
        holder.foodImage.setOnClickListener(v -> {
            int position = holder.getAdapterPosition();
            TypeArticleBean.Obj food = mfoodList.get(position);
            Intent intent = new Intent(mcontext, FoodActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("id", food.getPost().getId());
            intent.putExtras(bundle);
            mcontext.startActivity(intent);
        });

        //删除食谱
        holder.delete.setOnClickListener(v -> {
            int position = holder.getAdapterPosition();
            TypeArticleBean.Obj food = mfoodList.get(position);
            Repository.getDeleteArticleService().deleteArticle(food.getPost().getId()).enqueue(new Callback<CallBackBean>() {
                @Override
                public void onResponse(Call<CallBackBean> call, Response<CallBackBean> response) {
                    CallBackBean callBackBean = response.body();
                    if (callBackBean.getCode() == 200) {
                        if (mfoodList != null && mfoodList.size() > 0) {
                            mfoodList.remove(position);
                            notifyDataSetChanged();
                            Toast.makeText(mcontext, "删除成功", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<CallBackBean> call, Throwable t) {

                }
            });

        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TypeArticleBean.Obj food = mfoodList.get(position);

        holder.foodName.setText(food.getPost().getTitle());
        holder.foodContent.setText(food.getPost().getContent());
        Glide.with(FoodApplication.getContext()).load(food.getPost().getImage()).into(holder.foodImage);

        if (misDelete) {
            holder.delete.setVisibility(View.VISIBLE);
        } else {
            holder.delete.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return mfoodList.size();
    }

}

