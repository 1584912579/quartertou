package com.example.asus.quartertou.ui.login.contract;


import com.example.asus.quartertou.bean.LoginBean;
import com.example.asus.quartertou.ui.base.BaseContract;

public interface LoginContract {
    interface View  extends BaseContract.BaseView {
        //成功
        void loginSuccess(LoginBean loginBean);
    }
    interface Presenter extends BaseContract.BasePresenter<View>{
        //登录
        void login(String mobile, String password);
    }
}