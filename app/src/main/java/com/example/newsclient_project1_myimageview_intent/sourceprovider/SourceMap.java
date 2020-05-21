package com.example.newsclient_project1_myimageview_intent.sourceprovider;

import android.content.Context;

import com.example.newsclient_project1_myimageview_intent.enumeration.SourceType;
import com.example.newsclient_project1_myimageview_intent.sourceop.AssetssStreamOP;
import com.example.newsclient_project1_myimageview_intent.sourceop.HttpStreamOP;

import java.util.HashMap;

public class SourceMap {
    private static HashMap<SourceType,ISource>sourceMap;
    public  static HashMap<SourceType,ISource> getrateSource(Context context){
        if (sourceMap ==null){
            sourceMap = new HashMap<>();
            sourceMap.put(SourceType.SOURCE_FROM_SERVER,new HttpStreamOP());
            sourceMap.put(SourceType.SOURCE_FROM_ASSETSS,new AssetssStreamOP(context));
        }
        return sourceMap;


    }



}
