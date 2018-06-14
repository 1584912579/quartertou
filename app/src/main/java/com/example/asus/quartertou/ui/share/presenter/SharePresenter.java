package com.example.asus.quartertou.ui.share.presenter;


import com.example.asus.quartertou.bean.GetAdBean;
import com.example.asus.quartertou.bean.VideosBean;
import com.example.asus.quartertou.net.Api;
import com.example.asus.quartertou.ui.base.BasePresenter;
import com.example.asus.quartertou.ui.share.contract.ShareContract;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by asus on 2018/6/4.
 */

public class SharePresenter extends BasePresenter<ShareContract.View> implements ShareContract.Presenter {
    private Api api;
    @Inject
    public SharePresenter(Api api) {
        this.api = api;
    }

    @Override
    public void getAdPresenter() {
        api.getAd()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<GetAdBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetAdBean getAdBean) {
                        if (mView!=null){
                            mView.getAdSuccess(getAdBean);
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

    @Override
    public void getVideosPresenter(String uid, String type, String page) {
        api.getVideos(uid, type, page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<VideosBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(VideosBean videosBean) {
                        if (mView!=null){
                            mView.getVideosSuccess(videosBean);
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
