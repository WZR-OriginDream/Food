package com.wzr.food.ui.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;
import com.wzr.food.R;
import com.wzr.food.logic.model.CreateArticleBean;
import com.wzr.food.logic.model.ImageBean;
import com.wzr.food.repository.Repository;
import com.wzr.food.util.GlideEngine;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateMeasureAdapter extends RecyclerView.Adapter<CreateMeasureAdapter.ViewHolder> {

    private LinkedList<CreateArticleBean.Measures> measures;
    private Context context;
    private String image;

    public CreateMeasureAdapter(Context context, LinkedList<CreateArticleBean.Measures> measures) {
        this.measures= measures;
        this.context = context;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        EditText etMeasure;
        ImageView ivMeasure;
        TextView tvNumber,tvTip;

        public ViewHolder(View itemView) {
            super(itemView);
            etMeasure = itemView.findViewById(R.id.et_measure);
            ivMeasure = itemView.findViewById(R.id.iv_measure);
            tvNumber = itemView.findViewById(R.id.tv_number);
            tvTip = itemView.findViewById(R.id.tv_measure_tip);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.create_measure_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        holder.ivMeasure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PictureSelector.create((Activity) context)
                        .openGallery(PictureMimeType.ofImage())
                        .imageEngine(GlideEngine.createGlideEngine())
                        .theme(R.style.picture_WeChat_style)
                        .imageFormat(PictureMimeType.JPEG)
                        .isEnableCrop(true)
                        .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                        .showCropFrame(true)// 是否显示裁剪矩形边框 圆形裁剪时建议设为 false   true or false
                        .showCropGrid(true)// 是否显示裁剪矩形网格 圆形裁剪时建议设为 false    true or false
                        .rotateEnabled(true) // 裁剪是否可旋转图片 true or false
                        .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false
                        .forResult(new OnResultCallbackListener<LocalMedia>() {
                            @Override
                            public void onResult(List<LocalMedia> result) {
                                File file = new File(result.get(0).getCutPath());
                                Glide.with(context).load(file).into(holder.ivMeasure);
                                //将文件转化为RequestBody对象
                                //需要在表单中进行文件上传时，就需要使用该格式：multipart/form-data
                                RequestBody imgBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);

                                //将文件转化为MultipartBody.Part
                                //第一个参数：上传文件的key；第二个参数：文件名；第三个参数：RequestBody对象
                                MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", file.getName(), imgBody);

                                Repository.getPhotoService().uploadPhoto(filePart).enqueue(new Callback<ImageBean>() {
                                    @Override
                                    public void onResponse(Call<ImageBean> call, Response<ImageBean> response) {
                                        ImageBean body = response.body();
                                        image = body.getObj();
                                        Log.d("wen1", image);
                                    }

                                    @Override
                                    public void onFailure(Call<ImageBean> call, Throwable t) {

                                    }
                                });
                            }

                            @Override
                            public void onCancel() {

                            }
                        });
            }
        });
        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.etMeasure.setTag(position);
        holder.tvNumber.setText(1+position+"");
        holder.etMeasure.setText(measures.get(position).getContent());

        holder.etMeasure.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (holder.etMeasure.getTag() == (Integer) position) {//设置tag解决错乱问题
                    onContentFillListener.onContentFill(position, s.toString(), image);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return measures.size();
    }

    private OnContentFillListener onContentFillListener;

    public interface OnContentFillListener {
        void onContentFill(int position, String content,String image);
    }

    public void setOnContentFillListener(OnContentFillListener onContentFillListener) {
        this.onContentFillListener = onContentFillListener;
    }
}