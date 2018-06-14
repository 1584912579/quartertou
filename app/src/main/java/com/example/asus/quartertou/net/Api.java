package com.example.asus.quartertou.net;

import com.example.asus.quartertou.bean.BaseBean;
import com.example.asus.quartertou.bean.GetAdBean;
import com.example.asus.quartertou.bean.HotVideosBean;
import com.example.asus.quartertou.bean.JokesBean;
import com.example.asus.quartertou.bean.JokesDetailBean;
import com.example.asus.quartertou.bean.LoginBean;
import com.example.asus.quartertou.bean.UserVideosBean;
import com.example.asus.quartertou.bean.VideoDetailBean;
import com.example.asus.quartertou.bean.VideosBean;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by asus on 2018/6/4.
 */

public class Api {
    private static Api api;
    private Service service;

    private Api(Service service) {
        this.service = service;
    }

    public static Api getApi(Service service) {
        if (api == null) {
            api = new Api(service);
        }
        return api;
    }
    public Observable<GetAdBean> getAd() {
        return service.getAd();
    }
    public Observable<VideosBean> getVideos(String uid, String type, String page) {
        return service.getVideos(uid,type,page);
    }
    public Observable<JokesBean> getJokes(String page) {
        return service.getJokes(page);
    }
    public Observable<JokesDetailBean> getJokesDetail(String jid) {
        return service.getJokesDetail(jid);
    }
    public Observable<VideoDetailBean> getVideoDetail(String wid) {
        return service.getVideoDetail(wid);
    }
    public Observable<UserVideosBean> getUserVideos(String uid, String page) {
        return service.getUserVideos(uid,page);
    }
    public Observable<HotVideosBean> getHotVideos(String page, String token) {
        return service.getHotVideos(page,token);
    }
    public Observable<HotVideosBean> getNearVideos(String page,String token,String latitude,String longitude) {
        return service.getNearVideos(page,token,latitude,longitude);
    }
    public Observable<BaseBean> publishJoke(RequestBody uid, RequestBody token, RequestBody content, MultipartBody.Part jokeFiles) {
        return service.publishJoke(uid,token,content,jokeFiles);
    }
    public Observable<BaseBean> addFavorite(String uid, String token, String wid) {
        return service.addFavorite(uid,token,wid);
    }
    public Observable<BaseBean> cancelFavorite(String uid, String token, String wid) {
        return service.cancelFavorite(uid,token,wid);
    }
    public Observable<LoginBean> login(String mobile, String password){
        return service.login(mobile, password);
    }
    public Observable<BaseBean> register(String mobile, String password) {
        return service.register(mobile, password);
    }
    public Observable<BaseBean> upload(RequestBody uid, RequestBody token, MultipartBody.Part file) {
        return service.upload(uid, token,file);
    }
    public Observable<VideosBean> getFavorites(String uid, String token) {
        return service.getFavorites(uid, token);
    }
    public Observable<BaseBean> follow(String uid, String token,String followId) {
        return service.follow(uid, token,followId);
    }
}
