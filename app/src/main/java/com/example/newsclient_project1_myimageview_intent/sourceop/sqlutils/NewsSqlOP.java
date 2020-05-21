package com.example.newsclient_project1_myimageview_intent.sourceop.sqlutils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.newsclient_project1_myimageview_intent.bean.NewsInfo;
import com.example.newsclient_project1_myimageview_intent.properties.AppProperties;

import java.util.ArrayList;
import java.util.List;

public class NewsSqlOP {

    private final NewsSqliteHelper helper;

    public NewsSqlOP(Context context) {
        helper = new NewsSqliteHelper(context);

    }


    public boolean insert(List<NewsInfo> newsList) {

        SQLiteDatabase db = helper.getReadableDatabase();

        long start = -1;
        long end = -1;

        db.beginTransaction();

        try {
            for (NewsInfo newsInfo : newsList) {
                ContentValues values = new ContentValues();
                values.put("title", newsInfo.getTitle());
                values.put("link", newsInfo.getLink());
                values.put("author", newsInfo.getAuthor());
                values.put("image", newsInfo.getImage());
                values.put("pubdate", newsInfo.getPubDate());
                values.put("type", newsInfo.getType());
                values.put("description", newsInfo.getDescription());

                long result = db.insert(AppProperties.TABLE_NAME, null, values);

                if (start == -1) {
                    start = result;
                }
                end = result + 1;

            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();

            if (newsList.size() == end - start)
                return true;
            else
                return false;
        }

    }

    public boolean insertWithException(List<NewsInfo> newsList) {

        SQLiteDatabase db = helper.getReadableDatabase();
        db.beginTransaction();
        try {
            for (NewsInfo newsInfo : newsList) {
                ContentValues values = new ContentValues();
                values.put("title", newsInfo.getTitle());
                values.put("link", newsInfo.getLink());
                values.put("author", newsInfo.getAuthor());
                values.put("image", newsInfo.getImage());
                values.put("pubdate", newsInfo.getPubDate());
                values.put("type", newsInfo.getType());
                values.put("description", newsInfo.getDescription());
                long result = db.insert(AppProperties.TABLE_NAME, null, values);
            }
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            db.endTransaction();
            db.close();
        }
        return true;
    }

    public List<NewsInfo> queryNewsList() {
        List<NewsInfo> newsList = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(AppProperties.TABLE_NAME, null, null, null, null, null, null, null);
        if (cursor!= null && cursor.getCount() > 0){
            while (cursor.moveToNext()) {
                NewsInfo newsInfo = new NewsInfo();
                newsInfo.setTitle(cursor.getString(1));
                newsInfo.setLink(cursor.getString(2));
                newsInfo.setAuthor(cursor.getString(3));
                newsInfo.setImage(cursor.getString(4));
                newsInfo.setPubDate(cursor.getString(5));
                newsInfo.setType(cursor.getString(6));
                newsInfo.setDescription(cursor.getString(7));
                newsList.add(newsInfo);
            }
        }
        cursor.close();
        db.close();
        return newsList;

    }

}






