package com.zhibo.jufan.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.zhibo.jufan.R;
import com.zhibo.jufan.bean.GiftData;

import java.util.List;

/**
 * Created by 王鹏龙 on 2016/10/20.
 */
public class GiftAdapter extends BaseAdapter {

    Context context;
    List<GiftData.ContentEntity.JufanEntity.ListEntity> giftlist;

    public GiftAdapter(Context context, List<GiftData.ContentEntity.JufanEntity.ListEntity> giftlist) {

        this.context = context;
        this.giftlist = giftlist;
    }

    @Override
    public int getCount() {
        return giftlist.size();
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
            convertView = View.inflate(context, R.layout.gift_item, null);
        }
        ImageView img = (ImageView) convertView.findViewById(R.id.giftimage);
        TextView tv1 = (TextView) convertView.findViewById(R.id.giftTv1);
        TextView tv2 = (TextView) convertView.findViewById(R.id.giftTv2);

        ImageLoader.getInstance().displayImage(giftlist.get(position).url,img);
        tv1.setText(giftlist.get(position).exp+"个");
        tv2.setText(giftlist.get(position).desc);
        return convertView;
    }
}
