package com.zhibo.jufan.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by wangpenglong on 2016-9-9.
 */
public class GetIntent {
    public static boolean GetInfo(Context context) {
        //得到网络连接信息
        ConnectivityManager manager = (ConnectivityManager)context. getSystemService(Context.CONNECTIVITY_SERVICE);

        //判断网络是否连接
        if (manager.getActiveNetworkInfo()!=null){
            boolean flag=manager.getActiveNetworkInfo().isAvailable();
            if (flag){
                NetworkInfo.State state = manager.getActiveNetworkInfo().getState();
                if (state==NetworkInfo.State.CONNECTED){
                    return true;
                }
            }
        }
        return false;
    }
}
