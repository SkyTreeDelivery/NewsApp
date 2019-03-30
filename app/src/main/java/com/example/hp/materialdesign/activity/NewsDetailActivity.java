package com.example.hp.materialdesign.activity;

import android.app.Application;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hp.materialdesign.R;
import com.example.hp.materialdesign.domain.ImageUrl;
import com.example.hp.materialdesign.domain.Join.UserJoinNewsColl;
import com.example.hp.materialdesign.domain.NewsDetail;
import com.example.hp.materialdesign.greenDao.UserJoinNewsCollDao;

import java.util.List;

public class NewsDetailActivity extends BaseActivity {

    ImageView imageView = null;
    TextView textView = null;
    Toolbar toolbar = null;
    CollapsingToolbarLayout collapsingToolbarLayout = null;
    String title = null;
    NewsDetail newsDetail = null;
    TextView oriTitleView = null;
    FloatingActionButton floatColl = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        newsDetail = (NewsDetail)bundle.getSerializable("newsDetail");
        floatColl = findViewById(R.id.collection);
       // title = intent.getStringExtra("title");
        //int imageId = intent.getIntExtra("imageId",R.drawable.p1);
        title = newsDetail.getTitle();
        //临时这样写
        List<ImageUrl> imageUrls = (List<ImageUrl>)(newsDetail.getBundle().get("imageUrls"));
        String imageId = imageUrls.get(0).getImageUrl();
        imageView = findViewById(R.id.news_detail_image);
        textView = findViewById(R.id.text_view);
        oriTitleView = findViewById(R.id.oriTitle);
        toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        collapsingToolbarLayout = findViewById(R.id.collapsingTollBar);
        Glide.with(this).load(imageId).into(imageView);
        textView.setText(getText());
        oriTitleView.setText(newsDetail.getAllText().get(0) + "\n");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbarLayout.setTitle(title);
       // initFloatActionButton();
    }

    private void initFloatActionButton(){
        floatColl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserJoinNewsCollDao userJoinNewsCollDao = daoSession.getUserJoinNewsCollDao();
                UserJoinNewsColl userJoinNewsColl = new UserJoinNewsColl(null,app.getUser().getId(),newsDetail.getId());
                userJoinNewsCollDao.insert(userJoinNewsColl);
            }
        });

    }

    private String getText(){
        List<String> text = newsDetail.getAllText();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\t\t\t\t");
        for(int i = 1; i < text.size(); i++){
            String p = text.get(i);
            stringBuilder.append(p).append("\n\t\t\t\t");
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();

        }
        return true;
    }
}
