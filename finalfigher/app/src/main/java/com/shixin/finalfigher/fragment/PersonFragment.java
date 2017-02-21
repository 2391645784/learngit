package com.shixin.finalfigher.fragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.avos.avoscloud.AVUser;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.shixin.finalfigher.Activity.LoginActivity;
import com.shixin.finalfigher.Activity.OpenSourceActivity;
import com.shixin.finalfigher.Activity.SettingActivity;
import com.shixin.finalfigher.R;
import com.shixin.finalfigher.net.ApiClient;
import com.shixin.finalfigher.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import okhttp3.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonFragment extends Fragment {


    private ImageView avaterImg;
    private View view;
    private TextView mExitTextView;
    private LocalBroadcastManager broadcastManager;
    private PersonFragBroadCastReceiver receiver;
    private TextView mInfoLoginTv;
    private TextView mOpenSourceTv;
    private TextView mSettingTv;
    private ImageView mBingImg;

    public PersonFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (broadcastManager == null) {
            broadcastManager = LocalBroadcastManager.getInstance(getActivity());
            IntentFilter filter = new IntentFilter();
            filter.addAction("com.shixin.personfragment");
            // 创建广播接收器
            receiver = new PersonFragBroadCastReceiver();
            // 注册广播
            broadcastManager.registerReceiver(receiver, filter);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (receiver != null) {
            // 注销广播
            broadcastManager.unregisterReceiver(receiver);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_person, container, false);
            // 背景
            mBingImg = (ImageView) view.findViewById(R.id.bing_img);
            loadImageBackground();


            avaterImg = (ImageView) view.findViewById(R.id.avter_img_person);
            mExitTextView = (TextView) view.findViewById(R.id.exitLogin_textView);
            mInfoLoginTv = (TextView) view.findViewById(R.id.infoLogin_TextView);
            mOpenSourceTv = (TextView) view.findViewById(R.id.openSource_TextView);
            mSettingTv = (TextView) view.findViewById(R.id.settting_textView);
            Glide.with(getActivity())
                    .load(R.drawable.shirokuma)
                    .bitmapTransform(new CropCircleTransformation(getActivity()))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .skipMemoryCache(true)
                    .into(avaterImg);
            /**
             * 登陆
             */
            avaterImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mInfoLoginTv.getText().equals("点击头像进行登陆")) {
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        startActivity(intent);
                    } else {
                        ToastUtils.showToast(getContext(),"不能重复登陆");
                    }

                }
            });
            /**
             * 退出登陆
             */
            mExitTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mInfoLoginTv.getText().equals("点击头像进行登陆")) {
                        ToastUtils.showToast(getContext(),"请先登录");
                    } else {
                        ToastUtils.showToast(getContext(),"已退出");
                        // 获取当前用户并退出
                        AVUser.getCurrentUser().logOut();
                        mInfoLoginTv.setText("点击头像进行登陆");
                    }

                }
            });
            /**
             * 跳转到开源许可
             */
            mOpenSourceTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), OpenSourceActivity.class);
                    startActivity(intent);
                }
            });
            /**
             * 跳转到设置设置
             */
            mSettingTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), SettingActivity.class);
                    startActivity(intent);
                }
            });

        }
        return view;
    }

    private void loadImageBackground() {
        OkHttpUtils.get()
                .url(ApiClient.bingPicUrl)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("PersonFragment","加载背景失败");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Glide.with(getActivity())
                                .load(response)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .skipMemoryCache(true)
                                .centerCrop()
                                .into(mBingImg);
                    }
                });
    }

    class PersonFragBroadCastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("PersonFragment", "收到广播");
            String username = AVUser.getCurrentUser().getUsername();
            mInfoLoginTv.setText("已登录:" + username);

        }
    }

}
