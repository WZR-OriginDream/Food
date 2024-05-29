package com.wzr.food.ui.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.wzr.food.ui.fragment.GuideFragment1;
import com.wzr.food.ui.fragment.GuideFragment2;
import com.wzr.food.ui.fragment.GuideFragment3;

public class MyFragmentStateAdapter extends FragmentStateAdapter {
    public MyFragmentStateAdapter(FragmentActivity fa) {
        super(fa);
    }

    @Override
    public Fragment createFragment(int position) {
        // 根据position返回相应的Fragment实例
        switch (position) {
            case 0:
                return new GuideFragment1();
            case 1:
                return new GuideFragment2();
            case 2:
                return new GuideFragment3();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 3; // 总的Fragment数量
    }
}