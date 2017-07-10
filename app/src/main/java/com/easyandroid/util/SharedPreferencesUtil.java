package com.easyandroid.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by 三臻 on 2017/6/30.
 */

public class SharedPreferencesUtil {
    /**
     * 保存
     * @param context
     * @param key
     * @param value
     */
    public static void put(Context context, String key, String value) {
        //实例化SharedPreferences对象（第一步）
        SharedPreferences mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        //实例化SharedPreferences.Editor对象（第二步）
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        //用putString的方法保存数据
        editor.putString(key, value);
        //提交当前数据
        editor.commit();
    }

    /**
     * 读取
     * @param context
     * @param key
     * @return
     */
    public static String get(Context context, String key) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(key,"");
    }
}
