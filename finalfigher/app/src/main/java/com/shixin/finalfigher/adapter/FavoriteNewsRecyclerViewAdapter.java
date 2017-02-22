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
import com.shixin.finalfigher.Activity.SearchWebActivity;
import com.shixin.finalfigher.R;
import com.shixin.finalfigher.bean.WeiXinBean;
import com.shixin.finalfigher.utils.ToastUtils;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by mio on 2017/2/22.
 */

public class FavoriteNewsRecyclerViewAdapter extends RecyclerView.Adapter<FavoriteNewsRecyclerViewAdapter.FavoriteNewsViewHolder>{
    private Activity activity;
    private List<WeiXinBean.ResultBean> queryDateList;

    public FavoriteNewsRecyclerViewAdapter(Activity activity, List<WeiXinBean.ResultBean> queryDateList){

        this.activity = activity;
        this.queryDateList = queryDateList;
    }
    @Override
    public FavoriteNewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_newslist_item, parent, false);
        FavoriteNewsViewHolder favoriteNewsViewHolder = new FavoriteNewsViewHolder(itemView);
        return favoriteNewsViewHolder;
    }

    @Override
    public void onBindViewHolder(FavoriteNewsViewHolder holder, int position) {
        holder.pos = position;
        String title = queryDateList.get(position).getTitle();
        String picUrl = queryDateList.get(position).getPicUrl();
        holder.titleTv.setText(title);
        Glide.with(activity)
                .load(picUrl)
                .centerCrop()
                .bitmapTransform(new CropCircleTransformation(activity))
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.titleImg);
    }

    @Override
    public int getItemCount() {
        return queryDateList.size();
    }

    class FavoriteNewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        int pos;
        ImageView titleImg;
        TextView titleTv;
        public FavoriteNewsViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            titleImg = (ImageView) itemView.findViewById(R.id.title_img_news);
            titleTv = (TextView) itemView.findViewById(R.id.title_tv_news);
        }

        @Override
        public void onClick(View v) {
            ToastUtils.showToast(activity,"点了");
            String title = queryDateList.get(pos).getTitle();

            String picUrl = queryDateList.get(pos).getPicUrl();
            String url = queryDateList.get(pos).getUrl();
            Intent intent = new Intent(activity, SearchWebActivity.class);
            intent.putExtra("weixinDetailUrl",url);

            intent.putExtra("weixinTitle",title);
            intent.putExtra("weixinPicUrl",picUrl);
            activity.startActivity(intent);

        }
    }
}
