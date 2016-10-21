package com.zhibo.jufan.welcome;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.zhibo.jufan.R;
import com.zhibo.jufan.login.LoginActivity;
import com.zhibo.jufan.activity.MainActivity;
import com.zhibo.jufan.utils.Sp;

public class WelcomeActivity extends Activity {

    private SharedPreferences sp;
    private Boolean loing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //获取SharedPreferences对象
        sp = getSharedPreferences("login", MODE_PRIVATE);
        //登录状态
        loing = (Boolean) Sp.getInfo(WelcomeActivity.this, "login", false);
        //开启一个线程   2秒后自动跳转
        new Handler() {
            @Override
            public void handleMessage(Message msg) {
                // TODO Auto-generated method stub

                if (sp.getBoolean("isFirst", true)) {
                    //第一次要跳转到导航页
                    sp.edit().putBoolean("isFirst", false).commit();
                    //跳转
                    Intent intent = new Intent(WelcomeActivity.this, ViewPagerActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    if (loing) {//登陆状态下的时候直接跳进主的activity
                        startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                        finish();
                    } else {
                        //不是第一次登录需要跳进登陆的activity进行登陆
                        startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
                        finish();
                    }
                }
            }

            ;
        }.sendEmptyMessageDelayed(0, 2000);
    }
}
