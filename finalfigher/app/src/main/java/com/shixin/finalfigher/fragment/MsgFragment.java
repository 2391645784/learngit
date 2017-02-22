package com.shixin.finalfigher.fragment;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.shixin.finalfigher.Activity.HomeWebActivity;
import com.shixin.finalfigher.R;
import com.shixin.finalfigher.bean.V2exHot;
import com.shixin.finalfigher.net.ApiClient;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import okhttp3.Call;

/**
 * V2ex社区
 */
public class MsgFragment extends Fragment {
    List<V2exHot> v2exHots;


    private View view;
    private ViewFlipper mViewFlipper;
    private Button mTechButton;
    private Button mCreativeButton;
    private Fragment mMsgTechFragment;
    private Fragment msgIdeasFragment;
    private TextView mV2exTextView;
    private Typeface typeface;
    public MsgFragment() {
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {


        if (view == null) {
            view = inflater.inflate(R.layout.fragment_msg, container, false);
            mV2exTextView = (TextView) view.findViewById(R.id.v2ex_textView);
            // 设置字体为浪漫雅园
            typeface = Typeface.createFromAsset(getActivity().getAssets(), "font/lmyy.ttf");
            mV2exTextView.setTypeface(typeface);

            mViewFlipper = (ViewFlipper) view.findViewById(R.id.viewFlipper);
            mTechButton = (Button) view.findViewById(R.id.tech_btn);
            mCreativeButton = (Button) view.findViewById(R.id.creative_btn);
            // 创建片段

            mMsgTechFragment = new MsgTechFragment();



            // 将技术片段设置成默认的页面
            getChildFragmentManager()
                    .beginTransaction()
                    .add(R.id.container_msg, mMsgTechFragment)
                    .commit();

            mTechButton.setSelected(true);
            mTechButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // Log.e("mTechButton", "被点击");
                    if (!mTechButton.isSelected()) {
                        mTechButton.setSelected(true);
                        mCreativeButton.setSelected(false);
                        if (msgIdeasFragment != null) {
                            getChildFragmentManager()
                                    .beginTransaction()
                                    .hide(msgIdeasFragment)
                                    .commit();
                            getChildFragmentManager()
                                    .beginTransaction()
                                    .show(mMsgTechFragment)
                                    .commit();
                        }


                    }
                }
            });
            mCreativeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // Log.e("mCreativeButton", "被点击");
                    if (!mCreativeButton.isSelected()) {
                        mCreativeButton.setSelected(true);
                        mTechButton.setSelected(false);
                        if (msgIdeasFragment == null) {
                            getChildFragmentManager()
                                    .beginTransaction()
                                    .hide(mMsgTechFragment)
                                    .commit();
                            // 创建创意片段
                            msgIdeasFragment = new MsgIdeasFragment();
                            getChildFragmentManager()
                                    .beginTransaction()
                                    .add(R.id.container_msg, msgIdeasFragment)
                                    .commit();
                        } else {
                            getChildFragmentManager()
                                    .beginTransaction()
                                    .hide(mMsgTechFragment)
                                    .commit();
                            getChildFragmentManager()
                                    .beginTransaction()
                                    .show(msgIdeasFragment)
                                    .commit();
                        }


                    }
                }
            });
            initViewFlipper(inflater, container);

        }

        return view;
    }

    private void initViewFlipper(final LayoutInflater inflater, final ViewGroup container) {
        OkHttpUtils.get()
                .url(ApiClient.v2exHot)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("MsgFragment", "请求失败");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        // Log.e("MsgFragment", "请求成功");
                        v2exHots = JSON.parseArray(response, V2exHot.class);

                        for (int i = 0; i < v2exHots.size(); i++) {
                            View flipItemView = inflater.inflate(R.layout.layout_viewflipper_item, container, false);
                            TextView titleTv = (TextView) flipItemView.findViewById(R.id.title_tvFlip);
                            ImageView titleImg = (ImageView) flipItemView.findViewById(R.id.title_imgFlip);
                            titleTv.setText(v2exHots.get(i).getTitle());
                            String avatar_normal = v2exHots.get(i).getMember().getAvatar_normal();
                            String trueAvaterUrl = "http:" + avatar_normal;
                            //Log.e("头像地址", trueAvaterUrl);
                            Glide.with(getActivity())
                                    .load(trueAvaterUrl)
                                    .bitmapTransform(new CropCircleTransformation(getActivity()))
                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                    .skipMemoryCache(true)
                                    .into(titleImg);
                            final int y = i;
                            // 设置点击事件
                            flipItemView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(getActivity(), HomeWebActivity.class);
                                    String url = v2exHots.get(y).getUrl();
                                    intent.putExtra("homedetail", url);
                                    startActivity(intent);
                                }
                            });
                            mViewFlipper.addView(flipItemView);
                        }
                        mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.push_up_in));
                        mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.push_up_out));
                        mViewFlipper.setFlipInterval(2000);
                        mViewFlipper.startFlipping();
                    }
                });
    }

}
