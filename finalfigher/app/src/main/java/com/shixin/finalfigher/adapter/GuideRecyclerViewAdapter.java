package com.shixin.finalfigher.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.shixin.finalfigher.Activity.HomeWebActivity;
import com.shixin.finalfigher.R;
import com.shixin.finalfigher.bean.WeiXinBean;

import java.util.List;

/**
 * Created by mio on 2017/2/19.
 */

public class GuideRecyclerViewAdapter extends RecyclerView.Adapter<GuideRecyclerViewAdapter.GuideViewHolder>{
    private Activity activity;
    private List<WeiXinBean.ResultBean> dataList;

    public GuideRecyclerViewAdapter(Activity activity, List<WeiXinBean.ResultBean> list){
        this.activity = activity;
        dataList = list;
    }
    @Override
    public GuideViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_guidelist_item,parent,false);
        GuideViewHolder guideViewHolder = new GuideViewHolder(itemView);
        return guideViewHolder;
    }

    @Override
    public void onBindViewHolder(GuideViewHolder holder, int position) {
        holder.pos = position;
        WeiXinBean.ResultBean resultBean = dataList.get(position);
        holder.desTv.setText("来自:"+resultBean.getDescription());
        holder.titleTv.setText(resultBean.getTitle());
        Glide.with(activity).load(resultBean.getPicUrl())
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .skipMemoryCache(true).into(holder.picImg);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class GuideViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView desTv;
        TextView titleTv;
        ImageView picImg;
        int pos;

        public GuideViewHolder(View itemView) {
            super(itemView);
            desTv = (TextView) itemView.findViewById(R.id.dec_tv);
            titleTv = (TextView) itemView.findViewById(R.id.title_tv);
            picImg = (ImageView) itemView.findViewById(R.id.pic_img);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            String url = dataList.get(pos).getUrl();
            Intent intent = new Intent(activity, HomeWebActivity.class);
            intent.putExtra("homedetail",url);
            activity.startActivity(intent);
        }
    }
}
