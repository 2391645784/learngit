package com.shixin.finalfigher.Activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.shixin.finalfigher.R;
import com.shixin.finalfigher.fragment.GuideFragment;
import com.shixin.finalfigher.fragment.HomeFragment;
import com.shixin.finalfigher.fragment.MsgFragment;
import com.shixin.finalfigher.fragment.PersonFragment;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTabHost mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        // 初始化
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        mTabHost.addTab(mTabHost.newTabSpec("simple").setIndicator(getIndicator(0)),
                HomeFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("contacts").setIndicator(getIndicator(1)),
                GuideFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("custom").setIndicator(getIndicator(2)),
                MsgFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("throttle").setIndicator(getIndicator(3)),
                PersonFragment.class, null);
        //Log.e("MainActivity","初始化完成");
    }

    // 设置图片时要用到 格式最好为选择器selector
    // selector使用时需要指明正确的状态对应的图片
    // 否则顺序可能导致不生效
    private int[] imgs = new int[]{R.drawable.home_nav,
            R.drawable.guide_nav, R.drawable.msg_nav, R.drawable.person_nav};

    // 用来设置自定义指示器
    private View getIndicator(int index) {
        View view = getLayoutInflater().inflate(R.layout.layout_nav, null);
        ImageView imgTab = (ImageView) view.findViewById(R.id.img_navTab);
        imgTab.setImageResource(imgs[index]);
        return view;
    }
}
