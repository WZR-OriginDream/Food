package com.wzr.food.ui.fragment;

import android.animation.Animator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.wzr.food.R;
import com.wzr.food.databinding.FragmentContentBinding;
import com.wzr.food.logic.model.ArticleBean;
import com.wzr.food.ui.adapter.IngredientAdapter;
import com.wzr.food.ui.adapter.MeasuresAdapter;
import com.wzr.food.ui.base.BaseFragment;

import java.util.List;

public class ContentFragment extends BaseFragment {
    private FragmentContentBinding binding;

    private TextView tvcontent;
    private ListView inglistView;
    private RecyclerView rvmeasure;
    private LottieAnimationView lavBook;

    private final String content;
    private final List<ArticleBean.Obj.Measures> measuresList;
    private final List<ArticleBean.Obj.Ingredient> ingredientList;

    private ContentFragment(String content, List<ArticleBean.Obj.Measures> measuresList, List<ArticleBean.Obj.Ingredient> ingredientList) {
        this.content = content;
        this.measuresList = measuresList;
        this.ingredientList = ingredientList;
    }

    public static ContentFragment newInstance(String content, List<ArticleBean.Obj.Measures> measuresList, List<ArticleBean.Obj.Ingredient> ingredientList) {
        return new ContentFragment(content, measuresList, ingredientList);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected View initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentContentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    protected void initView() {
        rvmeasure = binding.rvMeasure;
        inglistView = binding.ingListView;
        tvcontent = binding.tvContent;
        lavBook = binding.lavBook;
    }

    @Override
    protected void initEvent() {
        show();
    }

    public void show() {

        showDefaultPage(lavBook);
        tvcontent.setText(content);

        IngredientAdapter ingredientAdapter = new IngredientAdapter(requireContext(), R.layout.ingredient_item, ingredientList);
        inglistView.setAdapter(ingredientAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvmeasure.setLayoutManager(linearLayoutManager);
        MeasuresAdapter measuresAdapter = new MeasuresAdapter(getContext(), measuresList);
        rvmeasure.setAdapter(measuresAdapter);
    }

    private void showDefaultPage(LottieAnimationView lav) {
        lav.setAnimation("default_page_currently_plant.json"); // 设置动画文件名
        lav.loop(true); // 设置是否循环播放
        lav.playAnimation(); // 开始播放动画
        lav.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                // 动画开始
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                // 动画结束
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
            // 其他事件处理...
        });
    }
}