package com.example.asus.quartertou.ui.recommend.presenter;



import com.example.asus.quartertou.bean.JokesBean;
import com.example.asus.quartertou.net.Api;
import com.example.asus.quartertou.ui.base.BasePresenter;
import com.example.asus.quartertou.ui.recommend.contract.JokesContract;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by asus on 2018/6/4.
 */

public class JokesPresenter extends BasePresenter<JokesContract.View> implements JokesContract.Presenter {
    private Api api;
    @Inject
    public JokesPresenter(Api api) {
        this.api = api;
    }

    @Override
    public void getJokesPresenter(String page) {
        api.getJokes( page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<JokesBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(JokesBean jokesBean) {
                        if (mView!=null){
                            mView.getJokesSuccess(jokesBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
