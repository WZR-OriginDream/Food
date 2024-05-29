package com.wzr.food.ui.activity;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;
import com.willy.ratingbar.ScaleRatingBar;
import com.wzr.food.R;
import com.wzr.food.databinding.ActivityFoodBinding;
import com.wzr.food.logic.dao.MessageDao;
import com.wzr.food.logic.model.AddCommentBean;
import com.wzr.food.logic.model.ArticleBean;
import com.wzr.food.ui.adapter.CommentAdapter;
import com.wzr.food.ui.adapter.ContentAdapter;
import com.wzr.food.ui.base.BaseActivity;
import com.wzr.food.ui.custom.LottieDialog;
import com.wzr.food.ui.fragment.ContentFragment;
import com.wzr.food.ui.fragment.StoryFragment;
import com.wzr.food.util.WeChatShareUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import de.hdodenhof.circleimageview.CircleImageView;

public class FoodActivity extends BaseActivity {
    private ActivityFoodBinding binding;
    private FoodViewModel foodViewModel;
    private WeChatShareUtils weChatShareUtils;

    private ScaleRatingBar ratingBar;
    private CircleImageView head;
    private AutoCompleteTextView etcomment;
    private ImageView ivcomment, ivrecommend;
    private RecyclerView recyclerView;
    private FloatingActionButton floatcomment;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TextView arttitle, tvLikes, tvCollects, tvCoins, tvShares;
    private ImageView artimage, ivback, ivlikes, ivcollect, ivshare, ivcoin, ivattention;
    private StoryFragment storyFragment;
    private ContentFragment contentFragment;
    private String action;
    private int entityType, entityId, entityUserId;

    private ContentAdapter adapter;
    private CommentAdapter commentAdapter;

    private String image, title, story, username, content, createtime;
    private int userId, likes;
    private List<ArticleBean.Obj.Measures> measuresList;
    private List<ArticleBean.Obj.Ingredient> ingredientList;
    private LinearLayoutManager linearLayoutManager;
    private String comment = null;

    private Boolean isLiked = false, isCollected = false, isShared = false, isCoined = false, isAttentioned = false;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        weChatShareUtils = WeChatShareUtils.getInstance(this);
        foodViewModel = new ViewModelProvider(this).get(FoodViewModel.class);

        ivback.setOnClickListener(v -> onBackPressed());

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int id = bundle.getInt("id");

        //展示数据
        foodViewModel.getArticleLiveData(id).observe(this, articleBean -> {
            image = articleBean.getObj().getPost().getImage();
            title = articleBean.getObj().getPost().getTitle();
            story = articleBean.getObj().getPost().getHistory();
            content = articleBean.getObj().getPost().getContent();
            measuresList = articleBean.getObj().getMeasures();
            ingredientList = articleBean.getObj().getIngredients();
            username = articleBean.getObj().getPost().getUsername();
            userId = articleBean.getObj().getPost().getUserId();
            likes = articleBean.getObj().getPost().getLikes();
            createtime = dateFormat(articleBean.getObj().getPost().getCreateTime());

            actionStatus(id);

            ivlikes.setOnLongClickListener(v -> {
                actionStatus(id);
                if (!isLiked) {
                    action = "like";
                    entityType = 1;
                    entityId = id;
                    entityUserId = userId;
                    action();
                }
                if (!isCollected) {
                    action = "collect";
                    entityType = 1;
                    entityId = id;
                    entityUserId = userId;
                    action();
                }
                if (!isCoined) {
                    action = "coin";
                    entityType = 1;
                    entityId = id;
                    entityUserId = userId;
                    action();
                }
                actionStatus(id);
                checkFourAction();
                // 返回true表示事件已被消费
                return true;
            });

            ivlikes.setOnClickListener(v -> {
                action = "like";
                entityType = 1;
                entityId = id;
                entityUserId = userId;
                action();
            });

            ivcollect.setOnClickListener(v -> {
                action = "collect";
                entityType = 1;
                entityId = id;
                entityUserId = userId;
                action();
            });

            ivcoin.setOnClickListener(v -> {
                action = "coin";
                entityType = 1;
                entityId = id;
                entityUserId = userId;
                action();
            });

            ivshare.setOnClickListener(v -> {
                action = "share";
                entityType = 1;
                entityId = id;
                entityUserId = userId;
                action();
            });

            ivattention.setOnClickListener(v -> {
                action = "follow";
                entityType = 0;
                entityId = userId;
                entityUserId = userId;
                action();
            });

            show();
        });


        floatcomment.setOnClickListener(v -> {
            BottomSheetDialog shareDialog;
            shareDialog = new BottomSheetDialog(FoodActivity.this, R.style.BottomSheetDialog);
            View aview = View.inflate(FoodActivity.this, R.layout.layout_popup_share, null);
            shareDialog.setContentView(aview);
            shareDialog.show();

            recyclerView = aview.findViewById(R.id.recycler_view_comment);
            showComment(id);

            etcomment = aview.findViewById(R.id.et_comment);
            etcomment.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    comment = s.toString();
                }
            });

            ivcomment = aview.findViewById(R.id.iv_comment);
            ivcomment.setOnClickListener(v1 -> {
                if (comment == null || comment.equals("")) {
                    Toast.makeText(FoodActivity.this, "还未输入内容", Toast.LENGTH_SHORT).show();
                } else {
                    AddCommentBean addCommentBean = new AddCommentBean(comment, 0, MessageDao.getSaveLoginBean().getObj().getNickname(), MessageDao.getSaveLoginBean().getObj().getUsername(), commentAdapter.getParentId(), id, MessageDao.getSaveLoginBean().getObj().getId());
                    foodViewModel.addCommentLiveData(addCommentBean).observe(FoodActivity.this, myCommentBean -> {
                        if (myCommentBean.getCode() == 200) {

                        }
                    });
                    etcomment.setText("");
                    showComment(id);
                }

            });

            shareDialog.setContentView(aview);
            shareDialog.show();
        });
    }


    @Override
    protected void initLayout() {
        binding = ActivityFoodBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void initView() {
        arttitle = binding.artTitle;
        artimage = binding.artImage;
        floatcomment = binding.floatComment;
        tabLayout = binding.foodTabLayout;
        viewPager = binding.foodViewPager;
        head = binding.headImage;
        ivback = binding.ivBack;
        ratingBar = binding.simpleRatingBar;
        ivrecommend = binding.ivRecommend;
        ivattention = binding.ivAttention;

        ivlikes = binding.ivLikes;
        ivcollect = binding.ivCollect;
        ivshare = binding.ivShare;
        ivcoin = binding.ivCoin;

        tvLikes = binding.tvLikes;
        tvCollects = binding.tvCollect;
        tvCoins = binding.tvCoin;
        tvShares = binding.tvShare;
    }

    public void show() {
        contentFragment = ContentFragment.newInstance(content, measuresList, ingredientList);
        storyFragment = StoryFragment.newInstance(story, createtime);

        arttitle.setText(title);
        Glide.with(getApplicationContext()).load(image).into(artimage);

        Fragment[] fragments = new Fragment[2];
        fragments[0] = storyFragment;
        fragments[1] = contentFragment;
        String[] titles = {"故事", "菜谱"};
        adapter = new ContentAdapter(getSupportFragmentManager(), fragments, titles);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        //点赞星数
        ratingBar.setRating(5);
        if (likes >= 0 && likes < 50) {
            ratingBar.setRating(1);
        }
        if (likes >= 50 && likes < 100) {
            ratingBar.setRating(2);
        }
        if (likes >= 100 && likes < 150) {
            ratingBar.setRating(3);
        }
        if (likes >= 150 && likes < 200) {
            ratingBar.setRating(4);
        }
        if (likes >= 200 && likes < 250) {
            ratingBar.setRating(5);
        }

        if (likes >= 300) {
            ivrecommend.setVisibility(View.VISIBLE);
            ratingBar.setRating(5);
        }

        head.setOnClickListener(v -> {
            Intent intent = new Intent(FoodActivity.this, OtherProfileActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("userId", userId);
            intent.putExtras(bundle);
            startActivity(intent);
        });
    }


    @SuppressLint("SetTextI18n")
    public void action() {
        foodViewModel.getActionLiveData(action, entityType, entityId, entityUserId).observe(this, actionBean -> {
            long count = actionBean.getObj().getEntityActionCount();
            Log.d("wen", count + "lllll");
            boolean isActioned = actionBean.getObj().getEntityActionStatus() == 1;
            switch (action) {
                case "like":
                    tvLikes.setText(count + "");
                    ivlikes.setImageResource(isActioned ? R.drawable.ic_likes_66_red : R.drawable.ic_likes_66);
                    Toast.makeText(FoodActivity.this, isActioned ? "点赞成功" : "取消点赞", Toast.LENGTH_SHORT).show();
                    break;
                case "collect":
                    tvCollects.setText(count + "");
                    ivcollect.setImageResource(isActioned ? R.drawable.ic_collect_66_red : R.drawable.ic_collect_66);
                    Toast.makeText(FoodActivity.this, isActioned ? "收藏成功" : "取消收藏", Toast.LENGTH_SHORT).show();
                    break;
                case "coin":
                    tvCoins.setText(count + "");
                    ivcoin.setImageResource(isActioned ? R.drawable.ic_coin_66_red : R.drawable.ic_coin_66);
                    Toast.makeText(FoodActivity.this, isActioned ? "投币成功" : "取消投币", Toast.LENGTH_SHORT).show();
                    break;
                case "share":
                    tvShares.setText(count + "");
                    ivshare.setImageResource(isActioned ? R.drawable.ic_share_66_red : R.drawable.ic_share_66);
                    if (isActioned) {
                        // 微信api分享或原生分享的代码
                        share(FoodActivity.this, content, image, title);
                        Toast.makeText(FoodActivity.this, "转发成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(FoodActivity.this, "取消转发", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case "follow":
                    ivattention.setImageResource(isActioned ? R.drawable.ic_attention_66 : R.drawable.ic_attention_66_red);
                    Toast.makeText(FoodActivity.this, isActioned ? "关注成功" : "取消关注", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    // 可选：处理未知action的情况
                    break;
            }
        });
    }

    public void actionStatus(int id) {
        foodViewModel.getActionStatusLiveData(1, id, userId).observe(this, actionStatusBean -> {
            if (actionStatusBean.getObj().getLikeCount() > 0) {
                tvLikes.setText(actionStatusBean.getObj().getLikeCount() + "");
            }

            if (actionStatusBean.getObj().getCollectCount() > 0) {
                tvCollects.setText(actionStatusBean.getObj().getCollectCount() + "");
            }

            if (actionStatusBean.getObj().getCoinCount() > 0) {
                tvCoins.setText(actionStatusBean.getObj().getCoinCount() + "");
            }

            if (actionStatusBean.getObj().getShareCount() > 0) {
                tvShares.setText(actionStatusBean.getObj().getShareCount() + "");
            }

            isLiked = actionStatusBean.getObj().getLikeStatus() == 1;
            isCollected = actionStatusBean.getObj().getCollectStatus() == 1;
            isCoined = actionStatusBean.getObj().getCoinStatus() == 1;
            isShared = actionStatusBean.getObj().getShareStatus() == 1;
            isAttentioned = actionStatusBean.getObj().getFollowStatus() == 1;

            if (isLiked) {
                ivlikes.setImageResource(R.drawable.ic_likes_66_red);
            } else {
                ivlikes.setImageResource(R.drawable.ic_likes_66);
            }

            if (isCollected) {
                ivcollect.setImageResource(R.drawable.ic_collect_66_red);
            } else {
                ivcollect.setImageResource(R.drawable.ic_collect_66);
            }

            if (isCoined) {
                ivcoin.setImageResource(R.drawable.ic_coin_66_red);
            } else {
                ivcoin.setImageResource(R.drawable.ic_coin_66);
            }

            if (isShared) {
                ivshare.setImageResource(R.drawable.ic_share_66_red);
            } else {
                ivshare.setImageResource(R.drawable.ic_share_66);
            }

            if (isAttentioned) {
                ivattention.setImageResource(R.drawable.ic_attention_66);
            } else {
                ivattention.setImageResource(R.drawable.ic_attention_66_red);
            }

        });
    }

    @SuppressLint("NotifyDataSetChanged")
    public void showComment(int id) {
        foodViewModel.getCommentLiveData(id).observe(FoodActivity.this, commentBean -> {
            linearLayoutManager = new LinearLayoutManager(FoodActivity.this);
            recyclerView.setLayoutManager(linearLayoutManager);
            commentAdapter = new CommentAdapter(FoodActivity.this, commentBean.getObj());
            recyclerView.setAdapter(commentAdapter);
            commentAdapter.notifyDataSetChanged();
        });
    }

    private String dateFormat(String mdate) {
        String formatDate = null;
        // 创建 SimpleDateFormat 对象，用于解析原始格式
        // 创建 SimpleDateFormat 对象，用于解析包含时区的 ISO 8601 格式字符串
        @SuppressLint("SimpleDateFormat") SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        isoFormat.setTimeZone(TimeZone.getTimeZone("GMT"));  // 设置时区为 GMT

        try {
            // 解析字符串到 Date 对象
            Date date = isoFormat.parse(mdate);

            // 创建 SimpleDateFormat 对象，用于格式化成新的格式（不包含毫秒）
            @SuppressLint("SimpleDateFormat") SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            targetFormat.setTimeZone(TimeZone.getDefault());  // 使用默认时区

            // 将 Date 对象格式化为字符串
            formatDate = targetFormat.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatDate;
    }

    private void checkFourAction() {
        showLottieDialog();
        Toast.makeText(FoodActivity.this, "达成三连·阿里嘎多~", Toast.LENGTH_SHORT).show();
    }

    public void showLottieDialog() {
        LottieDialog lottieDialog = new LottieDialog(this, R.style.MyDialogStyle);
        lottieDialog.show();
    }


    private void share(String content) {
        // 创建一个分享文本的Intent
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        // 设置分享的内容
        sendIntent.putExtra(Intent.EXTRA_TEXT, content);
        // 指定数据类型为文本
        sendIntent.setType("text/plain");

        // 检查是否安装了微信，如果安装了微信，则设置微信的包名
//        if (isWeChatAppInstalled()) {
//            sendIntent.setPackage("com.tencent.mm");
//        }

        // 创建一个选择器Intent
        Intent shareIntent = Intent.createChooser(sendIntent, "分享到");

        // 确保分享Intent是安全的
        if (shareIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(shareIntent);
        }
    }

    private void share(Context context, String content, String imageUrl, String title) {
        Log.d("wen", "分享");
        // 第1步：下载网络图片并保存到文件系统（使用Glide作为示例）
        Glide.with(context)
                .asBitmap()
                .load(imageUrl)
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        // 第2步：保存图片到文件并获取URI
                        File file = new File(context.getExternalFilesDir("."), title + ".jpg");
                        Log.d("wen", file.getAbsolutePath());
                        try {
                            FileOutputStream fos = new FileOutputStream(file);
                            resource.compress(Bitmap.CompressFormat.JPEG, 32, fos);
                            fos.flush();
                            fos.close();

                            // 对于Android 7.0及以上版本使用FileProvider
                            Uri imageUri;
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                imageUri = FileProvider.getUriForFile(
                                        context,
                                        context.getPackageName() + ".fileprovider",
                                        file);
                            } else {
                                imageUri = Uri.fromFile(file);
                            }
                            Log.d("wen", String.valueOf(imageUri));

                            // 第3步：创建分享Intent
                            Intent shareIntent = new Intent();
                            shareIntent.setAction(Intent.ACTION_SEND);

                            shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
                            shareIntent.setType("image/jpeg");

                            shareIntent.putExtra(Intent.EXTRA_TEXT, content);

                            // 检查是否安装了微信，如果安装了微信，则设置微信的包名
//                            if (isWeChatAppInstalled()) {
//                                shareIntent.setPackage("com.tencent.mm");
//                            }

                            // 授予临时访问权限
                            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                            // 创建分享选择器
                            Intent chooserIntent = Intent.createChooser(shareIntent, "分享到");

                            // 确保Intent是可解析的
                            if (shareIntent.resolveActivity(context.getPackageManager()) != null) {
                                context.startActivity(chooserIntent);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                        // 资源清除时的回调
                    }
                });
    }

    // 检查微信是否安装的方法
    private boolean isWeChatAppInstalled() {
        PackageManager packageManager = getPackageManager();
        try {
            packageManager.getPackageInfo("com.tencent.mm", 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

}