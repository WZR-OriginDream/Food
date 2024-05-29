package com.wzr.food.logic.model;

import java.io.Serializable;

public class ActionBean implements Serializable {
    private int code;
    private String message;
    private Obj obj;

    public class Obj implements Serializable{
        private long entityActionCount;
        private int entityActionStatus;

        public long getEntityActionCount() {
            return entityActionCount;
        }

        public void setEntityActionCount(long entityActionCount) {
            this.entityActionCount = entityActionCount;
        }

        public int getEntityActionStatus() {
            return entityActionStatus;
        }

        public void setEntityActionStatus(int entityActionStatus) {
            this.entityActionStatus = entityActionStatus;
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

    public Obj getObj() {
        return obj;
    }

    public void setObj(Obj obj) {
        this.obj = obj;
    }
}
