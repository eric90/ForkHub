package com.github.mobile.aspectj;

import android.util.Log;

/**
 * Created by Prateek on 2/12/2017.
 */

public class DebugLog {

    private DebugLog() {}

    /**
     * Send a debug log message
     *
     * @param tag Source of a log message.
     * @param message The message you would like logged.
     */
    public static void log(String tag, String message) {
        Log.d(tag, message);
    }
}