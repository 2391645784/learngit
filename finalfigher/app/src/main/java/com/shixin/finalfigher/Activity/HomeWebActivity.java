package com.shixin.finalfigher.Activity;

import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.shixin.finalfigher.R;

public class HomeWebActivity extends AppCompatActivity {

    private WebView mWebView;
    private Toolbar mToolbar;
    private ContentLoadingProgressBar mcontentLoadingProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_web);
        mWebView = (WebView) findViewById(R.id.webview_homeweb);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mcontentLoadingProgressBar = (ContentLoadingProgressBar) findViewById(R.id.contentLoadingProgressBar);
        mToolbar.setTitle("详情");
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        mWebView.setWebViewClient(new WebViewClient(){
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
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
