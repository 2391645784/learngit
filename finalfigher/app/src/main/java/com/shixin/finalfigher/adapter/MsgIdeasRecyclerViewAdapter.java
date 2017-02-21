package com.shixin.finalfigher.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
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
import com.shixin.finalfigher.bean.V2exIdeas;
import com.shixin.finalfigher.loader.GlideImageLoader;

import java.util.List;

/**
 * Created by mio on 2017/2/20.
 */

public class MsgIdeasRecyclerViewAdapter extends RecyclerView.Adapter<MsgIdeasRecyclerViewAdapter.MsgIdeasViewHolder> {


    private Activity activity;
    private List<V2exIdeas> dataList;

    public MsgIdeasRecyclerViewAdapter(Activity activity, List<V2exIdeas> dataList) {

        this.activity = activity;
        this.dataList = dataList;
    }

    @Override
    public MsgIdeasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_msgiteaslist_item, parent, false);
        MsgIdeasViewHolder msgIdeasViewHolder = new MsgIdeasViewHolder(itemView);
        return msgIdeasViewHolder;
    }

    @Override
    public void onBindViewHolder(MsgIdeasViewHolder holder, int position) {
        holder.pos = position;
        V2exIdeas v2exIdeas = dataList.get(position);
        String title = v2exIdeas.getTitle();
        String content = v2exIdeas.getContent();
        String avatar_normal = v2exIdeas.getMember().getAvatar_normal();
        String username = v2exIdeas.getMember().getUsername();
        holder.authorTv.setText("用户:"+username);
        avatar_normal="http:"+avatar_normal;
        holder.titleTv.setText(title);
        holder.contentTv.setText(content);
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

    class MsgIdeasViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView titleTv;
        TextView authorTv;
        TextView contentTv;
        ImageView avaterImg;
        int pos;

        public MsgIdeasViewHolder(View itemView) {
            super(itemView);
            titleTv = (TextView) itemView.findViewById(R.id.title_tv_MsgIdeas);
            authorTv = (TextView) itemView.findViewById(R.id.author_tv_MsgIdeas);
            contentTv = (TextView) itemView.findViewById(R.id.content_tv_MsgIdeas);
            avaterImg = (ImageView) itemView.findViewById(R.id.avater_img_MsgIdeas);
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
