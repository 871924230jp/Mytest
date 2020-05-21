package com.example.newsclient_project1_myimageview_intent.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.newsclient_project1_myimageview_intent.R;
import com.example.newsclient_project1_myimageview_intent.bean.NewsInfo;
import com.example.newsclient_project1_myimageview_intent.myview.MyImageView;

import java.util.List;

public class NewsAdapter extends BaseAdapter {

    private Context context;
    private  List<NewsInfo>newsList;
    public NewsAdapter(List<NewsInfo>newsList,Context context){

        this.newsList=newsList;
        this.context=context;

    }
    @Override
    public int getCount() {
        return newsList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView==null){
            convertView=View.inflate(context, R.layout.item_news,null);
             holder = new ViewHolder();
            holder.myiv_image=(MyImageView) convertView.findViewById(R.id.myiv_image);
            holder.tv_title=(TextView) convertView.findViewById(R.id.tv_title);
            holder.tv_desc=(TextView)convertView.findViewById(R.id.tv_desc);
            holder.tv_type=(TextView)convertView.findViewById(R.id.tv_type);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_title.setText(newsList.get(position).getTitle().trim());
        holder.tv_desc.setText(newsList.get(position).getDescription().trim().substring(0,21)+"...");
        holder.tv_type.setText(newsList.get(position).getType().trim());
        holder.myiv_image.setImageUrl(newsList.get(position).getImage());




        return convertView;
    }
    private  static class  ViewHolder{
        MyImageView myiv_image;
        TextView tv_title;
        TextView tv_desc;
        TextView tv_type;

    }
}
