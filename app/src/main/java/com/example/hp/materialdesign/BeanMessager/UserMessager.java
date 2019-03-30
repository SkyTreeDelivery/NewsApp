package com.example.hp.materialdesign.BeanMessager;

import android.app.Application;

import com.example.hp.materialdesign.application.MyApplication;
import com.example.hp.materialdesign.domain.NewsDetail;
import com.example.hp.materialdesign.domain.User;
import com.example.hp.materialdesign.greenDao.DaoSession;
import com.example.hp.materialdesign.greenDao.NewsDetailDao;
import com.example.hp.materialdesign.greenDao.UserDao;

import java.util.List;

public class UserMessager {

    MyApplication app = null;
    DaoSession daoSession = null;
    UserDao userDao = null;

    public UserMessager(Application application) {
        app = (MyApplication) application;
        daoSession = app.getDaoSession();
        userDao = daoSession.getUserDao();
    }

    public void saveUser(User user){
        userDao.insert(user);
    }

    public User getUserById(long id){
        return userDao.queryBuilder().where(UserDao.Properties.Userid.eq(id)).build().unique();
    }

    public List<NewsDetail> getNewsListCollByUserId(long userid) {
        User user = userDao.queryBuilder().where(UserDao.Properties.Userid.eq(userid)).build().unique();
        return user.getNewsDetails();
    }
    public List<NewsDetail> getNewsListBrowaeByUserId(long userid) {
        User user = userDao.queryBuilder().where(UserDao.Properties.Userid.eq(userid)).build().unique();
        return user.getBrowseNewsDetails();
    }
    public List<NewsDetail> getNewsListCommentByUserId(long userid) {
        User user = userDao.queryBuilder().where(UserDao.Properties.Userid.eq(userid)).build().unique();
        return user.getCommentNewsDetails();
    }

}
