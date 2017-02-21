package com.shixin.finalfigher.Activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SignUpCallback;
import com.shixin.finalfigher.R;
import com.shixin.finalfigher.utils.ToastUtils;

/**
 * 注册页面
 */
public class RegisterActivity extends AppCompatActivity {

    private EditText mUsernameEt;
    private EditText mPasswordEt;
    private EditText mEmailEt;
    private Button mRegisnterButton;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_register);
        setSupportActionBar(mToolbar);
        ActionBar actionbar=getSupportActionBar();
        actionbar.setTitle("注册");
        actionbar.setHomeButtonEnabled(true);
        actionbar.setDisplayHomeAsUpEnabled(true);

        mUsernameEt = (EditText) findViewById(R.id.username_editText_register);
        mPasswordEt = (EditText) findViewById(R.id.password_editText_register);
        mEmailEt = (EditText) findViewById(R.id.email_editText_register);
        mRegisnterButton = (Button) findViewById(R.id.register_button);

        mRegisnterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mUsernameEt.getText().toString().trim();
                String password = mPasswordEt.getText().toString().trim();
                String email = mEmailEt.getText().toString().trim();
                userRegisterEvent(username,password,email);

            }
        });
    }

    /**
     * 用户注册事件
     * @param username 用户名
     * @param password 密码
     * @param email 邮箱
     */
    private void userRegisterEvent(String username,String password,
                                   String email){
        AVUser user = new AVUser();// 新建 AVUser 对象实例
        user.setUsername(username);// 设置用户名
        user.setPassword(password);// 设置密码
        user.setEmail(email);//设置邮箱
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {
                    // 注册成功，把用户对象赋值给当前用户 AVUser.getCurrentUser()
                    RegisterActivity.this.finish();



                } else {
                    // 失败的原因可能有多种，常见的是用户名已经存在。
                    ToastUtils.showToast(RegisterActivity.this,e.getMessage());
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
