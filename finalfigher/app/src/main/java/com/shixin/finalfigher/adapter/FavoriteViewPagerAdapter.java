package com.shixin.finalfigher.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.shixin.finalfigher.fragment.FavoriteArticalFragment;
import com.shixin.finalfigher.fragment.FavoriteNewsFragment;

/**
 * Created by mio on 2017/2/22.
 */

public class FavoriteViewPagerAdapter extends FragmentPagerAdapter {
    private String[] titles = new String[]{"文章","新闻"};
    public FavoriteViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        Fragment f=null;
        switch (position){
            case 0:
                f = new FavoriteArticalFragment();
                break;
            case 1:
                f = new FavoriteNewsFragment();
                break;
        }
        return f;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
