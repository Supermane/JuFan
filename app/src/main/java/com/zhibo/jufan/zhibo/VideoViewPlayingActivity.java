package com.zhibo.jufan.zhibo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.Process;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.baidu.cyberplayer.core.BVideoView;
import com.baidu.cyberplayer.core.BVideoView.OnCompletionListener;
import com.baidu.cyberplayer.core.BVideoView.OnErrorListener;
import com.baidu.cyberplayer.core.BVideoView.OnInfoListener;
import com.baidu.cyberplayer.core.BVideoView.OnPlayingBufferCacheListener;
import com.baidu.cyberplayer.core.BVideoView.OnPreparedListener;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.zhibo.jufan.R;
import com.zhibo.jufan.adapter.GiftAdapter;
import com.zhibo.jufan.bean.GiftData;
import com.zhibo.jufan.dianzan.HeartLayout;
import com.zhibo.jufan.utils.GetHttpUtils;
import com.zhibo.jufan.view.Touxiang;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class VideoViewPlayingActivity extends Activity implements OnPreparedListener, OnCompletionListener,
        OnErrorListener, OnInfoListener, OnPlayingBufferCacheListener {

    private final String TAG = "VideoViewPlayingActivity";

    /**
     * 您的AK
     * 请到http://console.bce.baidu.com/iam/#/iam/accesslist获取
     */
    private String AK = "db82b63bb54249e0b178a58f1125b349";   //请录入您的AK !!!

    private String mVideoSource = null;
    private String giftpath = "http://api.vas.jufan.tv/cgi/gift/list?sign=55D0D8B26E3B3100B8633585DD7CA3F8CF2EFE69&r=zlbh";

    /**
     * 记录播放位置
     */
    private int mLastPos = 0;
    private ImageView fenxiang;
    private Uri uriPath;
    private ImageView close;
    private ImageView touxiang;
    private TextView name;
    private TextView num;
    private ImageView guanzhu;
    private String img;
    private String ming;
    private int number;
    private TextView dou;
    private TextView bianhao;
    private TextView time;
    private int score;
    private int rid;
    private LinearLayout imgs;
    private ImageView gift;
    private GridView gd;
    private List<GiftData.ContentEntity.JufanEntity.ListEntity> giftlist;
    private View pop_view;

    /**
     * 播放状态
     */
    private enum PLAYER_STATUS {
        PLAYER_IDLE, PLAYER_PREPARING, PLAYER_PREPARED,
    }

    private PLAYER_STATUS mPlayerStatus = PLAYER_STATUS.PLAYER_IDLE;

    private BVideoView mVV = null;

    private EventHandler mEventHandler;
    private HandlerThread mHandlerThread;

    private final Object SYNC_Playing = new Object();

    private WakeLock mWakeLock = null;
    private static final String POWER_LOCK = "VideoViewPlayingActivity";

    private boolean mIsHwDecode = false;

    private final int EVENT_PLAY = 0;
    private final int UI_EVENT_UPDATE_CURRPOSITION = 1;
    private HeartLayout heartLayout;

    class EventHandler extends Handler {
        public EventHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case EVENT_PLAY:
                    /**
                     * 如果已经播放了，等待上一次播放结束
                     */
                    if (mPlayerStatus != PLAYER_STATUS.PLAYER_IDLE) {
                        synchronized (SYNC_Playing) {
                            try {
                                SYNC_Playing.wait();
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    }

                    /**
                     * 设置播放url
                     */
                    mVV.setVideoPath(mVideoSource);

                    /**
                     * 续播，如果需要如此
                     */
                    if (mLastPos > 0) {

                        mVV.seekTo(mLastPos);
                        mLastPos = 0;
                    }

                    /**
                     * 显示或者隐藏缓冲提示
                     */
                    mVV.showCacheInfo(true);

                    /**
                     * 开始播放
                     */
                    mVV.start();

                    mPlayerStatus = PLAYER_STATUS.PLAYER_PREPARING;
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.controllerplaying);

        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        mWakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ON_AFTER_RELEASE, POWER_LOCK);

        mIsHwDecode = getIntent().getBooleanExtra("isHW", false);
        uriPath = getIntent().getData();

        //初始化控件
        initUI();

        //获取intent传过来的值
        Intent intent = getIntent();
        img = intent.getStringExtra("img");
        ming = intent.getStringExtra("name");
        number = intent.getIntExtra("online", 0);
        score = intent.getIntExtra("score", 0);
        rid = intent.getIntExtra("rid", 0);

        Log.e("主播地址==========", uriPath.toString());
        if (null != uriPath) {
            String scheme = uriPath.getScheme();
            if (null != scheme) {
                mVideoSource = uriPath.toString();
            } else {
                mVideoSource = uriPath.getPath();
            }
        }

        //点击事件
        onclicks();

        //赋值
        ImageLoader.getInstance().displayImage(img, touxiang);
        name.setText(ming);
        num.setText(number + "");
        bianhao.setText(rid + "");
        dou.setText(score + "");

        // 把请求好的毫秒数换算成时间
        Date date = new Date();
        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
        String str = sdformat.format(date);
        time.setText(str);

        /**
         * 开启后台事件处理线程
         */
        mHandlerThread = new HandlerThread("event handler thread",
                Process.THREAD_PRIORITY_BACKGROUND);
        mHandlerThread.start();
        mEventHandler = new EventHandler(mHandlerThread.getLooper());

    }

    //点击事件
    private void onclicks() {
        //分享
        fenxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare();
            }
        });

        //关闭
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VideoViewPlayingActivity.this.finish();
            }
        });

        //关注的小图片
        guanzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guanzhu.setVisibility(View.GONE);
            }
        });

        //头像的点击弹出框
        touxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //弹出框
                zhuBoAlertDialog();
            }
        });

        giftData();

    }

    /**
     * 礼物数据
     */
    private void giftData() {
        new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... params) {
                //请求数据
                String str = GetHttpUtils.Getstr(giftpath);
                return str;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                //开始解析
                Gson gson = new Gson();
                GiftData bean = gson.fromJson(s, GiftData.class);
                List<GiftData.ContentEntity.JufanEntity> jufan = bean.content.jufan;
                giftlist = jufan.get(0).list;

                //礼物的视图
                pop_view = getLayoutInflater().inflate(R.layout.gift_layout, null);
                gd = (GridView) pop_view.findViewById(R.id.pupGridView);

                //适配器
                gd.setAdapter(new GiftAdapter(VideoViewPlayingActivity.this,giftlist));

                //礼物的点击事件
                gift.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupWindow window = new PopupWindow(pop_view,
                                WindowManager.LayoutParams.MATCH_PARENT,
                                800);
                        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
                        //window.setFocusable(true);
                        window.setOutsideTouchable(true);
                        // 实例化一个ColorDrawable颜色为半透明
                        ColorDrawable dw = new ColorDrawable(0xb0000000);
                        // 在底部显示
                        window.setBackgroundDrawable(dw);
                        // pop.xml视图里面的控件
                        window.showAtLocation(v,Gravity.BOTTOM, 0, 0);
                    }
                });
            }
        }.execute();
    }

    /**
     * 主播弹出框
     */
    private void zhuBoAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();
        View dd = View.inflate(VideoViewPlayingActivity.this, R.layout.dialog_layout, null);
        dialog.setView(dd);

        ImageView guan = (ImageView) dd.findViewById(R.id.close_info);
        //头像
        Touxiang tou = (Touxiang) dd.findViewById(R.id.touxiang_info);
        ImageLoader.getInstance().displayImage(img, tou);
        //名字
        TextView name = (TextView) dd.findViewById(R.id.name_info);
        name.setText(ming);
        //地址
        TextView city = (TextView) dd.findViewById(R.id.city);


        guan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        //显示出来
        dialog.show();
    }


    /**
     * 初始化界面
     */
    private void initUI() {
        //分享
        fenxiang = (ImageView) findViewById(R.id.fenxiang);
        //关闭
        close = (ImageView) findViewById(R.id.close);
        heartLayout = (HeartLayout) findViewById(R.id.heart_layout);
        findViewById(R.id.member_send_good).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                heartLayout.addFavor();
            }
        });

        //礼物的控件
        gift = (ImageView) findViewById(R.id.gift);

        //小头像
        touxiang = (ImageView) findViewById(R.id.vido_small_touxiang);
        name = (TextView) findViewById(R.id.vido_name);
        num = (TextView) findViewById(R.id.vido_num);
        guanzhu = (ImageView) findViewById(R.id.video_guanzhu);
        dou = (TextView) findViewById(R.id.dou);
        bianhao = (TextView) findViewById(R.id.bianhao);
        time = (TextView) findViewById(R.id.time);

        //一排头像
        imgs = (LinearLayout) findViewById(R.id.small_img);

        /**
         * 设置ak
         */
        BVideoView.setAK(AK);

        /**
         * 获取BVideoView对象
         */
        mVV = (BVideoView) findViewById(R.id.video_view);

        /**
         * 注册listener
         */
        mVV.setOnPreparedListener(this);
        mVV.setOnCompletionListener(this);
        mVV.setOnErrorListener(this);
        mVV.setOnInfoListener(this);

        /**
         * 设置解码模式
         */
        mVV.setDecodeMode(mIsHwDecode ? BVideoView.DECODE_HW : BVideoView.DECODE_SW);


        //请求网络数据
        forInfor();
    }

    private String path = "http://live.jufan.tv/cgi/user/otherFollowerList?sign=E495021B176BFC734AE50D59D9068AFA8B9D938&targetUid=92713802&r=gjlps&page=0&size=100";

    private List<String> mList = new ArrayList<>();

    //请求网络数据
    private void forInfor() {
        new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... params) {
                String info = GetHttpUtils.Getstr(path);
                if (!info.equals("")) {
                    return info;
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s != null) {
                    try {
                        //解析
                        JSONObject jo = new JSONObject(s);
                        JSONObject object = jo.optJSONObject("content");
                        JSONArray array = object.optJSONArray("list");
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject obj = array.optJSONObject(i);
                            String head = obj.optString("head");
                            Log.e("tag", head);
                            if (head != null) {
                                mList.add(head);
                            }
                        }
                        for (int i = 0; i < mList.size(); i++) {
                            ImageView img = new ImageView(VideoViewPlayingActivity.this);
                            img.setScaleType(ImageView.ScaleType.FIT_XY);
                            //圆角的代码
                            DisplayImageOptions options = new DisplayImageOptions.Builder().displayer(new RoundedBitmapDisplayer(50)).build();
                            ImageLoader.getInstance().displayImage(mList.get(i), img, options);
                            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(100, 100);
                            p.setMargins(5, 0, 5, 0);
                            imgs.addView(img, p);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.execute();
    }


    private void updateTextViewWithTimeFormat(TextView view, int second) {
        int hh = second / 3600;
        int mm = second % 3600 / 60;
        int ss = second % 60;
        String strTemp = null;
        if (0 != hh) {
            strTemp = String.format("%02d:%02d:%02d", hh, mm, ss);
        } else {
            strTemp = String.format("%02d:%02d", mm, ss);
        }
        view.setText(strTemp);
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        /**
         * 在停止播放前 你可以先记录当前播放的位置,以便以后可以续播
         */
        if (mPlayerStatus == PLAYER_STATUS.PLAYER_PREPARED) {
            mLastPos = mVV.getCurrentPosition();
            mVV.stopPlayback();
        }
    }


    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        if (null != mWakeLock && (!mWakeLock.isHeld())) {
            mWakeLock.acquire();
        }
        /**
         * 发起一次播放任务,当然您不一定要在这发起
         */
        mEventHandler.sendEmptyMessage(EVENT_PLAY);
    }

    private long mTouchTime;
    private boolean barShow = true;


    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /**
         * 退出后台事件处理线程
         */
        mHandlerThread.quit();
    }

    @Override
    public boolean onInfo(int what, int extra) {
        // TODO Auto-generated method stub
        switch (what) {
            /**
             * 开始缓冲
             */
            case BVideoView.MEDIA_INFO_BUFFERING_START:
                break;
            /**
             * 结束缓冲
             */
            case BVideoView.MEDIA_INFO_BUFFERING_END:
                break;
            default:
                break;
        }
        return true;
    }

    /**
     * 当前缓冲的百分比， 可以配合onInfo中的开始缓冲和结束缓冲来显示百分比到界面
     */
    @Override
    public void onPlayingBufferCache(int percent) {
        // TODO Auto-generated method stub

    }

    /**
     * 播放出错
     */
    @Override
    public boolean onError(int what, int extra) {
        // TODO Auto-generated method stub
        synchronized (SYNC_Playing) {
            SYNC_Playing.notify();
        }
        mPlayerStatus = PLAYER_STATUS.PLAYER_IDLE;
        return true;
    }

    /**
     * 播放完成
     */
    @Override
    public void onCompletion() {
        // TODO Auto-generated method stub
        synchronized (SYNC_Playing) {
            SYNC_Playing.notify();
        }
        mPlayerStatus = PLAYER_STATUS.PLAYER_IDLE;
    }

    /**
     * 准备播放就绪
     */
    @Override
    public void onPrepared() {
        // TODO Auto-generated method stub
        mPlayerStatus = PLAYER_STATUS.PLAYER_PREPARED;
    }

    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("聚范直播");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl(uriPath + "");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("聚范直播");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(uriPath + "");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("聚范直播");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("ShareSDK");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(uriPath + "");

        // 启动分享GUI
        oks.show(this);
    }
}
