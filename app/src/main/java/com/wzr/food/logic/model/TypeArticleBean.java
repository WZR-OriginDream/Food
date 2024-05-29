package com.wzr.food.logic.model;

import java.io.Serializable;
import java.util.List;

public class TypeArticleBean implements Serializable {
    private int code;
    private String message;
    private List<Obj> obj;

    public class Obj implements Serializable {
        private Post post;
        private List<Measures> measures;
        private List<Ingredient> ingredients;

        public List<Ingredient> getIngredients() {
            return ingredients;
        }

        public void setIngredients(List<Ingredient> ingredients) {
            this.ingredients = ingredients;
        }

        public Post getPost() {
            return post;
        }

        public void setPost(Post post) {
            this.post = post;
        }

        public List<Measures> getMeasures() {
            return measures;
        }

        public void setMeasures(List<Measures> measures) {
            this.measures = measures;
        }

        public class Post implements Serializable {
            private int id;
            private String title;
            private String image;
            private String userId;
            private String nickname;
            private String username;
            private String history;
            private String content;
            private String ingredient;
            private String createTime;
            private int likes;
            private int type;

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

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
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

        public class Measures implements Serializable {
            private int id;
            private int number;
            private String content;
            private String picture;
            private int postId;

            public int getPostId() {
                return postId;
            }

            public void setPostId(int postId) {
                this.postId = postId;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }
        }

        private class Ingredient implements Serializable {
            private String name;
            private String amount;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }
        }
    }

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
}

