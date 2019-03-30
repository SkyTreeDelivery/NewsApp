package com.example.hp.materialdesign.domain;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class ImageUrl implements Serializable {

    static final long serialVersionUID = 2;
    @Id(autoincrement = true)
    long id;
    long newsId;  //外键

    public ImageUrl(String imageUrl, int positon, int height, int weight) {
        this.imageUrl = imageUrl;
        this.positon = positon;
        this.height = height;
        this.weight = weight;
    }

    String imageUrl;
    int positon;
    int height;
    int weight;
    @Generated(hash = 342224924)
    public ImageUrl(long id, long newsId, String imageUrl, int positon, int height,
            int weight) {
        this.id = id;
        this.newsId = newsId;
        this.imageUrl = imageUrl;
        this.positon = positon;
        this.height = height;
        this.weight = weight;
    }
    @Generated(hash = 792005330)
    public ImageUrl() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getNewsId() {
        return this.newsId;
    }
    public void setNewsId(long newsId) {
        this.newsId = newsId;
    }
    public String getImageUrl() {
        return this.imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public int getPositon() {
        return this.positon;
    }
    public void setPositon(int positon) {
        this.positon = positon;
    }
    public int getHeight() {
        return this.height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public int getWeight() {
        return this.weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }

}
