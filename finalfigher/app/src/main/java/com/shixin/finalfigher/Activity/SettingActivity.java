package com.shixin.finalfigher.Activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;

import com.shixin.finalfigher.R;

/**
 * 设置
 */
public class SettingActivity extends AppCompatActivity {

    private RelativeLayout mClearCacheRelative;
    private SwitchCompat mDoubleClickSwitch;
    private SwitchCompat mBackGroundPushSwitch;
    private SwitchCompat mAutoUpdateSwitch;
    private RelativeLayout mCheckUpdateRelative;
    private Toolbar mToolbar;
    private ActionBar actionbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_setting);
        setSupportActionBar(mToolbar);
        actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeButtonEnabled(true);
        actionbar.setTitle("设置");
        mClearCacheRelative = (RelativeLayout) findViewById(R.id.clearCache_RelativeLayout);
        mDoubleClickSwitch = (SwitchCompat) findViewById(R.id.doubleClickExit_switchCompar);
        mBackGroundPushSwitch = (SwitchCompat) findViewById(R.id.backgroundPush_switchCompat);
        mAutoUpdateSwitch = (SwitchCompat) findViewById(R.id.autoUpdate_switchCompat);
        mCheckUpdateRelative = (RelativeLayout) findViewById(R.id.checkUpdate_RelativeLayout);
        /**
         * 清空缓存
         */
        mClearCacheRelative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        /**
         * 双击退出
         */
        mDoubleClickSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
        /**
         * 后台推送
         */
        mBackGroundPushSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
        /**
         * 自动更新
         */
        mAutoUpdateSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
        /**
         * 检查更新
         */
        mCheckUpdateRelative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
