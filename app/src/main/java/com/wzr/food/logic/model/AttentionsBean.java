package com.wzr.food.logic.model;

import java.io.Serializable;
import java.util.List;

public class AttentionsBean implements Serializable {

    private int code;
    private String message;
    private List<Obj> obj;

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

    public void setObj(List<Obj> obj) {
        this.obj = obj;
    }

    public List<Obj> getObj() {
        return obj;
    }

    public class Obj implements Serializable{

        private int id;
        private String username;
        private String password;
        private String nickname;
        private String image;
        private String fan;
        private String attention;
        private String token;
        private String createTime;

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUsername() {
            return username;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPassword() {
            return password;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getNickname() {
            return nickname;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getImage() {
            return image;
        }

        public void setFan(String fan) {
            this.fan = fan;
        }

        public String getFan() {
            return fan;
        }

        public void setAttention(String attention) {
            this.attention = attention;
        }

        public String getAttention() {
            return attention;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }

    }

}
