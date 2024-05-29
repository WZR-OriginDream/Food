package com.wzr.food.repository;

import com.wzr.food.logic.network.request.Request;
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

public class Repository {
    private Repository() {

    }

    public synchronized static Repository getInstance() {
        return new Repository();
    }

    public static TypeArticleService getTypeArticleServie() {
        return Request.typeArticleService;
    }

    public static SearchService getSearchService() {
        return Request.searchService;
    }

    public static BriefsService getBriefsService() {
        return Request.briefsService;
    }

    public static CommentService getCommentService() {
        return Request.commentService;
    }

    public static ArticleService getArticleService() {
        return Request.articleService;
    }

    public static AddCommentService getAddCommentService() {
        return Request.addCommentService;
    }

    public static CreateArticleService getCreateArticleServive() {
        return Request.createArticleService;
    }
    public static PhotoService getPhotoService() {
        return Request.photoService;
    }

    public static UserArticleService getUserArticleService() {
        return Request.userArticleService;
    }

    public static RegisterService getRegisterService() {
        return Request.registerService;
    }

    public static LoginService getLoginService() {
        return Request.loginService;
    }

    public static DeleteArticleService getDeleteArticleService() {
        return Request.deleteArticleService;
    }

    public static CreateLiveService getCreateLiveService() {
        return Request.createLiveService;
    }

    public static DeleteLiveService getDeleteLiveService() {
        return Request.deleteLiveService;
    }

    public static GetAttentionsService getAttentionsService() {
        return Request.getAttentionsService;
    }

    public static GetFansService getFansService() {
        return Request.getFansService;
    }

    public static AddAttentionsService addAttentionsService() {
        return Request.addAttentionsService;
    }

    public static GetPullService getPullService() {
        return Request.getPullService;
    }

    public static GetLivesService getLivesService() {
        return Request.getLivesService;
    }

    public static ActionService getActionService() {
        return Request.actionService;
    }
    public static ActionStatusService getActionStatusService() {
        return Request.actionStatusService;
    }
    public static PostActionListService postActionListService() {
        return Request.postActionListService;
    }
    public static UserActionListService userActionListService() {
        return Request.userActionListService;
    }
    public static UserAction2ListService userAction2ListService() {
        return Request.userAction2ListService;
    }
    public static ProfileService profileService() {
        return Request.profileService;
    }
    public static MyCommentService myCommentService() {
        return Request.myCommentService;
    }
    public static RecommendService recommendService() {
        return Request.recommendService;
    }
    public static RankService rankService() {
        return Request.rankService;
    }

}
