package com.example.pinexample.utilities;

/**
 * Override of the android utility Log class
 * @author drakuwa
 *
 */
public class Log {

    private static boolean isEnabled = true;
    public static String TAG = "TestApp";

    public static void d(String tag, String message) {
        if (isEnabled) {
            android.util.Log.d(tag, message);

        }
    }

    public static void i(String tag, String message) {
        if (isEnabled) {
            android.util.Log.i(tag, message);
        }
    }

    public static void e(String tag, String message) {
        if (isEnabled) {
            android.util.Log.e(tag, message);
        }
    }

    public static void v(String tag, String message) {
        if (isEnabled) {
            android.util.Log.v(tag, message);
        }
    }

    public static void w(String tag, String message) {
        if (isEnabled) {
            android.util.Log.w(tag, message);
        }
    }
}
