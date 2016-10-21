package com.zhibo.jufan.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by wangpenglong on 2016-9-14.
 */
public class Sp {

    //往里面存数据
    public static void putInfo(Context context,String key,Object value){
        SharedPreferences sp = context.getSharedPreferences("sp", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        if (value instanceof String){
            editor.putString(key, (String) value);
        }else if (value instanceof Boolean){
            editor.putBoolean(key, (Boolean) value);
        }
        //提交数据
        editor.commit();
    }

    //取数据
    public static Object getInfo(Context context, String key, Object value){
        SharedPreferences sp = context.getSharedPreferences("sp", Context.MODE_PRIVATE);
        if (value instanceof String){
            return sp.getString(key,"");
        }else if (value instanceof Boolean){
            return sp.getBoolean(key,false);
        }
        return null;
    }

    public static void clearAll(Context context){
        SharedPreferences sp = context.getSharedPreferences("sp", Context.MODE_PRIVATE);
        sp.edit().clear();
    }
}
