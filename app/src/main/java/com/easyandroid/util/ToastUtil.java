package com.easyandroid.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by 三臻 on 2017/6/29.
 */

public class ToastUtil {
    public static void makeToastShort(Context context, String msg){
        Toast.makeText(context,msg, Toast.LENGTH_SHORT).show();
    }
}
