package com.example.newsclient_project1_myimageview_intent.sourceop;

import android.content.Context;

import com.example.newsclient_project1_myimageview_intent.sourceprovider.ISource;

import java.io.IOException;
import java.io.InputStream;

public class AssetssStreamOP implements ISource {

    private  Context context;
    public  AssetssStreamOP(Context context){
        this.context=context;
    }


    @Override
    public InputStream getInputStream(String path) throws IOException {

        return this.context.getAssets().open(path);

    }
}
