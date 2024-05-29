package com.wzr.food.logic.model;

import java.io.Serializable;

public class RegisterBean implements Serializable {

    private String nickname;
    private String password;
    private String username;

    public RegisterBean(String nickname, String password, String username) {
        this.nickname = nickname;
        this.password = password;
        this.username = username;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

}
