package com.example.hp.materialdesign.greenDao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.hp.materialdesign.domain.Join.UserJoinNewsComment;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "USER_JOIN_NEWS_COMMENT".
*/
public class UserJoinNewsCommentDao extends AbstractDao<UserJoinNewsComment, Long> {

    public static final String TABLENAME = "USER_JOIN_NEWS_COMMENT";

    /**
     * Properties of entity UserJoinNewsComment.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property UserId = new Property(1, Long.class, "userId", false, "USER_ID");
        public final static Property NewsId = new Property(2, Long.class, "newsId", false, "NEWS_ID");
        public final static Property Comment = new Property(3, String.class, "comment", false, "COMMENT");
        public final static Property CommentDate = new Property(4, java.util.Date.class, "commentDate", false, "COMMENT_DATE");
    }


    public UserJoinNewsCommentDao(DaoConfig config) {
        super(config);
    }
    
    public UserJoinNewsCommentDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USER_JOIN_NEWS_COMMENT\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"USER_ID\" INTEGER," + // 1: userId
                "\"NEWS_ID\" INTEGER," + // 2: newsId
                "\"COMMENT\" TEXT," + // 3: comment
                "\"COMMENT_DATE\" INTEGER);"); // 4: commentDate
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER_JOIN_NEWS_COMMENT\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, UserJoinNewsComment entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long userId = entity.getUserId();
        if (userId != null) {
            stmt.bindLong(2, userId);
        }
 
        Long newsId = entity.getNewsId();
        if (newsId != null) {
            stmt.bindLong(3, newsId);
        }
 
        String comment = entity.getComment();
        if (comment != null) {
            stmt.bindString(4, comment);
        }
 
        java.util.Date commentDate = entity.getCommentDate();
        if (commentDate != null) {
            stmt.bindLong(5, commentDate.getTime());
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, UserJoinNewsComment entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long userId = entity.getUserId();
        if (userId != null) {
            stmt.bindLong(2, userId);
        }
 
        Long newsId = entity.getNewsId();
        if (newsId != null) {
            stmt.bindLong(3, newsId);
        }
 
        String comment = entity.getComment();
        if (comment != null) {
            stmt.bindString(4, comment);
        }
 
        java.util.Date commentDate = entity.getCommentDate();
        if (commentDate != null) {
            stmt.bindLong(5, commentDate.getTime());
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public UserJoinNewsComment readEntity(Cursor cursor, int offset) {
        UserJoinNewsComment entity = new UserJoinNewsComment( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // userId
            cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2), // newsId
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // comment
            cursor.isNull(offset + 4) ? null : new java.util.Date(cursor.getLong(offset + 4)) // commentDate
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, UserJoinNewsComment entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setUserId(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setNewsId(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
        entity.setComment(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setCommentDate(cursor.isNull(offset + 4) ? null : new java.util.Date(cursor.getLong(offset + 4)));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(UserJoinNewsComment entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(UserJoinNewsComment entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(UserJoinNewsComment entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}