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
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by asus on 2018/6/4.
 */

public interface Service {
    @GET("ad/getAd")
    Observable<GetAdBean> getAd();
    //@FormUrlEncoded
    @GET("quarter/getVideos")
    Observable<VideosBean> getVideos(@Query("uid") String uid, @Query("type") String type, @Query("page") String page);
    @FormUrlEncoded
    @POST("quarter/getJokes ")
    Observable<JokesBean> getJokes(@Field("page") String page);
    @FormUrlEncoded
    @POST("quarter/getJokeDetail")
    Observable<JokesDetailBean> getJokesDetail(@Field("jid") String jid);
    @FormUrlEncoded
    @POST("quarter/getVideoDetail")
    Observable<VideoDetailBean> getVideoDetail(@Field("wid") String wid);
    @FormUrlEncoded
    @POST("quarter/getUserVideos ")
    Observable<UserVideosBean> getUserVideos(@Field("uid") String uid, @Field("page") String page);
    @FormUrlEncoded
    @POST("quarter/getHotVideos")
    Observable<HotVideosBean> getHotVideos(@Field("page") String page, @Field("token") String token);
    @FormUrlEncoded
    @POST("quarter/getNearVideos")
    Observable<HotVideosBean> getNearVideos(@Field("page") String page, @Field("token") String token, @Field("latitude") String latitude, @Field("longitude") String longitude);
    @Multipart
    @POST("quarter/publishJoke")
    //Observable<BaseBean> upload(@Part("uid") RequestBody uid, @Part("token") RequestBody token,@Part MultipartBody.Part file);
    Observable<BaseBean> publishJoke(@Part("uid") RequestBody uid, @Part("token") RequestBody token, @Part("content") RequestBody content, @Part MultipartBody.Part jokeFiles);
    @FormUrlEncoded
    @POST("quarter/addFavorite")
    Observable<BaseBean> addFavorite(@Field("uid") String uid, @Field("token") String token, @Field("wid") String wid);
    @FormUrlEncoded
    @POST("quarter/cancelFavorite")
    Observable<BaseBean> cancelFavorite(@Field("uid") String uid, @Field("token") String token, @Field("wid") String wid);
    @FormUrlEncoded
    @POST("user/login")
    Observable<LoginBean> login(@Field("mobile") String mobile, @Field("password") String password);
    @FormUrlEncoded
    @POST("quarter/register")
    Observable<BaseBean> register(@Field("mobile") String mobile,
                                  @Field("password") String password);
    @Multipart
    @POST("file/upload")
    Observable<BaseBean> upload(@Part("uid") RequestBody uid, @Part("token") RequestBody token, @Part MultipartBody.Part file);
    @FormUrlEncoded
    @POST("quarter/getFavorites")
    Observable<VideosBean> getFavorites(@Field("uid") String uid,
                                        @Field("token") String token);
    @FormUrlEncoded
    @POST("quarter/follow")
    Observable<BaseBean> follow(@Field("uid") String uid,
                                @Field("token") String token,
                                @Field("followId") String followId);
}
