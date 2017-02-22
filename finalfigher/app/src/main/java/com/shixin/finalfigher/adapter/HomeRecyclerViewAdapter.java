package com.shixin.finalfigher.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.shixin.finalfigher.Activity.HomeWebActivity;
import com.shixin.finalfigher.R;
import com.shixin.finalfigher.bean.GanhuoBean;

import java.util.ArrayList;
import java.util.List;



public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.HomeViewHolder> {
    private Activity activity;
    private List<GanhuoBean.ResultsBean> dataList;

    public HomeRecyclerViewAdapter(Activity activity, List<GanhuoBean.ResultsBean> list) {
        this.activity = activity;
        dataList = list;
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_homelist_item, parent, false);
        return new HomeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HomeViewHolder holder, int position) {
        holder.setPosition(position);
        GanhuoBean.ResultsBean resultsBean = dataList.get(position);
        String descTv = resultsBean.getDesc();
        holder.titleTv.setText(descTv);
        String id = resultsBean.getWho();
        holder.idTv.setText(id);
        String date = resultsBean.getCreatedAt();
        // 裁剪日期
        String subDate = date.substring(0, 10);
        holder.dateTv.setText("上传日期:"+subDate);
        List<String> images = resultsBean.getImages();
        if(images!=null){
            String imgUrl = images.get(0);
            Glide.with(activity)
                    .load(imgUrl)
                    .asBitmap()
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.titleImg);
        }

    }

    @Override
    public int getItemCount() {

        return dataList.size();
    }

    class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView titleImg;
        TextView titleTv;
        TextView idTv;
        TextView dateTv;
        int position;

        public void setPosition(int pos){
            position = pos;
        }
        public HomeViewHolder(View itemView) {
            super(itemView);
           titleImg = (ImageView) itemView.findViewById(R.id.title_img);
            titleTv = (TextView) itemView.findViewById(R.id.title_textView);
            idTv = (TextView) itemView.findViewById(R.id.id_tv);
            dateTv = (TextView) itemView.findViewById(R.id.date_textView);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            Intent intent = new Intent(activity, HomeWebActivity.class);
            String detailUrl = dataList.get(position).getUrl();

            String title = dataList.get(position).getDesc();

            intent.putExtra("detailUrl",detailUrl);

            intent.putExtra("title",title);
            Log.e("detailUrl","="+detailUrl);
            activity.startActivity(intent);
        }
    }
}
