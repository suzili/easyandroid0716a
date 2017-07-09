package com.easyandroid;

import android.app.Application;
import android.content.Context;

/**
 * Created by MrH on 2017/7/9.
 */

public class EasyAndroidApplication extends Application{

    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
