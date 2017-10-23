package com.example.dell.jd_demo.actvity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.example.dell.jd_demo.R;

public class MsActivity extends AppCompatActivity {
private WebView web_ms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ms);
        Intent intent = getIntent();
        String ms = intent.getStringExtra("ms");
        web_ms= (WebView) findViewById(R.id.web_ms);
        web_ms.loadUrl(ms);


    }
}
