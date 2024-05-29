package com.wzr.food.logic.model;

import java.io.Serializable;

public class PullBean implements Serializable {

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

        private Ret ret;
        private int code;
        private String requestId;

        public void setRet(Ret ret) {
            this.ret = ret;
        }

        public Ret getRet() {
            return ret;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public void setRequestId(String requestId) {
            this.requestId = requestId;
        }

        public String getRequestId() {
            return requestId;
        }

        public class Ret implements Serializable{

            private String httpPullUrl;
            private String hlsPullUrl;
            private String pushUrl;
            private String rtmpPullUrl;
            private String name;
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

            public void setPushUrl(String pushUrl) {
                this.pushUrl = pushUrl;
            }
            public String getPushUrl() {
                return pushUrl;
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

        }

    }
}
