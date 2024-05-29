package com.wzr.food.ui.wode;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;
import com.wzr.food.R;
import com.wzr.food.databinding.FragmentWodeBinding;
import com.wzr.food.logic.dao.MessageDao;
import com.wzr.food.ui.activity.OtherProfileViewModel;
import com.wzr.food.ui.activity.SettingsActivity;
import com.wzr.food.ui.adapter.ContentAdapter;
import com.wzr.food.ui.base.BaseFragment;
import com.wzr.food.ui.fragment.CookBookFragment;
import com.wzr.food.ui.fragment.FollowFragment;
import com.wzr.food.util.GlideEngine;

import java.util.List;

public class WodeFragment extends BaseFragment {

    private WodeViewModel wodeViewModel;
    private FragmentWodeBinding binding;
    private OtherProfileViewModel mViewModel;

    private ContentAdapter adapter;
    private CookBookFragment cookBookFragment;
    private FollowFragment followFragment;

    private TextView nickname, follow, fans,likes;
    private ImageView imageView, switchbg, personalbg;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        wodeViewModel = new ViewModelProvider(this).get(WodeViewModel.class);
        mViewModel = new ViewModelProvider(this).get(OtherProfileViewModel.class);

        mViewModel.getProfileLiveData(MessageDao.getSaveLoginBean().getObj().getId()).observe(this,profileBean -> {

            follow.setText(""+profileBean.getObj().getFolloweeCount());

            fans.setText(""+profileBean.getObj().getFollowerCount());

            likes.setText(""+profileBean.getObj().getUserLikeCount());
        });
    }

    @Override
    protected View initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentWodeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    protected void initView() {
        imageView = binding.ivSettings;
        tabLayout = binding.personalTab;
        viewPager = binding.personalViewpager;
        nickname = binding.tvNickname;
        follow = binding.tvFollow;
        fans = binding.tvFans;
        likes = binding.tvLikes;
        switchbg = binding.switchBg;
        personalbg = binding.ivPersonalBg;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void initEvent() {
        //菜谱及收藏
        cookBookFragment = CookBookFragment.newInstance(MessageDao.getSaveLoginBean().getObj().getId());
        followFragment = FollowFragment.newInstance(MessageDao.getSaveLoginBean().getObj().getId());

        Fragment[] fragments = new Fragment[2];
        fragments[0] = cookBookFragment;
        fragments[1] = followFragment;
        String[] titles = {"作品", "收藏"};

        adapter = new ContentAdapter(getChildFragmentManager(), fragments, titles);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        nickname.setText(MessageDao.getSaveLoginBean().getObj().getNickname());
        follow.setText(MessageDao.getSaveLoginBean().getObj().getAttention() + "");
        fans.setText(MessageDao.getSaveLoginBean().getObj().getFan() + "");

        //设置
        imageView.setOnClickListener(v -> startActivity(new Intent(getActivity(), SettingsActivity.class)));

        //图片背景
        if (MessageDao.isBgPathSaved()) {
            Glide.with(WodeFragment.this).load(MessageDao.getSaveBgPath()).into(personalbg);
        }

        switchbg.setOnClickListener(v -> PictureSelector.create(getActivity())
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
                        MessageDao.saveBgPath(result.get(0).getPath());
                        Glide.with(WodeFragment.this).load(result.get(0).getCutPath()).into(personalbg);
                    }

                    @Override
                    public void onCancel() {

                    }
                }));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}