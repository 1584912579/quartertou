package com.example.asus.quartertou.ui.register.contract;


import com.example.asus.quartertou.bean.BaseBean;
import com.example.asus.quartertou.ui.base.BaseContract;

public interface RegisterContract {
    interface View extends BaseContract.BaseView {
        void registerSuccess(BaseBean baseBean);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void register(String mobile, String password);
    }
}
