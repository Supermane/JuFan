package com.zhibo.jufan.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by wangpenglong on 2016-9-29.
 * 主要进行网络请求
 */
public class GetHttpUtils {

    public static String Getstr(String path) {
        try {
            //请求地址
            URL url = new URL(path);
            //HttpURLConnection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //设置请求方式和连接超时
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            //请求成功
            if (conn.getResponseCode() == 200) {
                //得到一个流
                InputStream inputStream = conn.getInputStream();
                //转换成json串
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
                StringBuffer sf = new StringBuffer();
                String str = "";
                while ((str = br.readLine()) != null) {
                    sf.append(str);
                }

                String info = sf.toString();
                return info;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
