package com.shixin.finalfigher.Activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.shixin.finalfigher.R;
import com.shixin.finalfigher.adapter.FavoriteViewPagerAdapter;

/**
 * 收藏
 */
public class FavoriteActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private FavoriteViewPagerAdapter pagerAdapter;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_favorite);
        mToolbar.setTitle("收藏");
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        mTabLayout = (TabLayout) findViewById(R.id.sliding_tabs_favorite);
        mViewPager = (ViewPager) findViewById(R.id.viewpager_favorite);
        pagerAdapter = new FavoriteViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(pagerAdapter);
        // 关联ViewPager
        mTabLayout.setupWithViewPager(mViewPager);
        // 标签固定
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
