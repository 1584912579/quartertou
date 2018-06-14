package com.example.asus.quartertou.ui.Favorites.presenter;

import com.example.asus.quartertou.bean.VideosBean;
import com.example.asus.quartertou.net.Api;
import com.example.asus.quartertou.ui.Favorites.contract.FavoritesContract;
import com.example.asus.quartertou.ui.base.BasePresenter;

import javax.inject.Inject;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FavoritesPresenter extends BasePresenter<FavoritesContract.View> implements FavoritesContract.Presenter {

    private Api api;
    @Inject
    public FavoritesPresenter(Api api){
        this.api=api;
    }

    @Override
    public void Favorites(String uid, String token) {

        api.getFavorites(uid, token)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<VideosBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(VideosBean videosBean) {
                        if (mView!=null){
                            mView.FavoritesSuccess(videosBean);
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