package com.shixin.finalfigher.Activity;

import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.ActionBar;
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

import com.shixin.finalfigher.R;
import com.shixin.finalfigher.utils.ToastUtils;

public class HomeWebActivity extends AppCompatActivity {

    private WebView mWebView;
    private Toolbar mToolbar;
    private ContentLoadingProgressBar mcontentLoadingProgressBar;
    private ImageView favoriteImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_web);
        mWebView = (WebView) findViewById(R.id.webview_homeweb);
        favoriteImg = (ImageView) findViewById(R.id.favorite_img);
        favoriteImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!favoriteImg.isSelected()) {
                    favoriteImg.setSelected(true);
                    ToastUtils.showToast(HomeWebActivity.this, "收藏");
                } else {

                    favoriteImg.setSelected(false);
                    ToastUtils.showToast(HomeWebActivity.this, "不收藏");
                }
            }
        });
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
        String url = getIntent().getStringExtra("homedetail");
        mWebView.loadUrl(url);

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
