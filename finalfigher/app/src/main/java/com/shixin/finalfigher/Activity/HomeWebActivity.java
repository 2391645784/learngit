package com.shixin.finalfigher.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
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
 * gank详情页
 */
public class HomeWebActivity extends AppCompatActivity {

    private WebView mWebView;
    private Toolbar mToolbar;
    private ContentLoadingProgressBar mcontentLoadingProgressBar;
    private ImageView favoriteImg;
    private String detailUrl;

    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_web);
        // 获取意图对象
        detailUrl = getIntent().getStringExtra("detailUrl");
        title = getIntent().getStringExtra("title");

        mWebView = (WebView) findViewById(R.id.webview_homeweb);
        favoriteImg = (ImageView) findViewById(R.id.favorite_img);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mcontentLoadingProgressBar = (ContentLoadingProgressBar) findViewById(R.id.contentLoadingProgressBar);
        mToolbar.setTitle("详情");
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);


        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mcontentLoadingProgressBar.setVisibility(View.INVISIBLE);
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient());
        // 开启js脚本支持
        mWebView.getSettings().setJavaScriptEnabled(true);
        // 加载网页
        mWebView.loadUrl(detailUrl);

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
                        ToastUtils.showToast(HomeWebActivity.this, "收藏");
                        AVObject ganhuo_table = new AVObject("Ganhuo_table");
                        // 加入所有者的id字段
                        ganhuo_table.put("gank_owner_id", AVUser.getCurrentUser().getObjectId());
                        // 加入所有者的名字
                        ganhuo_table.put("gank_owner_name", AVUser.getCurrentUser().getUsername());
                        ganhuo_table.put("gank_title", title);
                        // 用网址的唯一性
                        ganhuo_table.put("gank_detail", detailUrl);
                        ganhuo_table.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(AVException e) {
                                if (e == null) {
                                    ToastUtils.showToast(HomeWebActivity.this, "保存完成");
                                } else {
                                    ToastUtils.showToast(HomeWebActivity.this, e.getMessage());
                                }
                            }
                        });

                    } else {

                        // TODO　进行取消收藏
                        favoriteImg.setSelected(false);
                        ToastUtils.showToast(HomeWebActivity.this, "不收藏");

                        AVQuery<AVObject> avQuery = new AVQuery<>("Ganhuo_table");
                        avQuery.orderByDescending("createdAt");
                        // 用户要对应
                        avQuery.whereEqualTo("gank_owner_id", AVUser.getCurrentUser().getObjectId());
                        // 加入删除的判断条件，这里使用网址作为判断依据
                        avQuery.whereEqualTo("gank_detail",detailUrl);
                        avQuery.findInBackground(new FindCallback<AVObject>() {
                            @Override
                            public void done(List<AVObject> list, AVException e) {
                                if (e == null) {
                                    for (int i = 0; i < list.size(); i++) {
                                        // TODO 删除符合条件的数据
                                        list.get(i).deleteInBackground();
                                    }
                                } else {
                                    ToastUtils.showToast(HomeWebActivity.this, "取消收藏失败");
                                    e.printStackTrace();
                                }
                            }
                        });


                    }
                } else {
                    // 对话框提示
                    AlertDialog.Builder builder = new AlertDialog.Builder(HomeWebActivity.this);
                    builder.setIcon(R.drawable.avatar_img)
                            .setTitle("还未登陆")
                            .setMessage("是否现在登陆?")
                            .setPositiveButton("现在就去", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(HomeWebActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    HomeWebActivity.this.finish();
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
            AVQuery<AVObject> avQuery = new AVQuery<>("Ganhuo_table");
            avQuery.orderByDescending("createdAt");
            avQuery.include("owner");
            // 加入判断条件，这里使用网址作为判断依据
            avQuery.whereEqualTo("gank_detail",detailUrl);
            // 用户要对应
            avQuery.whereEqualTo("gank_owner_id", AVUser.getCurrentUser().getObjectId());
            avQuery.findInBackground(new FindCallback<AVObject>() {
                @Override
                public void done(List<AVObject> list, AVException e) {
                    if (e == null) {
                        if (list.size() == 0) {
                            ToastUtils.showToast(HomeWebActivity.this, "还没有收藏");
                            return;
                        }
                        // 设置图片为选中状态
                        favoriteImg.setSelected(true);
                    } else {
                        ToastUtils.showToast(HomeWebActivity.this, "查询失败");
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
