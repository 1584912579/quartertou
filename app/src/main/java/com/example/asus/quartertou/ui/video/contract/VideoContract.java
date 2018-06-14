package com.example.asus.quartertou.ui.video.contract;

import com.example.asus.quartertou.bean.HotVideosBean;
import com.example.asus.quartertou.ui.base.BaseContract;

/**
 * Created by asus on 2018/6/4.
 */

public interface VideoContract {
    interface View extends BaseContract.BaseView{
        void getHotVideosSuccess(HotVideosBean hotVideosBean);
    }

    interface Presenter extends BaseContract.BasePresenter<View>{
        void getHotVideosPresenter(String page, String token);
    }
}
