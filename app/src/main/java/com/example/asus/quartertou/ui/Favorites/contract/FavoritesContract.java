package com.example.asus.quartertou.ui.Favorites.contract;

import com.example.asus.quartertou.bean.VideosBean;
import com.example.asus.quartertou.ui.base.BaseContract;
import com.example.asus.quartertou.ui.base.BasePresenter;

public interface FavoritesContract {
    interface View  extends BaseContract.BaseView {
        //成功
        void FavoritesSuccess(VideosBean videosBean);
    }
    interface  Presenter extends BaseContract.BasePresenter<View> {
        //登录
        public abstract void Favorites(String uid, String token);
    }
}