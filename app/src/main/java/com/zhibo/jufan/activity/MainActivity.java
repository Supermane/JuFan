package com.zhibo.jufan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.zhibo.jufan.R;
import com.zhibo.jufan.fragment.mine.MineFragment;
import com.zhibo.jufan.fragment.shouye.ShouyeFragment;

public class MainActivity extends FragmentActivity {

    private FragmentManager manager;
    private ShouyeFragment sfragment;
    private MineFragment mfragment;
    private RadioGroup rg;
    private RadioButton shouye;
    private ImageView jia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取控件
        getview();

        //进行fragment的操作
        setFragment();

        // 默认首页被选中
        shouye.setChecked(true);
        //默认显示首页fragment
        switchFragment("sfragment");

        //点击事件
        setonclicks();
    }

    /**
     * 点击事件
     */
    private void setonclicks() {
        jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, ShareActivity.class);
                startActivity(in);
            }
        });
    }

    /**
     * 选择fragment
     */
    private void switchFragment(String tag) {
        // 事物
        FragmentTransaction transaction = manager.beginTransaction();
        if ("sfragment".equals(tag)) {
            transaction.show(sfragment);
            transaction.hide(mfragment);
        } else if ("mfragment".equals(tag)) {
            transaction.show(mfragment);
            transaction.hide(sfragment);
        }
        transaction.commit();
    }

    /**
     * 进行操作fragment
     */
    private void setFragment() {
        //创建一个管理类
        manager = getSupportFragmentManager();
        //创建一个事务
        FragmentTransaction transaction = manager.beginTransaction();
        //实例化fragment类
        sfragment = new ShouyeFragment();
        mfragment = new MineFragment();
        //将fragment添加进事务
        transaction.add(R.id.fragment, sfragment, "sfragment");
        transaction.add(R.id.fragment, mfragment, "mfragment");
        //提交
        transaction.commit();

        //rg的监听事件
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.main_shouye:
                        switchFragment("sfragment");
                        break;
                    case R.id.main_my:
                        switchFragment("mfragment");
                        break;
                }
            }
        });
    }

    /**
     * 获取控件
     */
    public void getview() {

        FrameLayout fragment = (FrameLayout) findViewById(R.id.fragment);
        rg = (RadioGroup) findViewById(R.id.rg);
        jia = (ImageView) findViewById(R.id.main_jia);
        shouye = (RadioButton) findViewById(R.id.main_shouye);
        RadioButton mine = (RadioButton) findViewById(R.id.main_my);
    }
}
