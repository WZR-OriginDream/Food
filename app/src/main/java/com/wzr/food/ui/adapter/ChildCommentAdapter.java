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
import com.wzr.food.logic.model.CommentBean;
import com.wzr.food.ui.activity.OtherProfileActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class ChildCommentAdapter extends RecyclerView.Adapter<ChildCommentAdapter.ViewHolder> {

    private List<CommentBean.Obj.ChildComment> mcommentList;
    private Context mcontext;

    public ChildCommentAdapter(Context context, List<CommentBean.Obj.ChildComment> commentList) {
        mcommentList = commentList;
        mcontext = context;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nickname, parentcomment, commemttime,reply;
        ImageView commenthead, commentlikes;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nickname = itemView.findViewById(R.id.tv_nickname);
            parentcomment = itemView.findViewById(R.id.tv_parentcomment);
            commenthead = itemView.findViewById(R.id.iv_comment_head);
            commemttime = itemView.findViewById(R.id.tv_comment_time);
            commentlikes = itemView.findViewById(R.id.iv_comment_likes);
            reply = itemView.findViewById(R.id.tv_reply);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CommentBean.Obj.ChildComment comment = mcommentList.get(position);
        holder.nickname.setText(comment.getNickname());
        holder.parentcomment.setText(comment.getContent());
        holder.commemttime.setText(dateFormat(comment.getCreateTime()));
        holder.reply.setVisibility(View.GONE);

        holder.commenthead.setOnClickListener(v -> {
            Intent intent = new Intent(mcontext, OtherProfileActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("userId", comment.getUserId());
            intent.putExtras(bundle);
            mcontext.startActivity(intent);
        });

        holder.commentlikes.setOnClickListener(v->{
            holder.commentlikes.setImageResource(R.drawable.ic_likes_66_red);
        });

    }

    @Override
    public int getItemCount() {
        return mcommentList.size();
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

