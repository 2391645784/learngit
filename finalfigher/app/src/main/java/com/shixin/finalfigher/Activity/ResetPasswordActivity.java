package com.shixin.finalfigher.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.RequestPasswordResetCallback;
import com.shixin.finalfigher.R;
import com.shixin.finalfigher.utils.EmailUtils;
import com.shixin.finalfigher.utils.ToastUtils;

/**
 * 重置密码页面
 */
public class ResetPasswordActivity extends AppCompatActivity {

    private EditText mEmailEt;
    private Button mResetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        // 邮箱输入框
        mEmailEt = (EditText) findViewById(R.id.email_editText_reset);
        mResetButton = (Button) findViewById(R.id.reset_button);
        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmailEt.getText().toString().trim();
                if(TextUtils.isEmpty(email)){
                    ToastUtils.showToast(ResetPasswordActivity.this,"邮箱不能为空");
                } else {
                    resetPassword(email);
                }

            }
        });

    }
    private void resetPassword(String email){
        AVUser.requestPasswordResetInBackground(email, new RequestPasswordResetCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {
                    ToastUtils.showToast(ResetPasswordActivity.this,"发送成功");
                    Intent intent = new Intent(ResetPasswordActivity.this, EmailWebActivity.class);
                    startActivity(intent);
                } else {
                    ToastUtils.showToast(ResetPasswordActivity.this,"邮箱未注册或邮件达到上限");
                    e.printStackTrace();
                }
            }
        });
    }
}
