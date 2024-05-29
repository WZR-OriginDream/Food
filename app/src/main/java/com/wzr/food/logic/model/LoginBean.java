package com.wzr.food.logic.model;

import java.io.Serializable;
import java.util.Date;

public class LoginBean implements Serializable {

    private int code;
    private String message;
    private Obj obj;

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

    public void setObj(Obj obj) {
        this.obj = obj;
    }

    public Obj getObj() {
        return obj;
    }

    public class Obj implements Serializable{

        private int id;
        private String username;
        private String password;
        private String nickname;
        private String image;
        private int fan;
        private int attention;
        private String token;
        private Date createTime;

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

        public void setFan(int fan) {
            this.fan = fan;
        }

        public int getFan() {
            return fan;
        }

        public void setAttention(int attention) {
            this.attention = attention;
        }

        public int getAttention() {
            return attention;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }

        public void setCreateTime(Date createTime) {
            this.createTime = createTime;
        }

        public Date getCreateTime() {
            return createTime;
        }

    }
}
