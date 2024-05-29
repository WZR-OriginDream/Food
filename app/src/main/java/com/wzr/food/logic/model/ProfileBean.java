package com.wzr.food.logic.model;

import java.io.Serializable;
import java.util.Date;

public class ProfileBean implements Serializable {
    private int code;
    private String message;
    private Obj obj;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Obj getObj() {
        return obj;
    }

    public void setObj(Obj obj) {
        this.obj = obj;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public class Obj implements Serializable{
        private User user;
        private int followeeCount;
        private int followerCount;
        private int userLikeCount;
        private int hasFollowed;

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public int getFolloweeCount() {
            return followeeCount;
        }

        public void setFolloweeCount(int followeeCount) {
            this.followeeCount = followeeCount;
        }

        public int getFollowerCount() {
            return followerCount;
        }

        public void setFollowerCount(int followerCount) {
            this.followerCount = followerCount;
        }

        public int getUserLikeCount() {
            return userLikeCount;
        }

        public void setUserLikeCount(int userLikeCount) {
            this.userLikeCount = userLikeCount;
        }

        public int getHasFollowed() {
            return hasFollowed;
        }

        public void setHasFollowed(int hasFollowed) {
            this.hasFollowed = hasFollowed;
        }

        public class User implements Serializable{

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


}


