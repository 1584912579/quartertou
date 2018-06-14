package com.example.asus.quartertou.ui.video;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.quartertou.R;
import com.example.asus.quartertou.bean.BaseBean;
import com.example.asus.quartertou.bean.VideoDetailBean;
import com.example.asus.quartertou.component.DaggerHttpComponent;
import com.example.asus.quartertou.module.HttpModule;
import com.example.asus.quartertou.ui.base.BaseActivity;
import com.example.asus.quartertou.ui.recommend.JokeDetailActivity;
import com.example.asus.quartertou.ui.video.contract.VideoDetailContract;
import com.example.asus.quartertou.ui.video.presenter.VideoDetailPresenter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMWeb;
import com.universalvideoview.UniversalMediaController;
import com.universalvideoview.UniversalVideoView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoDetailActivity extends BaseActivity<VideoDetailPresenter> implements VideoDetailContract.View {


    @BindView(R.id.v_f)
    ImageView mVF;
    @BindView(R.id.v_g)
    ImageView mVG;
    @BindView(R.id.v_qg)
    ImageView mVQg;
    @BindView(R.id.v_fx)
    ImageView mVFx;
    @BindView(R.id.v_sim)
    SimpleDraweeView mVSim;
    @BindView(R.id.videoview)
    UniversalVideoView mVideoView;
    @BindView(R.id.media_control)
    UniversalMediaController mControl;
    @BindView(R.id.video_layout)
    FrameLayout videoLayout;
    @BindView(R.id.v_title)
    TextView mVTitle;
    @BindView(R.id.v_ci)
    TextView mVCi;
    private int mSeekPPosition;
    private static final String TAG = "VideoDetailActivity";
    private boolean flag=true;
    private String videoUrl;
    private SharedPreferences sharedPreferences;
    private String uid;
    private String token;
    private int ssuid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_video_detail);
        ButterKnife.bind(this);

        sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        uid = sharedPreferences.getString("uid", "");
        token = sharedPreferences.getString("token", "");

        Intent intent = getIntent();
        final int wid = intent.getIntExtra("wid", 0);
        mPresenter.getVideoDetailPresenter(wid + "");
        mVF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VideoDetailActivity.this.finish();
            }
        });
        //分享
        mVFx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMWeb umWeb = new UMWeb(videoUrl);
                new ShareAction(VideoDetailActivity.this).withMedia(umWeb).setDisplayList(SHARE_MEDIA.SINA,
                        SHARE_MEDIA.QQ,
                        SHARE_MEDIA.QZONE,
                        SHARE_MEDIA.WEIXIN,
                        SHARE_MEDIA.WEIXIN_CIRCLE
                ).setCallback(shareListener).open();
            }
        });
        mVG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag){
                    mVG.setImageResource(R.drawable.xin2);
                    mPresenter.getaddFavoritePresenter(uid,token,wid+"");
                }else {
                    mVG.setImageResource(R.drawable.xin1);
                    mPresenter.getcancelFavoritePresenter(uid,token,wid+"");
                }
                flag=!flag;
            }
        });
        mVQg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关注
                mPresenter.getfollowPresenter(uid,token,ssuid+"");
                mVQg.setImageResource(R.drawable.raw1499933215);
            }
        });
    }

    //控制ActionBar的显示与隐藏
    private void setActionBar(boolean isFullscreen){
        ActionBar actionBar = getSupportActionBar();
        if(isFullscreen){
            actionBar.show();
        }else{
            actionBar.hide();
        }
    }
    @Override
    public int getContentLayout() {
        return R.layout.activity_video_detail;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .httpModule(new HttpModule())
                .build()
                .inject(this);
    }

    @Override
    public void getVideoDetailSuccess(VideoDetailBean videoDetailBean) {
        final VideoDetailBean.DataBean data = videoDetailBean.getData();
        ssuid = data.getUid();
        videoUrl = data .getVideoUrl();
        if (videoUrl ==""){
            return;
        }
        mVideoView.setMediaController(mControl);

        mVideoView.setVideoPath(videoUrl);
        mVideoView.requestFocus();
        mSeekPPosition = mVideoView.getCurrentPosition();//获取当前播放的进度
        mVideoView.setVideoViewCallback(new UniversalVideoView.VideoViewCallback() {
            @Override
            public void onScaleChange(boolean isFullscreen) {

                if(isFullscreen){
                    ViewGroup.LayoutParams parmas = videoLayout.getLayoutParams();
                    parmas.height = ViewGroup.LayoutParams.MATCH_PARENT;
                    parmas.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    videoLayout.setLayoutParams(parmas);
                }else{
                    ViewGroup.LayoutParams parmas = videoLayout.getLayoutParams();
                    parmas.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    parmas.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    videoLayout.setLayoutParams(parmas);
                }
                setActionBar(!isFullscreen);
            }

            @Override
            public void onPause(MediaPlayer mediaPlayer) {
                Log.d(TAG, "onPause: "+"视频暂停播放");
            }
            @Override
            public void onStart(MediaPlayer mediaPlayer) {
                Log.d(TAG, "onStart: "+"视频开始播放");
            }

            @Override
            public void onBufferingStart(MediaPlayer mediaPlayer) {
                Log.d(TAG, "onBufferingStart: "+"视频开始缓冲");
            }
            @Override
            public void onBufferingEnd(MediaPlayer mediaPlayer) {
                Log.d(TAG, "onBufferingEnd: "+"视频缓冲结束");
            }
        });
        mVideoView.start();
        mVTitle.setText(data.getWorkDesc());
        mVCi.setText(data.getPlayNum()+"次");
        mVSim.setImageURI(data.getCover());
        mVSim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int uid = data.getUid();
                Intent intent = new Intent(VideoDetailActivity.this, JokeDetailActivity.class);
                intent.putExtra("uid",uid);
                startActivity(intent);
            }
        });
    }

    @Override
    public void getaddFavoriteSuccess(BaseBean baseBean) {
        Toast.makeText(VideoDetailActivity.this,baseBean.getMsg(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getcancelFavoriteSuccess(BaseBean baseBean) {
        Toast.makeText(VideoDetailActivity.this,baseBean.getMsg(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getfollowSuccess(BaseBean baseBean) {
        Toast.makeText(VideoDetailActivity.this,baseBean.getMsg(),Toast.LENGTH_SHORT).show();
    }

    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(VideoDetailActivity.this, "成功了", Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(VideoDetailActivity.this, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(VideoDetailActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };
}
