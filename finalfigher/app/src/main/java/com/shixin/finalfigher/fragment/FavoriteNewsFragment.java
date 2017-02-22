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
import com.shixin.finalfigher.R;
import com.shixin.finalfigher.adapter.FavoriteNewsRecyclerViewAdapter;
import com.shixin.finalfigher.bean.GanhuoBean;
import com.shixin.finalfigher.bean.WeiXinBean;
import com.shixin.finalfigher.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 收藏 新闻
 */
public class FavoriteNewsFragment extends Fragment {

    private List<WeiXinBean.ResultBean> queryDateList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private View view;
    private LinearLayoutManager linearLayoutManager;
    private FavoriteNewsRecyclerViewAdapter adapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    public FavoriteNewsFragment() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_favorite_news, container, false);
            mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout_favoriteNews);
            mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_favoriteNews);
            linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            adapter = new FavoriteNewsRecyclerViewAdapter(getActivity(),queryDateList);
            mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),LinearLayoutManager.VERTICAL));
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.setAdapter(adapter);

            mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    queryData();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mSwipeRefreshLayout.setRefreshing(false);
                        }
                    },2000);
                }
            });

            queryData();


        }
        return view;
    }

    private void queryData() {
        if (AVUser.getCurrentUser() != null) {
            AVQuery<AVObject> avQuery = new AVQuery<>("Weixin_table");
            avQuery.orderByDescending("createdAt");
            // 用户要对应
            avQuery.whereEqualTo("weixin_owner_id", AVUser.getCurrentUser().getObjectId());
            avQuery.findInBackground(new FindCallback<AVObject>() {
                @Override
                public void done(List<AVObject> list, AVException e) {
                    if (e == null) {
                        // 先清空容器
                        queryDateList.clear();
                        adapter.notifyDataSetChanged();
                        for (int i = 0; i < list.size(); i++) {
                            String weixin_title = (String) list.get(i).get("weixin_title");
                            String weixin_detail = (String) list.get(i).get("weixin_detail");
                            Log.e("newsFragment",weixin_detail);
                            String picUrl = (String) list.get(i).get("weixinPicUrl");
                            // 创建对象
                            WeiXinBean.ResultBean resultsBean = new WeiXinBean.ResultBean();

                            resultsBean.setTitle(weixin_title);
                            resultsBean.setUrl(weixin_detail);
                            resultsBean.setPicUrl(picUrl);

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
