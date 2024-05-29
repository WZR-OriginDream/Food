package com.wzr.food.ui.adapter;

import android.annotation.SuppressLint;
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

import com.wzr.food.R;
import com.wzr.food.logic.model.UserActionListBean;
import com.wzr.food.ui.activity.OtherProfileActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class AttentionsAdapter extends RecyclerView.Adapter<AttentionsAdapter.ViewHolder> {

    private List<UserActionListBean.Obj> mfoodList;
    private Context mcontext;

    public AttentionsAdapter(Context context, List<UserActionListBean.Obj> foodList) {
        mfoodList = foodList;
        mcontext = context;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView head;
        TextView nickname,createtime,chat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            head = itemView.findViewById(R.id.head_image);
            nickname = itemView.findViewById(R.id.tv_nickname);
            createtime = itemView.findViewById(R.id.tv_createtime);
            chat=itemView.findViewById(R.id.tv_chat);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.attentions_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserActionListBean.Obj food = mfoodList.get(position);
        holder.head.setOnClickListener(v->{
            Intent intent = new Intent(mcontext, OtherProfileActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("userId", food.getUser().getId());
            intent.putExtras(bundle);
            mcontext.startActivity(intent);
        });
        holder.nickname.setText(food.getUser().getNickname());
        holder.createtime.setText(dateFormat(food.getActiontime()));
        holder.chat.setOnClickListener(v -> {
//                query(food.getUsername());
        });
    }

    @Override
    public int getItemCount() {
        return mfoodList.size();
    }

    private String dateFormat(String mdate) {
        String formatDate = null;
        // 创建 SimpleDateFormat 对象，用于解析原始格式
        // 创建 SimpleDateFormat 对象，用于解析包含时区的 ISO 8601 格式字符串
        @SuppressLint("SimpleDateFormat") SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        isoFormat.setTimeZone(TimeZone.getTimeZone("GMT"));  // 设置时区为 GMT

        try {
            // 解析字符串到 Date 对象
            Date date = isoFormat.parse(mdate);

            // 创建 SimpleDateFormat 对象，用于格式化成新的格式（不包含毫秒）
            @SuppressLint("SimpleDateFormat") SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            targetFormat.setTimeZone(TimeZone.getDefault());  // 使用默认时区

            // 将 Date 对象格式化为字符串
            formatDate = targetFormat.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatDate;
    }

}
