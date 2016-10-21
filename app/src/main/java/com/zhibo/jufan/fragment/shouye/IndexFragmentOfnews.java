package com.zhibo.jufan.fragment.shouye;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.zhibo.jufan.R;
import com.zhibo.jufan.adapter.NewsAdapter;
import com.zhibo.jufan.bean.NewsData;
import com.zhibo.jufan.utils.GetHttpUtils;
import com.zhibo.jufan.utils.GetIntent;
import com.zhibo.jufan.zhibo.VideoViewPlayingActivity;

import java.util.List;

/**
 * Created by 王鹏龙 on 2016-10-6.
 */
public class IndexFragmentOfnews extends Fragment {

    //最新的地址------pager为0的时候
    private String path = "http://live.jufan.tv/cgi/hall/get?sign=4E28973E05FB19007E8E0D786AC05C1BB7436F09&userid=500056489&type=new&r=lggt&page=0";
    private View v;
    private GridView gd;
    private List<NewsData.ContentEntity.ListEntity> beanlist;
    private PullToRefreshScrollView pf;
    private NewsAdapter adapter;
    private Handler handler = new Handler();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.index_fragment3, null);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //获取控件
        initview();
        //判断网络
        boolean is = GetIntent.GetInfo(getActivity());
        if (is) {
            //请求数据
            initData();
        } else {
            Toast.makeText(getActivity(), "没有网路连接", Toast.LENGTH_LONG).show();
        }
        //点击事件
        gd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //传值
                Intent intent = new Intent(getActivity(), VideoViewPlayingActivity.class);
                intent.setData(Uri.parse(beanlist.get(position).video));
                intent.putExtra("img", beanlist.get(position).smallheadimg);
                intent.putExtra("name", beanlist.get(position).name);
                intent.putExtra("online", beanlist.get(position).online);
                intent.putExtra("score", beanlist.get(position).score);
                intent.putExtra("rid", beanlist.get(position).rid);
                startActivity(intent);

            }
        });

        //监听
        onclicks();
    }

    /**
     * 刷新的监听
     */
    private void onclicks() {
        //刷新模式
        pf.setMode(PullToRefreshBase.Mode.BOTH);
        //监听
        pf.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        beanlist.clear();
                        //刷新适配器
                        adapter.addrest(beanlist);
                        initData();
                        //刷新成功
                        pf.onRefreshComplete();
                    }
                }, 2000);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "已加载全部内容", Toast.LENGTH_LONG);
                        //刷新适配器
                        adapter.addrest(beanlist);
                        //刷新成功
                        pf.onRefreshComplete();
                    }
                }, 2000);
            }
        });
    }

    /**
     * 获取控件
     */
    private void initview() {
        //gridview
        gd = (GridView) v.findViewById(R.id.index_news_gd);
        //刷新
        pf = (PullToRefreshScrollView) v.findViewById(R.id.pf);
    }

    /**
     * 获取数据
     */
    private void initData() {
        new AsyncTask<Void, Void, String>() {


            @Override
            protected String doInBackground(Void... params) {
                //请求数据
                String json = GetHttpUtils.Getstr(path);
                return json;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                //解析数据
                Gson gson = new Gson();
                NewsData newsbean = gson.fromJson(s, NewsData.class);
                beanlist = newsbean.content.list;

                //适配器
                adapter = new NewsAdapter(getActivity(), beanlist);
                gd.setAdapter(adapter);

            }
        }.execute();
    }
}
