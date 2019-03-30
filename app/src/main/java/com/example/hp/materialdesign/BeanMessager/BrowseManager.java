package com.example.hp.materialdesign.BeanMessager;

import android.app.Application;

import com.example.hp.materialdesign.application.MyApplication;
import com.example.hp.materialdesign.domain.Join.UserJoinNewsBrowse;
import com.example.hp.materialdesign.domain.Join.UserJoinNewsComment;
import com.example.hp.materialdesign.greenDao.DaoSession;
import com.example.hp.materialdesign.greenDao.UserJoinNewsBrowseDao;
import com.example.hp.materialdesign.greenDao.UserJoinNewsCommentDao;

import java.util.List;

public class BrowseManager {

    MyApplication app = null;
    DaoSession daoSession  = null;
    UserJoinNewsBrowseDao dao = null;


    public BrowseManager(Application application) {
        app = (MyApplication)application;
        daoSession = app.getDaoSession();
        daoSession.getUserJoinNewsBrowseDao();
    }

    public List<UserJoinNewsBrowse> geBrowseByUserId(Long id){
        return dao.queryBuilder().where(UserJoinNewsBrowseDao.Properties.Id.eq(id)).build().list();
    }

    public List<UserJoinNewsBrowse> getBrowseByNewsId(Long newsId){
        return dao.queryBuilder().where(UserJoinNewsBrowseDao.Properties.NewsId.eq(newsId)).build().list();
    }
}
