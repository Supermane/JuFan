package com.zhibo.jufan.base;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.io.InputStream;

/**
 * Created by 王鹏龙 on 2016/10/17.
 */
public abstract class BaseActivity extends Activity implements View.OnClickListener {

    //用来控制头部控件的显示
    public static final int BA_LETT_IMAGE = 0;
    public static final int BA_LEFT_TEXT = 1;
    public static final int BA_RIGHT_IMAGE = 2;
    public static final int BA_RIGHT_TEXT = 3;
    public static final int BA_LETI_IMAGE_RIGHT_IMAGE = 4;
    public static final int BA_LETI_IMAGE_RIGHT_TEXT = 5;
    public static final int BA_LEFT_TEXT_RIGHT_IMAGE = 6;
    public static final int BA_LEFT_TEXT_RIGHT_TEXT = 7;
    public static final int BA_RIGHT_IMAGE_TWO_IMAGE = 8;

    //头部控件
    private TextView tv_main_title_left_return;
    private TextView tv_main_title_textview;
    private TextView tv_main_title_setting;
    private ImageView iv_right_view;
    private ImageView iv_left_view;

    //右边第二张图片
    private ImageView iv_right_view_two;
    private LinearLayout ll_left_layout;
    private RelativeLayout rl_main_title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        super.onCreate(savedInstanceState);
        setContentView(setLayoutResouceId());
        initView();
        initData();
        initListener();
    }

    protected void initView() {
    }


    protected void initListener() {
    }

    protected void initData() {
    }

    // 点击事件就直接复用这个方法即可。
    protected abstract void processClick(View v);


    @Override
    public void onClick(View v) {
        processClick(v);
    }

    /**
     * 获取数据。  封装个okhttp
     */
    public void requestData(HttpRequest.HttpMethod httpMethod, String url,
                            RequestParams params, RequestCallBack<String> callBack) {
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(httpMethod, url, params, callBack);
    }

    /**
     * 查找控件
     *
     * @param id
     * @param <T>
     * @return
     */
    protected <T extends View> T getView(int id) {

        return (T) super.findViewById(id);
    }

    /**
     * 设置根布局资源id
     *
     * @return
     * @author 漆可
     * @date 2016-10-17
     */
    protected abstract int setLayoutResouceId();

    /**
     * 重写此方法防止某个模块崩掉导致Fragment重叠
     *
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //必须注释掉
        //super.onSaveInstanceState(outState);
    }

    /*初始化头部控件的方法*/
    public abstract void initHeader();

    /*查找控件的方法*/
    public abstract void initWidget();

    /*设置状态的方法*/
    public abstract void setWidgetState();


    /*初始化头部控件*//*
    public void inittHeaderWidget() {
        tv_main_title_left_return = getView(R.id.id_tv_back);
        tv_main_title_textview = getView(R.id.tv_main_title_textview);
        tv_main_title_setting = getView(R.id.tv_main_title_setting);
        iv_right_view = getView(R.id.iv_right_view);
        iv_left_view = getView(R.id.iv_left_view);
        ll_left_layout = getView(R.id.ll_left_layout);
        rl_main_title = getView(R.id.rl_main_title);
    }*/


    /*左边的控件显示*/
    public void addBtnLeftListener(View.OnClickListener listener) {
        tv_main_title_left_return.setVisibility(View.VISIBLE);
        tv_main_title_left_return.setOnClickListener(listener);
    }


    /*左边文字控件自定义文字内容*/
    public void addBtnLeftTextListener(View.OnClickListener listener, String title) {
        tv_main_title_left_return.setVisibility(View.VISIBLE);
        tv_main_title_left_return.setOnClickListener(listener);
        tv_main_title_left_return.setText(title);
    }


    /*左边的图片控件显示*/
    public void addIMGLeftListener(View.OnClickListener listener) {
        iv_left_view.setVisibility(View.VISIBLE);
        ll_left_layout.setOnClickListener(listener);
    }

    /*左边控件自定义图片*/
    public void addIMGLeftBitmaplistener(View.OnClickListener listener, int id) {
        InputStream is = getResources().openRawResource(id);
        Bitmap mBitmap = BitmapFactory.decodeStream(is);
        iv_left_view.setVisibility(View.VISIBLE);
        iv_left_view.setImageBitmap(mBitmap);
        ll_left_layout.setOnClickListener(listener);
    }


    /*右边文字控件*/
    public void addBtnRightListener(View.OnClickListener listener) {
        tv_main_title_setting.setVisibility(View.VISIBLE);
        tv_main_title_setting.setOnClickListener(listener);
    }

    /*右边文字控件自定义文字内容*/
    public void addBtnRightTextListener(View.OnClickListener listener, String title) {
        tv_main_title_setting.setVisibility(View.VISIBLE);
        tv_main_title_setting.setOnClickListener(listener);
        tv_main_title_setting.setText(title);
    }

    /*右边图片控件*/
    public void addIMGRightListener(View.OnClickListener listener) {
        iv_right_view.setVisibility(View.VISIBLE);
        iv_right_view.setOnClickListener(listener);
    }

    /*右边图片控件自定义图片*/
    public void addIMGRightBitmapListener(View.OnClickListener listener, int id) {
        InputStream is = getResources().openRawResource(id);
        Bitmap mBitmap = BitmapFactory.decodeStream(is);
        iv_right_view.setVisibility(View.VISIBLE);
        iv_right_view.setOnClickListener(listener);
        iv_right_view.setImageBitmap(mBitmap);
    }

    /*设置中间文字的内容*/
    public void setTitle(String title) {
        iv_left_view.setVisibility(View.GONE);
        iv_right_view.setVisibility(View.GONE);
        tv_main_title_setting.setVisibility(View.GONE);
        tv_main_title_left_return.setVisibility(View.GONE);

        iv_right_view_two.setVisibility(View.GONE);
        tv_main_title_textview.setText(title);
    }


    /*控制头部控件的显示与隐藏*/
    public void setTitleVisible(int staute) {
        switch (staute) {
            case 0://显示左边的图片返回键
                iv_left_view.setVisibility(View.VISIBLE);
                break;
            case 1://显示左边的文字返回键
                tv_main_title_setting.setVisibility(View.VISIBLE);
                break;
            case 2://显示右边的图片返回键
                iv_right_view.setVisibility(View.VISIBLE);
                break;
            case 3://显示右边的文字返回键
                tv_main_title_left_return.setVisibility(View.VISIBLE);
                break;
            case 4://显示左边的图片和右边的图片返回键
                iv_left_view.setVisibility(View.VISIBLE);
                iv_right_view.setVisibility(View.VISIBLE);
                break;
            case 5://显示左边的图片和右边的文字返回键
                iv_left_view.setVisibility(View.VISIBLE);
                tv_main_title_left_return.setVisibility(View.VISIBLE);
                break;
            case 6://显示左边的文字和右边的图片返回键
                iv_right_view.setVisibility(View.VISIBLE);
                tv_main_title_setting.setVisibility(View.VISIBLE);
                break;
            case 7://显示左边的文字和右边的文字返回键
                tv_main_title_setting.setVisibility(View.VISIBLE);
                tv_main_title_left_return.setVisibility(View.VISIBLE);
            case 8://同事显示右边两张图片的控件
                iv_right_view_two.setVisibility(View.VISIBLE);
                iv_right_view.setVisibility(View.VISIBLE);
                break;
            case 9://全部隐藏
                iv_right_view_two.setVisibility(View.GONE);
                iv_right_view.setVisibility(View.GONE);
                break;
        }
    }
}
