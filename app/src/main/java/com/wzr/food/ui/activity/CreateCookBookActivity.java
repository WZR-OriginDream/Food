package com.wzr.food.ui.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.wzr.food.R;
import com.wzr.food.databinding.ActivityCreateCookBookBinding;
import com.wzr.food.logic.dao.MessageDao;
import com.wzr.food.logic.model.CreateArticleBean;
import com.wzr.food.logic.model.CreateResponseBean;
import com.wzr.food.logic.model.ImageBean;
import com.wzr.food.repository.Repository;
import com.wzr.food.ui.adapter.CreateIngredientAdapter;
import com.wzr.food.ui.adapter.CreateMeasureAdapter;
import com.wzr.food.ui.base.BaseActivity;
import com.wzr.food.util.GlideEngine;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateCookBookActivity extends BaseActivity implements CreateIngredientAdapter.OnNameFillListener, CreateIngredientAdapter.OnAmountFillListener, CreateMeasureAdapter.OnContentFillListener {
    private ActivityCreateCookBookBinding binding;
    private CreateCookBookViewModel createCookBookViewModel;

    private static final MediaType FROM_DATA = MediaType.parse("multipart/form-data");

    private Boolean isSave = false;
    private Boolean isPost = true;
    private Map<String, String> map = new HashMap<>();
    private String image, title, history,content;
    private int type;
    private CreateArticleBean.Post post;
    private CreateArticleBean createArticleBean;

    private RadioGroup radioGroup;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private RadioButton radioButton4;

    private EditText etTitle, etHistory, etContent;
    private TextView tvTip, tvPost, tvSave, addIngredient, addMeasure, deleteMeasure;
    private ImageView ivBack, ivTitle;
    private RecyclerView rvIngredient, rvMeasure;
    private CreateIngredientAdapter ingredientAdapter;
    private CreateMeasureAdapter measureAdapter;
    private LinkedList<CreateArticleBean.Ingredients> ingredients;
    private LinkedList<CreateArticleBean.Measures> measures;
    private LinearLayoutManager ilayoutManager, mlayoutManager;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createCookBookViewModel = new ViewModelProvider(this).get(CreateCookBookViewModel.class);

        //食材用量
        ilayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        ilayoutManager.setStackFromEnd(true);
        rvIngredient.setLayoutManager(ilayoutManager);
        ingredients = new LinkedList<>();
        ingredients.add(new CreateArticleBean.Ingredients());
        ingredients.add(new CreateArticleBean.Ingredients());
        ingredientAdapter = new CreateIngredientAdapter(this, ingredients);
        ingredientAdapter.setOnNameFillListener(this);
        ingredientAdapter.setOnAmountFillListener(this);
        rvIngredient.setAdapter(ingredientAdapter);

        //做法步骤
        mlayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mlayoutManager.setStackFromEnd(true);
        rvMeasure.setLayoutManager(mlayoutManager);
        measures = new LinkedList<>();
        measures.add(new CreateArticleBean.Measures());
        measureAdapter = new CreateMeasureAdapter(this, measures);
        measureAdapter.setOnContentFillListener(this);
        rvMeasure.setAdapter(measureAdapter);

        etTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                title = s.toString();
                tvTransColor(tvSave, title);
            }
        });

        etHistory.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                history = s.toString();
                tvTransColor(tvSave, history);
            }
        });

        etContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                content = s.toString();
                tvTransColor(tvSave, content);
            }
        });

        ivBack.setOnClickListener(v -> onBackPressed());

        ivTitle.setOnClickListener(v -> {
            tvTip.setVisibility(View.INVISIBLE);
            PictureSelector.create(CreateCookBookActivity.this)
                    .openGallery(PictureMimeType.ofImage())
                    .imageEngine(GlideEngine.createGlideEngine())
                    .theme(R.style.picture_WeChat_style)
                    .imageFormat(PictureMimeType.JPEG)
                    .compress(true)
                    .isEnableCrop(true)
                    .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                    .showCropFrame(true)// 是否显示裁剪矩形边框 圆形裁剪时建议设为 false   true or false
                    .showCropGrid(true)// 是否显示裁剪矩形网格 圆形裁剪时建议设为 false    true or false
                    .rotateEnabled(true) // 裁剪是否可旋转图片 true or false
                    .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false
                    .forResult(PictureConfig.CHOOSE_REQUEST);
        });

        addIngredient.setOnClickListener(v -> {
            if (ingredients != null) {
                ingredients.add(new CreateArticleBean.Ingredients());
                ingredientAdapter.notifyDataSetChanged();
            }
        });

        addMeasure.setOnClickListener(v -> {
            if (measures != null) {
                measures.add(new CreateArticleBean.Measures());
                measureAdapter.notifyDataSetChanged();
            }
        });

        deleteMeasure.setOnClickListener(v -> {
            if (measures != null) {
                measures.removeLast();
                measureAdapter.notifyDataSetChanged();
            }
        });

        tvSave.setOnClickListener(v -> {
            if (!isSave) {
                Toast.makeText(CreateCookBookActivity.this, "还未输入任何数据", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(CreateCookBookActivity.this, "保存草稿成功", Toast.LENGTH_SHORT).show();
            }
        });

        // 设置 RadioGroup 的 OnCheckedChangeListener
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // 当 RadioButton 的选中状态发生变化时调用
                if (checkedId == R.id.radioButton1) {
                    // RadioButton1 被选中
                    type = 1;
                } else if (checkedId == R.id.radioButton2) {
                    // RadioButton2 被选中
                    type = 2;
                } else if (checkedId == R.id.radioButton3) {
                    type = 3;
                } else if (checkedId == R.id.radioButton4) {
                    type = 4;
                }
                // 可以继续添加更多的条件判断其他 RadioButton
            }
        });

        tvPost.setOnClickListener(v -> {
            if (isPost) {
                for (int i = 0; i < ingredients.size(); i++) {
                    map.put(ingredients.get(i).getName(), ingredients.get(i).getAmount());
                }
                post = new CreateArticleBean.Post(content, "2021-11-15T14:36:48.595Z", history, 1, image, new Gson().toJson(map), 0, MessageDao.getSaveLoginBean().getObj().getNickname(), MessageDao.getSaveLoginBean().getObj().getUsername(), title, type, MessageDao.getSaveLoginBean().getObj().getId());
                createArticleBean = new CreateArticleBean(ingredients, measures, post);
                createCookBookViewModel.getCreateResponseLiveData(createArticleBean).observe(CreateCookBookActivity.this, new Observer<CreateResponseBean>() {
                    @Override
                    public void onChanged(CreateResponseBean createResponseBean) {
                        if (createResponseBean.getCode() == 200) {
                            Toast.makeText(CreateCookBookActivity.this, "上传菜谱成功", Toast.LENGTH_SHORT).show();
                            Intent resultIntent = new Intent();
                            resultIntent.putExtra("key", "value"); // 传递数据
                            setResult(Activity.RESULT_OK, resultIntent);
                            finish();
                        }
                    }
                });
                isPost = false;
            }

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    List<LocalMedia> result = PictureSelector.obtainMultipleResult(data);

                    File file = new File(result.get(0).getCutPath());
                    Log.d("wen", result.get(0).getCutPath());

                    Glide.with(this).load(file).into(ivTitle);

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
                            Log.d("wen", image);
                        }

                        @Override
                        public void onFailure(Call<ImageBean> call, Throwable t) {

                        }
                    });

                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected void initLayout() {
        binding = ActivityCreateCookBookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void initView() {
        ivBack = binding.ivBack;
        ivTitle = binding.ivTitle;

        etHistory = binding.etHistory;
        etTitle = binding.etTitle;
        etContent = binding.etContent;

        rvIngredient = binding.rvIngredient;
        rvMeasure = binding.rvMeasure;

        addIngredient = binding.tvAddIngredient;
        addMeasure = binding.tvAddMeasure;
        deleteMeasure = binding.tvDeleteMeasure;
        tvTip = binding.tvTip;
        tvPost = binding.tvPost;
        tvSave = binding.tvSave;

        // 获取 RadioGroup 和 RadioButton
        radioGroup = binding.radioGroup;
        radioButton1 = binding.radioButton1;
        radioButton2 = binding.radioButton2;
        radioButton3 = binding.radioButton3;
        radioButton4 = binding.radioButton4;

    }

    @Override
    public void onNameFill(int position, String name) {
        if (position < ingredients.size()) {
            ingredients.get(position).setName(name);
            tvTransColor(tvSave, name);
        }
    }

    @Override
    public void onAmountFill(int position, String amount) {
        if (position < ingredients.size()) {
            ingredients.get(position).setAmount(amount);
            tvTransColor(tvSave, amount);
        }
    }

    @Override
    public void onContentFill(int position, String content, String image) {
        if (position < measures.size()) {
            tvTransColor(tvSave, content);
            measures.get(position).setId(position + 1);
            measures.get(position).setPostId(8);
            measures.get(position).setNumber(position + 1);
            measures.get(position).setContent(content);
            measures.get(position).setPicture(image);
        }
    }

    private void tvTransColor(TextView tv, String s) {
        if (!TextUtils.isEmpty(s) || s != "") {
            tv.setTextColor(Color.parseColor("#FF412B"));
            tv.setBackgroundResource(R.drawable.rectangle_red);
            isSave = true;
        }
        if (TextUtils.isEmpty(s) || s == "") {
            tv.setTextColor(Color.parseColor("#CBA3A2A0"));
            tv.setBackgroundResource(R.drawable.rectangle_gray);
            isSave = false;
        }
    }


    public static String sendFromDataPostRequest(String url, File file, String typeName) throws IOException {
        RequestBody fileBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody body = new MultipartBody.Builder()
                .setType(FROM_DATA)
                .addFormDataPart(typeName, "2.png", fileBody)
                .build();
        Request request = new Request.Builder()
                .post(body)
                .url(url)
                .build();
        OkHttpClient client = new OkHttpClient();
        return client.newCall(request).execute().body().string();
    }

}