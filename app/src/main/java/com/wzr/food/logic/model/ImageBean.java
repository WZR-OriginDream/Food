package com.wzr.food.logic.model;

import java.io.Serializable;

public class ImageBean implements Serializable {
    private int code;
    private String message;
    private String obj;

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

    public String getObj() {
        return obj;
    }

    public void setObj(String obj) {
        this.obj = obj;
    }
}
