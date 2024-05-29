package com.wzr.food.logic.model;

import java.io.Serializable;
import java.util.List;

public class CreateArticleBean implements Serializable {
    private List<Ingredients> ingredients;
    private List<Measures> measures;
    private Post post;

    public CreateArticleBean(List<Ingredients> ingredients, List<Measures> measures, Post post) {
        this.ingredients = ingredients;
        this.measures = measures;
        this.post = post;
    }

    public void setIngredients(List<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Ingredients> getIngredients() {
        return ingredients;
    }

    public void setMeasures(List<Measures> measures) {
        this.measures = measures;
    }

    public List<Measures> getMeasures() {
        return measures;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Post getPost() {
        return post;
    }

    public static class Ingredients implements Serializable{

        private String amount;
        private String name;

        public Ingredients() {

        }

        public Ingredients(String amount, String name) {
            this.amount = amount;
            this.name = name;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getAmount() {
            return amount;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

    }

    public static class Measures implements Serializable{

        private String content;
        private int id;
        private int number;
        private String picture;
        private int postId;

        public Measures() {

        }

        public Measures(String content, int id, int number, String picture, int postId) {
            this.content = content;
            this.id = id;
            this.number = number;
            this.picture = picture;
            this.postId = postId;
        }

        public int getPostId() {
            return postId;
        }

        public void setPostId(int postId) {
            this.postId = postId;
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

        public void setNumber(int number) {
            this.number = number;
        }

        public int getNumber() {
            return number;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getPicture() {
            return picture;
        }

    }

    public static class Post implements Serializable{

        private String content;
        private String createTime;
        private String history;
        private int id;
        private String image;
        private String ingredient;
        private int likes;
        private String nickname;
        private String username;
        private String title;
        private int type;
        private int userId;

        public Post(String content, String createTime, String history, int id, String image, String ingredient, int likes, String nickname, String username, String title, int type, int userId) {
            this.content = content;
            this.createTime = createTime;
            this.history = history;
            this.id = id;
            this.image = image;
            this.ingredient = ingredient;
            this.likes = likes;
            this.nickname = nickname;
            this.username = username;
            this.title = title;
            this.type = type;
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

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setHistory(String history) {
            this.history = history;
        }

        public String getHistory() {
            return history;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getImage() {
            return image;
        }

        public void setIngredient(String ingredient) {
            this.ingredient = ingredient;
        }

        public String getIngredient() {
            return ingredient;
        }

        public void setLikes(int likes) {
            this.likes = likes;
        }

        public int getLikes() {
            return likes;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getNickname() {
            return nickname;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getType() {
            return type;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getUserId() {
            return userId;
        }

    }
}
