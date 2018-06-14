package com.example.asus.quartertou.ui.recommend.presenter;



import com.example.asus.quartertou.bean.BaseBean;
import com.example.asus.quartertou.bean.UserVideosBean;
import com.example.asus.quartertou.net.Api;
import com.example.asus.quartertou.ui.base.BasePresenter;
import com.example.asus.quartertou.ui.recommend.contract.JokesDetailContract;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by asus on 2018/6/4.
 */

public class JokesDetailPresenter extends BasePresenter<JokesDetailContract.View> implements JokesDetailContract.Presenter {
    private Api api;
    @Inject
    public JokesDetailPresenter(Api api) {
        this.api = api;
    }



    @Override
    public void getUserVideosPresenter(String uid,String page) {
        api.getUserVideos(uid,page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<UserVideosBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserVideosBean userVideosBean) {
                        if (mView!=null){
                            mView.getUserVideosSuccess(userVideosBean);
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
