package com.example.dell.jd_demo.actvity.utlis;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 刘海峰.19:23
 */

public class Http {
    public void getdata(String url, final NetDataCallback netDataCallback) {
        //初始化一个 OkHttpClient 并且设置连接和读取超时时间
        OkHttpClient okhttp = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
        //构造一个Request对象
        Request request = new Request.Builder().url(url).build();
        //通过request的对象去构造得到一个Call对象
        Call call=okhttp.newCall(request);
        //调用的是call.enqueue，将call加入调度队列，然后等待任务执行完成，我们在Callback中即可得到结果。
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                netDataCallback.err(500,e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String  s = response.body().string();
                netDataCallback.callback(s);
            }
        });
    }
}
