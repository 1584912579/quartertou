package com.example.asus.quartertou.ui.recommend.contract;
import com.example.asus.quartertou.bean.JokesBean;
import com.example.asus.quartertou.ui.base.BaseContract;

/**
 * Created by asus on 2018/6/4.
 */

public interface JokesContract {
    interface View extends BaseContract.BaseView{
        void getJokesSuccess(JokesBean jokesBean);
    }

    interface Presenter extends BaseContract.BasePresenter<View>{

        void getJokesPresenter(String page);
    }
}
