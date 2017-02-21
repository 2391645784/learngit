package com.shixin.finalfigher.fragment;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shixin.finalfigher.R;
import com.shixin.finalfigher.adapter.HomeViewPagerAdapter;
import com.shixin.finalfigher.utils.ToastUtils;

import java.util.List;


public class HomeFragment extends Fragment {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private FloatingActionButton mFab;
    private View view;
    private HomeViewPagerAdapter myViewPagerAdapt;
    private int currentFragmentPos = 0;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            // Inflate the layout for this fragment
            view = inflater.inflate(R.layout.fragment_home, container, false);
            mTabLayout = (TabLayout) view.findViewById(R.id.sliding_tabs);
            mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
            mFab = (FloatingActionButton) view.findViewById(R.id.fab);
            myViewPagerAdapt = new HomeViewPagerAdapter(getChildFragmentManager());
            // 为ViewPager设置适配器
            mViewPager.setAdapter(myViewPagerAdapt);
            // 关联ViewPager
            mTabLayout.setupWithViewPager(mViewPager);
            // 标签可滚动
            mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
            // 标签固定
            //mTabLayout.setTabMode(TabLayout.MODE_FIXED);
            mFab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    List<HomeItemFragment> fragList = myViewPagerAdapt.getFragList();
                    if (fragList != null&&fragList.size()>0) {
                    HomeItemFragment homeItemFragment = fragList.get(currentFragmentPos);
                        if(homeItemFragment!=null){
                            ToastUtils.showToast(getContext(),"回到顶部");
                            // 列表回到顶部
                            homeItemFragment.toTop();
                        }

                    }
                }
            });
            mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {

                    currentFragmentPos = position;
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }

        return view;
    }


}
