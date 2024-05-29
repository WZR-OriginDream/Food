package com.wzr.food.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import org.jetbrains.annotations.NotNull;


public class ContentAdapter extends FragmentStatePagerAdapter {
    private String[] titles;
    private Fragment[] fragments;
    public ContentAdapter(@NonNull @NotNull FragmentManager fm, Fragment[] fragments, String[] titles) {
        super(fm);
        this.fragments=fragments;
        this.titles=titles;
    }


    public ContentAdapter(@NonNull @NotNull FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String plateName = titles[position];
        if (plateName == null) {
            plateName = "";
        } else if (plateName.length() > 15) {
            plateName = plateName.substring(0, 15) + "...";
        }
        return plateName;
    }
}
