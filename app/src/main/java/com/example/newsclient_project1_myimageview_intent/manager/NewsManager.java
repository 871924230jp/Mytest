package com.example.newsclient_project1_myimageview_intent.manager;

import android.content.Context;

import com.example.newsclient_project1_myimageview_intent.bean.NewsInfo;
import com.example.newsclient_project1_myimageview_intent.enumeration.SourceType;
import com.example.newsclient_project1_myimageview_intent.exception.ErrorResponseCodeException;
import com.example.newsclient_project1_myimageview_intent.sourceop.sqlutils.NewsSqlOP;
import com.example.newsclient_project1_myimageview_intent.sourceprovider.ISource;
import com.example.newsclient_project1_myimageview_intent.sourceprovider.SourceFactory;
import com.example.newsclient_project1_myimageview_intent.utils.NewsXmlParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class NewsManager {

    public List<NewsInfo>getNewsListFromSource(Context context, SourceType sourceType, final String path){

        List<NewsInfo>newsList = null;
        switch (sourceType){
            case SOURCE_FROM_SERVER:
            case SOURCE_FROM_ASSETSS:

                newsList = getNewsListFromInputStream(context, sourceType, path);
                break;
            case SOURCE_INTENT:

                break;
            case SOURCE_FROM_DB:
                newsList = new NewsSqlOP(context).queryNewsList();
                break;

              default:
                  newsList = new ArrayList<>();
                  break;
        }

        return newsList;
    }

    private List<NewsInfo> getNewsListFromInputStream(Context context,SourceType sourceType, String path) {
        List<NewsInfo>newsList= new ArrayList<>();
        InputStream in=null;
        try {
            ISource iSource = SourceFactory.sourceCreate(context, sourceType);
            in=iSource.getInputStream(path);
            newsList = NewsXmlParser.parse(in);
            saveNewsInfo2DB(context,newsList);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ErrorResponseCodeException e) {
            e.printStackTrace();
        }finally {
            try {
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return newsList;
    }
    private void saveNewsInfo2DB(final Context context, final List<NewsInfo>newsList) {

        new Thread(){
            @Override
            public void run() {
                NewsSqlOP newsSqlOP = new NewsSqlOP(context);
                int counter= 0;
                while (!newsSqlOP.insertWithException(newsList)){

                    System.out.println("--------failed");
                    if (counter++ == 3)
                        break;
                }
                if (counter!=4)
                    System.out.println("success");

            }


        }.start();

    }


}
