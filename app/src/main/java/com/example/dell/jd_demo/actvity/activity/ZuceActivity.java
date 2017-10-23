package com.example.dell.jd_demo.actvity.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dell.jd_demo.R;
import com.example.dell.jd_demo.actvity.net.Api;
import com.example.dell.jd_demo.actvity.view.LoginView;

import java.io.IOException;

import okhttp3.Call;

public class ZuceActivity extends AppCompatActivity implements LoginView {
    private EditText name;
    private EditText pwd;
    private Button zuce;
    private String url= Api.ZUCE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zuce);
        name= (EditText) findViewById(R.id.zuce_name);
        pwd= (EditText) findViewById(R.id.zuce_pwd);
        zuce= (Button) findViewById(R.id.zuce_zuce);
        zuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ZuceActivity.this,"注册成功",Toast.LENGTH_LONG).show();
                finish();

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
        Toast.makeText(ZuceActivity.this,msg,Toast.LENGTH_LONG).show();

    }

    @Override
    public void passError(String msg) {
        Toast.makeText(ZuceActivity.this,msg,Toast.LENGTH_LONG).show();

    }

    @Override
    public void loginsccess(String code, String msg, String uid, String num, String username) {

    }


    @Override
    public void loginfinl(String code, String msg) {

    }

    @Override
    public void failure(Call call, IOException e) {

    }
}
