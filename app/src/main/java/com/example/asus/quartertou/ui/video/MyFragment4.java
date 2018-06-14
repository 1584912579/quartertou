package com.example.asus.quartertou.ui.video;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.asus.quartertou.R;
import com.example.asus.quartertou.bean.HotVideosBean;
import com.example.asus.quartertou.component.DaggerHttpComponent;
import com.example.asus.quartertou.inter.OnItemClickListener;
import com.example.asus.quartertou.module.HttpModule;
import com.example.asus.quartertou.ui.base.BaseFragment;
import com.example.asus.quartertou.ui.video.adapter.XRVA3dapter;
import com.example.asus.quartertou.ui.video.contract.NearVideosContract;
import com.example.asus.quartertou.ui.video.presenter.NearVideosPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2018/6/5.
 */

public class MyFragment4 extends BaseFragment<NearVideosPresenter> implements NearVideosContract.View {

    private XRecyclerView mXrv3;
    private List<HotVideosBean.DataBean> datas=new ArrayList<>();
    private XRVA3dapter xrva3dapter;
    private int  page=1;
    private boolean  flag=true;
    private String token="A753C3EF1D5CD17396618D2DF49D1544n";
    private String latitude="39.1";
    private String longitude="32.1";
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            //获取纬度
            latitude1 = String.valueOf(amapLocation.getLatitude());
            //获取经度
            longitude1 = String.valueOf(amapLocation.getLongitude());
        }
    };
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    private String latitude1;
    private String longitude1;
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

        //初始化定位
        mLocationClient = new AMapLocationClient(getContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();

        /**
         * 设置定位场景，目前支持三种场景（签到、出行、运动，默认无场景）
         */
        mLocationOption.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);

        if (null != mLocationClient) {
            mLocationClient.setLocationOption(mLocationOption);
            //设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
            mLocationClient.stopLocation();
            mLocationClient.startLocation();
        }
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //获取一次定位结果：
        //该方法默认为false。
        mLocationOption.setOnceLocation(true);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();


       // Log.i("llll",latitude1);
        mXrv3 = (XRecyclerView) view.findViewById(R.id.xrv3);
        mXrv3.setLayoutManager(new GridLayoutManager(getActivity(),2));


        xrva3dapter = new XRVA3dapter(getActivity(), datas);
        mXrv3.setAdapter(xrva3dapter);
        mPresenter.getNearVideosPresenter(page+"",token,latitude,longitude);

        mXrv3.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //刷新
                page=1;
                flag=true;
                mPresenter.getNearVideosPresenter(page+"",token,latitude,longitude);
            }
            @Override
            public void onLoadMore() {
                //加载更多
                page++;
                flag=false;
                mPresenter.getNearVideosPresenter(page+"",token,latitude,longitude);
            }
        });
    }

    @Override
    public void getNearVideosSuccess(HotVideosBean hotVideosBean) {
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
