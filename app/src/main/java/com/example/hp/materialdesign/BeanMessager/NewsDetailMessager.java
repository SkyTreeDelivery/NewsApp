package com.example.hp.materialdesign.BeanMessager;

import android.app.Application;

import com.example.hp.materialdesign.application.MyApplication;
import com.example.hp.materialdesign.domain.News;
import com.example.hp.materialdesign.domain.NewsDetail;
import com.example.hp.materialdesign.domain.User;
import com.example.hp.materialdesign.greenDao.DaoSession;
import com.example.hp.materialdesign.greenDao.NewsDetailDao;
import com.example.hp.materialdesign.greenDao.UserDao;

import java.util.List;

public class NewsDetailMessager {

    MyApplication app = null;
    DaoSession daoSession  = null;
    NewsDetailDao dao = null;

    public NewsDetailMessager(Application application) {
        app = (MyApplication)application;
        daoSession = app.getDaoSession();
        dao = daoSession.getNewsDetailDao();
    }

    public void saveNewsDetail(NewsDetail newsDetail){
            dao.insert(newsDetail);
    }

    public void saveNewsDetail(List<NewsDetail> newsDetailList){
        for(NewsDetail newsDetail:newsDetailList){
            dao.insert(newsDetail);
        }
    }

    public NewsDetail getNewsDetailById(long id){
       return dao.queryBuilder().where(NewsDetailDao.Properties.Id.eq(id)).build().unique();
    }

    public List<User> getUsersCollNewsById(long id) {
        NewsDetail news = dao.queryBuilder().where(NewsDetailDao.Properties.Id.eq(id)).build().unique();
        return news.getUsers();
    }

    public List<User> getUsersBrowseNewsById(long id) {
        NewsDetail news = dao.queryBuilder().where(NewsDetailDao.Properties.Id.eq(id)).build().unique();
        return news.getBrowseUsers();
    }
    public List<User> getUsersCommentNewsById(long id) {
        NewsDetail news = dao.queryBuilder().where(NewsDetailDao.Properties.Id.eq(id)).build().unique();
        return news.getCommmentUser();
    }


}
