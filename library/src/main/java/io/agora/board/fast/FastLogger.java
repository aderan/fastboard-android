package io.agora.board.fast;

import android.util.Log;

class FastLogger {
    private final static String LOG_TAG = "Fastboard";

    static void debug(String msg) {
        Log.d(LOG_TAG, msg);
    }

    static void info(String msg) {
        Log.i(LOG_TAG, msg);
    }

    static void warn(String msg) {
        Log.w(LOG_TAG, msg);
    }

    static void error(String msg) {
        error(msg, null);
    }

    static void error(String msg, Throwable throwable) {
        Log.e(LOG_TAG, msg, throwable);
    }
}
