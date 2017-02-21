package com.shixin.finalfigher.Activity;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.shixin.finalfigher.R;
import com.shixin.finalfigher.adapter.SearchRecyclerViewAdapter;
import com.shixin.finalfigher.bean.WeiXinBean;
import com.shixin.finalfigher.net.ApiClient;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class SearchActivity extends AppCompatActivity {
    List<WeiXinBean.ResultBean> resultList = new ArrayList<>();
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;
    private SearchRecyclerViewAdapter adapter;
    private String searchUrl;
    private ImageView mEmptyImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_search);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_search);
        // 创建瀑布流布局管理器
        mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        adapter = new SearchRecyclerViewAdapter(this,resultList);
        mRecyclerView.setLayoutManager(mStaggeredGridLayoutManager);
        mRecyclerView.setAdapter(adapter);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        mEmptyImg = (ImageView) findViewById(R.id.empty_img);
        Glide.with(this).load(R.drawable.buka_loading).diskCacheStrategy(DiskCacheStrategy.ALL).skipMemoryCache(true).into(mEmptyImg);


    }

    private void searchData(String query) {
        // 清空容器
        resultList.clear();
        adapter.notifyDataSetChanged();
        searchUrl = ApiClient.getUrl(1,30,query);
        OkHttpUtils.get()
                .url(searchUrl)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("SearchActivity","请求失败");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("SearchActivity","请求有效");
                        WeiXinBean weiXinBean = JSON.parseObject(response, WeiXinBean.class);
                        List<WeiXinBean.ResultBean> result = weiXinBean.getResult();
                        resultList.addAll(result);
                        // 刷新数据
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);
        SearchView searchView = (SearchView) MenuItemCompat
                .getActionView(menu.findItem(R.id.action_search));
        searchView.setOnQueryTextListener(new SearchListener());
        // 让SearchView默认展开
        searchView.onActionViewExpanded();
        searchView.setQueryHint("在这里搜索");
        return true;
    }
    class SearchListener implements SearchView.OnQueryTextListener {

        @Override
        public boolean onQueryTextSubmit(String query) {
            // TODO　按回车后执行这个方法
            if (query.length()>0){
                if(View.VISIBLE==mEmptyImg.getVisibility()){
                    // 隐藏空图片
                    mEmptyImg.setVisibility(View.INVISIBLE);
                }
            }

            searchData(query);
            return true;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
// TODO 输入的文字发生改变时执行这个方法

            return true;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return true;
    }
}
