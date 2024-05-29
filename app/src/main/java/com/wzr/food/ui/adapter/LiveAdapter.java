package com.wzr.food.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
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
import com.wzr.food.logic.model.LivesBean;
import com.wzr.food.ui.activity.FoodActivity;

import java.util.List;

public class LiveAdapter extends RecyclerView.Adapter<LiveAdapter.ViewHolder> {

    private List<LivesBean.Obj.Ret.List1> mfoodList;
    private Context mcontext;
    private List<String> mpullUrl;

    public LiveAdapter(Context context, List<LivesBean.Obj.Ret.List1> foodList,List<String> pullUrl){
        mfoodList=foodList;
        mcontext=context;
        mpullUrl=pullUrl;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.live_item,parent,false);
        ViewHolder holder=new ViewHolder(view);

        holder.foodImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                LivesBean.Obj.Ret.List1 food=mfoodList.get(position);
                Intent intent=new Intent(mcontext, FoodActivity.class);

                intent.putExtra("pullUrl",mpullUrl.get(position));
                Log.d("温宗荣1",mpullUrl.get(position));

                mcontext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LivesBean.Obj.Ret.List1 food=mfoodList.get(position);
        holder.userName.setText(food.getFilename());

        if (position==1){
            Glide.with(FoodApplication.getContext()).load(R.drawable.page1).into(holder.foodImage);
            holder.foodContent.setText("传承中华传统美食，回味悠长");
        }
        if (position==2){
            Glide.with(FoodApplication.getContext()).load(R.drawable.page2).into(holder.foodImage);
            holder.foodContent.setText("东坡肉：一辈子的最爱");
        }
        if (position==3){
            Glide.with(FoodApplication.getContext()).load(R.drawable.page3).into(holder.foodImage);
            holder.foodContent.setText("老少咸宜，一款脆口的甜品");
        }
        if (position==4){
            Glide.with(FoodApplication.getContext()).load(R.drawable.page4).into(holder.foodImage);
            holder.foodContent.setText("又逢端午，粽子飘香");
        }

    }

    @Override
    public int getItemCount() {
        return mfoodList.size();
    }

}

