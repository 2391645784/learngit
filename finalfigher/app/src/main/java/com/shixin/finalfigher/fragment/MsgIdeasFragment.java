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

import com.alibaba.fastjson.JSON;
import com.shixin.finalfigher.R;
import com.shixin.finalfigher.adapter.MsgIdeasRecyclerViewAdapter;
import com.shixin.finalfigher.bean.V2exIdeas;
import com.shixin.finalfigher.net.ApiClient;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 创意
 */
public class MsgIdeasFragment extends Fragment {
    private List<V2exIdeas> dataList = new ArrayList<>();
    View view;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private MsgIdeasRecyclerViewAdapter adapter;

    public MsgIdeasFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (view == null) {
            Log.e("MsgIdeasFragment","被创建了");
            view = inflater.inflate(R.layout.fragment_msg_ideas, container, false);
            mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_MsgIdeas);
            linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            adapter = new MsgIdeasRecyclerViewAdapter(getActivity(),dataList);
            mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),LinearLayoutManager.VERTICAL));
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.setAdapter(adapter);

            OkHttpUtils.get()
                    .url(ApiClient.v2exIdeas)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Log.e("MsgIdeasFragment","请求失败");
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            Log.e("MsgIdeasFragment","请求成功");
                            List<V2exIdeas> v2exIdeases = JSON.parseArray(response, V2exIdeas.class);
                            dataList.addAll(v2exIdeases);
                            adapter.notifyDataSetChanged();
                        }
                    });
        }
        return view;
    }

}
