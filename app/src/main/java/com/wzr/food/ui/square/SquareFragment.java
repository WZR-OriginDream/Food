package com.wzr.food.ui.square;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;
import com.wzr.food.R;
import com.wzr.food.databinding.FragmentSquareBinding;
import com.wzr.food.ui.activity.FoodActivity;
import com.wzr.food.ui.activity.HistoryActivity;
import com.wzr.food.ui.adapter.ContentAdapter;
import com.wzr.food.ui.base.BaseFragment;
import com.wzr.food.ui.fragment.PushFragment;
import com.wzr.food.ui.fragment.RankFragment;
import com.wzr.food.ui.fragment.RecomendFragment;

public class SquareFragment extends BaseFragment {
    private SquareViewModel squareViewModel;
    private FragmentSquareBinding binding;

    private RecomendFragment recomendFragment;
    private PushFragment pushFragment;
    private RankFragment rankFragment;

    private ContentAdapter adapter;

    private TextView tvLiveName;
    private EditText etLiveName;
    private ImageView tolive, tovisit;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private String liveName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        squareViewModel = new ViewModelProvider(this).get(SquareViewModel.class);
    }

    @Override
    protected View initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSquareBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    protected void initView() {
        tabLayout = binding.tabLayout;
        viewPager = binding.viewPager;
        tolive = binding.ivLive;
        tovisit = binding.ivHistory;
    }

    @Override
    protected void initEvent() {
        recomendFragment = RecomendFragment.newInstance();
        pushFragment = PushFragment.newInstance();
        rankFragment = RankFragment.newInstance();
        Fragment[] fragments = new Fragment[3];
        fragments[0] = recomendFragment;
        fragments[1] = pushFragment;
        fragments[2] = rankFragment;
        String[] titles = {"全部", "推荐", "排行榜"};

        adapter = new ContentAdapter(getChildFragmentManager(), fragments, titles);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        tovisit.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), HistoryActivity.class);
            startActivity(intent);
        });

        tolive.setOnClickListener(v -> {
            BottomSheetDialog shareDialog;
            shareDialog = new BottomSheetDialog(getContext(), R.style.BottomSheetDialog);
            View aview = View.inflate(getContext(), R.layout.layout_popup_live, null);
            shareDialog.setContentView(aview);
            shareDialog.show();
            etLiveName = aview.findViewById(R.id.et_liveName);
            etLiveName.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    liveName = s.toString();
                }
            });
            tvLiveName = aview.findViewById(R.id.tv_startLive);
            tvLiveName.setOnClickListener(v1 -> {
                if (!TextUtils.isEmpty(liveName) && liveName != "") {
                    Intent intent = new Intent(getActivity(), FoodActivity.class);
                    intent.putExtra("livename", liveName);
                    startActivity(intent);
                }
            });
            shareDialog.setContentView(aview);
            shareDialog.show();
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}