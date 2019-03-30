package com.example.hp.materialdesign.domain;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.ToMany;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Unique;

import com.example.hp.materialdesign.domain.Join.UserJoinNewsBrowse;
import com.example.hp.materialdesign.domain.Join.UserJoinNewsColl;
import com.example.hp.materialdesign.domain.Join.UserJoinNewsComment;
import com.example.hp.materialdesign.greenDao.DaoSession;
import com.example.hp.materialdesign.greenDao.UserDao;
import com.example.hp.materialdesign.greenDao.ImageUrlDao;
import com.example.hp.materialdesign.greenDao.NewsDetailDao;
import com.example.hp.materialdesign.greenDao.StringConverter;

@Entity
public class NewsDetail implements Serializable
{
    static final long serialVersionUID = 1;
    @Id(autoincrement = true)
    long id;
    @Unique
    String   nid;    //每条新闻的id
    String channelId;
    String title;
    String link;
    String source;
    String desc;
    Date date;
    //用于传递数据
    @Transient
    private Map<String, Object> bundle = new HashMap<String, Object>();
    @Convert(converter = StringConverter.class,columnType = String.class)
    List<String> allText;
    @ToMany(referencedJoinProperty = "newsId")
    List<ImageUrl> imageurls;
    @ToMany
    @JoinEntity(entity = UserJoinNewsColl.class,sourceProperty = "newsId", targetProperty = "userId")
    List<User> users;
    @ToMany
    @JoinEntity(entity = UserJoinNewsBrowse.class,sourceProperty = "newsId",targetProperty = "userId")
    List<User> browseUsers;
    @ToMany
    @JoinEntity(entity = UserJoinNewsComment.class,sourceProperty = "newsId",targetProperty = "userId")
    List<User> commmentUser;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 994591376)
    private transient NewsDetailDao myDao;
    @Generated(hash = 875887720)
    public NewsDetail(long id, String nid, String channelId, String title, String link, String source,
            String desc, Date date, List<String> allText) {
        this.id = id;
        this.nid = nid;
        this.channelId = channelId;
        this.title = title;
        this.link = link;
        this.source = source;
        this.desc = desc;
        this.date = date;
        this.allText = allText;
    }
    @Generated(hash = 1491886114)
    public NewsDetail() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getNid() {
        return this.nid;
    }
    public void setNid(String nid) {
        this.nid = nid;
    }
    public String getChannelId() {
        return this.channelId;
    }
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getLink() {
        return this.link;
    }
    public void setLink(String link) {
        this.link = link;
    }
    public String getSource() {
        return this.source;
    }
    public void setSource(String source) {
        this.source = source;
    }
    public String getDesc() {
        return this.desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public Date getDate() {
        return this.date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public List<String> getAllText() {
        return this.allText;
    }
    public void setAllText(List<String> allText) {
        this.allText = allText;
    }

    public Map<String, Object> getBundle() {
        return bundle;
    }

    public void setBundle(Map<String, Object> bundle) {
        this.bundle = bundle;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 419033060)
    public List<ImageUrl> getImageurls() {
        if (imageurls == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ImageUrlDao targetDao = daoSession.getImageUrlDao();
            List<ImageUrl> imageurlsNew = targetDao._queryNewsDetail_Imageurls(id);
            synchronized (this) {
                if (imageurls == null) {
                    imageurls = imageurlsNew;
                }
            }
        }
        return imageurls;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1639890357)
    public synchronized void resetImageurls() {
        imageurls = null;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 2065926109)
    public List<User> getUsers() {
        if (users == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            UserDao targetDao = daoSession.getUserDao();
            List<User> usersNew = targetDao._queryNewsDetail_Users(id);
            synchronized (this) {
                if (users == null) {
                    users = usersNew;
                }
            }
        }
        return users;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1027274768)
    public synchronized void resetUsers() {
        users = null;
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
    @Generated(hash = 327782504)
    public List<User> getBrowseUsers() {
        if (browseUsers == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            UserDao targetDao = daoSession.getUserDao();
            List<User> browseUsersNew = targetDao._queryNewsDetail_BrowseUsers(id);
            synchronized (this) {
                if (browseUsers == null) {
                    browseUsers = browseUsersNew;
                }
            }
        }
        return browseUsers;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 799228165)
    public synchronized void resetBrowseUsers() {
        browseUsers = null;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1217639966)
    public List<User> getCommmentUser() {
        if (commmentUser == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            UserDao targetDao = daoSession.getUserDao();
            List<User> commmentUserNew = targetDao._queryNewsDetail_CommmentUser(id);
            synchronized (this) {
                if (commmentUser == null) {
                    commmentUser = commmentUserNew;
                }
            }
        }
        return commmentUser;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 86987)
    public synchronized void resetCommmentUser() {
        commmentUser = null;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 743767654)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getNewsDetailDao() : null;
    }


}
