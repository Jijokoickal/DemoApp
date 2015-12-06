package com.costrategix.survey.log;

import android.util.Log;

import com.costrategix.survey.BuildConfig;
import com.costrategix.survey.constants.Constants;

/**
 * Application Log class
 */
public class AppLog {
    // Application Tag

    // Log a String
    public static void logString(String message) {
        // If log is enabled, the print the log
        if (Constants.IS_LOG_ENABLED && BuildConfig.DEBUG) {
            Log.i(Constants.APP_TAG, " " + message);
        }
    }
}