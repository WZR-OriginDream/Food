package com.wzr.food.logic.model;

import java.io.Serializable;
import java.util.List;

public class MyCommentBean implements Serializable {
    private int code;
    private String message;
    private List<Obj> obj;

    public List<Obj> getObj() {
        return obj;
    }

    public void setObj(List<Obj> obj) {
        this.obj = obj;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }


    public class Obj implements Serializable {
        private int id;
        private int userId;
        private String nickname;
        private String username;
        private int postId;
        private String content;
        private int parentId;
        private String createTime;
        private Posts posts;

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

        public Posts getPosts() {
            return posts;
        }

        public void setPosts(Posts posts) {
            this.posts = posts;
        }

        public class Posts implements Serializable {
            private int id;
            private String title;
            private String image;
            private int userId;
            private String nickname;
            private String username;
            private String history;
            private String content;
            private String ingredient;
            private String createTime;
            private int likes;
            private int type;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
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

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getHistory() {
                return history;
            }

            public void setHistory(String history) {
                this.history = history;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getIngredient() {
                return ingredient;
            }

            public void setIngredient(String ingredient) {
                this.ingredient = ingredient;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public int getLikes() {
                return likes;
            }

            public void setLikes(int likes) {
                this.likes = likes;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getUserId() {
            return userId;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getNickname() {
            return nickname;
        }

        public void setPostId(int postId) {
            this.postId = postId;
        }

        public int getPostId() {
            return postId;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getContent() {
            return content;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public int getParentId() {
            return parentId;
        }

    }
}
