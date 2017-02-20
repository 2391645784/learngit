package com.shixin.finalfigher.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.shixin.finalfigher.R;
import com.shixin.finalfigher.adapter.MsgTechRecyclerViewAdapter;
import com.shixin.finalfigher.bean.V2exTech;
import com.shixin.finalfigher.net.ApiClient;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 技术
 */
public class MsgTechFragment extends Fragment {
    private  List<V2exTech> dataList = new ArrayList<>();
    View view;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private MsgTechRecyclerViewAdapter adapter;

    public MsgTechFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            Log.e("MsgTechFragment","被创建");
            view = inflater.inflate(R.layout.fragment_msg_tech, container, false);
            mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_MsgTech);
            linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            adapter = new MsgTechRecyclerViewAdapter(getActivity(),dataList);
            mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),LinearLayoutManager.VERTICAL));
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.setAdapter(adapter);
            OkHttpUtils.get()
                    .url(ApiClient.v2exTech)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Log.e("MsgTechFragment","请求失败");
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            Log.e("MsgTechFragment","请求成功");
                            List<V2exTech> v2exTeches = JSON.parseArray(response, V2exTech.class);
                            dataList.addAll(v2exTeches);
                            adapter.notifyDataSetChanged();

                        }
                    });
        }
        return view;
    }

}
