package com.example.hp.materialdesign.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.hp.materialdesign.application.MyApplication;
import com.example.hp.materialdesign.greenDao.DaoSession;

public class BaseActivity extends AppCompatActivity {

    DaoSession daoSession;

    MyApplication app = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("BaseActivity", getClass().getSimpleName());
        ActivityController.addActivity(this);
        daoSession = ((MyApplication)(getApplication())).getDaoSession();
        app = (MyApplication) getApplication();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityController.removeActivity(this);
    }
}
