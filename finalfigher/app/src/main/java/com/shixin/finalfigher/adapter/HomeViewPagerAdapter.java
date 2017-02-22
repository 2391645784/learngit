package com.shixin.finalfigher.adapter;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.shixin.finalfigher.fragment.HomeItemFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mio on 2017/2/19.
 */

public class HomeViewPagerAdapter extends FragmentPagerAdapter{

    private List<HomeItemFragment> fragList = new ArrayList<>();
    private String[] titles = new String[] { "首页", "Android", "iOS", "前端", "推荐",
            "产品" };
    private String[] dataName = new String[] { "拓展资源", "Android", "iOS", "前端", "App",
            "all" };
    public HomeViewPagerAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putString("dataName",dataName[position]);
        HomeItemFragment homeItemFragment = new HomeItemFragment();
        homeItemFragment.setArguments(bundle);
        fragList.add(homeItemFragment);
        return homeItemFragment;
    }

    /**
     * 获取添加的片段对象列表
     * @return
     */
    public List<HomeItemFragment> getFragList(){
        return fragList;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
