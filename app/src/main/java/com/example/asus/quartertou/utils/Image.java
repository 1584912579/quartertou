package com.example.asus.quartertou.utils;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.loader.ImageLoader;


/**
 * Created by asus on 2018/6/4.
 */

public class Image extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {

        Uri uri = Uri.parse((String) path);
        imageView.setImageURI(uri);
    }
    @Override
    public ImageView createImageView(Context context){

        SimpleDraweeView simpleDraweeView=new SimpleDraweeView(context);
        return simpleDraweeView;
    }
}
