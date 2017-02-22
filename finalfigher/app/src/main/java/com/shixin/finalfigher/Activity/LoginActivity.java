package com.shixin.finalfigher.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.shixin.finalfigher.R;
import com.shixin.finalfigher.utils.ToastUtils;

/**
 * 登陆页面
 */
public class LoginActivity extends AppCompatActivity {

    private EditText mUsernameEt;
    private EditText mPasswordEt;
    private Button mLoginButton;
    private TextView mRegisterTv;
    private TextView mForgetPasswordTv;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_login);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("登陆");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        mUsernameEt = (EditText) findViewById(R.id.username_editText_login);
        mPasswordEt = (EditText) findViewById(R.id.password_editText_login);
        mLoginButton = (Button) findViewById(R.id.login_button);
        mRegisterTv = (TextView) findViewById(R.id.register_textview_login);
        mForgetPasswordTv = (TextView) findViewById(R.id.forgetpassword_textview);
        mRegisterTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO　登陆
                String username = mUsernameEt.getText().toString().trim();
                String password = mPasswordEt.getText().toString().trim();
                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                    ToastUtils.showToast(LoginActivity.this, "账号或密码不能为空");
                } else {
                    userLoginEvent(username, password);
                }

            }
        });

        mForgetPasswordTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO 忘记密码
                Intent intent = new Intent(LoginActivity.this, ResetPasswordActivity.class);
                startActivity(intent);
            }
        });

    }

    private void userLoginEvent(String username, String password) {
        AVUser.logInInBackground(username, password, new LogInCallback<AVUser>() {
            @Override
            public void done(AVUser avUser, AVException e) {
                if (e == null) {
                    String currentUsername = AVUser.getCurrentUser().getUsername();
                    ToastUtils.showToast(LoginActivity.this, "当前用户"+currentUsername);

                    Intent intent = new Intent("com.shixin.personfragment");
                    // 发送广播
                    LocalBroadcastManager.getInstance(LoginActivity.this).sendBroadcast(intent);
                    finish();
                } else {
                    ToastUtils.showToast(LoginActivity.this, e.getMessage());
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
