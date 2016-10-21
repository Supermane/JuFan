package com.zhibo.jufan.fragment.shouye;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.gson.Gson;
import com.zhibo.jufan.R;
import com.zhibo.jufan.adapter.GuanzhuAdapter;
import com.zhibo.jufan.bean.NewsData;
import com.zhibo.jufan.utils.GetHttpUtils;
import com.zhibo.jufan.zhibo.VideoViewPlayingActivity;

import java.util.List;

/**
 * Created by wangpenglong on 2016-10-6.
 */
public class IndexFragmentOfguanzhu extends Fragment {

    private String path = "http://live.jufan.tv/cgi/hall/get?sign=9C679E54884A5500035414F255E8B752F8BBB430&userid=500056489&type=new&r=eayi&page=0";
    private View v;
    private GridView gd;
    private List<NewsData.ContentEntity.ListEntity> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.index_fragment1, null);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //获取控件
        gd = (GridView) v.findViewById(R.id.index_guanzhu_gd);

        //获取数据
        GetData();

        gd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //传值
                Intent intent = new Intent(getActivity(), VideoViewPlayingActivity.class);
                intent.setData(Uri.parse(list.get(position).video));
                intent.putExtra("img", list.get(position).smallheadimg);
                intent.putExtra("name", list.get(position).name);
                intent.putExtra("online", list.get(position).online);
                intent.putExtra("score", list.get(position).score);
                intent.putExtra("rid", list.get(position).rid);
                startActivity(intent);
            }
        });
    }

    /**
     * 获取数据
     */
    private void GetData() {
        new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... params) {

                //请求
                String str = GetHttpUtils.Getstr(path);
                return str;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                //解析
                Gson gson = new Gson();
                NewsData data = gson.fromJson(s, NewsData.class);
                list = data.content.list;

                //适配器
                gd.setAdapter(new GuanzhuAdapter(getActivity(), list));
            }
        }.execute();
    }
}
