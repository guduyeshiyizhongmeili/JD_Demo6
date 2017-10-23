package com.example.dell.jd_demo.actvity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dell.jd_demo.R;
import com.example.dell.jd_demo.actvity.activity.MainActivity;

/**
 * 欢迎页面实现三秒跳转到主页
 */
public class LoginActivity extends AppCompatActivity {
    private int count=0;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            handler.sendEmptyMessageDelayed(1,1000);
            count++;
            if(count==3){
                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        handler.sendEmptyMessageDelayed(1,1000);
    }
}
