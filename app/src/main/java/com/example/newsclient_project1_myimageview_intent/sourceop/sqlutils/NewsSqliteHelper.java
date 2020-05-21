package com.example.newsclient_project1_myimageview_intent.sourceop.sqlutils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.newsclient_project1_myimageview_intent.properties.AppProperties;

 class NewsSqliteHelper extends SQLiteOpenHelper {/*只在同一个包内可见*/



    public NewsSqliteHelper( Context context) {
        super(context, "class14.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table "+ AppProperties.TABLE_NAME+"(_id integer primary key autoincrement,title varchar(100),link varchar(200),author varchar(100),image varchar(300),pubdate varchar(100),type varchar(20),description text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
