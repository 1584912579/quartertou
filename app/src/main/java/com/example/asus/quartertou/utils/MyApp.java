package com.example.asus.quartertou.utils;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

/**
 * Created by asus on 2018/6/4.
 */

public class MyApp extends Application {
    {    //改里面的值
       // PlatformConfig.setWeixin("wx396ea2b17e2f8938", "e21c38fb0064a9631b05957f6bec73bd");
        PlatformConfig.setQQZone("1106962484", "9UOOuU6I8lqs091J");
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        //友盟舒适化
        //53f11ecd275528ea5d6780aeaf51090d
        UMConfigure.init(this, "5b1a8c3ff43e484a9e00015e"
                , "umeng", UMConfigure.DEVICE_TYPE_PHONE, "");//5b1a8c3ff43e484a9e00015e 5b1a8c3ff43e484a9e00015e
    }
}
