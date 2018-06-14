package com.example.asus.quartertou.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.example.asus.quartertou.R;
import com.example.asus.quartertou.ui.Creation.CreationActivity;
import com.example.asus.quartertou.ui.Favorites.FavoritesActivity;
import com.example.asus.quartertou.ui.UserInfo.UserInfoActivity;
import com.example.asus.quartertou.ui.login.AnimationActivity;
import com.example.asus.quartertou.ui.recommend.FragmentRecommend;
import com.example.asus.quartertou.ui.share.FragmentShare;
import com.example.asus.quartertou.ui.video.FragmentVideo;
import com.example.asus.quartertou.utils.Mytitle;
import com.facebook.drawee.view.SimpleDraweeView;

public class MainActivity extends AppCompatActivity  {

    private FragmentManager fragmentManager;
    private RadioButton mRbHomepage;
    private RadioGroup mRg;
    private FragmentRecommend fragmentRecommend;
    private FragmentShare fragmentShare;
    private FragmentVideo fragmentVideo;
    private int currentIndex = 1;
    private Mytitle mytitle;
    private DrawerLayout drawer;
    private Mytitle mMytitle;
    private FrameLayout mFlont;
    /**
     * 推荐
     */
    private RadioButton mRb1;
    /**
     * 段子
     */
    private RadioButton mRb2;
    /**
     * 视频
     */
    private RadioButton mRb3;
    private SimpleDraweeView mMyImageView;
    private LinearLayout mToutou;
    /**
     * 我的关注
     */
    private TextView mTakecare;
    /**
     * 我的收藏
     */
    private LinearLayout mCollection;
    /**
     * 搜索好友
     */
    private TextView mFriend;
    /**
     * 消息通知
     */
    private TextView mMessages;
    /**
     * 夜间模式
     */
    private TextView mNight;
    /**
     * 我的作品
     */
    private TextView mMyDirectory;
    /**
     * 设置
     */
    private TextView mSettings;
    private DrawerLayout mDrawerLayout;
    private SharedPreferences sharedPreferences;
    private TextView mname;
    private String uid;
    private String name;
    private String iconUrl;
    private TextView qianming;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        fragmentManager = getSupportFragmentManager();
        fragmentRecommend = new FragmentRecommend();
        fragmentShare = new FragmentShare();
        fragmentVideo = new FragmentVideo();
        fragmentManager.beginTransaction()
                .replace(R.id.flont, fragmentShare)
                .commit();
        mRbHomepage.setChecked(true);
        //设置点击事件
        setLisenter();
        //分享
        mytitle.setJiekou(new Mytitle.OnsetHuida() {
            @Override
            public void huida() {
                Intent intent = new Intent(MainActivity.this, CreationActivity.class);
                startActivity(intent);
            }
        });
        //点击显示我的侧拉
        mytitle.setmein(new Mytitle.OnsetHuidas() {
            @Override
            public void huida() {
                drawer.openDrawer(Gravity.LEFT);
            }
        });

    }

    private void setLisenter() {
        mRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb1:
                        //首页
                        if (currentIndex == 1) {
                            return;
                        }
                        mytitle.settv("推荐");
                        currentIndex = 1;
                        fragmentManager.beginTransaction().replace(R.id.flont, fragmentShare).commit();
                        break;
                    case R.id.rb2:
                        if (currentIndex == 2) {
                            return;
                        }
                        mytitle.settv("段子");
                        currentIndex = 2;

                        fragmentManager.beginTransaction().replace(R.id.flont, fragmentRecommend).commit();
                        break;
                    case R.id.rb3:
                        if (currentIndex == 3) {
                            return;
                        }
                        mytitle.settv("视频");
                        currentIndex = 3;
                        fragmentManager.beginTransaction().replace(R.id.flont, fragmentVideo).commit();
                        break;

                }
            }
        });
    }

    private void initView() {
        mytitle = (Mytitle) findViewById(R.id.mytitle);
        mRbHomepage = (RadioButton) findViewById(R.id.rb1);
        mRg = (RadioGroup) findViewById(R.id.rg);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mMytitle = (Mytitle) findViewById(R.id.mytitle);
        mFlont = (FrameLayout) findViewById(R.id.flont);
        mRb1 = (RadioButton) findViewById(R.id.rb1);
        mRb2 = (RadioButton) findViewById(R.id.rb2);
        mRb3 = (RadioButton) findViewById(R.id.rb3);
        mMyImageView = (SimpleDraweeView) findViewById(R.id.my_image_view);
        mToutou = (LinearLayout) findViewById(R.id.toutou);
        mTakecare = (TextView) findViewById(R.id.takecare);
        mCollection = (LinearLayout) findViewById(R.id.collection);
        mFriend = (TextView) findViewById(R.id.friend);
        mMessages = (TextView) findViewById(R.id.messages);
        mNight = (TextView) findViewById(R.id.night);
        mMyDirectory = (TextView) findViewById(R.id.my_directory);
        mSettings = (TextView) findViewById(R.id.settings);
        mSettings = (TextView) findViewById(R.id.settings);
        qianming = (TextView) findViewById(R.id.qianming);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mname = (TextView) findViewById(R.id.name);

    }

    @Override
    protected void onResume() {
        super.onResume();
        sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        uid = sharedPreferences.getString("uid", "");
        name = sharedPreferences.getString("name", "");
        iconUrl = sharedPreferences.getString("iconUrl", "");
        if (TextUtils.isEmpty(uid)){
            mMyImageView.setImageURI("http://f9.topitme.com/9/37/30/11224703137bb30379o.jpg");
        }else {
            mMyImageView.setImageURI(iconUrl);

        }
        if (!TextUtils.isEmpty(name)) {
            mname.setText(name);
        }else{
            mname.setText("登录/注册 >");
        }
        mToutou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(uid)) {
                    //上传头像
                    Intent intent = new Intent(MainActivity.this, UserInfoActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(MainActivity.this, AnimationActivity.class);
                    startActivity(intent);
                }
            }
        });
        mCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //我的收藏
                Intent intent = new Intent(MainActivity.this, FavoritesActivity.class);
                startActivity(intent);
            }
        });
    }


}
