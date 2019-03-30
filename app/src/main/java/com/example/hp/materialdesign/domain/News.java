package com.example.hp.materialdesign.domain;

public class News {

    String title;
    int imageId;
    public News(String title, int imageId){
        this.title = title;
        this.imageId = imageId;
    }
    public String getTitle() {
        return title;
    }

    public int getImageId() {
        return imageId;
    }
}
