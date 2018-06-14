package com.example.asus.quartertou.ui.video.presenter;

import com.example.asus.quartertou.bean.BaseBean;
import com.example.asus.quartertou.bean.VideoDetailBean;
import com.example.asus.quartertou.net.Api;
import com.example.asus.quartertou.ui.base.BasePresenter;
import com.example.asus.quartertou.ui.video.contract.VideoDetailContract;
import javax.inject.Inject;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by asus on 2018/6/4.
 */

public class VideoDetailPresenter extends BasePresenter<VideoDetailContract.View> implements VideoDetailContract.Presenter {
    private Api api;
    @Inject
    public VideoDetailPresenter(Api api) {
        this.api = api;
    }


    @Override
    public void getVideoDetailPresenter(String wid) {
        api.getVideoDetail(wid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<VideoDetailBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(VideoDetailBean videoDetailBean) {
                        if (mView!=null){
                            mView.getVideoDetailSuccess(videoDetailBean);
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
    public void getaddFavoritePresenter(String uid, String token, String wid) {
        api.addFavorite(uid,token,wid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean baseBean) {
                        if (mView!=null){
                            mView.getaddFavoriteSuccess(baseBean);
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
    public void getcancelFavoritePresenter(String uid, String token, String wid) {
        api.cancelFavorite(uid,token,wid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean baseBean) {
                        if (mView!=null){
                            mView.getcancelFavoriteSuccess(baseBean);
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
    public void getfollowPresenter(String uid, String token, String followId) {
        api.follow(uid,token,followId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean baseBean) {
                        if (mView!=null){
                            mView.getfollowSuccess(baseBean);
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
