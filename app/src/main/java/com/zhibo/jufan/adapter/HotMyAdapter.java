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
import com.zhibo.jufan.bean.HotBean;

import java.util.List;

/**
 * Created by 王鹏龙 on 2016/10/11.
 */
public class HotMyAdapter extends BaseAdapter {

    private List<HotBean.ContentEntity.ListEntity> zblist;
    private Context context;

    public HotMyAdapter(List<HotBean.ContentEntity.ListEntity> zblist, Context context) {
        this.zblist = zblist;
        this.context = context;
    }

    public void addrest(List<HotBean.ContentEntity.ListEntity> zblist) {

        this.zblist.addAll(zblist);
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return zblist.size();
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
            convertView = View.inflate(context, R.layout.hot_image_item, null);
        }

        //获取控件
        ImageView hot_image = (ImageView) convertView.findViewById(R.id.hot_image);
        ImageView hot_small_touxiang = (ImageView) convertView.findViewById(R.id.hot_small_touxiang);
        TextView hot_zhubo_name = (TextView) convertView.findViewById(R.id.hot_zhubo_name);
        TextView hot_zhubo_location = (TextView) convertView.findViewById(R.id.hot_zhubo_location);

        //圆角的代码
        DisplayImageOptions options = new DisplayImageOptions.Builder().displayer(new RoundedBitmapDisplayer(30)).build();

        //大图片
        ImageLoader.getInstance().displayImage(zblist.get(position).bigheadimg, hot_image, options);
        //头像
        ImageLoader.getInstance().displayImage(zblist.get(position).smallheadimg, hot_small_touxiang);
        //主播名
        hot_zhubo_name.setText(zblist.get(position).name);
        //定位
        hot_zhubo_location.setText(zblist.get(position).place);

        return convertView;
    }
}

