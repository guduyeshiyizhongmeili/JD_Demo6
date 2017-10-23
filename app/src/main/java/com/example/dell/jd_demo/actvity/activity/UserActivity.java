package com.example.dell.jd_demo.actvity.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.jd_demo.R;

public class UserActivity extends AppCompatActivity {

    private TextView username;
    private TextView over;
    private  TextView num;
    private SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        username= (TextView) findViewById(R.id.username);
        num= (TextView) findViewById(R.id.num);
        over= (TextView) findViewById(R.id.over);
        sp = getSharedPreferences("User", MODE_PRIVATE);
        boolean islog = sp.getBoolean("islog", false);
        String name = sp.getString("username", "未登录");
        String number = sp.getString("num", "未登录");
        if(islog){
            username.setText(number);
            num.setText(name);
            over.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences.Editor edit = sp.edit();
                    SharedPreferences.Editor islog1 = edit.putBoolean("islog", false);
                    islog1.commit();
                    finish();
                }
            });
        }else{
            Toast.makeText(this,"请先登录",Toast.LENGTH_LONG).show();
            over.setText("去登录");
            over.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(UserActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }


    }
}

