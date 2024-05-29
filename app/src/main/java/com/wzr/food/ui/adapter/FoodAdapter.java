package com.wzr.food.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.wzr.food.FoodApplication;
import com.wzr.food.R;
import com.wzr.food.logic.model.BriefsBean;
import com.wzr.food.ui.activity.FoodActivity;
import com.wzr.food.ui.activity.OtherProfileActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    private List<BriefsBean.Obj> mfoodList;
    private Context mcontext;

    public FoodAdapter(Context context, List<BriefsBean.Obj> foodList) {
        mfoodList = foodList;
        mcontext = context;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView userName, foodName, content, likes;
        ImageView head, foodImage, comment, share;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            head = itemView.findViewById(R.id.item_head);
            userName = itemView.findViewById(R.id.item_name);
            foodName = itemView.findViewById(R.id.item_foodname);
            foodImage = itemView.findViewById(R.id.item_foodimage);
            likes = itemView.findViewById(R.id.tv_likes);
            comment = itemView.findViewById(R.id.iv_comment);
            content = itemView.findViewById(R.id.item_foodcontent);
            share = itemView.findViewById(R.id.iv_share);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BriefsBean.Obj food = mfoodList.get(position);
        holder.userName.setText(food.getNickname());
        holder.foodName.setText(food.getTitle());
        holder.likes.setText(food.getLikes());
        holder.content.setText(food.getContent());
        Glide.with(FoodApplication.getContext()).load(food.getImage()).override(400, 400).into(holder.foodImage);

        holder.head.setOnClickListener(view1 -> {
            Intent intent = new Intent(mcontext, OtherProfileActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("userId", food.getUserId());
            intent.putExtras(bundle);
            mcontext.startActivity(intent);
        });
        holder.foodImage.setOnClickListener(v -> {
            Intent intent = new Intent(mcontext, FoodActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("id", food.getId());
            intent.putExtras(bundle);
            mcontext.startActivity(intent);
        });

        holder.comment.setOnClickListener(v -> {
        });
        holder.share.setOnClickListener(v -> {
            share(mcontext, food.getContent(), food.getImage(), food.getTitle());
        });
    }

    @Override
    public int getItemCount() {
        return mfoodList.size();
    }

    private void share(Context context, String content, String imageUrl, String title) {
        Log.d("wen", "分享");
        // 第1步：下载网络图片并保存到文件系统（使用Glide作为示例）
        Glide.with(context)
                .asBitmap()
                .load(imageUrl)
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        // 第2步：保存图片到文件并获取URI
                        File file = new File(context.getExternalFilesDir("."), title + ".jpg");
                        Log.d("wen", file.getAbsolutePath());
                        try {
                            FileOutputStream fos = new FileOutputStream(file);
                            resource.compress(Bitmap.CompressFormat.JPEG, 32, fos);
                            fos.flush();
                            fos.close();

                            // 对于Android 7.0及以上版本使用FileProvider
                            Uri imageUri;
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                imageUri = FileProvider.getUriForFile(
                                        context,
                                        context.getPackageName() + ".fileprovider",
                                        file);
                            } else {
                                imageUri = Uri.fromFile(file);
                            }
                            Log.d("wen", String.valueOf(imageUri));

                            // 第3步：创建分享Intent
                            Intent shareIntent = new Intent();
                            shareIntent.setAction(Intent.ACTION_SEND);

                            shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
                            shareIntent.setType("image/jpeg");

                            shareIntent.putExtra(Intent.EXTRA_TEXT, content);

                            // 检查是否安装了微信，如果安装了微信，则设置微信的包名
//                            if (isWeChatAppInstalled()) {
//                                shareIntent.setPackage("com.tencent.mm");
//                            }

                            // 授予临时访问权限
                            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                            // 创建分享选择器
                            Intent chooserIntent = Intent.createChooser(shareIntent, "分享到");

                            // 确保Intent是可解析的
                            if (shareIntent.resolveActivity(context.getPackageManager()) != null) {
                                context.startActivity(chooserIntent);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                        // 资源清除时的回调
                    }
                });
    }
}
