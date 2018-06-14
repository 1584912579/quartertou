package com.example.asus.quartertou.ui.video;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.example.asus.quartertou.R;
import com.example.asus.quartertou.bean.HotVideosBean;
import com.example.asus.quartertou.component.DaggerHttpComponent;
import com.example.asus.quartertou.inter.OnItemClickListener;
import com.example.asus.quartertou.module.HttpModule;
import com.example.asus.quartertou.ui.base.BaseFragment;
import com.example.asus.quartertou.ui.video.adapter.XRVA3dapter;
import com.example.asus.quartertou.ui.video.contract.VideoContract;
import com.example.asus.quartertou.ui.video.presenter.VideoPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2018/6/5.
 */

public class MyFragment3 extends BaseFragment<VideoPresenter> implements VideoContract.View {

    private XRecyclerView mXrv3;
    private List<HotVideosBean.DataBean> datas=new ArrayList<>();
    private XRVA3dapter xrva3dapter;
    private int  page=1;
    private boolean  flag=true;
    private String token="A753C3EF1D5CD17396618D2DF49D1544n";
    private List<Integer> mHeights;

    @Override
    public int getContentLayout() {
        return R.layout.v_video;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .httpModule(new HttpModule())
                .build()
                .inject(this);
    }

    public void initView(View view) {
        mXrv3 = (XRecyclerView) view.findViewById(R.id.xrv3);
        mXrv3.setLayoutManager(new GridLayoutManager(getActivity(),2));



        xrva3dapter = new XRVA3dapter(getActivity(), datas);
        mXrv3.setAdapter(xrva3dapter);
        mPresenter.getHotVideosPresenter(page+"",token);

        mXrv3.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //刷新
                page=1;
                flag=true;
                mPresenter.getHotVideosPresenter(page+"",token);
            }
            @Override
            public void onLoadMore() {
                //加载更多
                page++;
                flag=false;
                mPresenter.getHotVideosPresenter(page+"",token);
            }
        });
    }



    @Override
    public void getHotVideosSuccess(HotVideosBean hotVideosBean) {
        final List<HotVideosBean.DataBean> data = hotVideosBean.getData();

        if (flag){
            xrva3dapter.shuxin(data);
            mXrv3.refreshComplete();//设置刷新完成

        }else {
            if (xrva3dapter!=null){
                xrva3dapter.jiazai(data);
                mXrv3.loadMoreComplete();
            }
        }
        xrva3dapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                int wid = data.get(position).getWid();
                Intent intent = new Intent(getActivity(), VideoDetailActivity.class);
                intent.putExtra("wid",wid);
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(int position) {

            }
        });
    }
}
