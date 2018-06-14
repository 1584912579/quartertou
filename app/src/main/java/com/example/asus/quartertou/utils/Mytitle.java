package com.example.asus.quartertou.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.asus.quartertou.R;
import com.facebook.drawee.view.SimpleDraweeView;


/**
 * Created by asus on 2018/5/14.
 */
public class Mytitle extends LinearLayout {
    private OnsetHuida onsethuida;
    private OnsetHuidas onsethuidas;
    private TextView tv;
    private SimpleDraweeView sim;
    private ImageView fx;

    public Mytitle(Context context) {
        this(context, null);
    }

    public Mytitle(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Mytitle( Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater from = LayoutInflater.from(context);
        View inflate = from.inflate(R.layout.title, this, true);
        sim = (SimpleDraweeView)inflate.findViewById(R.id.sim);
        fx = inflate.findViewById(R.id.fx);
        tv = (TextView)inflate.findViewById(R.id.tv);
        sim.setImageURI("http://imgsrc.baidu.com/imgad/pic/item/f703738da9773912d40a27abf2198618377ae2c8.jpg");
        fx.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onsethuida.huida();
            }
        });
        sim.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onsethuidas.huida();
            }
        });
    }
    public interface OnsetHuida{
        void huida();
    }
    public void setJiekou(OnsetHuida onsethuida){
        this.onsethuida=onsethuida;
    }
    public interface OnsetHuidas{
        void huida();
    }
    public void setmein(OnsetHuidas onsethuidas){
        this.onsethuidas=onsethuidas;
    }
    public void settv(String ss){
        tv.setText(ss);
    }

}


