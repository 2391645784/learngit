package com.shixin.finalfigher.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.SaveCallback;
import com.shixin.finalfigher.R;
import com.shixin.finalfigher.utils.ToastUtils;

import java.util.List;

/**
 * 搜索详情页
 */
public class SearchWebActivity extends AppCompatActivity {
    private WebView mWebView;
    private Toolbar mToolbar;
    private ContentLoadingProgressBar mcontentLoadingProgressBar;
    private ImageView favoriteImg;
    
    private String weixinDetailUrl;
    private String weixinTitle;
    private String weixinPicUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_web);
        // 获取意图对象
        weixinDetailUrl = getIntent().getStringExtra("weixinDetailUrl");
        weixinTitle = getIntent().getStringExtra("weixinTitle");
        weixinPicUrl = getIntent().getStringExtra("weixinPicUrl");
        

        mWebView = (WebView) findViewById(R.id.webview_searchWeb);
        favoriteImg = (ImageView) findViewById(R.id.favorite_img_search);

        mToolbar = (Toolbar) findViewById(R.id.toolbar_searchWeb);
        mcontentLoadingProgressBar = (ContentLoadingProgressBar) findViewById(R.id.contentLoadingProgressBar);
        mToolbar.setTitle("详情");
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        /**
         * 监听网页是否加载完成
         */
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mcontentLoadingProgressBar.setVisibility(View.INVISIBLE);
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient());

        /**
         * 加载网页
         */
        mWebView.loadUrl(weixinDetailUrl);

        /**
         * 收藏按钮
         */
        favoriteImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AVUser.getCurrentUser() != null) {
                    if (!favoriteImg.isSelected()) {

                        // TODO　进行收藏操作
                        favoriteImg.setSelected(true);
                        ToastUtils.showToast(SearchWebActivity.this, "收藏");
                        AVObject weixin_table = new AVObject("Weixin_table");
                        // 加入所有者的id字段
                        weixin_table.put("weixin_owner_id", AVUser.getCurrentUser().getObjectId());
                        // 加入所有者的名字
                        weixin_table.put("weixin_owner_name", AVUser.getCurrentUser().getUsername());
                        // 加入标题
                        weixin_table.put("weixin_title", weixinTitle);
                        // 加入图片地址
                        weixin_table.put("weixinPicUrl", weixinPicUrl);
                        
                        // 用网址的唯一性
                        weixin_table.put("weixin_detail", weixinDetailUrl);
                        weixin_table.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(AVException e) {
                                if (e == null) {
                                    ToastUtils.showToast(SearchWebActivity.this, "保存完成");
                                } else {
                                    ToastUtils.showToast(SearchWebActivity.this, e.getMessage());
                                }
                            }
                        });

                    } else {

                        // TODO　进行取消收藏
                        favoriteImg.setSelected(false);
                        ToastUtils.showToast(SearchWebActivity.this, "不收藏");

                        AVQuery<AVObject> avQuery = new AVQuery<>("Weixin_table");
                        avQuery.orderByDescending("createdAt");
                        // 用户要对应
                        avQuery.whereEqualTo("weixin_owner_id", AVUser.getCurrentUser().getObjectId());
                        // 加入删除的判断条件，这里使用网址作为判断依据
                        avQuery.whereEqualTo("weixin_detail",weixinDetailUrl);
                        avQuery.findInBackground(new FindCallback<AVObject>() {
                            @Override
                            public void done(List<AVObject> list, AVException e) {
                                if (e == null) {
                                    for (int i = 0; i < list.size(); i++) {
                                        // TODO 删除符合条件的数据
                                        list.get(i).deleteInBackground();
                                    }
                                } else {
                                    ToastUtils.showToast(SearchWebActivity.this, "取消收藏失败");
                                    e.printStackTrace();
                                }
                            }
                        });


                    }
                } else {
                    // 对话框提示
                    AlertDialog.Builder builder = new AlertDialog.Builder(SearchWebActivity.this);
                    builder.setIcon(R.drawable.avatar_img)
                            .setTitle("还未登陆")
                            .setMessage("是否现在登陆?")
                            .setPositiveButton("现在就去", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(SearchWebActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    SearchWebActivity.this.finish();
                                }
                            })
                            .setNegativeButton("算了",null)
                            .create()
                            .show();
                }
            }
        });
        /**
         * 设置收藏图片是否选中
         */
        if (AVUser.getCurrentUser() != null) {
            AVQuery<AVObject> avQuery = new AVQuery<>("Weixin_table");
            avQuery.orderByDescending("createdAt");
            avQuery.include("owner");
            // 加入判断条件，这里使用网址作为判断依据
            avQuery.whereEqualTo("weixin_detail",weixinDetailUrl);
            // 用户要对应
            avQuery.whereEqualTo("weixin_owner_id", AVUser.getCurrentUser().getObjectId());
            avQuery.findInBackground(new FindCallback<AVObject>() {
                @Override
                public void done(List<AVObject> list, AVException e) {
                    if (e == null) {
                        if (list.size() == 0) {
                            ToastUtils.showToast(SearchWebActivity.this, "还没有收藏");
                            return;
                        }
                        // 设置图片为选中状态
                        favoriteImg.setSelected(true);
                    } else {
                        ToastUtils.showToast(SearchWebActivity.this, "查询失败");
                        e.printStackTrace();
                    }
                }
            });
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
