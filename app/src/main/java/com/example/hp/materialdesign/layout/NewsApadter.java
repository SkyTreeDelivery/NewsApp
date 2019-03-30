package com.example.hp.materialdesign.layout;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hp.materialdesign.R;
import com.example.hp.materialdesign.activity.MainActivity;
import com.example.hp.materialdesign.activity.NewsDetailActivity;
import com.example.hp.materialdesign.application.MyApplication;
import com.example.hp.materialdesign.domain.Channel;
import com.example.hp.materialdesign.domain.ImageUrl;
import com.example.hp.materialdesign.domain.NewsDetail;
import com.example.hp.materialdesign.util.NewsApi;

import java.util.LinkedList;
import java.util.List;

public class NewsApadter extends RecyclerView.Adapter<NewsApadter.ViewHolder>  {

    private Context mContext;
    private MyApplication app;
    private MainActivity mainActivity;

    //private List<News> newsList = null;
    private List<NewsDetail> newsList = null;

    public NewsApadter(List<NewsDetail> list){
        newsList = list;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView imageView;
        TextView textView;
        public ViewHolder(View view){
            super(view);
            cardView = (CardView)view;
            imageView = view.findViewById(R.id.news_image);
            textView = view.findViewById(R.id.news_title);
        }
    }
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        mainActivity = (MainActivity)mContext;
        app = (MyApplication)(mContext.getApplicationContext());
        View view = LayoutInflater.from(mContext).inflate(R.layout.news_item,parent,false);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, NewsDetailActivity.class);
                int index = viewHolder.getAdapterPosition();
                NewsDetail news = newsList.get(index);
                Bundle bundle = new Bundle();
                bundle.putSerializable("newsDetail",news);
                intent.putExtra("bundle",bundle);
                mContext.startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NewsDetail news  = newsList.get(position);
        holder.textView.setText(news.getTitle());
        List<ImageUrl> list = (List<ImageUrl>)(news.getBundle().get("imageUrls"));
        Glide.with(mContext).load(list.get(0).getImageUrl()).into(holder.imageView);
        if(position == getItemCount()/2){
            loadMoreData();
        }
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public void loadMoreData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                NewsApi newsApi = new NewsApi(app);
                Integer index = Integer.parseInt(mainActivity.getChannelPageNum(mainActivity.getChannelIndex()));
                index = index + 1;
                List<NewsDetail> temp = newsApi.requestNewsBychannelAndPage(mainActivity.getChannelId(mainActivity.getChannelIndex()),index.toString());
                mainActivity.LoadMoreDataRefresh(temp);
                mainActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mainActivity.callNewsAdapterRefresh();
                    }
                });
            }
        }).start();

    }
}

