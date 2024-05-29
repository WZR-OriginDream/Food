package com.wzr.food.logic.model;

import java.io.Serializable;

public class ActionStatusBean implements Serializable {
    private int code;
    private String message;
    private Obj obj;

    public class Obj implements Serializable {
        private int shareCount;
        private int shareStatus;

        private int followCount;
        private int followStatus;

        private int collectCount;
        private int collectStatus;

        private int likeCount;
        private int likeStatus;

        private int coinStatus;
        private int coinCount;

        public int getShareCount() {
            return shareCount;
        }

        public void setShareCount(int shareCount) {
            this.shareCount = shareCount;
        }

        public int getShareStatus() {
            return shareStatus;
        }

        public void setShareStatus(int shareStatus) {
            this.shareStatus = shareStatus;
        }

        public int getFollowCount() {
            return followCount;
        }

        public void setFollowCount(int followCount) {
            this.followCount = followCount;
        }

        public int getFollowStatus() {
            return followStatus;
        }

        public void setFollowStatus(int followStatus) {
            this.followStatus = followStatus;
        }

        public int getCollectCount() {
            return collectCount;
        }

        public void setCollectCount(int collectCount) {
            this.collectCount = collectCount;
        }

        public int getCollectStatus() {
            return collectStatus;
        }

        public void setCollectStatus(int collectStatus) {
            this.collectStatus = collectStatus;
        }

        public int getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(int likeCount) {
            this.likeCount = likeCount;
        }

        public int getLikeStatus() {
            return likeStatus;
        }

        public void setLikeStatus(int likeStatus) {
            this.likeStatus = likeStatus;
        }

        public int getCoinStatus() {
            return coinStatus;
        }

        public void setCoinStatus(int coinStatus) {
            this.coinStatus = coinStatus;
        }

        public int getCoinCount() {
            return coinCount;
        }

        public void setCoinCount(int coinCount) {
            this.coinCount = coinCount;
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
