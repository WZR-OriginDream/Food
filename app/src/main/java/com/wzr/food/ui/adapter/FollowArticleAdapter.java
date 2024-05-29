package com.wzr.food.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wzr.food.FoodApplication;
import com.wzr.food.R;
import com.wzr.food.logic.model.PostActionListBean;
import com.wzr.food.ui.activity.FoodActivity;

import java.util.List;

public class FollowArticleAdapter extends RecyclerView.Adapter<FollowArticleAdapter.ViewHolder> {

    private List<PostActionListBean.Obj> mfoodList;
    private Context mcontext;

    public FollowArticleAdapter(Context context, List<PostActionListBean.Obj> foodList) {
        mfoodList=foodList;
        mcontext=context;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView userName,foodContent;
        ImageView foodImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userName=itemView.findViewById(R.id.item_name);
            foodImage=itemView.findViewById(R.id.item_foodimage);
            foodContent=itemView.findViewById(R.id.item_content);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.follow_item,parent,false);
        ViewHolder holder=new ViewHolder(view);

        holder.foodImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                PostActionListBean.Obj food=mfoodList.get(position);
                Intent intent=new Intent(mcontext, FoodActivity.class);
                Bundle bundle=new Bundle();
                bundle.putInt("id",food.getPost().getId());
                intent.putExtras(bundle);
                mcontext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PostActionListBean.Obj food=mfoodList.get(position);
        holder.userName.setText(food.getPost().getNickname());
        holder.foodContent.setText(food.getPost().getContent());
        Glide.with(FoodApplication.getContext()).load(food.getPost().getImage()).into(holder.foodImage);
    }

    @Override
    public int getItemCount() {
        return mfoodList.size();
    }

}

