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
 * Created by mio on 2017/2/20.
 */

public class SearchRecyclerViewAdapter extends RecyclerView.Adapter<SearchRecyclerViewAdapter.SearchViewHolder>{
    private Activity activity;
    List<WeiXinBean.ResultBean> resultList;
    public SearchRecyclerViewAdapter(Activity activity, List<WeiXinBean.ResultBean> resultList) {
        this.activity = activity;
        this.resultList=resultList;
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_searchlist_item,parent,false);
        SearchViewHolder searchViewHolder = new SearchViewHolder(itemView);
        return searchViewHolder;
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        holder.pos = position;
        holder.titleTv.setMaxLines(position%4+2);
        WeiXinBean.ResultBean resultBean = resultList.get(position);
        String title = resultBean.getTitle();
        String picUrl = resultBean.getPicUrl();
        holder.titleTv.setText(title);
        Glide.with(activity).load(picUrl)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .skipMemoryCache(true)
                .into(holder.titleImg);
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    class SearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView titleImg;
        TextView titleTv;
        int pos;
        public SearchViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            titleImg = (ImageView) itemView.findViewById(R.id.title_img);
            titleTv = (TextView) itemView.findViewById(R.id.title_tv);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(activity, HomeWebActivity.class);
            intent.putExtra("homedetail",resultList.get(pos).getUrl());
            activity.startActivity(intent);
        }
    }
}
