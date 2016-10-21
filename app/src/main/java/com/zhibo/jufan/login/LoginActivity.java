package com.zhibo.jufan.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.zhibo.jufan.R;
import com.zhibo.jufan.activity.MainActivity;
import com.zhibo.jufan.utils.Sp;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

public class LoginActivity extends Activity implements View.OnClickListener {

    private ImageView login_qq;
    private ImageView login_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //在您的项目启动时，调用下面的代码
        SMSSDK.initSDK(this, "1523c6d63a310", "bce7fdbd1ae79a9b4251962287cd741b");

        //获取控件
        initview();

        //qq和短信的点击事件
        login_qq.setOnClickListener(this);
        login_phone.setOnClickListener(this);
    }

    /**
     * 获取控件
     */
    private void initview() {
        ImageView login_wchat = (ImageView) findViewById(R.id.login_wchat);
        login_qq = (ImageView) findViewById(R.id.login_qq);
        login_phone = (ImageView) findViewById(R.id.login_phone);
        ImageView login_sina = (ImageView) findViewById(R.id.login_sina);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_qq:
                login();
                break;
            case R.id.login_phone:
                //打开注册页面
                RegisterPage registerPage = new RegisterPage();
                registerPage.setRegisterCallback(new EventHandler() {
                    public void afterEvent(int event, int result, Object data) {
                        // 解析注册结果
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            @SuppressWarnings("unchecked")
                            HashMap<String, Object> phoneMap = (HashMap<String, Object>) data;
                            String country = (String) phoneMap.get("country");
                            String phone = (String) phoneMap.get("phone");

                            //记录登录状态
                            Sp.putInfo(LoginActivity.this, "login", true);


                            //跳转至mianactivity
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
                registerPage.show(LoginActivity.this);
                break;
        }
    }

    /**
     * 第三方登录
     */
    private void login() {
        ShareSDK.initSDK(this);
        Platform qq = ShareSDK.getPlatform(this, QQ.NAME);
        qq.authorize();

        qq.setPlatformActionListener(new PlatformActionListener() {

            @Override
            public void onError(Platform qq, int arg1, Throwable arg2) {
                // 弹出失败窗口
                System.out.println("第三方登录失败");
            }

            @Override
            public void onComplete(Platform qq, int arg1, HashMap<String, Object> arg2) {

                // 获取网名
                String userName = qq.getDb().getUserName();
                // 获取头像
                String userIcon = qq.getDb().getUserIcon();
                Log.i("name", userName);
                // 显示头像
                Sp.putInfo(LoginActivity.this, "img", userIcon);
                // 显示网名
                Sp.putInfo(LoginActivity.this, "name", userName);
                //记录登录状态
                Sp.putInfo(LoginActivity.this, "login", true);
               /* //退出该页面
                LoginActivity.this.finish();*/
                //跳转至mianactivity
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onCancel(Platform arg0, int arg1) {
                Log.i("登录失败", "shibai");
            }
        });
    }
}
