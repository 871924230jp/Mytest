package com.example.newsclient_project1_myimageview_intent.sourceprovider;

import com.example.newsclient_project1_myimageview_intent.exception.ErrorResponseCodeException;

import java.io.IOException;
import java.io.InputStream;

public interface ISource {

     InputStream getInputStream(String path) throws IOException, ErrorResponseCodeException;


}
