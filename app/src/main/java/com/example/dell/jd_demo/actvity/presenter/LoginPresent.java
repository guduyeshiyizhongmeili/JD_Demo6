package com.example.dell.jd_demo.actvity.presenter;


import android.text.TextUtils;

import com.example.dell.jd_demo.actvity.model.LoginModel;
import com.example.dell.jd_demo.actvity.view.LoginView;

import java.io.IOException;

import okhttp3.Call;

/**
 * Created by dell on 2017/10/17.
 */
public class LoginPresent implements LoginModel.ILogin{
    private LoginModel loginModel;
    private LoginView loginView;

    public LoginPresent(LoginView loginView) {
        this.loginView = loginView;
        loginModel=new LoginModel();
        loginModel.setiLogin(this);
    }
    public void login(String mobile,String pass,String url){
        loginView.showProgressbar();
        if(TextUtils.isEmpty(mobile)){
            loginView.nameError("账户不能为空");
        }
        if(TextUtils.isEmpty(pass)){
            loginView.passError("密码不能为空");
        }
        loginModel.login(mobile,pass,url);
    }

    @Override
    public void loginSuccess(String code, String msg,String uid,String num,String username) {
           loginView.loginsccess(code,msg,uid,num,username);
           loginView.hindProgressbar();
    }

    @Override
    public void loginFail(String code, String msg) {
        loginView.loginfinl(code,msg);
        loginView.hindProgressbar();
    }

    @Override
    public void failure(Call call, IOException e) {
        loginView.failure(call,e);
        loginView.hindProgressbar();
    }
}
