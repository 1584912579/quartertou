package com.example.asus.quartertou.ui.Creation.wjokes.contract;
import com.example.asus.quartertou.bean.BaseBean;
import com.example.asus.quartertou.ui.base.BaseContract;

/**
 * Created by asus on 2018/6/4.
 */

public interface WJokesContract {
    interface View extends BaseContract.BaseView{
        void getWJokesSuccess(BaseBean baseBean);
    }
    interface Presenter extends BaseContract.BasePresenter<View>{
        void getWJokesPresenter(String uid, String token, String content, String jokeFiles);

    }
}
