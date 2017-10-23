package com.example.dell.jd_demo.actvity.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.dell.jd_demo.R;
import com.example.dell.jd_demo.actvity.adapter.Logadapter;
import com.example.dell.jd_demo.actvity.fragment.Fragment_1;
import com.example.dell.jd_demo.actvity.fragment.Fragment_2;
import com.example.dell.jd_demo.actvity.fragment.Fragment_3;
import com.example.dell.jd_demo.actvity.fragment.Fragment_5;
import com.example.dell.jd_demo.actvity.fragment.GouwucheFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements  ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {
    private long exitTime = 0;
    private RadioButton rb_shouye;
    private RadioButton rb_fenlei;
    private RadioButton rb_find;
    private RadioButton rb_shopingcar;
    private RadioButton rb_geren;
    private RadioGroup radio;
    private ViewPager viewPage;

    private List<Fragment> fragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        addlist();
        Logadapter logadapter=new Logadapter(getSupportFragmentManager(),fragments);
        viewPage.setAdapter(logadapter);
        viewPage.setOnPageChangeListener(this);
        radio.setOnCheckedChangeListener(this);
    }


    public  void initView(){
        rb_shouye= (RadioButton) findViewById(R.id.rb_shouye);
        rb_fenlei= (RadioButton)  findViewById(R.id.rb_fenlei);
        rb_find= (RadioButton)  findViewById(R.id.rb_find);
        rb_shopingcar= (RadioButton)  findViewById(R.id.rb_shopingcar);
        rb_geren= (RadioButton)  findViewById(R.id.rb_geren);
        radio= (RadioGroup) findViewById(R.id.radio);
        viewPage= (ViewPager) findViewById(R.id.viewPage);
    }
    public void addlist(){
        fragments=new ArrayList<>();
        fragments.add(new Fragment_1());
        fragments.add(new Fragment_2());
        fragments.add(new Fragment_3());
        fragments.add(new GouwucheFragment());
        fragments.add(new Fragment_5());
    }
    /*
     *连按两次退出程序
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-exitTime) > 2000){
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.rb_shouye:
                viewPage.setCurrentItem(0);
                break;
            case R.id.rb_fenlei:
                viewPage.setCurrentItem(1);
                break;
            case R.id.rb_find:
                viewPage.setCurrentItem(2);
                break;
            case R.id.rb_shopingcar:
                viewPage.setCurrentItem(3);
                break;
            case R.id.rb_geren:
                viewPage.setCurrentItem(4);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position){
            case 0:
                radio.check(R.id.rb_shouye);
                break;
            case 1:
                radio.check(R.id.rb_fenlei);
                break;
            case 2:
                radio.check(R.id.rb_find);
                break;
            case 3:
                radio.check(R.id.rb_shopingcar);
                break;
            case 4:
                radio.check(R.id.rb_geren);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
