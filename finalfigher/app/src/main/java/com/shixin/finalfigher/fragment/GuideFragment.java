package com.shixin.finalfigher.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.shixin.finalfigher.Activity.SearchActivity;
import com.shixin.finalfigher.R;
import com.shixin.finalfigher.adapter.GuideRecyclerViewAdapter;
import com.shixin.finalfigher.bean.WeiXinBean;
import com.shixin.finalfigher.loader.GlideImageLoader;
import com.shixin.finalfigher.net.ApiClient;
import com.shixin.finalfigher.net.BannerClient;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class GuideFragment extends Fragment implements View.OnClickListener{


    private View view;
    private LinearLayout mSearchLayout;
    private LinearLayoutManager linearLayoutManager;
    private XRecyclerView mXRecyclerView;
    private GuideRecyclerViewAdapter adapter;
    private int pageCount = 1;
    private View headerView;
    private Banner mBanner;

    public GuideFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            Log.e("Guide","测试");
            initUI(inflater, container);
            String weixinUrl= ApiClient.getUrl(1,20,"科技");
            OkHttpUtils.get()
                    .url(weixinUrl)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Log.e("GuideFragment","请求失败");
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            Log.e("GuideFragment","链接有效");
                            WeiXinBean weiXinBean = JSON.parseObject(response, WeiXinBean.class);
                            List<WeiXinBean.ResultBean> result = weiXinBean.getResult();
                           String title = result.get(0).getTitle();
                           Log.e("Guide",title);
                        }
                    });
        }
        return view;
    }

    private void initUI(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.fragment_guide, container, false);
        mSearchLayout = (LinearLayout) view.findViewById(R.id.search_linearLayout);
        mSearchLayout.setOnClickListener(this);
        mXRecyclerView = (XRecyclerView) view.findViewById(R.id.xRecyclerView_guide);

        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        adapter = new GuideRecyclerViewAdapter();
        mXRecyclerView.setLayoutManager(linearLayoutManager);
        mXRecyclerView.setAdapter(adapter);
        mXRecyclerView.setPullRefreshEnabled(false);
        mXRecyclerView.setLoadingMoreEnabled(false);
        headerView = inflater.inflate(R.layout.listitem_banner, container, false);
        mBanner = (Banner) headerView.findViewById(R.id.banner);
        mBanner.setImageLoader(new GlideImageLoader());
        // 获取广告图片的地址
        List<String> bannerList = BannerClient.getBanner();
        // 添加存放图片的容器
        mBanner.setImages(bannerList);
        // 设置指示器位置
        mBanner.setIndicatorGravity(BannerConfig.RIGHT);
        // 设置动画样式
        mBanner.setBannerAnimation(Transformer.Accordion);
        mBanner.setOffscreenPageLimit(bannerList.size());
        // 设置切换时间
        mBanner.setDelayTime(2000);
        mBanner.start();
        mXRecyclerView.addHeaderView(headerView);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), SearchActivity.class);
        startActivity(intent);
        // 取消Activity切换的动画
        getActivity().overridePendingTransition(0,0);
    }
}
