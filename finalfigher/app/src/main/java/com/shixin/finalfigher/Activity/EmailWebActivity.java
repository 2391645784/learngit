package com.shixin.finalfigher.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.shixin.finalfigher.R;

/**
 * 查看邮箱页面
 */
public class EmailWebActivity extends AppCompatActivity {

    private WebView mEmailWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_web);
        mEmailWebView = (WebView) findViewById(R.id.email_webView);
        mEmailWebView.setWebViewClient(new WebViewClient());
        mEmailWebView.setWebChromeClient(new WebChromeClient());
        mEmailWebView.getSettings().setJavaScriptEnabled(true);
        mEmailWebView.loadUrl("https://mail.qq.com");
    }
}
