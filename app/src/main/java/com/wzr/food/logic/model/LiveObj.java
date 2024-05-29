package com.wzr.food.logic.model;

import java.io.Serializable;

public class LiveObj implements Serializable{

    private Ret ret;
    private String requestId;
    private int code;

    public void setRet(Ret ret) {
        this.ret = ret;
    }

    public Ret getRet() {
        return ret;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public class Ret implements Serializable {

        private String httpPullUrl;
        private String hlsPullUrl;
        private String rtmpPullUrl;
        private String name;
        private String pushUrl;
        private long ctime;
        private String cid;

        public void setHttpPullUrl(String httpPullUrl) {
            this.httpPullUrl = httpPullUrl;
        }

        public String getHttpPullUrl() {
            return httpPullUrl;
        }

        public void setHlsPullUrl(String hlsPullUrl) {
            this.hlsPullUrl = hlsPullUrl;
        }

        public String getHlsPullUrl() {
            return hlsPullUrl;
        }

        public void setRtmpPullUrl(String rtmpPullUrl) {
            this.rtmpPullUrl = rtmpPullUrl;
        }

        public String getRtmpPullUrl() {
            return rtmpPullUrl;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setPushUrl(String pushUrl) {
            this.pushUrl = pushUrl;
        }

        public String getPushUrl() {
            return pushUrl;
        }

        public void setCtime(long ctime) {
            this.ctime = ctime;
        }

        public long getCtime() {
            return ctime;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getCid() {
            return cid;
        }

    }

}

