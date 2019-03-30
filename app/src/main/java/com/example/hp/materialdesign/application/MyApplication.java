package com.example.hp.materialdesign.application;

import android.app.Application;
import android.content.SharedPreferences;

import com.example.hp.materialdesign.domain.User;
import com.example.hp.materialdesign.greenDao.DaoMaster;
import com.example.hp.materialdesign.domain.Channel;
import com.example.hp.materialdesign.greenDao.DaoSession;
import com.example.hp.materialdesign.greenDao.UserDao;
import com.example.hp.materialdesign.util.NewsApi;

import org.greenrobot.greendao.database.Database;

import java.util.ArrayList;
import java.util.List;

public class MyApplication extends Application {

    private DaoSession daoSession = null;

    private List<Channel> channelList = null;
    private MyApplication app = null;
    private User user;

    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this,"location-db");
        Database db = helper.getWritableDb();
        app = this;
        daoSession = new DaoMaster(db).newSession();
       channelList = new ArrayList<Channel>();

    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public List<Channel> getChannelList() {
        return channelList;
    }

    public void setChannelList(List<Channel> channelList) {
        this.channelList = channelList;
    }

    private void initUserInfo(){
        /*UserDao userDao = daoSession.getUserDao();
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo",MODE_PRIVATE);
        long userid = sharedPreferences.getLong("userid",0);
        userDao.queryBuilder().where(UserDao.Properties.Userid.eq(userid)).build().unique();*/
        user = new User((long)0,(long)123,"zhang113751@sina.com","zhang002508","19972287888","天空树快递","/2019-03-29");
    }

    public User getUser(){
        return user;
    }
}
