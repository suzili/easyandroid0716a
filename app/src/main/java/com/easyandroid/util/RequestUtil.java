package com.easyandroid.util;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created by MrH on 2017/7/10.
 */

public class RequestUtil<T> {

    private Handler handler;

    public interface RequestCallback {
        void before();

        void success(String res);

        void error(Exception e);
    }

    private HttpUtil httpUtil;
    private String url;

    public RequestUtil(String url) {
        this.httpUtil = HttpUtil.getInstance();
        this.url = url;
        handler = new Handler(Looper.getMainLooper());
    }

    public void conn(T params, Class<T> tClass, final RequestCallback requestCallback) {
        String json = new Gson().toJson(params, tClass);
        before(requestCallback);
        httpUtil.postJson(url, json, new HttpUtil.PostCallBack() {
            @Override
            public void success(String res) {
                requestCallback.success(res);
            }

            @Override
            public void error(Exception e) {
                requestCallback.error(e);
            }
        });
    }

    private void before(final RequestCallback requestCallback) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                requestCallback.before();
            }
        });
    }

    public void conn(Map<String, String> params, Map<String, File> files, final RequestCallback requestCallback) {
        before(requestCallback);
        httpUtil.postForm(url, params, files, new HttpUtil.PostCallBack() {
            @Override
            public void success(String res) {
                requestCallback.success(res);
            }

            @Override
            public void error(Exception e) {
                requestCallback.error(e);
            }
        });
    }

}
