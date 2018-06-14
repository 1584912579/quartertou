package com.example.asus.quartertou.ui.UserInfo.contract;


import com.example.asus.quartertou.ui.base.BaseContract;

public interface UpdateContract {

    interface View extends BaseContract.BaseView{
        void updateSuccess(String code);
    }

    interface Presenter extends BaseContract.BasePresenter<View>{
        void updateHeader(String uid, String token, String filePath);
    }
}
