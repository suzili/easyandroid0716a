package com.easyandroid.util;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by MrH on 2017/7/8.
 */

public class HttpUtil {
    private static HttpUtil httpUtil;

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private OkHttpClient client;

    private Handler handler;

    public interface PostCallBack {
        void success(String res);

        void error(Exception e);
    }


    private HttpUtil() {
        client = new OkHttpClient();
        handler = new Handler(Looper.getMainLooper());
    }

    public static HttpUtil getInstance() {
        if (httpUtil == null)
            httpUtil = new HttpUtil();
        return httpUtil;
    }

    public void postJson(String url, String json, final PostCallBack postCallBack) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        postCallBack.error(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String res = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        postCallBack.success(res);
                    }
                });
            }
        });
    }



}
