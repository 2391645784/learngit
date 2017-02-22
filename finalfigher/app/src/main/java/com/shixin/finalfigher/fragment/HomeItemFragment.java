package com.shixin.finalfigher.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.shixin.finalfigher.R;
import com.shixin.finalfigher.adapter.HomeRecyclerViewAdapter;
import com.shixin.finalfigher.bean.GanhuoBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeItemFragment extends Fragment {

    // String url = "http://gank.io/api/data/Android/10/1";
    public static final String apiHomeUrl = "http://gank.io/api/data/";


    private int pageCount = 1;
    private String dataName = "";
    private List<GanhuoBean.ResultsBean> dataList = new ArrayList<>();

    private View view;
    private XRecyclerView mXRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private HomeRecyclerViewAdapter adapter;


    public void toTop() {
        if (mXRecyclerView != null) {
            mXRecyclerView.scrollToPosition(0);

        }

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        String dataName = bundle.getString("dataName");
        this.dataName = dataName;
        //Log.e("dataName", "=" + dataName);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            initUI(inflater, container);
            initData();
        }
        return view;
    }

    private void initData() {
        String url = apiHomeUrl + dataName + "/10/" + pageCount;
        // Log.e("请求地址", "" + url);
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("HomeItemFragment", "请求失败");

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        // Log.e("HomeItemFragment","请求成功");
                        GanhuoBean ganhuoBeen = JSON.parseObject(response, GanhuoBean.class);
                        List<GanhuoBean.ResultsBean> results = ganhuoBeen.getResults();
                        dataList.addAll(results);
                        adapter.notifyDataSetChanged();
                        // Log.e("刷新数据","完成");
                    }
                });
        Log.e("初始化", "完成");
    }

    private void initUI(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.fragment_home_item, container, false);
        mXRecyclerView = (XRecyclerView) view.findViewById(R.id.xRecyclerView_home);
        // 创建线性布局管理器
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        adapter = new HomeRecyclerViewAdapter(getActivity(), dataList);
        mXRecyclerView.setLayoutManager(linearLayoutManager);
        mXRecyclerView.setAdapter(adapter);

        mXRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mXRecyclerView.refreshComplete();
                        // 页数重置
                        pageCount=1;
                        // 清空容器
                        dataList.clear();
                        initData();


                    }
                }, 1500);
            }

            @Override
            public void onLoadMore() {
                // 当前页数加1
                pageCount++;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mXRecyclerView.loadMoreComplete();
                        initData();
                        adapter.notifyDataSetChanged();
                    }
                }, 1500);
            }
        });


    }

}
