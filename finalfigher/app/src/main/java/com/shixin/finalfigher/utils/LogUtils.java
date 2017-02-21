package com.shixin.finalfigher.utils;

import android.util.Log;

/**
 * Created by mio on 2017/2/21.
 */

public class LogUtils {
    /**
     * 上架的时候把这个值改成false
     */
    public static final boolean isDebugging = true;

    public static void v(String tag, String msg) {
        if (isDebugging) {
            Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (isDebugging) {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (isDebugging) {
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (isDebugging) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (isDebugging) {
            Log.e(tag, msg);
        }
    }
}
