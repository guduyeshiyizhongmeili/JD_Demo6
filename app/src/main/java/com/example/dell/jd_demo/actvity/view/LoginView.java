package com.example.dell.jd_demo.actvity.view;

import java.io.IOException;

import okhttp3.Call;

/**
 * Created by dell on 2017/10/17.
 */
public interface LoginView {
    void showProgressbar();
    void hindProgressbar();
    void nameError(String msg);
    void passError(String msg);
    void loginsccess(String code, String msg, String uid, String num, String username);
    void loginfinl(String code, String msg);
    void failure(Call call, IOException e);
}
