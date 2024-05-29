package com.wzr.food.logic.model;

import java.io.Serializable;
import java.util.List;

public class CommentBean implements Serializable {
    private int code;
    private String message;
    private List<Obj> obj;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Obj> getObj() {
        return obj;
    }

    public void setObj(List<Obj> obj) {
        this.obj = obj;
    }

    public class Obj implements Serializable{
        private ParentComment parentComment;
        private List<ChildComment> childComment;

        public ParentComment getParentComment() {
            return parentComment;
        }

        public void setParentComment(ParentComment parentComment) {
            this.parentComment = parentComment;
        }

        public List<ChildComment> getChildComment() {
            return childComment;
        }

        public void setChildComment(List<ChildComment> childComment) {
            this.childComment = childComment;
        }

        public class ParentComment implements Serializable{
            private int id;
            private int userId;
            private String nickname;
            private String username;
            private int postId;
            private String content;
            private int parentId;
            private String createTime;

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public int getPostId() {
                return postId;
            }

            public void setPostId(int postId) {
                this.postId = postId;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getParentId() {
                return parentId;
            }

            public void setParentId(int parentId) {
                this.parentId = parentId;
            }
        }

        public class ChildComment implements Serializable{
            private int id;
            private int userId;
            private String nickname;
            private String username;
            private int postId;
            private String content;
            private int parentId;
            private String createTime;

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public int getPostId() {
                return postId;
            }

            public void setPostId(int postId) {
                this.postId = postId;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getParentId() {
                return parentId;
            }

            public void setParentId(int parentId) {
                this.parentId = parentId;
            }
        }
    }
}
