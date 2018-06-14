package com.example.asus.quartertou.ui.register.presenter;

import com.example.asus.quartertou.bean.BaseBean;
import com.example.asus.quartertou.net.Api;
import com.example.asus.quartertou.ui.base.BasePresenter;
import com.example.asus.quartertou.ui.register.contract.RegisterContract;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RegisterPresenter extends BasePresenter<RegisterContract.View> implements RegisterContract.Presenter {
    private Api api;
    @Inject
    public RegisterPresenter(Api api){
        this.api=api;
    }

    @Override
    public void register(String mobile, String password) {
        api.register(mobile, password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean baseBean) {
                        if (mView!=null){
                            mView.registerSuccess(baseBean);
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
