package com.wzr.food.logic.model;

import java.io.Serializable;

public class CreateResponseBean implements Serializable {

    private int code;
    private String message;
    private String obj;

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

    public void setObj(String obj) {
        this.obj = obj;
    }

    public String getObj() {
        return obj;
    }

}
