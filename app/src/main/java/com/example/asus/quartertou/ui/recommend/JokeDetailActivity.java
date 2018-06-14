package com.example.asus.quartertou.ui.recommend;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.asus.quartertou.R;
import com.example.asus.quartertou.bean.BaseBean;
import com.example.asus.quartertou.bean.UserVideosBean;
import com.example.asus.quartertou.component.DaggerHttpComponent;
import com.example.asus.quartertou.module.HttpModule;
import com.example.asus.quartertou.ui.base.BaseActivity;
import com.example.asus.quartertou.ui.recommend.adpter.XRV2Adapters;
import com.example.asus.quartertou.ui.recommend.contract.JokesDetailContract;
import com.example.asus.quartertou.ui.recommend.presenter.JokesDetailPresenter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JokeDetailActivity extends BaseActivity<JokesDetailPresenter> implements JokesDetailContract.View {

    @BindView(R.id.u_sim)
    SimpleDraweeView mUSim;
    @BindView(R.id.u_fh)
    ImageView mUFh;
    @BindView(R.id.u_tv)
    TextView mUTv;
    @BindView(R.id.u_fx)
    ImageView mUFx;
    @BindView(R.id.u_zan)
    ImageView mUZan;
    @BindView(R.id.u_sim2)
    SimpleDraweeView mUSim2;
    @BindView(R.id.u_fxgz)
    TextView mUFxgz;
    @BindView(R.id.u_zhan)
    SimpleDraweeView mUZhan;
    @BindView(R.id.j_gz)
    TextView mJgz;
    @BindView(R.id.u_xrv)
    XRecyclerView mUXrv;
    private SimpleDraweeView mIcon;
    private TextView mText;
    private TextView mText2;
    private UserVideosBean.DataBean dataBean;
    List<UserVideosBean.DataBean> data=new ArrayList<>();
    private XRV2Adapters xrv2Adapters;
    private int  page=1;
    private boolean  flag=true;
    private boolean  ff=true;
    private SharedPreferences sharedPreferences;
    private String uuid;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_joke_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        final int uid = intent.getIntExtra("uid", 0);

        sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        uuid = sharedPreferences.getString("uid", "");
        token = sharedPreferences.getString("token", "");

        mUFh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JokeDetailActivity.this.finish();
            }
        });
        mPresenter.getUserVideosPresenter(uid+"","1");
        mUXrv.setLayoutManager(new LinearLayoutManager(this));
        xrv2Adapters = new XRV2Adapters(this, data);
        mUXrv.setAdapter(xrv2Adapters);
        mUXrv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //刷新
                page=1;
                flag=true;
                mPresenter.getUserVideosPresenter(uid+"",page+"");
            }

            @Override
            public void onLoadMore() {
                //加载更多
                page++;
                flag=false;
                mPresenter.getUserVideosPresenter(uid+"",page+"");


            }
        });
        mJgz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ff){
                    mPresenter.getfollowPresenter(uuid,token,uid+"");
                    mJgz.setText("关注");
                }else{
                    mJgz.setText("已关注");
                }
                ff=!ff;
            }
        });
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_joke_detail;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .httpModule(new HttpModule())
                .build()
                .inject(this);
    }


    @Override
    public void getUserVideosSuccess(UserVideosBean userVideosBean) {
        final List<UserVideosBean.DataBean> data = userVideosBean.getData();

        mUSim.setImageURI("https://www.zhaoapi.cn/images/quarter/1515587806806151496296995.jpg");
        mUSim2.setImageURI("https://www.zhaoapi.cn/images/quarter/1515587806806151496296995.jpg");
        mUZhan.setImageURI("https://www.zhaoapi.cn/images/quarter/1515587806806151496296995.jpg");
        if (data.size()!=0){
            dataBean = data.get(0);
            mUFxgz.setText(dataBean.getLatitude()+"评论"+dataBean.getLongitude()+"关注");
            mUTv.setText(dataBean.getWorkDesc());
        }

        if (flag){
            xrv2Adapters.shuxin(data);
            mUXrv.refreshComplete();//设置刷新完成

        }else {
            if (xrv2Adapters!=null){
                xrv2Adapters.jiazai(data);
                mUXrv.loadMoreComplete();
            }
        }
    }

    @Override
    public void getfollowSuccess(BaseBean baseBean) {
        Toast.makeText(JokeDetailActivity.this,baseBean.getMsg(),Toast.LENGTH_SHORT).show();
    }
}
