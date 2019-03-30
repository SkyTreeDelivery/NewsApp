package com.example.hp.materialdesign.BeanMessager;

import android.app.Application;

import com.example.hp.materialdesign.domain.Join.UserJoinNewsComment;
import com.example.hp.materialdesign.greenDao.UserJoinNewsCommentDao;
import com.example.hp.materialdesign.application.MyApplication;
import com.example.hp.materialdesign.greenDao.DaoSession;

import java.util.List;

public class CommentManager {

    MyApplication app = null;
    DaoSession daoSession  = null;
    UserJoinNewsCommentDao dao = null;


    public CommentManager(Application application) {
        app = (MyApplication)application;
        daoSession = app.getDaoSession();
        daoSession.getUserJoinNewsCommentDao();
    }

    public List<UserJoinNewsComment> getCommentsByUserId(Long id){
         return dao.queryBuilder().where(UserJoinNewsCommentDao.Properties.Id.eq(id)).build().list();
    }

    public List<UserJoinNewsComment> getCommentsByNewsId(Long newsId){
        return dao.queryBuilder().where(UserJoinNewsCommentDao.Properties.NewsId.eq(newsId)).build().list();
    }
}
