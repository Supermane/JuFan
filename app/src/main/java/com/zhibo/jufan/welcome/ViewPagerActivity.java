package com.zhibo.jufan.welcome;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhibo.jufan.R;
import com.zhibo.jufan.login.LoginActivity;

public class ViewPagerActivity extends Activity {

    private ViewPager vp;
    private TextView tv;
    // 定义一个图片的集合
    int[] imgs = {
            R.mipmap.li_img_guide_1,
            R.mipmap.li_img_guide_2,
            R.mipmap.li_img_guide_3,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        // 获取控件
        vp = (ViewPager) findViewById(R.id.vp);


        // viewpager的适配器
        Setadapter();

        //viewpager的监听事件
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == imgs.length - 1) {
                    tv.setVisibility(View.VISIBLE);
                    //立即体验的监听事件
                    tv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //跳转至主界面
                            Intent in = new Intent(ViewPagerActivity.this, LoginActivity.class);
                            startActivity(in);
                            finish();
                        }
                    });
                } else {
                    tv.setVisibility(View.INVISIBLE);
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
     * viewpager的适配器
     */
    private void Setadapter() {
        vp.setAdapter(new PagerAdapter() {

            @Override
            public int getCount() {
                return imgs.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                // TODO Auto-generated method stub
                container.removeView((View) object);
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                // 获取视图
                View v = View.inflate(ViewPagerActivity.this, R.layout.viewpageractivity_vp_item, null);
                // 获取控件
                ImageView img = (ImageView) v.findViewById(R.id.vp_img);
                tv = (TextView) v.findViewById(R.id.welcome);

                //隐藏文字
                tv.setVisibility(View.INVISIBLE);

                // 赋值
                img.setImageResource(imgs[position]);
                // 添加视图
                container.addView(v);
                return v;
            }
        });
    }
}
