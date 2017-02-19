package com.shixin.finalfigher.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by mio on 2017/2/19.
 */

public class GuideRecyclerViewAdapter extends RecyclerView.Adapter<GuideRecyclerViewAdapter.GuideViewHolder>{
    @Override
    public GuideViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(GuideViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class GuideViewHolder extends RecyclerView.ViewHolder{

        public GuideViewHolder(View itemView) {
            super(itemView);
        }
    }
}
