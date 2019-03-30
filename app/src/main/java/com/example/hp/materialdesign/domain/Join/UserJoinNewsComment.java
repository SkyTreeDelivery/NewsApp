package com.example.hp.materialdesign.domain.Join;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class UserJoinNewsComment {
    @Id
    Long id;
    Long userId;
    Long newsId;
    String comment;
    Date commentDate;
    @Generated(hash = 776446148)
    public UserJoinNewsComment(Long id, Long userId, Long newsId, String comment,
            Date commentDate) {
        this.id = id;
        this.userId = userId;
        this.newsId = newsId;
        this.comment = comment;
        this.commentDate = commentDate;
    }
    @Generated(hash = 85843277)
    public UserJoinNewsComment() {
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
    public String getComment() {
        return this.comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public Date getCommentDate() {
        return this.commentDate;
    }
    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }
}
