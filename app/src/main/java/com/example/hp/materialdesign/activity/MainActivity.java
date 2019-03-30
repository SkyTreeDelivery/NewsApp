package com.example.hp.materialdesign.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;


import com.example.hp.materialdesign.application.MyApplication;
import com.example.hp.materialdesign.domain.Channel;
import com.example.hp.materialdesign.greenDao.DaoSession;
import com.example.hp.materialdesign.layout.NewsApadter;
import com.example.hp.materialdesign.R;
import com.example.hp.materialdesign.domain.NewsDetail;
import com.example.hp.materialdesign.util.NewsApi;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private DrawerLayout mDrawerLayout = null;
    private NavigationView navView;
    //private List<News> mNewsList = null;
    private NewsApadter newsApadter = null;
    private SwipeRefreshLayout swipeRefresh = null;
    List<List<NewsDetail>> allData;
    List<NewsDetail> details = null;
    List<Channel> channelList;
    TabLayout tabLayout;
    int channelIndex = 0;
    List<Integer> pageIndexList;
    DaoSession daoSession;
    MyApplication app = null;
    NewsApi newsApi = null;
    int oldChannelNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        app = ((MyApplication)(getApplication()));
        channelList = new ArrayList<Channel>();
        pageIndexList = new ArrayList<Integer>();
        oldChannelNum = channelList.size();
        reFreshPageIndexList();
        allData = new ArrayList<List<NewsDetail>>();
        newsApi = new NewsApi(app);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);    //设置toolbar
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        mDrawerLayout = findViewById(R.id.drawer_layout);    //得到导航栏
        navView = findViewById(R.id.nav_view);
        tabLayout = findViewById(R.id.tabLayout);
        FloatingActionButton fab = findViewById(R.id.fab);
        if(actionBar != null){                           //设置home图标
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_memu);
        }
        navView.setCheckedItem(R.id.home_page);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"已经收藏xxx", Snackbar.LENGTH_SHORT).setAction("查看", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //TODO
                        //跳转到我的收藏
                    }
                }).show();
            }
        });
        MyApplication application= (MyApplication)getApplication();
        daoSession = ((MyApplication)(getApplication())).getDaoSession();
       // channelList = daoSession.getChannelDao().queryBuilder().build().list();
        updateChanelOnNewApp();
        initCardView();
        initSwipeRefresh();
        initTabLayout();
    }

    public void updateChanelOnNewApp(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                channelList = newsApi.requestChannel();
                reFreshPageIndexList();
                final List<NewsDetail> temp = newsApi.requestNewsBychannelAndPage(getChannelName(channelIndex),getChannelPageNum(channelIndex));
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        allData.clear();
                        tabLayout.removeAllTabs();
                        for(Channel channel: channelList){
                            TabLayout.Tab tab = tabLayout.newTab();
                            tab.setText(channel.getChannelName());
                            tabLayout.addTab(tab);
                            allData.add(new ArrayList<NewsDetail>());
                        }
                        app.setChannelList(channelList);
                        allData.set(channelIndex,temp);
                    }
                });
            }
        }).start();

        channelList.add(new Channel("5572a108b3cdc86cf39001cd","国内焦点"));
        channelList.add(new Channel("5572a108b3cdc86cf39001ce","国际焦点"));
        channelList.add(new Channel("5572a108b3cdc86cf39001cf","军事焦点"));
        channelList.add(new Channel("5572a108b3cdc86cf39001d0","财经焦点"));
        channelList.add(new Channel("5572a108b3cdc86cf39001d1","互联网焦点"));
        channelList.add(new Channel("5572a108b3cdc86cf39001d2","房产焦点"));
        channelList.add(new Channel("5572a108b3cdc86cf39001d3","汽车焦点"));
        channelList.add(new Channel("5572a108b3cdc86cf39001d4","体育焦点"));


    }

    public String getChannelName(int index){
        return channelList.get(index).getChannelName();
    }
    public String getChannelId(int index){
        return channelList.get(index).getChannelId();
    }
    public String getChannelPageNum(int index){
        return pageIndexList.get(index).toString();
    }
    public int getChannelIndex(){
        return channelIndex;
    }
    public List<NewsDetail> getDetails(){
        return details;
    }
    public List<List<NewsDetail>> getAllData(){
        return allData;
    }
    public void setdata(int index, List<NewsDetail> list){
        allData.set(index,list);
    }
    public void addNewsDetail(NewsDetail newsDetail){
        details.add(newsDetail);
        allData.get(channelIndex).add(newsDetail);
    }
    public void callNewsAdapterRefresh(){
        newsApadter.notifyDataSetChanged();
    }
    public void reFreshPageIndexList(){
        for(int i = 0 ; i < channelList.size(); i++){
            pageIndexList.add(new Integer(1));
        }
    }
    public void LoadMoreDataRefresh(List<NewsDetail> newsDetailList){
        for(NewsDetail newsDetail: newsDetailList){
            addNewsDetail(newsDetail);
        }
        pageIndexList.set(channelIndex,pageIndexList.get(channelIndex) + 1);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
            break;
        }
        return true;
    }

    private void initTabLayout(){
        int size = channelList.size();
        for(int i = 0; i < size;i++){
            Channel channel = channelList.get(i);
            TabLayout.Tab tab = tabLayout.newTab();
            tab.setText(channel.getChannelName());
            tabLayout.addTab(tab);
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                final int index = tab.getPosition();
                channelIndex = index;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final NewsApi newsApi = new NewsApi(getApplication());
                        List<NewsDetail> temp = newsApi.requestNewsBychannelAndPage(channelList.get(index).getChannelId(),pageIndexList.get(channelIndex).toString());
                        //注意！！！这里大坑，如果之前添加的list的指向对象发生了改变，那这个方法不会被执行。所以要clear
                        if(details != null){
                           details.clear();
                        }
                        for(NewsDetail news: temp){
                            details.add(news);
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                newsApadter.notifyDataSetChanged();
                            }
                        });
                    }
                }).start();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    private void initCardView(){
        //initNews();
       /* runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Object lock = new Object();
                try {
                    synchronized(lock){
                        lock.wait(2000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });*/

        new Thread(new Runnable() {
            @Override
            public void run() {
                NewsApi newsApi = new NewsApi(getApplication());
                //第一次更新直接获得对象
                details = newsApi.requestNewsBychannelAndPage(channelList.get(0).getChannelId(),"1");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        RecyclerView recyclerView = findViewById(R.id.recycler_view);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this,LinearLayout.VERTICAL,false);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        newsApadter = new NewsApadter(details);
                        recyclerView.setAdapter(newsApadter);
                    }
                });
            }
        }).start();



    }

    public void initSwipeRefresh(){
        swipeRefresh = findViewById(R.id.swipeRefreshLayout);
        swipeRefresh.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshNews();
            }
        });
    }

    public void refreshNews(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //必须在子线程中进行数据的请求
                    //获得新的数据
                    NewsApi newsApi = new NewsApi(getApplication());
                    List<NewsDetail> temp = newsApi.requestNewsBychannelAndPage(channelList.get(channelIndex).getChannelId(),"1");
                    if(details != null){
                        details.clear();
                    }
                    for(NewsDetail news: temp){
                        details.add(news);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //initNews();
                        newsApadter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }
}


