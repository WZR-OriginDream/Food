package com.wzr.food.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;

import com.wzr.food.databinding.FragmentHomeBinding;
import com.wzr.food.logic.model.BannerItemBean;
import com.wzr.food.logic.model.BriefsBean;
import com.wzr.food.ui.activity.FoodActivity;
import com.wzr.food.ui.activity.TypeFoodActivity;
import com.wzr.food.ui.base.BaseFragment;
import com.wzr.food.ui.fragment.RecomendViewModel;
import com.wzr.food.util.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment {

    private HomeViewModel homeViewModel;
    private RecomendViewModel recomendViewModel;
    private FragmentHomeBinding binding;
    private String content;

    private ImageView food;
    private ImageView dessert;
    private ImageView location;
    private ImageView calendor;
    private ImageView ivsearch;
    private CardView cardView;
    private AutoCompleteTextView etsearch;

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        recomendViewModel = new ViewModelProvider(this).get(RecomendViewModel.class);

    }

    @Override
    protected View initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    protected void initView() {
        food = binding.homeFood;
        dessert = binding.homeDessert;
        location = binding.homeLocation;
        calendor = binding.homeCalendar;
        ivsearch = binding.ivSearch;
        cardView = binding.hot;
        etsearch = binding.etSearch;

    }

    @Override
    protected void initEvent() {
        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TypeFoodActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id", 1);
                bundle.putString("box", "主食");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        dessert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TypeFoodActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id", 2);
                bundle.putString("box", "甜品");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TypeFoodActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id", 3);
                bundle.putString("box", "地方美食");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        calendor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TypeFoodActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id", 4);
                bundle.putString("box", "节日美食");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TypeFoodActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id", 5);
                bundle.putString("box", "热门精选");
                intent.putExtras(bundle);
                startActivity(intent);
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
                content = s.toString();
            }
        });

        ivsearch.setOnClickListener(v -> {
            if (content != null) {
                Intent intent = new Intent(getContext(), TypeFoodActivity.class);
                intent.putExtra("etsearch", content);
                getContext().startActivity(intent);
            } else {
                Toast.makeText(getContext(), "未找到相关消息!", Toast.LENGTH_SHORT);
            }
        });
        bannerPicture();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void bannerPicture() {
        List<String> images = new ArrayList<>();
        List<BannerItemBean> bannerItems = new ArrayList<>();

        recomendViewModel.getBriefsLiveData(1, 5).observe(this, briefsBean -> {
            for (BriefsBean.Obj item : briefsBean.getObj()) {
                bannerItems.add(new BannerItemBean(item.getImage(), item.getId()));

                images.add(item.getImage());

                Banner banner = binding.banner;
                //设置图片加载器
                banner.setImageLoader(new GlideImageLoader());
                //设置图片集合
                banner.setImages(images);
                //设置banner动画效果
                banner.setBannerAnimation(Transformer.Default);
                //设置自动轮播，默认为true
                banner.isAutoPlay(true);
                //设置轮播时间
                banner.setDelayTime(1500);
                //设置指示器位置（当banner模式中有指示器时）
                banner.setIndicatorGravity(BannerConfig.CENTER);
                //banner设置方法全部调用完毕时最后调用
                banner.start();

                banner.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        // position 是用户点击的Banner图片在bannerItems列表中的索引
                        BannerItemBean clickedItem = bannerItems.get(position);
                        Intent intent = new Intent(getContext(), FoodActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("id", clickedItem.getId());
                        intent.putExtras(bundle);
                        getContext().startActivity(intent);
                    }
                });
            }
        });


    }
}