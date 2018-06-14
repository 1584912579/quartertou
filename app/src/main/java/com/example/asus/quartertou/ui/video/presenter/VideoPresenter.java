package com.example.asus.quartertou.ui.video.presenter;
import com.example.asus.quartertou.bean.HotVideosBean;
import com.example.asus.quartertou.net.Api;
import com.example.asus.quartertou.ui.base.BasePresenter;
import com.example.asus.quartertou.ui.video.contract.VideoContract;
import javax.inject.Inject;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by asus on 2018/6/4.
 */

public class VideoPresenter extends BasePresenter<VideoContract.View> implements VideoContract.Presenter {
    private Api api;
    @Inject
    public VideoPresenter(Api api) {
        this.api = api;
    }
    @Override
    public void getHotVideosPresenter( String page,String token) {
        api.getHotVideos(page,token)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<HotVideosBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HotVideosBean hotVideosBean) {
                        if (mView!=null){
                            mView.getHotVideosSuccess(hotVideosBean);
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
