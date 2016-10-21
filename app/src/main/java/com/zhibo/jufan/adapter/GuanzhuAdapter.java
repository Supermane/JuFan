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
 * Created by 王鹏龙 on 2016/10/18.
 */
public class GuanzhuAdapter extends BaseAdapter {

    Context context;
    List<NewsData.ContentEntity.ListEntity> list;
    private ImageView news_img;
    private TextView news_name;
    private TextView news_location;

    public GuanzhuAdapter(Context context, List<NewsData.ContentEntity.ListEntity> list) {

        this.context = context;
        this.list = list;

    }

    @Override
    public int getCount() {
        return 3;
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
        //视图
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.guanzhu_item, null);
        }

        //获取控件
        news_img = (ImageView) convertView.findViewById(R.id.guanzhu_image);
        news_name = (TextView) convertView.findViewById(R.id.guanzhu_name);
        news_location = (TextView) convertView.findViewById(R.id.guanzhu_location);

        //圆角的代码
        DisplayImageOptions options = new DisplayImageOptions.Builder().displayer(new RoundedBitmapDisplayer(20)).build();

        //赋值
        ImageLoader.getInstance().displayImage(list.get(position).bigheadimg, news_img, options);
        news_name.setText(list.get(position).name);
        news_location.setText(list.get(position).place);
        return convertView;
    }
}
