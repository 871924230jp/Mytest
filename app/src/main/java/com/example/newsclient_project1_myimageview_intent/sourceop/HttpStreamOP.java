package com.example.newsclient_project1_myimageview_intent.sourceop;

import com.example.newsclient_project1_myimageview_intent.exception.ErrorResponseCodeException;
import com.example.newsclient_project1_myimageview_intent.sourceprovider.ISource;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpStreamOP implements ISource {


    @Override
    public InputStream getInputStream(String path) throws IOException, ErrorResponseCodeException {
        InputStream in = null;
        URL url = new URL(path);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        int code = connection.getResponseCode();
        if (code == 200) {
             in = connection.getInputStream();
        } else {
            throw  new ErrorResponseCodeException("error request code is"+ code);
        }
        return  in;
    }
}
