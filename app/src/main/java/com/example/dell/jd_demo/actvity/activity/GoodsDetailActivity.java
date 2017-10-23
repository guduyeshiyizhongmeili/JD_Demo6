package com.example.dell.jd_demo.actvity.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.dell.jd_demo.R;
import com.example.dell.jd_demo.actvity.fragment.PingJia;
import com.example.dell.jd_demo.actvity.fragment.ShangPin;
import com.example.dell.jd_demo.actvity.fragment.XiangQing;

import java.util.ArrayList;
import java.util.List;

public class GoodsDetailActivity extends FragmentActivity{


    private List<Fragment> list=new ArrayList<>();

    private String[] titles = {"商品", "详情", "评价"};
    private ImageView mImageView;
    private TabLayout mTablayout;
    private ImageView mImageView2;
    private ImageView mImageView1;
    private LinearLayout mLin;
    private ViewPager mVp;
    private RelativeLayout mActivityGoodsDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
        mVp = (ViewPager) findViewById(R.id.vp);
       mTablayout= (TabLayout) findViewById(R.id.tablayout);

        list.add(new ShangPin());
        list.add(new XiangQing());
        list.add(new PingJia());

      mVp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
          @Override
          public Fragment getItem(int position) {
              return list.get(position);
          }

          @Override
          public int getCount() {
              System.out.println(list.size()+"");
              return list.size();



          }

          @Override
          public CharSequence getPageTitle(int position) {
              System.out.println(titles[position]+"");
              return titles[position];
          }
      });

mTablayout.setupWithViewPager(mVp);


    }



}
