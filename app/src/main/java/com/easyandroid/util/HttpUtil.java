package com.easyandroid.util;

import android.os.Handler;
import android.os.Looper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
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

    /**
     * 向服务器端传输数据json数据，并以json格式返回
     * @param url 请求地址 ，json 请求的json参数 ，postCallBack 回掉对象
     * @return 没有返回值
     */
    public void postJson(String url, String json, final PostCallBack postCallBack){
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
                if (response.isSuccessful()) {
                    final String res = response.body().string();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            postCallBack.success(res);
                        }
                    });
                } else {
                    this.onFailure(call, new IOException("数据传输失败"));
                }
            }
        });
    }


    /**
     * 向服务器端传输数据表单格式数据，并以json格式返回
     * @param url 请求地址 ，params 请求的键值对普通参数 ，files 请求的键值对文件参数
     * @return 没有返回值
     */
    public void postForm(final String url, final Map<String, String> params, Map<String, File> files, final PostCallBack postCallBack) {

        MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);

        for (Map.Entry<String, File> file : files.entrySet()) {
            RequestBody body = RequestBody.create(MediaType.parse("*/*"), file.getValue());
            requestBody.addFormDataPart(file.getKey(), file.getValue().getName(), body);
        }

        for (Map.Entry<String, String> param : params.entrySet()) {
            requestBody.addFormDataPart(param.getKey(), param.getValue());
        }

        Request request = new Request.Builder().url(url).post(requestBody.build()).build();
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
                if (response.isSuccessful()) {
                    final String res = response.body().string();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            postCallBack.success(res);
                        }
                    });
                } else {
                    this.onFailure(call, new IOException("数据传输失败"));
                }
            }
        });
    }

}
