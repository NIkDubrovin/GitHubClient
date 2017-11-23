package com.nikdubrovin.GithubProjects.common.utils;

import android.content.Context;

import com.nikdubrovin.GithubProjects.BuildConfig;
import com.nikdubrovin.GithubProjects.data.StorageClass;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * Created by NikDubrovin on 16.09.2017.
 */

public class GLog {
    private static final String TAG = "GithubClient";
    private static GLog sGLog;

    /**
     * initialize the logger.
     */
    public GLog() {

        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(2)         // (Optional) How many method line to show. Default 2
//              .methodOffset(3)        // (Optional) Skips some method invokes in stack trace. Default 5
//              .logStrategy(customLog) // (Optional) Changes the log strategy to print out. Default LogCat
                .tag(TAG)   // (Optional) Custom tag for each log. Default PRETTY_LOGGER
                .build();


        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });
    }

    public static GLog get(){
        if(sGLog == null){
            sGLog = new GLog();
        }
        return sGLog;
    }

    /**
     * log.i
     *
     * @param msg
     */
    public static void i(String msg) {
        if(BuildConfig.DEBUG)
        Logger.i(msg);
    }

    public static void i(int imsg) {
        if(BuildConfig.DEBUG)
            Logger.i(String.valueOf(imsg));
    }

    public static <T> void i(T t){
        if(BuildConfig.DEBUG)
            Logger.i(t.toString());
    }

    /**
     * log.d
     *
     * @param msg
     */
    public static void d(String msg) {
        if(BuildConfig.DEBUG)
        Logger.d(msg);
    }

    /**
     * log.w
     *
     * @param msg
     */
    public static void w(String msg) {
        if(BuildConfig.DEBUG)
        Logger.w(msg);
    }

    /**
     * log.e
     *
     * @param msg
     */
    public static void e(String msg) {
        if(BuildConfig.DEBUG)
        Logger.e(msg);
    }

    public static void e(Throwable e) {
        if(BuildConfig.DEBUG)
        Logger.e(e, "");
    }

    /**
     * @param msg
     */
    public static void json(String msg) {
        if(BuildConfig.DEBUG)
        Logger.json(msg);
    }

    /**
     * @param msg
     */
    public static void xml(String msg) {
        if(BuildConfig.DEBUG)
        Logger.xml(msg);
    }
}
