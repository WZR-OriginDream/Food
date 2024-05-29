package com.wzr.food.logic.model;

import java.io.Serializable;

public class AddCommentBean implements Serializable {
    private String content;
    private int id;
    private String nickname;
    private String username;
    private int parentId;
    private int postId;
    private int userId;

    public AddCommentBean(String content, int id, String nickname, String username, int parentId, int postId, int userId) {
        this.content = content;
        this.id = id;
        this.nickname = nickname;
        this.username = username;
        this.parentId = parentId;
        this.postId = postId;
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String getContent() {
        return content;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getNickname() {
        return nickname;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
    public int getParentId() {
        return parentId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }
    public int getPostId() {
        return postId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getUserId() {
        return userId;
    }
}
