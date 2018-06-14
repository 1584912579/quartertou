package com.example.asus.quartertou.ui.recommend.adpter;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.asus.quartertou.R;
import com.example.asus.quartertou.bean.JokesBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2018/5/28.
 */

public class XRV2Adapter extends RecyclerView.Adapter {
    private Context context;
    private List<JokesBean.DataBean> list;
    private LayoutInflater inflater;

    private List<ImageView> images;
    private OnitemOnClickListener onitemOnClickListener;
    private ImageView imageView;
    private Handler handler = new Handler();
    public XRV2Adapter(Context context, List<JokesBean.DataBean> list) {
        this.context = context;
        this.list = list;
        inflater=LayoutInflater.from(context);
        images = new ArrayList<>();
    }
    public interface OnitemOnClickListener{
        Void OnClickListener(int position);
    }
    public void setOnitemOnClickListener(OnitemOnClickListener onitemOnClickListener){
        this.onitemOnClickListener= onitemOnClickListener;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.d_item, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final boolean[] flag={true};
        final ViewHolder holder1 =(ViewHolder) holder;
        JokesBean.DataBean dataBean = list.get(position);
        holder1.img.setImageURI(dataBean.getUser().getIcon());
        holder1.title.setText(dataBean.getContent());
         holder1.tv.setText(dataBean.getCreateTime());
        holder1.tv1.setText(dataBean.getUser().getNickname()+"");
        holder1.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onitemOnClickListener.OnClickListener(position);
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
        private final TextView tv;
        private final TextView tv1;
        private final RelativeLayout ll;
        private final RelativeLayout r;
        private final ImageView jia;
        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.d_sim);
            title = itemView.findViewById(R.id.d_title);
            tv = itemView.findViewById(R.id.d_tv);
            tv1 = itemView.findViewById(R.id.d_tv1);
            ll = itemView.findViewById(R.id.d_ll);
            r = itemView.findViewById(R.id.r);
            jia = itemView.findViewById(R.id.jia);
        }
    }
    public void shuxin(List<JokesBean.DataBean> templist){
        this.list.clear();
        this.list.addAll(templist);
        notifyDataSetChanged();

    }
    public void jiazai(List<JokesBean.DataBean> list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }
    public void addImg(boolean flag, final ViewHolder viewHolder, int uri, float address){

        if (flag) {
            imageView = new ImageView(context);
            images.add(imageView);
            imageView.setImageResource(uri);

            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(40, 40);
            lp.addRule(RelativeLayout.ALIGN_TOP, R.id.jia);
            lp.addRule(RelativeLayout.ALIGN_RIGHT, R.id.jia);

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
