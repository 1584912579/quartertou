package com.example.asus.quartertou.ui.Favorites;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.example.asus.quartertou.R;
import com.example.asus.quartertou.bean.VideosBean;
import com.example.asus.quartertou.component.DaggerHttpComponent;
import com.example.asus.quartertou.ui.Favorites.contract.FavoritesContract;
import com.example.asus.quartertou.ui.Favorites.presenter.FavoritesPresenter;
import com.example.asus.quartertou.ui.base.BaseActivity;
import com.example.asus.quartertou.ui.share.adapter.XRVAdapter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class FavoritesActivity extends BaseActivity<FavoritesPresenter> implements FavoritesContract.View {

    private SharedPreferences sharedPreferences;
    private String uid;
    private String token;
    /**
     * <返回
     */
    private TextView mFFh;
    /**
     * 删除
     */
    private TextView mFSc;
    private XRecyclerView mFXrv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_favorites);
        sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        uid = sharedPreferences.getString("uid", "");
        token = sharedPreferences.getString("token", "");
        initView();
        mFFh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FavoritesActivity.this.finish();
            }
        });
        mFSc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_favorites;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .build()
                .inject(this);
    }

    @Override
    public void FavoritesSuccess(VideosBean videosBean) {
        List<VideosBean.DataBean> data = videosBean.getData();
        mFXrv.setLayoutManager(new LinearLayoutManager(FavoritesActivity.this));
        XRVAdapter xrvAdapter = new XRVAdapter(FavoritesActivity.this, data);
        mFXrv.setAdapter(xrvAdapter);
    }

    private void initView() {
        mFFh = (TextView) findViewById(R.id.f_fh);
        mFSc = (TextView) findViewById(R.id.f_sc);
        mFXrv = (XRecyclerView) findViewById(R.id.f_xrv);
    }
}
