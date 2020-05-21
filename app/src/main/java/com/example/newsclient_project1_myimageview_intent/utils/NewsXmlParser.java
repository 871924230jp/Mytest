package com.example.newsclient_project1_myimageview_intent.utils;

import android.util.Xml;

import com.example.newsclient_project1_myimageview_intent.bean.NewsInfo;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
/*工具类的异常自己处理，不用抛出去*/
public class NewsXmlParser {
    public static List<NewsInfo>parse(InputStream in){
        List<NewsInfo>newsList= new ArrayList<>();

        XmlPullParser parser = Xml.newPullParser();
        try {
            parser.setInput(in,"utf-8");
            int type = parser.getEventType();

            NewsInfo newsInfo=null;
            while (type != XmlPullParser.END_DOCUMENT){
                switch (type){
                    case  XmlPullParser.START_TAG:
                        if (parser.getName().equals("item"))
                        {
                             newsInfo = new NewsInfo();
                        }else if(parser.getName().equals("title")){
                            newsInfo.setTitle(parser.nextText());
                        }
                        else if(parser.getName().equals("link")){
                            newsInfo.setLink(parser.nextText());
                        }
                        else if(parser.getName().equals("author")){
                            newsInfo.setAuthor(parser.nextText());
                        }else if(parser.getName().equals("image")){
                            newsInfo.setImage(parser.nextText());
                        }else if(parser.getName().equals("pubDate")){
                            newsInfo.setPubDate(parser.nextText());
                        }else if(parser.getName().equals("description")){
                            newsInfo.setDescription(parser.nextText());
                        }else if(parser.getName().equals("type")){
                            newsInfo.setType(parser.nextText());
                        }
                    break;
                    case  XmlPullParser.END_TAG:
                        if (parser.getName().equals("item")){
                            newsList.add(newsInfo);
                        }


                        break;
                }
                type =parser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return  newsList;
    }
}
