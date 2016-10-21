package com.zhibo.jufan.fragment.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.zhibo.jufan.R;
import com.zhibo.jufan.utils.Sp;

/**
 * Created by wangpenglong on 2016-10-6.
 */
public class  MineFragment extends Fragment {

    private TextView mUsername;
    private ImageView usrimg;
    private String touxiang;
    private String mingzi;
    private View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v =  inflater.inflate(R.layout.mfragment, null);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        usrimg = (ImageView) v.findViewById(R.id.touxiang);
        mUsername = (TextView) v.findViewById(R.id.name);

    }

    @Override
    public void onResume() {
        super.onResume();

        Boolean login = (Boolean) Sp.getInfo(getActivity(), "login", false);

        if (login) {
            //取出保存好的网名和头像
            touxiang = (String) Sp.getInfo(getActivity(), "img", "");
            mingzi = (String) Sp.getInfo(getActivity(), "name", "");
            //修改网名和头像
            mUsername.setText(mingzi);
            ImageLoader.getInstance().displayImage(touxiang, usrimg);
        } else {
            usrimg.setImageResource(R.drawable.li_default_head);
            mUsername.setText("花大爷");
        }
    }
}
