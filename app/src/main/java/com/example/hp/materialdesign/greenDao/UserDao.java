package com.example.hp.materialdesign.greenDao;

import java.util.List;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import com.example.hp.materialdesign.domain.Join.UserJoinNewsBrowse;
import com.example.hp.materialdesign.domain.Join.UserJoinNewsColl;
import com.example.hp.materialdesign.domain.Join.UserJoinNewsComment;

import com.example.hp.materialdesign.domain.User;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "USER".
*/
public class UserDao extends AbstractDao<User, Long> {

    public static final String TABLENAME = "USER";

    /**
     * Properties of entity User.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Userid = new Property(1, Long.class, "userid", false, "USERID");
        public final static Property Email = new Property(2, String.class, "email", false, "EMAIL");
        public final static Property Password = new Property(3, String.class, "password", false, "PASSWORD");
        public final static Property Phone = new Property(4, String.class, "phone", false, "PHONE");
        public final static Property Nickname = new Property(5, String.class, "nickname", false, "NICKNAME");
        public final static Property Pictrue = new Property(6, String.class, "pictrue", false, "PICTRUE");
    }

    private DaoSession daoSession;

    private Query<User> newsDetail_UsersQuery;
    private Query<User> newsDetail_BrowseUsersQuery;
    private Query<User> newsDetail_CommmentUserQuery;

    public UserDao(DaoConfig config) {
        super(config);
    }
    
    public UserDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USER\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"USERID\" INTEGER UNIQUE ," + // 1: userid
                "\"EMAIL\" TEXT," + // 2: email
                "\"PASSWORD\" TEXT," + // 3: password
                "\"PHONE\" TEXT," + // 4: phone
                "\"NICKNAME\" TEXT," + // 5: nickname
                "\"PICTRUE\" TEXT);"); // 6: pictrue
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, User entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long userid = entity.getUserid();
        if (userid != null) {
            stmt.bindLong(2, userid);
        }
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(3, email);
        }
 
        String password = entity.getPassword();
        if (password != null) {
            stmt.bindString(4, password);
        }
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(5, phone);
        }
 
        String nickname = entity.getNickname();
        if (nickname != null) {
            stmt.bindString(6, nickname);
        }
 
        String pictrue = entity.getPictrue();
        if (pictrue != null) {
            stmt.bindString(7, pictrue);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, User entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long userid = entity.getUserid();
        if (userid != null) {
            stmt.bindLong(2, userid);
        }
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(3, email);
        }
 
        String password = entity.getPassword();
        if (password != null) {
            stmt.bindString(4, password);
        }
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(5, phone);
        }
 
        String nickname = entity.getNickname();
        if (nickname != null) {
            stmt.bindString(6, nickname);
        }
 
        String pictrue = entity.getPictrue();
        if (pictrue != null) {
            stmt.bindString(7, pictrue);
        }
    }

    @Override
    protected final void attachEntity(User entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public User readEntity(Cursor cursor, int offset) {
        User entity = new User( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // userid
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // email
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // password
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // phone
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // nickname
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6) // pictrue
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, User entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setUserid(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setEmail(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setPassword(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setPhone(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setNickname(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setPictrue(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(User entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(User entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(User entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "users" to-many relationship of NewsDetail. */
    public List<User> _queryNewsDetail_Users(Long newsId) {
        synchronized (this) {
            if (newsDetail_UsersQuery == null) {
                QueryBuilder<User> queryBuilder = queryBuilder();
                queryBuilder.join(UserJoinNewsColl.class, UserJoinNewsCollDao.Properties.UserId)
                    .where(UserJoinNewsCollDao.Properties.NewsId.eq(newsId));
                newsDetail_UsersQuery = queryBuilder.build();
            }
        }
        Query<User> query = newsDetail_UsersQuery.forCurrentThread();
        query.setParameter(0, newsId);
        return query.list();
    }

    /** Internal query to resolve the "browseUsers" to-many relationship of NewsDetail. */
    public List<User> _queryNewsDetail_BrowseUsers(Long newsId) {
        synchronized (this) {
            if (newsDetail_BrowseUsersQuery == null) {
                QueryBuilder<User> queryBuilder = queryBuilder();
                queryBuilder.join(UserJoinNewsBrowse.class, UserJoinNewsBrowseDao.Properties.UserId)
                    .where(UserJoinNewsBrowseDao.Properties.NewsId.eq(newsId));
                newsDetail_BrowseUsersQuery = queryBuilder.build();
            }
        }
        Query<User> query = newsDetail_BrowseUsersQuery.forCurrentThread();
        query.setParameter(0, newsId);
        return query.list();
    }

    /** Internal query to resolve the "commmentUser" to-many relationship of NewsDetail. */
    public List<User> _queryNewsDetail_CommmentUser(Long newsId) {
        synchronized (this) {
            if (newsDetail_CommmentUserQuery == null) {
                QueryBuilder<User> queryBuilder = queryBuilder();
                queryBuilder.join(UserJoinNewsComment.class, UserJoinNewsCommentDao.Properties.UserId)
                    .where(UserJoinNewsCommentDao.Properties.NewsId.eq(newsId));
                newsDetail_CommmentUserQuery = queryBuilder.build();
            }
        }
        Query<User> query = newsDetail_CommmentUserQuery.forCurrentThread();
        query.setParameter(0, newsId);
        return query.list();
    }

}