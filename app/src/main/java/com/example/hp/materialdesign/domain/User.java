package com.example.hp.materialdesign.domain;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Unique;

import com.example.hp.materialdesign.domain.Join.UserJoinNewsBrowse;
import com.example.hp.materialdesign.domain.Join.UserJoinNewsColl;
import com.example.hp.materialdesign.domain.Join.UserJoinNewsComment;
import com.example.hp.materialdesign.greenDao.DaoSession;
import com.example.hp.materialdesign.greenDao.NewsDetailDao;
import com.example.hp.materialdesign.greenDao.UserDao;

@Entity
public class User {

    @Id(autoincrement = true)
    Long id;
    @Unique
    Long userid;
    String email;
    String password;
    String phone;
    String nickname;
    String pictrue;
    @ToMany
    @JoinEntity(entity = UserJoinNewsColl.class,sourceProperty = "userId",targetProperty = "newsId")
    List<NewsDetail> newsDetails;
    @ToMany
    @JoinEntity(entity = UserJoinNewsBrowse.class,sourceProperty = "userId",targetProperty = "newsId")
    List<NewsDetail> browseNewsDetails;
    @ToMany
    @JoinEntity(entity = UserJoinNewsComment.class,sourceProperty = "userId",targetProperty = "newsId")
    List<NewsDetail> commentNewsDetails;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1507654846)
    private transient UserDao myDao;
    @Generated(hash = 338954627)
    public User(Long id, Long userid, String email, String password, String phone, String nickname,
            String pictrue) {
        this.id = id;
        this.userid = userid;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.nickname = nickname;
        this.pictrue = pictrue;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public Long getUserid() {
        return this.userid;
    }
    public void setUserid(Long userid) {
        this.userid = userid;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getNickname() {
        return this.nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getPictrue() {
        return this.pictrue;
    }
    public void setPictrue(String pictrue) {
        this.pictrue = pictrue;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1118489732)
    public List<NewsDetail> getNewsDetails() {
        if (newsDetails == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            NewsDetailDao targetDao = daoSession.getNewsDetailDao();
            List<NewsDetail> newsDetailsNew = targetDao._queryUser_NewsDetails(id);
            synchronized (this) {
                if (newsDetails == null) {
                    newsDetails = newsDetailsNew;
                }
            }
        }
        return newsDetails;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1768377094)
    public synchronized void resetNewsDetails() {
        newsDetails = null;
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1470008238)
    public List<NewsDetail> getBrowseNewsDetails() {
        if (browseNewsDetails == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            NewsDetailDao targetDao = daoSession.getNewsDetailDao();
            List<NewsDetail> browseNewsDetailsNew = targetDao._queryUser_BrowseNewsDetails(id);
            synchronized (this) {
                if (browseNewsDetails == null) {
                    browseNewsDetails = browseNewsDetailsNew;
                }
            }
        }
        return browseNewsDetails;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 798658156)
    public synchronized void resetBrowseNewsDetails() {
        browseNewsDetails = null;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1816923913)
    public List<NewsDetail> getCommentNewsDetails() {
        if (commentNewsDetails == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            NewsDetailDao targetDao = daoSession.getNewsDetailDao();
            List<NewsDetail> commentNewsDetailsNew = targetDao._queryUser_CommentNewsDetails(id);
            synchronized (this) {
                if (commentNewsDetails == null) {
                    commentNewsDetails = commentNewsDetailsNew;
                }
            }
        }
        return commentNewsDetails;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1817336546)
    public synchronized void resetCommentNewsDetails() {
        commentNewsDetails = null;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 2059241980)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getUserDao() : null;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }

}
