package com.wzr.food.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.wzr.food.databinding.ActivityOtherProfileBinding;
import com.wzr.food.ui.adapter.ContentAdapter;
import com.wzr.food.ui.base.BaseActivity;
import com.wzr.food.ui.fragment.CookBookFragment;
import com.wzr.food.ui.fragment.FollowFragment;

public class OtherProfileActivity extends BaseActivity {
    private ActivityOtherProfileBinding binding;
    private OtherProfileViewModel mViewModel;
    private FoodViewModel foodViewModel;

    private ContentAdapter adapter;
    private CookBookFragment cookBookFragment;
    private FollowFragment followFragment;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private TextView tvNickName, tvUserName, tvFollowee, tvFollower, tvLike, tvAttentioned;
    private ImageView ivBack;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(OtherProfileViewModel.class);
        foodViewModel = new ViewModelProvider(this).get(FoodViewModel.class);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int userId = bundle.getInt("userId");

        ivBack.setOnClickListener(v -> onBackPressed());

        mViewModel.getProfileLiveData(userId).observe(this, profileBean -> {
            tvUserName.setText("食说号: " + profileBean.getObj().getUser().getUsername());

            tvNickName.setText(profileBean.getObj().getUser().getNickname());

            tvFollowee.setText("" + profileBean.getObj().getFolloweeCount());

            tvFollower.setText("" + profileBean.getObj().getFollowerCount());

            tvLike.setText("" + profileBean.getObj().getUserLikeCount());

            if (profileBean.getObj().getHasFollowed() == 1) {
                tvAttentioned.setText("已关注");
            }
        });

        //菜谱及点赞
        cookBookFragment = CookBookFragment.newInstance(userId);
        followFragment = FollowFragment.newInstance(userId);

        Fragment[] fragments = new Fragment[2];
        fragments[0] = cookBookFragment;
        fragments[1] = followFragment;
        String[] titles = {"作品", "收藏"};

        adapter = new ContentAdapter(getSupportFragmentManager(), fragments, titles);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        tvAttentioned.setOnClickListener(v->{
            action("follow",0,userId,userId);
        });
    }

    @Override
    protected void initLayout() {
        binding = ActivityOtherProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void initView() {
        tabLayout = binding.otherProfileTabLayout;
        viewPager = binding.otherProfileViewPager;
        tvAttentioned = binding.tvAttentioned;
        tvFollowee = binding.tvFollowee;
        tvFollower = binding.tvFollower;
        tvLike = binding.tvLike;
        tvNickName = binding.tvNickname;
        tvUserName = binding.tvUsername;
        ivBack = binding.ivBack;
    }

    @SuppressLint("SetTextI18n")
    public void action(String action, int entityType, int entityId, int entityUserId) {
        foodViewModel.getActionLiveData(action, entityType, entityId, entityUserId).observe(this, actionBean -> {
            long count = actionBean.getObj().getEntityActionCount();
            boolean isActioned = actionBean.getObj().getEntityActionStatus() == 1;
            if ("follow".equals(action)) {
                if (isActioned) {
                    tvAttentioned.setText("已关注");
                    Toast.makeText(this, "关注成功", Toast.LENGTH_SHORT).show();
                } else {
                    tvAttentioned.setText("关注");
                    Toast.makeText(this, "取消关注", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}