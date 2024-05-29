package com.wzr.food.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.wzr.food.FoodApplication;
import com.wzr.food.R;
import com.wzr.food.logic.model.ArticleBean;

import java.util.List;

public class MeasureAdapter extends ArrayAdapter<ArticleBean.Obj.Measures> {
    private int resourseId;
    public MeasureAdapter(@NonNull Context context, int textViewResourceId, @NonNull List<ArticleBean.Obj.Measures> objects) {
        super(context, textViewResourceId, objects);
        resourseId=textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ArticleBean.Obj.Measures measures=getItem(position);
        View view= LayoutInflater.from(parent.getContext()).inflate(resourseId,parent,false);
        TextView number=view.findViewById(R.id.tv_num);
        TextView content=view.findViewById(R.id.tv_content);
        ImageView picture=view.findViewById(R.id.iv_pic);
        number.setText(measures.getNumber());
        content.setText(measures.getContent());

        Glide.with(FoodApplication.getContext()).load(measures.getPicture()).into(picture);
        return view;
    }
}
