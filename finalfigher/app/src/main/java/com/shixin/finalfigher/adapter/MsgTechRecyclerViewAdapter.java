package com.shixin.finalfigher.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.shixin.finalfigher.Activity.HomeWebActivity;
import com.shixin.finalfigher.R;
import com.shixin.finalfigher.bean.V2exTech;

import java.util.List;

/**
 * Created by mio on 2017/2/20.
 */

public class MsgTechRecyclerViewAdapter extends RecyclerView.Adapter<MsgTechRecyclerViewAdapter.MsgTechViewHolder>{
    private Activity activity;
    private List<V2exTech> dataList;

    public MsgTechRecyclerViewAdapter(Activity activity, List<V2exTech> dataList){

        this.activity = activity;
        this.dataList = dataList;
    }
    @Override
    public MsgTechViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_msgtechilist_item,parent,false);
        MsgTechViewHolder msgTechViewHolder = new MsgTechViewHolder(itemView);
        return msgTechViewHolder;
    }

    @Override
    public void onBindViewHolder(MsgTechViewHolder holder, int position) {
        holder.pos = position;
        V2exTech v2exTech = dataList.get(position);
        String title = v2exTech.getTitle();
        String username = v2exTech.getMember().getUsername();
        String content = v2exTech.getContent();
        holder.titleTv.setText(title);
        holder.authorTv.setText("用户:"+username);
        holder.contentTv.setText(content);
        String avatar_normal = v2exTech.getMember().getAvatar_normal();
        avatar_normal = "http:"+avatar_normal;
        Glide.with(activity)
                .load(avatar_normal)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.avaterImg);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class MsgTechViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView avaterImg;
        TextView titleTv;
        TextView authorTv;
        TextView contentTv;
        int pos;
        public MsgTechViewHolder(View itemView) {
            super(itemView);
            avaterImg = (ImageView) itemView.findViewById(R.id.avater_img_MsgTech);
            titleTv = (TextView) itemView.findViewById(R.id.title_tv_MsgTech);
            authorTv = (TextView) itemView.findViewById(R.id.author_tv_MsgTech);
            contentTv = (TextView) itemView.findViewById(R.id.content_tv_MsgTech);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            Intent intent = new Intent(activity, HomeWebActivity.class);
            intent.putExtra("homedetail",dataList.get(pos).getUrl());
            activity.startActivity(intent);
        }
    }
}
