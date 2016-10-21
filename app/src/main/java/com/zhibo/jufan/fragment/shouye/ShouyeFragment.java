package com.zhibo.jufan.fragment.shouye;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.zhibo.jufan.R;

/**
 * Created by wangpenglong on 2016-10-6.
 */
public class ShouyeFragment extends Fragment {

    private View v;
    private ViewPager vp;
    private RadioGroup rg;
    private IndexFragmentOfguanzhu guanzhu;
    private IndexFragmentOfhot hot;
    private IndexFragmentOfnews news;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.sfragment, null);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //获取控件
        getview();
        // 按钮
        initbutton();
        //viewpager的适配器和事件
        SetPagerAdapter();
        //默认选中第二个
        ChangeView(1);
        vp.setCurrentItem(1);

    }

    /**
     * 适配器和事件
     */
    private void SetPagerAdapter() {
        //适配器
        vp.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {

            Fragment fragment = new Fragment();
            @Override
            public Fragment getItem(int position) {
                switch (position){
                    case 0:
                        guanzhu = new IndexFragmentOfguanzhu();
                        fragment = guanzhu;
                        break;
                    case 1:
                        hot = new IndexFragmentOfhot();
                        fragment = hot;
                        break;
                    case 2:
                        news = new IndexFragmentOfnews();
                        fragment = news;
                        break;
                }

                return fragment;
            }

            @Override
            public int getCount() {
                return 3;
            }
        });

        //监听事件
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                vp.setCurrentItem(position); // 导航
                ChangeView(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 获取控件
     */
    public void getview() {
        //搜索
        ImageView sousuo = (ImageView) v.findViewById(R.id.sousuo);
        //奖牌
        ImageView jiang = (ImageView) v.findViewById(R.id.shouye_jiang);
        //radiogroup
        rg = (RadioGroup) v.findViewById(R.id.shouye_rg);
        //关注、最新、热门
        TextView guanzhu = (TextView) v.findViewById(R.id.guanzhu);
        TextView hot = (TextView) v.findViewById(R.id.hot);
        TextView news = (TextView) v.findViewById(R.id.news);
        //viewpager
        vp = (ViewPager) v.findViewById(R.id.shouye_vp);
    }

    /**
     * 按钮
     */
    public void initbutton() {
        // TODO Auto-generated method stub
        for (int i = 0; i < rg.getChildCount(); i++) {

            TextView but = (TextView) rg.getChildAt(i);
            final int index = i;
            // 事件
            but.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    // 切换视图
                    vp.setCurrentItem(index);
                    // 改变字体颜色
                    ChangeView(index);
                }
            });
        }
    }

    /**
     * 改变字体颜色和大小
     * @param tag
     */
    public void ChangeView(int tag) {
        // TODO Auto-generated method stub
        for (int i = 0; i < rg.getChildCount(); i++) {
            TextView rb = (TextView) rg.getChildAt(i);
            if (tag == i) {
                rb.setTextColor(Color.RED);
                rb.setTextSize(18);
            } else {
                rb.setTextColor(Color.GRAY);
                rb.setTextSize(14);
            }
        }
    }
}
