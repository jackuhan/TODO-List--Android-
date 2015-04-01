package com.simpletodo.util;

import android.util.Log;

import com.example.simpletodo.BuildConfig;

/**
 * log
 */
public class LogUtil {
    private static final String TAG = "Tests ";
    private static final boolean DEBUG = BuildConfig.DEBUG;


    //======================================= log.e =========================================//
    public static void e(String tag, String err) {
        if (DEBUG) {
            Log.e(TAG + tag, err);
        }
    }

    public static void e(String tag, String debug, Throwable e) {
        if (DEBUG) {
            Log.e(TAG + tag, debug, e);
        }
    }

//    public static void e(String msg) {
//        if (DEBUG) {
//            Log.e(TAG, msg);
//        }
//    }

    //======================================= log.d =========================================//
    public static void d(String tag, String debug) {
        if (DEBUG) {
            Log.d(TAG + tag, debug);
        }
    }

    public static void d(String tag, String debug, Throwable e) {
        if (DEBUG) {
            Log.d(TAG + tag, debug, e);
        }
    }

//    public static void d(String msg) {
//        if (DEBUG) {
//            Log.d(TAG, msg);
//        }
//    }

    //======================================= log.i =========================================//
    public static void i(String tag, String info) {
        if (DEBUG) {
            Log.i(TAG + tag, info);
        }
    }

    public static void i(String tag, String debug, Throwable e) {
        if (DEBUG) {
            Log.i(TAG + tag, debug, e);
        }
    }

//    public static void i(String msg) {
//        if (DEBUG) {
//            Log.i(TAG, msg);
//        }
//    }

    //======================================= log.w =========================================//
    public static void w(String tag, String info) {
        if (DEBUG) {
            Log.w(TAG + tag, info);
        }
    }

    public static void w(String tag, String debug, Throwable e) {
        if (DEBUG) {
            Log.w(TAG + tag, debug, e);
        }
    }

//    public static void w(String msg) {
//        if (DEBUG) {
//            Log.w(TAG, msg);
//        }
//    }

    //======================================= log.v =========================================//
    public static void v(String tag, String err) {
        if (DEBUG) {
            Log.v(TAG + tag, err);
        }
    }

    public static void v(String tag, String debug, Throwable e) {
        if (DEBUG) {
            Log.v(TAG + tag, debug, e);
        }
    }

//    public static void v(String msg) {
//        if (DEBUG) {
//            Log.v(TAG, msg);
//        }
//    }

    // ===================================== log.exception =======================================//
    public static void logException(Throwable e) {
        if (DEBUG) {
            e.printStackTrace();
        }
    }
}
