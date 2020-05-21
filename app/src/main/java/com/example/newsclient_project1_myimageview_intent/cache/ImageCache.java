package com.example.newsclient_project1_myimageview_intent.cache;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.example.newsclient_project1_myimageview_intent.R;
import com.example.newsclient_project1_myimageview_intent.exception.ErrorResponseCodeException;
import com.example.newsclient_project1_myimageview_intent.sourceop.HttpStreamOP;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageCache {

    private  Context context;
    public  ImageCache(Context context){

     this.context=context;
    }

    public Bitmap getBitmapFromUrl(String path){
        File file = new File(this.context.getCacheDir(), Base64.encodeToString(path.getBytes(),Base64.DEFAULT));
        if (file.exists() && file.length() >0){
        }else {
            try {
                InputStream inputStream = new HttpStreamOP().getInputStream(path);
               if (inputStream != null){
                   Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                   writeImage2Cache(bitmap,file);
                   return bitmap;
               }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ErrorResponseCodeException e) {
                e.printStackTrace();
                return  BitmapFactory.decodeResource(this.context.getResources(), R.mipmap.load_failure);
            }
        }
        System.out.println("------------------server");
        return BitmapFactory.decodeFile(file.getAbsolutePath());
    }

    private void writeImage2Cache(final Bitmap bitmap, final File file) {

        if(bitmap == null || file == null)
            return;
        new Thread(){
            @Override
            public void run() {
                BufferedOutputStream bufferedOutputStream = null;
                FileOutputStream fileOutputStream =  null;
                try {
                    fileOutputStream= new FileOutputStream(file);
                    bufferedOutputStream = new BufferedOutputStream(fileOutputStream,2*1024);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, bufferedOutputStream);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }finally {
                        try {
                            bufferedOutputStream.flush();
                            bufferedOutputStream.close();
                            fileOutputStream.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
        }.start();
    }
}
