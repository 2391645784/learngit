package com.shixin.finalfigher.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shixin.finalfigher.Activity.HomeWebActivity;
import com.shixin.finalfigher.R;
import com.shixin.finalfigher.bean.GanhuoBean;
import com.shixin.finalfigher.utils.ToastUtils;

import java.util.List;

/**
 * Created by mio on 2017/2/22.
 */

public class FavoriteArticalRecyclerViewAdapter extends RecyclerView.Adapter<FavoriteArticalRecyclerViewAdapter.FavoriteArticalViewHolder> {
    private Activity activity;
    private List<GanhuoBean.ResultsBean> queryDateList;

    public FavoriteArticalRecyclerViewAdapter(Activity activity, List<GanhuoBean.ResultsBean> queryDateList) {
        this.activity = activity;
        this.queryDateList = queryDateList;
    }

    @Override
    public FavoriteArticalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_articallist_item, parent, false);
        FavoriteArticalViewHolder favoriteArticalViewHolder = new FavoriteArticalViewHolder(itemView);
        return favoriteArticalViewHolder;
    }

    @Override
    public void onBindViewHolder(FavoriteArticalViewHolder holder, int position) {
        holder.pos=position;
        String desc = queryDateList.get(position).getDesc();
        holder.titleTv.setText(desc);
    }

    @Override
    public int getItemCount() {
        return queryDateList.size();
    }

    class FavoriteArticalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        int pos;
        TextView titleTv;
        public FavoriteArticalViewHolder(View itemView) {
            super(itemView);
            titleTv = (TextView) itemView.findViewById(R.id.title_tv_artical);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            String title = queryDateList.get(pos).getDesc();
            String url = queryDateList.get(pos).getUrl();
            Intent intent = new Intent(activity, HomeWebActivity.class);
            // TODO　这里同样需要这些字段
            intent.putExtra("title",title);
            intent.putExtra("detailUrl",url);
            activity.startActivity(intent);
        }
    }
}
