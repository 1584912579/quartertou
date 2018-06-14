package com.example.asus.quartertou.ui.recommend.adpter;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.quartertou.R;
import com.example.asus.quartertou.bean.UserVideosBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import cn.jzvd.JZVideoPlayerStandard;

/**
 * Created by asus on 2018/5/28.
 */

public class XRV2Adapters extends RecyclerView.Adapter {
    private Context context;
    private List<UserVideosBean.DataBean> list;
    private LayoutInflater inflater;
    private boolean fla=true;
    private boolean[] ff={true};
    private boolean[] fff={true};
    private List<ImageView> images;
    private ImageView imageView;
    private Handler handler = new Handler();
    public XRV2Adapters(Context context, List<UserVideosBean.DataBean> list) {
        this.context = context;
        this.list = list;
        inflater=LayoutInflater.from(context);
        images = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.items, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final boolean[] flag={true};
        final ViewHolder holder1 =(ViewHolder) holder;
        final UserVideosBean.DataBean dataBean = list.get(position);
        holder1.img.setImageURI(dataBean.getCover());
        holder1.title.setText(dataBean.getWorkDesc());
        holder1.tv.setText(dataBean.getCreateTime());
        //setVideoURI(Uri.parse(dataBean.getVideoUrl()));
//        holder1.video.setImageURI(dataBean.getCover());
        holder1.video.setUp(dataBean.getVideoUrl(), JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL,"谁还不是小仙女呢！");

        Glide.with(context).load(dataBean.getCover()).into(holder1.video.thumbImageView);
        holder1.video.thumbImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fla) {
                    dataBean.setFlag(true);
                    //holder1.video.start();
                }else {
                    dataBean.setFlag(false);
                }
                fla=!fla;
                notifyDataSetChanged();
            }
        });
        if (dataBean.isFlag()){
            holder1.lll.setVisibility(View.VISIBLE);
          //  holder1.bf.setVisibility(View.GONE);
        }else {
            holder1.lll.setVisibility(View.GONE);
          //  holder1.bf.setVisibility(View.VISIBLE);
        }
        holder1.img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ff[0]){
                    holder1.img1.setImageResource(R.drawable.xin2);
                    ff[0]=!ff[0];
                }else{
                    holder1.img1.setImageResource(R.drawable.xin1);
                    ff[0]=!ff[0];
                }

            }
        });
        holder1.img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fff[0]){
                    holder1.img2.setImageResource(R.drawable.wujiaoxing1);
                    fff[0]=!fff[0];
                }else{
                    holder1.img2.setImageResource(R.drawable.wujiaoxing2);
                    fff[0]=!fff[0];
                }

            }
        });
        holder1.jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag[0]){
                    holder1.jia.setImageResource(R.drawable.ji);
                    ObjectAnimator rotation = ObjectAnimator.ofFloat(holder1.jia, "rotation", 180f, 0f);
                    rotation.setDuration(500);
                    rotation.start();
                    addImg(flag[0],holder1,R.drawable.heart,-100f);
                    addImg(flag[0],holder1,R.drawable.fff,-200f);
                    addImg(flag[0],holder1,R.drawable.comment,-300f);
                }else {
                    holder1.jia.setImageResource(R.drawable.j);
                    ObjectAnimator rotation = ObjectAnimator.ofFloat(holder1.jia, "rotation", 180f, 360f);
                    rotation.setDuration(500);
                    rotation.start();
                    addImg(flag[0],holder1,R.drawable.heart,-100f);
                    addImg(flag[0],holder1,R.drawable.fff,-200f);
                    addImg(flag[0],holder1,R.drawable.comment,-300f);
                }
                flag[0] = !flag[0];
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView img;
        private final TextView title;
        //private final ImageView bf;
        private final LinearLayout lll;
        private final RelativeLayout r;
        private final TextView tv;
        private final ImageView img1;
        private final ImageView img2;
        private final ImageView jia;
        private final JZVideoPlayerStandard video;
        @SuppressLint("WrongViewCast")
        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.sim2);
            title = itemView.findViewById(R.id.title);
            tv = itemView.findViewById(R.id.tv);
            video = itemView.findViewById(R.id.video);
            //bf = itemView.findViewById(R.id.bf);
            img1 = itemView.findViewById(R.id.img1);
            img2 = itemView.findViewById(R.id.img2);
            jia = itemView.findViewById(R.id.s_jia);
            lll = itemView.findViewById(R.id.lll);
            r = itemView.findViewById(R.id.s_ll);
        }
    }
    public void shuxin(List<UserVideosBean.DataBean> templist){
        this.list.clear();
        this.list.addAll(templist);
        notifyDataSetChanged();

    }
    public void jiazai(List<UserVideosBean.DataBean> list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }
    public void addImg(boolean flag, final ViewHolder viewHolder, int uri, float address){

        if (flag) {
            imageView = new ImageView(context);
            images.add(imageView);
            imageView.setImageResource(uri);

            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(45, 45);
            lp.addRule(RelativeLayout.ALIGN_TOP, R.id.s_jia);
            lp.addRule(RelativeLayout.ALIGN_RIGHT, R.id.s_jia);

            viewHolder.r.addView(imageView, lp);

            ObjectAnimator rotation = ObjectAnimator.ofFloat(imageView, "rotation", 180f, 0f);
            ObjectAnimator alpha = ObjectAnimator.ofFloat(imageView, "alpha", 0f, 1.0f);
            float translationX = imageView.getTranslationX();
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView, "translationX", translationX, address);
            AnimatorSet set = new AnimatorSet();
            //添加动画
            set.play(objectAnimator).with(alpha).with(rotation);
            set.setDuration(500);
            set.start();
        }else {
            if (images != null) {
                for (int i = 0 ; i < images.size() ; i++ ) {
                    ObjectAnimator rotation = ObjectAnimator.ofFloat(images.get(i), "rotation", 0f, 180f);
                    ObjectAnimator alpha = ObjectAnimator.ofFloat(images.get(i), "alpha", 1.0f, 0f);
                    float translationX = images.get(i).getTranslationX();
                    ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(images.get(i), "translationX", translationX, -address);
                    AnimatorSet set = new AnimatorSet();
                    //添加动画
                    set.play(objectAnimator).with(alpha).with(rotation);
                    set.setDuration(500);
                    set.start();
                    final int finalI = i;
                    System.out.println(handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            viewHolder.r.removeView(images.get(finalI));
                        }
                    },500));

                }
            }
        }
    }
}
