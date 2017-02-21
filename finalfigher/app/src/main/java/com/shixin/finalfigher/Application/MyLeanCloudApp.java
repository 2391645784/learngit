package com.shixin.finalfigher.Application;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;

/**
 * Created by mio on 2017/2/21.
 */

public class MyLeanCloudApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this,"v0c8IBuTCBm8Fc4JtJiWt7TL-gzGzoHsz","dNCryGp5zAQF9xAwWrSsK2O3");
    }
}
