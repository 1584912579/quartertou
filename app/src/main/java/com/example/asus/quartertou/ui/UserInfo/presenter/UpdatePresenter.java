package com.example.asus.quartertou.ui.UserInfo.presenter;


import com.example.asus.quartertou.bean.BaseBean;
import com.example.asus.quartertou.net.Api;
import com.example.asus.quartertou.ui.UserInfo.contract.UpdateContract;
import com.example.asus.quartertou.ui.base.BasePresenter;

import java.io.File;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by asus on 2018/5/22.
 */

public class UpdatePresenter extends BasePresenter<UpdateContract.View> implements UpdateContract.Presenter  {
    private Api api;
    @Inject
    public UpdatePresenter(Api api) {
        this.api = api;
    }

    @Override
    public void updateHeader(String uid,String token, String filePath) {
        int i = filePath.lastIndexOf("/");
        String fileName = filePath.substring(i+1);
        RequestBody file = RequestBody.create(MediaType.parse("application/octet-stream"), new File(filePath));

        MediaType textType = MediaType.parse("text/plain");
        RequestBody u = RequestBody.create(textType, uid);
        RequestBody t = RequestBody.create(textType, token);
        MultipartBody.Part f = MultipartBody.Part.createFormData("file", fileName, file);
        api.upload(u,t, f)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<BaseBean, String>() {
                    @Override
                    public String apply(BaseBean baseBean) throws Exception {
                        return baseBean.getCode();
                    }
                }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                if (mView!=null){
                    mView.updateSuccess(s);
                }
            }
        });

    }


}
