package com.example.newsclient_project1_myimageview_intent.sourceprovider;

import android.content.Context;

import com.example.newsclient_project1_myimageview_intent.enumeration.SourceType;

public class SourceFactory {

    public static ISource sourceCreate(Context context, SourceType sourceType){


        return  SourceMap.getrateSource(context).get(sourceType);

    }
}


