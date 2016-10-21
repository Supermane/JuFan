package com.zhibo.jufan.base;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 王鹏龙 on 2016/10/17.
 */
public abstract class BaseFragment extends Fragment {

    /**
     * 贴附的activity
     */
    protected FragmentActivity mActivity;

    /**
     * 根view
     */
    protected View mRootView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(setLayoutResouceId(), container, false);
        initView();
        initData(getArguments());
        setListener();
        //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }

        return mRootView;
    }


    /**
     * 初始化数据
     *
     * @param arguments 接收到的从其他地方传递过来的参数
     * @author 王鹏龙
     * @date 2016-10-17
     */
    protected void initData(Bundle arguments) {

    }

    /**
     * 初始化View
     *
     * @author 王鹏龙
     * @date 2016-10-17
     */
    protected void initView() {

    }

    /**
     * 设置监听事件
     *
     * @author 王鹏龙
     * @date 2016-10-17
     */
    protected void setListener() {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (FragmentActivity) activity;
    }

    /**
     * 设置根布局资源id
     *
     * @return
     * @author 王鹏龙
     * @date 2016-10-17
     */
    protected abstract int setLayoutResouceId();

    protected <T extends View> T getView(int id) {
        if (mRootView == null) {
            return null;
        }

        return (T) mRootView.findViewById(id);
    }
}
