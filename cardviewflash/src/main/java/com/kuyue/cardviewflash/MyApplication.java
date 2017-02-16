package com.kuyue.cardviewflash;

import android.app.Application;
import android.content.Context;

/**
 * Created by sen young on 2017/2/16 17:51.
 * 邮箱:595327086@qq.com.
 */

public class MyApplication extends Application {

    private Context mContext;
    private static MyApplication sMyApplication;

    public static MyApplication getMyApplication() {
        return sMyApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        sMyApplication = this;
    }

    public Context getContext() {
        return mContext;
    }
}
