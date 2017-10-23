package com.example.dell.jd_demo.actvity.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.jd_demo.R;
import com.example.dell.jd_demo.actvity.model.LoginModel;
import com.example.dell.jd_demo.actvity.net.Api;
import com.example.dell.jd_demo.actvity.presenter.LoginPresent;
import com.example.dell.jd_demo.actvity.view.LoginView;

import java.io.IOException;

import okhttp3.Call;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private EditText loginname;
    private EditText loginpwd;
    private TextView zuce;
    private Button login;
    private String url= Api.DENGLU;
    private LoginModel mLoginModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        loginname= (EditText) findViewById(R.id.loginname);
        loginpwd= (EditText) findViewById(R.id.loginpwd);
        zuce= (TextView) findViewById(R.id.zuce);
        login= (Button) findViewById(R.id.login);
        zuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten=new Intent(LoginActivity.this,ZuceActivity.class);
                startActivity(inten);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginPresent loginPresent=new LoginPresent(LoginActivity.this);
                loginPresent.login(loginname.getText().toString(),loginpwd.getText().toString(),Api.DENGLU);
            }
        });
    }


    @Override
    public void showProgressbar() {

    }

    @Override
    public void hindProgressbar() {

    }

    @Override
    public void nameError(String msg) {
        Toast.makeText(LoginActivity.this,msg,Toast.LENGTH_LONG).show();

    }

    @Override
    public void passError(String msg) {
        Toast.makeText(LoginActivity.this,msg,Toast.LENGTH_LONG).show();

    }

    @Override
    public void loginsccess(String code, String msg, String uid,String num,String username) {
        SharedPreferences sp=getSharedPreferences("User",MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean("islog",true);
        edit.putString("uid",uid);
        edit.putString("num",num);
        edit.putString("username",username);
        edit.commit();
        finish();
    }

    @Override
    public void loginfinl(String code, String msg) {
        Toast.makeText(LoginActivity.this,msg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void failure(Call call, IOException e) {
        Toast.makeText(LoginActivity.this,"请检查网络",Toast.LENGTH_LONG).show();

    }
}
