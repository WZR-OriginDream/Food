package com.wzr.food.ui.adapter;

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
import com.wzr.food.logic.model.ArticleBean;

import java.util.List;

public class FoodContentAdapter extends RecyclerView.Adapter<FoodContentAdapter.ViewHolder> {

    private List<ArticleBean.Obj.Measures> mfoodList;
    public FoodContentAdapter( List<ArticleBean.Obj.Measures> foodList){
        mfoodList=foodList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView userName;
        TextView foodName;
        ImageView foodImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userName=itemView.findViewById(R.id.item_name1);
            foodName=itemView.findViewById(R.id.item_foodname1);
            foodImage=itemView.findViewById(R.id.item_foodimage1);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_content_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
//        holder.foodImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int position=holder.getAdapterPosition();
//                BriefsBean.Obj food=mfoodList.get(position);
//                Intent intent=new Intent(mcontext, FoodActivity.class);
//                Bundle bundle=new Bundle();
//                bundle.putInt("id", food.getId());
//                intent.putExtras(bundle);
//                mcontext.startActivity(intent);
//            }
//        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ArticleBean.Obj.Measures food=mfoodList.get(position);
        Log.d("温宗荣",food.getNumber()+"");
        Log.d("温宗荣",food.getContent()+"");
        Log.d("温宗荣",food.getPicture()+"");
        holder.userName.setText(food.getNumber());
        holder.foodName.setText(food.getContent());
        Glide.with(FoodApplication.getContext()).load(food.getPicture()).override(400,400).into(holder.foodImage);
    }

    @Override
    public int getItemCount() {
        return mfoodList.size();
    }

}
