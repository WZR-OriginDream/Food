package com.wzr.food.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wzr.food.FoodApplication;
import com.wzr.food.databinding.ActivityTypeFoodBinding;
import com.wzr.food.logic.model.BriefsBean;
import com.wzr.food.logic.model.TypeArticleBean;
import com.wzr.food.ui.adapter.FoodAdapter;
import com.wzr.food.ui.adapter.SmallArticleAdapter;
import com.wzr.food.ui.base.BaseActivity;

import java.util.List;

public class TypeFoodActivity extends BaseActivity {
    private ActivityTypeFoodBinding binding;
    private TypeFoodViewModel typeFoodViewModel;

    private TextView tvDes, tvName, tvBox,tvLikes;
    private CardView cvRecommend, cvHot;
    private ImageView ivsearch, ivhot;
    private AutoCompleteTextView etsearch;
    private RecyclerView recyclerView;
    private SmallArticleAdapter smallArticleAdapter;
    private FoodAdapter foodAdapter;
    private ImageView ivBack;
    private LinearLayoutManager linearLayoutManager;
    private int id = 0;
    private String content, nowcontent, box;

    public TypeFoodActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        typeFoodViewModel = new ViewModelProvider(this).get(TypeFoodViewModel.class);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        id = bundle.getInt("id");
        box = bundle.getString("box");
        content = intent.getStringExtra("etsearch");

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        etsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                nowcontent = s.toString();
            }
        });

        ivsearch.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(nowcontent) || nowcontent == "") {
                typeFoodViewModel.getSearchLiveData(nowcontent).observe(TypeFoodActivity.this, new Observer<BriefsBean>() {
                    @Override
                    public void onChanged(BriefsBean briefsBean) {
                        linearLayoutManager = new LinearLayoutManager(FoodApplication.getContext());
                        recyclerView.setLayoutManager(linearLayoutManager);
                        foodAdapter = new FoodAdapter(TypeFoodActivity.this, briefsBean.getObj());
                        recyclerView.setAdapter(foodAdapter);
                        cvRecommend.setVisibility(View.GONE);
                        tvBox.setVisibility(View.GONE);
                    }
                });
            } else {
                Toast.makeText(TypeFoodActivity.this, "未找到相关消息!", Toast.LENGTH_SHORT).show();
                typeFoodViewModel.getTypeArticleLiveData(id).observe(TypeFoodActivity.this, new Observer<TypeArticleBean>() {
                    @Override
                    public void onChanged(TypeArticleBean typeArticleBean) {

                        linearLayoutManager = new LinearLayoutManager(FoodApplication.getContext());
                        recyclerView.setLayoutManager(linearLayoutManager);
                        smallArticleAdapter = new SmallArticleAdapter(TypeFoodActivity.this, typeArticleBean.getObj());
                        recyclerView.setAdapter(smallArticleAdapter);
                        cvRecommend.setVisibility(View.VISIBLE);
                        tvBox.setVisibility(View.VISIBLE);
                    }
                });
            }

        });

        if (id != 0) {
            typeFoodViewModel.getTypeArticleLiveData(id).observe(this, new Observer<TypeArticleBean>() {
                @Override
                public void onChanged(TypeArticleBean typeArticleBean) {

                    linearLayoutManager = new LinearLayoutManager(FoodApplication.getContext());
                    recyclerView.setLayoutManager(linearLayoutManager);
                    smallArticleAdapter = new SmallArticleAdapter(TypeFoodActivity.this, typeArticleBean.getObj());
                    recyclerView.setAdapter(smallArticleAdapter);

                    int max = 0;
                    for (int i = 1; i < typeArticleBean.getObj().size(); i++) {
                        if (typeArticleBean.getObj().get(i).getPost().getLikes() > typeArticleBean.getObj().get(max).getPost().getLikes()) {
                            max = i;
                        }
                    }
                    cvRecommend.setVisibility(View.VISIBLE);
                    List<TypeArticleBean.Obj> mfoodList = typeArticleBean.getObj();
                    TypeArticleBean.Obj food = mfoodList.get(max);
                    Glide.with(FoodApplication.getContext()).load(food.getPost().getImage()).into(ivhot);
                    tvDes.setText(food.getPost().getContent());
                    tvName.setText(food.getPost().getNickname());
                    tvBox.setText(box);
                    tvLikes.setText(food.getPost().getLikes()+"");

                    ivhot.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(TypeFoodActivity.this, FoodActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putInt("id", food.getPost().getId());
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    });
                }
            });
        } else if (content != null) {
            typeFoodViewModel.getSearchLiveData(content).observe(this, new Observer<BriefsBean>() {
                @Override
                public void onChanged(BriefsBean briefsBean) {
                    linearLayoutManager = new LinearLayoutManager(FoodApplication.getContext());
                    recyclerView.setLayoutManager(linearLayoutManager);
                    foodAdapter = new FoodAdapter(TypeFoodActivity.this, briefsBean.getObj());
                    recyclerView.setAdapter(foodAdapter);
                    cvRecommend.setVisibility(View.GONE);
                    tvBox.setVisibility(View.GONE);
                }
            });
        }
    }

    @Override
    protected void initLayout() {
        //获取binding
        binding = ActivityTypeFoodBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void initView() {
        recyclerView = binding.typefoodRecyclerView;
        ivBack = binding.ivBack;
        ivsearch = binding.ivSearch;
        etsearch = binding.etSearch;
        cvHot = binding.cvHot;
        cvRecommend = binding.cvRecommend;
        tvDes = binding.tvDes;
        tvName = binding.tvName;
        tvBox = binding.tvBox;
        ivhot = binding.hot;
        tvLikes= binding.tvLikes;
    }
}