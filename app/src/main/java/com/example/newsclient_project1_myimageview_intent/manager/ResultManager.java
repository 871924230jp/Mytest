package com.example.newsclient_project1_myimageview_intent.manager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.example.newsclient_project1_myimageview_intent.bean.NewsInfo;
import com.example.newsclient_project1_myimageview_intent.properties.AppProperties;

public class ResultManager {

    private  Context context;
    public ResultManager(Context context){
        this.context=context;
    }
    public NewsInfo getStringFromMain(){
        Intent intent = ((Activity)context).getIntent();
        NewsInfo newsInfo = (NewsInfo) intent.getSerializableExtra(AppProperties.NEWS_INTENT_NAME);
        return  newsInfo;
    }


}
