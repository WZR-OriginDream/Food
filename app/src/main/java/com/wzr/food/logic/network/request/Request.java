package com.wzr.food.logic.network.request;


import com.wzr.food.logic.network.creator.ServiceCreator;
import com.wzr.food.logic.network.service.ActionService;
import com.wzr.food.logic.network.service.ActionStatusService;
import com.wzr.food.logic.network.service.AddAttentionsService;
import com.wzr.food.logic.network.service.AddCommentService;
import com.wzr.food.logic.network.service.ArticleService;
import com.wzr.food.logic.network.service.BriefsService;
import com.wzr.food.logic.network.service.CommentService;
import com.wzr.food.logic.network.service.CreateArticleService;
import com.wzr.food.logic.network.service.CreateLiveService;
import com.wzr.food.logic.network.service.DeleteArticleService;
import com.wzr.food.logic.network.service.DeleteLiveService;
import com.wzr.food.logic.network.service.GetAttentionsService;
import com.wzr.food.logic.network.service.GetFansService;
import com.wzr.food.logic.network.service.GetLivesService;
import com.wzr.food.logic.network.service.GetPullService;
import com.wzr.food.logic.network.service.LoginService;
import com.wzr.food.logic.network.service.MyCommentService;
import com.wzr.food.logic.network.service.PhotoService;
import com.wzr.food.logic.network.service.PostActionListService;
import com.wzr.food.logic.network.service.ProfileService;
import com.wzr.food.logic.network.service.RankService;
import com.wzr.food.logic.network.service.RecommendService;
import com.wzr.food.logic.network.service.RegisterService;
import com.wzr.food.logic.network.service.SearchService;
import com.wzr.food.logic.network.service.TypeArticleService;
import com.wzr.food.logic.network.service.UserAction2ListService;
import com.wzr.food.logic.network.service.UserActionListService;
import com.wzr.food.logic.network.service.UserArticleService;

public class Request {
    public static final ArticleService articleService = ServiceCreator.getRetrofit().create(ArticleService.class);
    public static final BriefsService briefsService = ServiceCreator.getRetrofit().create(BriefsService.class);
    public static final SearchService searchService = ServiceCreator.getRetrofit().create(SearchService.class);
    public static final CommentService commentService = ServiceCreator.getRetrofit().create(CommentService.class);
    public static final TypeArticleService typeArticleService = ServiceCreator.getRetrofit().create(TypeArticleService.class);
    public static final AddCommentService addCommentService = ServiceCreator.getRetrofit().create(AddCommentService.class);
    public static final CreateArticleService createArticleService = ServiceCreator.getRetrofit().create(CreateArticleService.class);
    public static final PhotoService photoService = ServiceCreator.getRetrofit().create(PhotoService.class);
    public static final UserArticleService userArticleService = ServiceCreator.getRetrofit().create(UserArticleService.class);
    public static final RegisterService registerService = ServiceCreator.getRetrofit().create(RegisterService.class);
    public static final LoginService loginService = ServiceCreator.getRetrofit().create(LoginService.class);
    public static final DeleteArticleService deleteArticleService = ServiceCreator.getRetrofit().create(DeleteArticleService.class);
    public static final CreateLiveService createLiveService = ServiceCreator.getRetrofit().create(CreateLiveService.class);
    public static final DeleteLiveService deleteLiveService = ServiceCreator.getRetrofit().create(DeleteLiveService.class);
    public static final AddAttentionsService addAttentionsService = ServiceCreator.getRetrofit().create(AddAttentionsService.class);
    public static final GetAttentionsService getAttentionsService = ServiceCreator.getRetrofit().create(GetAttentionsService.class);
    public static final GetFansService getFansService = ServiceCreator.getRetrofit().create(GetFansService.class);
    public static final GetLivesService getLivesService = ServiceCreator.getRetrofit().create(GetLivesService.class);
    public static final GetPullService getPullService = ServiceCreator.getRetrofit().create(GetPullService.class);

    public static final ActionService actionService = ServiceCreator.getRetrofit().create(ActionService.class);
    public static final ActionStatusService actionStatusService = ServiceCreator.getRetrofit().create(ActionStatusService.class);
    public static final PostActionListService postActionListService = ServiceCreator.getRetrofit().create(PostActionListService.class);
    public static final UserActionListService userActionListService = ServiceCreator.getRetrofit().create(UserActionListService.class);
    public static final UserAction2ListService userAction2ListService = ServiceCreator.getRetrofit().create(UserAction2ListService.class);
    public static final ProfileService profileService = ServiceCreator.getRetrofit().create(ProfileService.class);
    public static final MyCommentService myCommentService = ServiceCreator.getRetrofit().create(MyCommentService.class);
    public static final RecommendService recommendService = ServiceCreator.getRetrofit().create(RecommendService.class);
    public static final RankService rankService = ServiceCreator.getRetrofit().create(RankService.class);

}
