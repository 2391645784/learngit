package com.shixin.finalfigher.Activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SaveCallback;
import com.shixin.finalfigher.R;
import com.shixin.finalfigher.fragment.GuideFragment;
import com.shixin.finalfigher.fragment.HomeFragment;
import com.shixin.finalfigher.fragment.MsgFragment;
import com.shixin.finalfigher.fragment.PersonFragment;
import com.shixin.finalfigher.utils.ToastUtils;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // 测试 leancloud SDK 是否正常工作的代码
//        AVObject testObject = new AVObject("TestObject");
//        testObject.put("words","Hello World!");
//
//        testObject.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(AVException e) {
//                if(e == null){
//                    Log.d("saved","success!");
//                }
//            }
//        });
//end
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

    private boolean isSure;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            if(isSure){
                finish();
            } else {
                // 首次点击返回键弹出提示
                ToastUtils.showToast(MainActivity.this,"再按一次退出");
                isSure = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 经过1.5s后还原
                        isSure = false;
                    }
                }, 1500);
            }
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /**
         * 退出当前空户
         */
        if(AVUser.getCurrentUser()!=null){
            // 获取当前用户并退出
            AVUser.getCurrentUser().logOut();
        }
    }
}
