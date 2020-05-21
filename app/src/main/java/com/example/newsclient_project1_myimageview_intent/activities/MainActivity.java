package com.example.newsclient_project1_myimageview_intent.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.newsclient_project1_myimageview_intent.R;
import com.example.newsclient_project1_myimageview_intent.adapter.NewsAdapter;
import com.example.newsclient_project1_myimageview_intent.bean.NewsInfo;
import com.example.newsclient_project1_myimageview_intent.enumeration.SourceType;
import com.example.newsclient_project1_myimageview_intent.manager.NewsManager;
import com.example.newsclient_project1_myimageview_intent.properties.AppProperties;


import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lv_newsList;
    private NewsManager newsManager;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage( Message msg) {
            newsList = (List<NewsInfo>) msg.obj;
            lv_newsList.setAdapter(new NewsAdapter(newsList,MainActivity.this));


        }
    };
    private SharedPreferences sp;
    private List<NewsInfo> newsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        String type = sp.getString("type",SourceType.SOURCE_FROM_SERVER.getType());
        String path = sp.getString("path", AppProperties.SERVER_PATH);
         showNewsList(path,SourceType.valueOf(type));
        intent();

    }

    private void intent() {
        lv_newsList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                NewsInfo newsInfo =newsList.get(position);
                intent.putExtra(AppProperties.NEWS_INTENT_NAME,newsInfo);
                intent.setClass(MainActivity.this, ResultActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        sp = getSharedPreferences("news", MODE_PRIVATE);
        newsManager = new NewsManager();
    }

    private void initView() {
        lv_newsList = (ListView) findViewById(R.id.lv_newsList);
    }

    public void click(View view) {
        SharedPreferences.Editor edit = this.sp.edit();
        switch (view.getId()){
            case R.id.btn_server:
                edit.putString("type",SourceType.SOURCE_FROM_SERVER.getType());
                edit.putString("path",AppProperties.SERVER_PATH);
                showNewsList(AppProperties.SERVER_PATH,SourceType.SOURCE_FROM_SERVER);
                break;
            case R.id.btn_asset:
                edit.putString("type",SourceType.SOURCE_FROM_ASSETSS.getType());
                edit.putString("path",AppProperties.ASSETS_FILE_PATH);
                showNewsList(AppProperties.ASSETS_FILE_PATH,SourceType.SOURCE_FROM_ASSETSS);
                break;
                case R.id.btn_db:
                    edit.putString("type",SourceType.SOURCE_FROM_DB.getType());
                    edit.putString("path","");
                    showNewsList(null,SourceType.SOURCE_FROM_DB);
                break;
        }
        edit.commit();
    }

    private void showNewsList(final String path, final SourceType sourceType) {

        new Thread(){
            @Override
            public void run() {
                List<NewsInfo> newsList = newsManager.getNewsListFromSource(MainActivity.this,sourceType,path);
                Message msg = Message.obtain();
                msg.obj = newsList;
                handler.sendMessage(msg);
            }
        }.start();



    }
}
