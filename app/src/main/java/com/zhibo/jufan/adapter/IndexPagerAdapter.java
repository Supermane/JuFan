package com.zhibo.jufan.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.zhibo.jufan.R;
import com.zhibo.jufan.bean.HotBean;

import java.util.List;

/**
 * Created by wangpenglong on 2016-10-8.
 */
public class IndexPagerAdapter extends PagerAdapter {

    Context context;
    List<HotBean.ContentEntity.BannerEntity> blist;
    Handler handler;

    public IndexPagerAdapter(Context context, List<HotBean.ContentEntity.BannerEntity> blist, Handler handler) {
        this.context = context;
        this.blist = blist;
        this.handler = handler;
    }


    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
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
        View v = View.inflate(context, R.layout.index_vp_item, null);
        //获取控件
        ImageView image = (ImageView) v.findViewById(R.id.vp_item_img);
        //图片的触控
        image.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: // 手指按下的事件
                        // 全部取消
                        handler.removeCallbacksAndMessages(null);
                        break;
                    case MotionEvent.ACTION_UP: // 手指抬起的事件
                        handler.sendEmptyMessageDelayed(1, 2000);
                        break;

                    case MotionEvent.ACTION_CANCEL: // 取消的事件
                        handler.sendEmptyMessageDelayed(1, 2000);
                        break;

                    default:
                        break;
                }
                return true;
            }
        });

        //圆角的代码
        DisplayImageOptions options = new DisplayImageOptions.Builder().displayer(new RoundedBitmapDisplayer(20)).build();
        //赋值
        ImageLoader.getInstance().displayImage(blist.get(position % blist.size()).img, image, options);
        container.addView(v);
        return v;
    }
}
