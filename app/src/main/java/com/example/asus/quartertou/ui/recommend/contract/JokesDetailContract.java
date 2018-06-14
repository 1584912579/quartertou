package com.example.asus.quartertou.ui.recommend.contract;


import com.example.asus.quartertou.bean.BaseBean;
import com.example.asus.quartertou.bean.UserVideosBean;
import com.example.asus.quartertou.ui.base.BaseContract;

/**
 * Created by asus on 2018/6/4.
 */

public interface JokesDetailContract {
    interface View extends BaseContract.BaseView{
        void getUserVideosSuccess(UserVideosBean userVideosBean);
        void getfollowSuccess(BaseBean baseBean);
    }

    interface Presenter extends BaseContract.BasePresenter<View>{

        void getUserVideosPresenter(String uid, String page);
        void getfollowPresenter(String uid, String token, String followId);
    }
}
