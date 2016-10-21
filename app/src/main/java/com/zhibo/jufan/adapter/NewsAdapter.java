package com.zhibo.jufan.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.zhibo.jufan.R;
import com.zhibo.jufan.bean.NewsData;

import java.util.List;

/**
 * Created by 王鹏龙 on 2016/10/11.
 */
public class NewsAdapter extends BaseAdapter {

    Context context;
    List<NewsData.ContentEntity.ListEntity> beanlist;
    private ImageView news_img;
    private TextView news_name;
    private TextView news_location;

    public NewsAdapter(Context context, List<NewsData.ContentEntity.ListEntity> beanlist) {
        this.context = context;
        this.beanlist = beanlist;
    }

    public void addrest(List<NewsData.ContentEntity.ListEntity> pageList) {
        this.beanlist.addAll(pageList);
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return beanlist.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.news_gd_item, null);
        }

        //获取控件
        news_img = (ImageView) convertView.findViewById(R.id.news_image);
        news_name = (TextView) convertView.findViewById(R.id.news_name);
        news_location = (TextView) convertView.findViewById(R.id.news_location);

        //圆角的代码
        DisplayImageOptions options = new DisplayImageOptions.Builder().displayer(new RoundedBitmapDisplayer(20)).build();


        //赋值
        ImageLoader.getInstance().displayImage(beanlist.get(position).bigheadimg, news_img ,options);
        news_name.setText(beanlist.get(position).name);
        news_location.setText(beanlist.get(position).place);
        return convertView;
    }
}
