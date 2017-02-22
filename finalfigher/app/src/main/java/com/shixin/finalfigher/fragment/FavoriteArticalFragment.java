package com.shixin.finalfigher.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.shixin.finalfigher.Activity.FavoriteActivity;
import com.shixin.finalfigher.R;
import com.shixin.finalfigher.adapter.FavoriteArticalRecyclerViewAdapter;
import com.shixin.finalfigher.bean.GanhuoBean;
import com.shixin.finalfigher.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 收藏 文章
 */
public class FavoriteArticalFragment extends Fragment {

    private List<GanhuoBean.ResultsBean> queryDateList = new ArrayList<>();
    private View view;
    private RecyclerView mRecyclerView;
    private FavoriteArticalRecyclerViewAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    public FavoriteArticalFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(R.layout.fragment_favorite_artical, container, false);
            mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout_favoriteArtical);
            mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_favoriteArtical);
            adapter = new FavoriteArticalRecyclerViewAdapter(getActivity(),queryDateList);
            linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),LinearLayoutManager.VERTICAL));
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.setAdapter(adapter);
            mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // 2s后刷新完成
                            mSwipeRefreshLayout.setRefreshing(false);
                        }
                    },2000);
                    queryData();
                }
            });


            queryData();


        }
        return view;
    }

    private void queryData() {
        if (AVUser.getCurrentUser() != null) {
            AVQuery<AVObject> avQuery = new AVQuery<>("Ganhuo_table");
            avQuery.orderByDescending("createdAt");
            // 用户要对应
            avQuery.whereEqualTo("gank_owner_id", AVUser.getCurrentUser().getObjectId());
            avQuery.findInBackground(new FindCallback<AVObject>() {
                @Override
                public void done(List<AVObject> list, AVException e) {
                    if (e == null) {
                        // 先清空容器
                        queryDateList.clear();
                        adapter.notifyDataSetChanged();
                        for (int i = 0; i < list.size(); i++) {
                            String gank_title = (String) list.get(i).get("gank_title");
                            String url = (String) list.get(i).get("gank_detail");
                            // 创建对象
                            GanhuoBean.ResultsBean resultsBean = new GanhuoBean.ResultsBean();
                            resultsBean.setDesc(gank_title);
                            resultsBean.setUrl(url);
                            queryDateList.add(resultsBean);
                        }

                        // 刷新数据
                        adapter.notifyDataSetChanged();

                    } else {
                        ToastUtils.showToast(getContext(), "没有收藏");
                        e.printStackTrace();
                    }
                }
            });
        } else {
            ToastUtils.showToast(getContext(), "还没登陆哦");
        }
    }


}
