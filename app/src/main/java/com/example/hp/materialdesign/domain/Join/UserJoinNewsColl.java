package com.example.hp.materialdesign.domain.Join;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Unique;

@Entity
public class UserJoinNewsColl {
    @Id(autoincrement = true)
    Long id;
    Long  userId;
    Long newsId;
    @Generated(hash = 359836838)
    public UserJoinNewsColl(Long id, Long userId, Long newsId) {
        this.id = id;
        this.userId = userId;
        this.newsId = newsId;
    }
    @Generated(hash = 1939826523)
    public UserJoinNewsColl() {
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

}
