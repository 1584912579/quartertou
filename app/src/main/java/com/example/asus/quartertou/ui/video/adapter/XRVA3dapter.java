package com.example.asus.quartertou.ui.video.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.quartertou.R;
import com.example.asus.quartertou.bean.HotVideosBean;
import com.example.asus.quartertou.inter.OnItemClickListener;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2018/5/28.
 */

public class XRVA3dapter extends RecyclerView.Adapter {
    private Context context;
    private List<HotVideosBean.DataBean> list;
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;
    private List<Integer> mHeights;

    public XRVA3dapter(Context context, List<HotVideosBean.DataBean> list) {
        this.context = context;
        this.list = list;
        inflater=LayoutInflater.from(context);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.v_item, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        mHeights = new ArrayList<Integer>();
        for (int i = 0; i < list.size(); i++){
            mHeights.add( (int) (100 + Math.random() * 300));
        }
        ViewHolder holder1 =(ViewHolder) holder;

        ViewGroup.LayoutParams lp = holder1.img.getLayoutParams();
        lp.height = mHeights.get(position);
        holder1.img.setLayoutParams(lp);

        HotVideosBean.DataBean dataBean = list.get(position);
        holder1.img.setImageURI(dataBean.getUser().getIcon());

        holder1.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView img;
        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.v_sim);
        }
    }

    public void shuxin(List<HotVideosBean.DataBean> templist){
        this.list.clear();
        this.list.addAll(templist);
        notifyDataSetChanged();

    }
    public void jiazai(List<HotVideosBean.DataBean> list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }
}
