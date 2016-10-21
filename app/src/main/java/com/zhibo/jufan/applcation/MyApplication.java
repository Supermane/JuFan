package com.zhibo.jufan.applcation;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by wangpenglong on 2016-10-8.
 */
public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this).build();
        // 初使化imagerLoader
        ImageLoader.getInstance().init(configuration);

    }
}
