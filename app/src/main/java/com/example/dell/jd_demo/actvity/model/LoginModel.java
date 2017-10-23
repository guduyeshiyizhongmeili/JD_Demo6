package com.example.dell.jd_demo.actvity.model;

import android.os.Handler;

import com.example.dell.jd_demo.actvity.bean.UserBean;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by dell on 2017/10/7.
 */
public class LoginModel {
    private Handler handler=new Handler();
    public void login(String mobile, String pwd,String url){
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("mobile",mobile);
        builder.add("password",pwd);
        FormBody body = builder.build();

        Request request = new Request.Builder().post(body).url(url).build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(final okhttp3.Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iLogin.failure(call,e);
                    }
                });
            }
            @Override
            public void onResponse(final okhttp3.Call call, final Response response) throws IOException {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (response!=null&&response.isSuccessful()) {
                            String result = null;
                            try {
                                result = response.body().string();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Gson gson=new Gson();
                            UserBean userBean = gson.fromJson(result, UserBean.class);
                            //走解析逻辑，解析完成走如下逻辑
                            if (userBean.getCode().equals("0")) {
                                iLogin.loginSuccess(userBean.getCode(), userBean.getMsg(),userBean.getData().getUid()+"",userBean.getData().getNickname()+"",userBean.getData().getUsername());
                            } else {
                                iLogin.loginFail(userBean.getCode(),userBean.getMsg());
                            }
                        }
                    }
                });

            }
        });
    }
    private ILogin iLogin;
    /**
     * 对外暴露的回调接口
     * @param iLogin
     */
    public void setiLogin(ILogin iLogin) {
        this.iLogin = iLogin;
    }

    public interface ILogin{
        void loginSuccess(String code,String msg,String uid,String num,String username);
        void loginFail(String code,String msg);
        void failure(okhttp3.Call call, IOException e);
    }
}
