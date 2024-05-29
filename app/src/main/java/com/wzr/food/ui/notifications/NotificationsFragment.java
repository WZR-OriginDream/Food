package com.wzr.food.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.wzr.food.databinding.FragmentNotificationsBinding;
import com.wzr.food.ui.adapter.ContentAdapter;
import com.wzr.food.ui.fragment.AttentionsFragment;
import com.wzr.food.ui.fragment.ChatFragment;
import com.wzr.food.ui.fragment.FansFragment;
import com.wzr.food.ui.fragment.CommentFragment;


public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private FragmentNotificationsBinding binding;

    private CommentFragment commentFragment;
    private ChatFragment chatFragment;
    private FansFragment fansFragment;
    private AttentionsFragment attentionsFragment;

    private ContentAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel = new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


//        ImageView imageView=binding.addFriend;
//        ImageView nitceimage=binding.message;

        TabLayout tabLayout = binding.tabLayout;
        ViewPager viewPager = binding.viewPager;

        chatFragment = ChatFragment.newInstance();
        commentFragment = CommentFragment.newInstance();

        fansFragment = FansFragment.newInstance();
        attentionsFragment = AttentionsFragment.newInstance();
        Fragment[] fragments = new Fragment[4];
        fragments[0] = attentionsFragment;
        fragments[1] = fansFragment;
        fragments[2] = chatFragment;
        fragments[3] = commentFragment;
        String[] titles = {"关注", "粉丝", "私信", "评论"};
        adapter = new ContentAdapter(getChildFragmentManager(), fragments, titles);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });
//        nitceimage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getActivity(), MessageActivity.class));
//            }
//        });
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getActivity(), AddFriendActivity.class));
//            }
//        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}