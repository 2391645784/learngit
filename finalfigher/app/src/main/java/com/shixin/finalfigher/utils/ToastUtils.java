package com.shixin.finalfigher.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {
    /**
     * 防止多次点击重复弹出吐司
     */
    private static Toast toast;
    public static void showToast(Context context, String content) {
        if (toast == null) {
            toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }
}
