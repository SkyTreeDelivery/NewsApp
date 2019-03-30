package com.example.hp.materialdesign.domain.Join;

import android.provider.ContactsContract;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class UserJoinNewsBrowse {
    @Id
    Long id;
    Long userId;
    Long newsId;
    Date browseDate;
    @Generated(hash = 1811596625)
    public UserJoinNewsBrowse(Long id, Long userId, Long newsId, Date browseDate) {
        this.id = id;
        this.userId = userId;
        this.newsId = newsId;
        this.browseDate = browseDate;
    }
    @Generated(hash = 680839902)
    public UserJoinNewsBrowse() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getUserId() {
        return this.userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getNewsId() {
        return this.newsId;
    }
    public void setNewsId(Long newsId) {
        this.newsId = newsId;
    }
    public Date getBrowseDate() {
        return this.browseDate;
    }
    public void setBrowseDate(Date browseDate) {
        this.browseDate = browseDate;
    }
}
