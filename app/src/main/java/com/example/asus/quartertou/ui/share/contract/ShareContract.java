package com.example.asus.quartertou.ui.share.contract;

import com.example.asus.quartertou.bean.GetAdBean;
import com.example.asus.quartertou.bean.VideosBean;
import com.example.asus.quartertou.ui.base.BaseContract;

/**
 * Created by asus on 2018/6/4.
 */

public interface ShareContract {
    interface View extends BaseContract.BaseView{
        void getAdSuccess(GetAdBean getAdBean);
        void getVideosSuccess(VideosBean videosBean);
    }

    interface Presenter extends BaseContract.BasePresenter<View>{
        void getAdPresenter();
        void getVideosPresenter(String uid, String type, String page);
    }
}
