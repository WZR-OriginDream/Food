package com.wzr.food.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
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
import com.wzr.food.logic.model.ArticleBean;

import java.util.List;

public class MeasuresAdapter extends RecyclerView.Adapter<MeasuresAdapter.ViewHolder> {

    private List<ArticleBean.Obj.Measures> mmeasuresList;
    private Context mcontext;

    public MeasuresAdapter(Context context, List<ArticleBean.Obj.Measures> measuresList){
        mmeasuresList=measuresList;
        mcontext=context;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView number;
        TextView content;
        ImageView picture;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            number=itemView.findViewById(R.id.tv_num);
            content=itemView.findViewById(R.id.tv_measure_content);
            picture=itemView.findViewById(R.id.iv_pic);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.measures_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ArticleBean.Obj.Measures measures=mmeasuresList.get(position);
        holder.number.setText("步骤 "+measures.getNumber());
        holder.content.setText(measures.getContent());
        Glide.with(FoodApplication.getContext()).load(measures.getPicture()).into(holder.picture);
    }

    @Override
    public int getItemCount() {
        return mmeasuresList.size();
    }

}