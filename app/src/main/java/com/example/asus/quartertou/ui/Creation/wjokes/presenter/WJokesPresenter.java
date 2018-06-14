package com.example.asus.quartertou.ui.Creation.wjokes.presenter;

import com.example.asus.quartertou.bean.BaseBean;
import com.example.asus.quartertou.net.Api;
import com.example.asus.quartertou.ui.Creation.wjokes.contract.WJokesContract;
import com.example.asus.quartertou.ui.base.BasePresenter;

import java.io.File;
import javax.inject.Inject;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by asus on 2018/6/4.
 */

public class WJokesPresenter extends BasePresenter<WJokesContract.View> implements WJokesContract.Presenter {
    private Api api;
    @Inject
    public WJokesPresenter(Api api) {
        this.api = api;
    }



    @Override
    public void getWJokesPresenter(String uid, String token, String content,String jokeFiles) {
        int i = jokeFiles.lastIndexOf("/");
        String fileName = jokeFiles.substring(i+1);
        RequestBody file = RequestBody.create(MediaType.parse("application/octet-stream"), new File(jokeFiles));

        MediaType textType = MediaType.parse("text/plain");
        RequestBody u = RequestBody.create(textType, uid);
        RequestBody t = RequestBody.create(textType, token);
        RequestBody c = RequestBody.create(textType, content);
        MultipartBody.Part f = MultipartBody.Part.createFormData("file", fileName, file);

        api.publishJoke( u,t,c,f)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean baseBean) {
                        if (mView!=null){
                            mView.getWJokesSuccess(baseBean);
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
