package com.wzr.food.logic.model;

public class BannerItemBean {
    private String imageUrl;
    private int id;

    public BannerItemBean(String imageUrl, int id) {
        this.imageUrl = imageUrl;
        this.id = id;
    }

    // Getter和Setter方法
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
