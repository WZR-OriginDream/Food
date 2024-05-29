package com.wzr.food.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.wzr.food.R;
import com.wzr.food.logic.model.ArticleBean;

import java.util.List;

public class IngredientAdapter extends ArrayAdapter<ArticleBean.Obj.Ingredient> {
    private int resourseId;
    public IngredientAdapter(@NonNull Context context, int textViewResourceId, @NonNull List<ArticleBean.Obj.Ingredient> objects) {
        super(context, textViewResourceId, objects);
        resourseId=textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ArticleBean.Obj.Ingredient ingredient=getItem(position);
        @SuppressLint("ViewHolder") View view= LayoutInflater.from(parent.getContext()).inflate(resourseId,parent,false);
        TextView name=view.findViewById(R.id.ing_name);
        TextView amount=view.findViewById(R.id.ing_amount);
        name.setText(ingredient.getName());
        amount.setText(ingredient.getAmount());
        return view;
    }
}
