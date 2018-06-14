package com.example.asus.quartertou.ui.recommend;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.asus.quartertou.R;
import com.example.asus.quartertou.bean.JokesBean;
import com.example.asus.quartertou.component.DaggerHttpComponent;
import com.example.asus.quartertou.module.HttpModule;
import com.example.asus.quartertou.ui.base.BaseFragment;
import com.example.asus.quartertou.ui.recommend.adpter.XRV2Adapter;
import com.example.asus.quartertou.ui.recommend.contract.JokesContract;
import com.example.asus.quartertou.ui.recommend.presenter.JokesPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2018/6/4.
 */

public class FragmentRecommend extends BaseFragment<JokesPresenter> implements JokesContract.View {

    private XRecyclerView mXrv2;
    private List<JokesBean.DataBean> data=new ArrayList<>();
    private XRV2Adapter xrv2Adapter;
    private int  page=1;
    private boolean  flag=true;

    @Override
    public int getContentLayout() {
        return R.layout.recommend;
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

        mXrv2 = (XRecyclerView) view.findViewById(R.id.xrv2);
        mXrv2.setLayoutManager(new LinearLayoutManager(getActivity()));
        xrv2Adapter = new XRV2Adapter(getActivity(), data);
        mXrv2.setAdapter(xrv2Adapter);
        mPresenter.getJokesPresenter(page+"");
        mXrv2.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //刷新
                page=1;
                flag=true;
                mPresenter.getJokesPresenter(page+"");
            }

            @Override
            public void onLoadMore() {
                //加载更多
                page++;
                flag=false;
                mPresenter.getJokesPresenter(page+"");


            }
        });
    }

    @Override
    public void getJokesSuccess(JokesBean jokesBean) {
        final List<JokesBean.DataBean> data = jokesBean.getData();

        if (flag){
            xrv2Adapter.shuxin(data);
            mXrv2.refreshComplete();//设置刷新完成

        }else {
            if (xrv2Adapter!=null){
                xrv2Adapter.jiazai(data);
                mXrv2.loadMoreComplete();
            }
        }
        xrv2Adapter.setOnitemOnClickListener(new XRV2Adapter.OnitemOnClickListener() {
            @Override
            public Void OnClickListener(int position) {
                int uid = data.get(position).getUid();
                Intent intent = new Intent(getActivity(), JokeDetailActivity.class);
                intent.putExtra("uid",uid);
                startActivity(intent);
                return null;
            }
        });
    }
}
