package com.example.asus.quartertou.ui.share;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.asus.quartertou.R;
import com.example.asus.quartertou.bean.GetAdBean;
import com.example.asus.quartertou.bean.VideosBean;
import com.example.asus.quartertou.component.DaggerHttpComponent;
import com.example.asus.quartertou.inter.OnItemClickListener;
import com.example.asus.quartertou.module.HttpModule;
import com.example.asus.quartertou.ui.base.BaseFragment;
import com.example.asus.quartertou.ui.recommend.JokeDetailActivity;
import com.example.asus.quartertou.ui.share.adapter.XRVAdapter;
import com.example.asus.quartertou.ui.share.contract.ShareContract;
import com.example.asus.quartertou.ui.share.presenter.SharePresenter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * Created by asus on 2018/6/4.
 */

public class MyFragment2 extends BaseFragment<SharePresenter> implements ShareContract.View {

    private SimpleDraweeView sim;
    private XRecyclerView xrv;
    private int page=1;
    private String type="1";
    private String uid="14530";
    @Override
    public int getContentLayout() {
        return R.layout.ss_item;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .httpModule(new HttpModule())
                .build()
                .inject(this);
    }

    @Override
    public void initView(View view) {
        sim = view.findViewById(R.id.sim);
        xrv = view.findViewById(R.id.s_xrv);
        mPresenter.getAdPresenter();
        mPresenter.getVideosPresenter(uid,type,page+"");
    }

    @Override
    public void getAdSuccess(GetAdBean getAdBean) {
        sim.setImageURI(getAdBean.getData().get(0).getIcon());
    }

    @Override
    public void getVideosSuccess(VideosBean videosBean) {
        final List<VideosBean.DataBean> data = videosBean.getData();
        xrv.setLayoutManager(new LinearLayoutManager(getActivity()));
        XRVAdapter xrvAdapter = new XRVAdapter(getContext(), data);
        xrv.setAdapter(xrvAdapter);
        xrvAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                int uid = data.get(position).getUid();
                Intent intent = new Intent(getActivity(), JokeDetailActivity.class);
                intent.putExtra("uid",uid);
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(int position) {

            }
        });
    }
}
