package com.zhibo.jufan.fragment.shouye;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.zhibo.jufan.R;
import com.zhibo.jufan.adapter.HotMyAdapter;
import com.zhibo.jufan.adapter.IndexPagerAdapter;
import com.zhibo.jufan.bean.HotBean;
import com.zhibo.jufan.utils.GetHttpUtils;
import com.zhibo.jufan.utils.GetIntent;
import com.zhibo.jufan.zhibo.VideoViewPlayingActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangpenglong on 2016-10-6.
 */
public class IndexFragmentOfhot extends Fragment {

    private View v;
    private ViewPager vp;
    private String path = "http://live.jufan.tv/cgi/hall/get?sign=3E1C02CDE8AE682136B658A73F63406D700846EC&r=xjjf&page=0&type=hot&userid=500056449";
    private List<HotBean.ContentEntity.BannerEntity> blist;
    private List<HotBean.ContentEntity.ListEntity> zblist;
    private LinearLayout hot_tishi;
    private TextView tishi;
    //存放的是viewPager下面的点
    List<ImageView> ivList = new ArrayList<>();
    private LinearLayout ll_dot;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int a = msg.what;
            switch (a) {
                case 0:
                    //初始化小圆点
                    initDot();
                    vp.setCurrentItem(100000);
                    vp.setAdapter(new IndexPagerAdapter(getActivity(), blist, handler));
                    sendEmptyMessage(1);
                    //vp的事件
                    ViewPagerPagerListener();
                    break;
                case 1:
                    int count = vp.getCurrentItem();
                    count++;
                    vp.setCurrentItem(count);
                    handler.sendEmptyMessageDelayed(1, 2000);
                    break;

                default:
                    break;
            }
        }
    };
    private ListView zlistview;
    private PullToRefreshScrollView pf;
    private HotMyAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.index_fragment2, null);
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
            //获取数据
            GetData();
        } else {
            Toast.makeText(getActivity(), "没有网路连接", Toast.LENGTH_LONG).show();
        }
        //监听事件
        Setclicks();

        //点击事件
        zlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //传值
                Intent intent = new Intent(getActivity(), VideoViewPlayingActivity.class);
                intent.setData(Uri.parse(zblist.get(position).video));
                intent.putExtra("img", zblist.get(position).smallheadimg);
                intent.putExtra("name", zblist.get(position).name);
                intent.putExtra("online", zblist.get(position).online);
                intent.putExtra("score", zblist.get(position).score);
                intent.putExtra("rid", zblist.get(position).rid);
                startActivity(intent);
            }
        });
    }

    /**
     * vp的事件
     */
    private void ViewPagerPagerListener() {
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //遍历小点的集合
                for (int i = 0; i < ivList.size(); i++) {
                    if (position % blist.size() == i) {
                        ivList.get(i).setImageResource(R.drawable.yuan_clicked);
                    } else {
                        ivList.get(i).setImageResource(R.drawable.yuan_normal);
                    }
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 监听事件
     */
    private void Setclicks() {
        //提示的点击时间
        tishi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //提示界面消失
                hot_tishi.setVisibility(View.GONE);
            }
        });


        //刷新模式
        pf.setMode(PullToRefreshBase.Mode.BOTH);
        //监听
        pf.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (zblist!=null){
                            zblist.clear();
                        }
                        //刷新适配器
                        adapter.addrest(zblist);
                        GetData();
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
                        //添加数据
                        Toast.makeText(getActivity(), "已加载全部内容", Toast.LENGTH_LONG);
                        //刷新适配器
                        adapter.addrest(zblist);
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
        //热门的无限轮播
        vp = (ViewPager) v.findViewById(R.id.index_vp);
        //提示的那个布局
        hot_tishi = (LinearLayout) v.findViewById(R.id.hot_tishi);
        tishi = (TextView) v.findViewById(R.id.tishi);
        //小圆点布局
        ll_dot = (LinearLayout) v.findViewById(R.id.ll_dot);
        //RecyclerView
        zlistview = (ListView) v.findViewById(R.id.hot_recyclerView);
        //刷新
        pf = (PullToRefreshScrollView) v.findViewById(R.id.pf);

    }

    /**
     * 获取数据
     */
    private void GetData() {
        //异步加载
        new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... params) {
                //网络请求
                String str = GetHttpUtils.Getstr(path);

                Log.e("================>", str);
                return str;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                //解析
                Gson gson = new Gson();
                HotBean bean = gson.fromJson(s, HotBean.class);
                blist = bean.content.banner;  //热门下边的轮播图
                zblist = bean.content.list;   //主播的信息

                Log.e("222222222222222", zblist.toString());
                Log.e("++++++++++++++++>", blist.toString());

                //创建并管理适配器
                adapter = new HotMyAdapter(zblist, getActivity());
                zlistview.setAdapter(adapter);

                //发送到handler
                handler.sendEmptyMessage(0);

            }
        }.execute();
    }


    /**
     * 初使化点
     */
    protected void initDot() {

        if (ivList != null) {
            ivList.clear();
        }
        //移除小圆点
        ll_dot.removeAllViews();
        for (int i = 0; i < blist.size(); i++) {

            ImageView iv = new ImageView(getActivity());

            if (i == 0) {
                //被选中的时候
                iv.setImageResource(R.drawable.yuan_clicked);
            } else {
                //正常情况的小圆点
                iv.setImageResource(R.drawable.yuan_normal);
            }

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(10, 10);
            //控制两点之间的距离的
            params.setMargins(5, 0, 5, 0);
            //把小圆点放进集合
            ivList.add(iv);
            ll_dot.addView(iv, params);
        }
    }
}
