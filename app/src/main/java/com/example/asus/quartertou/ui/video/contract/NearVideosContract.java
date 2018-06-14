package com.example.asus.quartertou.ui.video.contract;


import com.example.asus.quartertou.bean.HotVideosBean;
import com.example.asus.quartertou.ui.base.BaseContract;

/**
 * Created by asus on 2018/6/4.
 */

public interface NearVideosContract {
    interface View extends BaseContract.BaseView{
        void getNearVideosSuccess(HotVideosBean hotVideosBean);
    }

    interface Presenter extends BaseContract.BasePresenter<View>{
        void getNearVideosPresenter(String page, String token, String latitude, String longitude);
    }
}
