package com.example.newsclient_project1_myimageview_intent.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newsclient_project1_myimageview_intent.R;
import com.example.newsclient_project1_myimageview_intent.bean.NewsInfo;
import com.example.newsclient_project1_myimageview_intent.manager.ResultManager;
import com.example.newsclient_project1_myimageview_intent.myview.MyImageView;
import com.example.newsclient_project1_myimageview_intent.properties.AppProperties;

public class ResultActivity extends AppCompatActivity {


    private ResultManager resultManager;
    private TextView tv_title_rs;
    private TextView tv_author_rs;
    private TextView tv_description_rs;
    private TextView tv_pubDate_rs;
    private TextView tv_link_rs;
    private MyImageView myiv_image_rs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        initView();
        getIntentFromMain();
    }

    private void getIntentFromMain() {
        NewsInfo newsinfo = resultManager.getStringFromMain();
        tv_description_rs.setText(newsinfo.getDescription());
        tv_pubDate_rs.setText(newsinfo.getPubDate());
        tv_title_rs.setText(newsinfo.getTitle());
        tv_link_rs.setText(newsinfo.getLink());
        myiv_image_rs.setImageUrl(newsinfo.getImage());
        tv_author_rs.setText(newsinfo.getAuthor());



    }
    private void initView() {
        resultManager = new ResultManager(ResultActivity.this);
        tv_title_rs = (TextView) findViewById(R.id.tv_title_rs);
        tv_author_rs = (TextView) findViewById(R.id.tv_author_rs);
        tv_description_rs = (TextView) findViewById(R.id.tv_description_rs);
        tv_pubDate_rs = (TextView) findViewById(R.id.tv_pubDate_rs);
        tv_link_rs = (TextView) findViewById(R.id.tv_link_rs);
        myiv_image_rs = (MyImageView) findViewById(R.id.myiv_image_rs); }
    public void click(View view) {
        switch (view.getId()) {
            case  R.id.finish:
                finish();
                break;
            case  R.id.tv_link_rs:
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse(tv_link_rs.getText().toString());
                intent.setData(content_url);
                startActivity(intent);
                break;
        }
    }
}
