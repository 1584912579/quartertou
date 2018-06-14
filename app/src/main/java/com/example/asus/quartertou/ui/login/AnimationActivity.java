package com.example.asus.quartertou.ui.login;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.quartertou.R;
import com.example.asus.quartertou.ui.MainActivity;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

public class AnimationActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView backtrack;
    /**
     * 其他登录方式
     */
    private TextView other_login_methods;
    private RelativeLayout relativeLayout;
    private ObjectAnimator animator;
    /**
     * QQ登录
     */
    private Button qq;
    /**
     * 微信登录
     */
    private Button winxin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        initView();

    }

    private void initView() {
        relativeLayout = findViewById(R.id.relativeLayout);
        backtrack = (ImageView) findViewById(R.id.backtrack);
        backtrack.setOnClickListener(this);
        other_login_methods = (TextView) findViewById(R.id.other_login_methods);
        other_login_methods.setOnClickListener(this);
        qq = (Button) findViewById(R.id.qq);
        qq.setOnClickListener(this);
        winxin = (Button) findViewById(R.id.winxin);
        winxin.setOnClickListener(this);


    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //后退
            case R.id.backtrack:
                AnimationActivity.this.finish();
                break;
            //其他登录方式
            case R.id.other_login_methods:
                Intent intent1 = new Intent(AnimationActivity.this, LoginActivity.class);
                startActivity(intent1);
                break;
            case R.id.qq:
                UMShareAPI.get(AnimationActivity.this)
                        .getPlatformInfo(AnimationActivity.this, SHARE_MEDIA.QQ, authListener);

                break;
            case R.id.winxin:
                break;
        }
    }
    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
        }
        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Toast.makeText(AnimationActivity.this, "成功了", Toast.LENGTH_LONG).show();
            String name = data.get("name");
            String iconurl = data.get("iconurl");
            String uid = data.get("uid");
            String token = data.get("token");
            SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString("uid", uid);
            //  Log.i("uuuu",userBean.getData().getUid()+"");
            editor.putString("name", name);
            editor.putString("iconUrl", iconurl);
            editor.putString("token", token);
            // Log.i("uuuu",userBean.getData().getToken()+"");
            editor.commit();
            Intent intent=new Intent(AnimationActivity.this,MainActivity.class);
            startActivity(intent);
        }
        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(AnimationActivity.this, "失败：" + t.getMessage(),                                     Toast.LENGTH_LONG).show();
        }
        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(AnimationActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };


}
