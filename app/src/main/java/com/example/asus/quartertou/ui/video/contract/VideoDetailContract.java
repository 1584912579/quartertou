package com.example.asus.quartertou.ui.video.contract;

import com.example.asus.quartertou.bean.BaseBean;
import com.example.asus.quartertou.bean.VideoDetailBean;
import com.example.asus.quartertou.ui.base.BaseContract;

/**
 * Created by asus on 2018/6/4.
 */

public interface VideoDetailContract {
    interface View extends BaseContract.BaseView{
        void getVideoDetailSuccess(VideoDetailBean videoDetailBean);
        void getaddFavoriteSuccess(BaseBean baseBean);
        void getcancelFavoriteSuccess(BaseBean baseBean);
        void getfollowSuccess(BaseBean baseBean);
    }

    interface Presenter extends BaseContract.BasePresenter<View>{
        void getVideoDetailPresenter(String wid);
        void getaddFavoritePresenter(String uid, String token, String wid);
        void getcancelFavoritePresenter(String uid, String token, String wid);
        void getfollowPresenter(String uid, String token, String followId);
    }
}
