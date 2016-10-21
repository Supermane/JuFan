package com.zhibo.jufan.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.zhibo.jufan.R;

public class ShareActivity extends Activity {

    private ImageView close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        //获取控件
        getview();

        //事件
        setonclicks();
    }

    /**
     * 事件
     */
    private void setonclicks() {
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 获取控件
     */
    public void getview() {
        //关闭
        close = (ImageView) findViewById(R.id.close);
    }
}
