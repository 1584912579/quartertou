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
import com.example.asus.quartertou.utils.Image;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2018/6/4.
 */

public class MyFragment extends BaseFragment<SharePresenter> implements ShareContract.View {


    private Banner banner;
    private XRecyclerView xrv;
    private int page=1;
    private String type="1";
    private String uid="";
    @Override
    public int getContentLayout() {
        return R.layout.item;
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
        banner = view.findViewById(R.id.banner);
        xrv = view.findViewById(R.id.xrv);
        mPresenter.getAdPresenter();
        mPresenter.getVideosPresenter(uid,type,page+"");
        banner.setImageLoader(new Image());
    }

    @Override
    public void getAdSuccess(GetAdBean getAdBean) {
        List<GetAdBean.DataBean> data = getAdBean.getData();
        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i <data.size() ; i++) {
            String icon = data.get(i).getIcon();
            list.add(icon);
        }
        banner.setImages(list);
        banner.start();
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        banner.stopAutoPlay();
    }
}
