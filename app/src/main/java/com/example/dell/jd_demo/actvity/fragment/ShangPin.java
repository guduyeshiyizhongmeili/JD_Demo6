package com.example.dell.jd_demo.actvity.fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.jd_demo.R;
import com.example.dell.jd_demo.actvity.adapter.ListAdapter;
import com.example.dell.jd_demo.actvity.bean.BaseBean;
import com.example.dell.jd_demo.actvity.bean.ListBean;
import com.example.dell.jd_demo.actvity.bean.XqBean;
import com.example.dell.jd_demo.actvity.net.Api;
import com.example.dell.jd_demo.actvity.utlis.GlideImageLaoder;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * 刘海峰.16:14
 */

public class ShangPin  extends Fragment {
    private String uid="160";
    private List<String> mListImage;
    private List<String> mListTitle;
    private String gscid="1";
    private String url= Api.XQ;
    private String pid;
    private RadioButton xq_lx;
    private RadioButton xq_sj;
    private RadioButton xq_dp;
    private TextView xq_addcar;
    private Banner xq_banner;
    private TextView xq_title;
    private TextView xq_price;
    private TextView xq_subhead;
    private ImageView xq_back;
    private ImageView xq_fx;
    private RecyclerView xq_RecyclerView;
    private View v;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         v=View.inflate(getActivity(), R.layout.shangpin,null);
        Intent intent = getActivity().getIntent();
        pid = intent.getStringExtra("pid");
        initView();
        getData();
        getdata();
        xq_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              getActivity().  finish();
            }
        });

        return  v;
    }
    public void initView(){
        xq_lx= (RadioButton) v.findViewById(R.id.xq_lx);
        xq_sj= (RadioButton) v.findViewById(R.id.xq_sj);
        xq_dp= (RadioButton)v. findViewById(R.id.xq_dp);
        xq_addcar= (TextView) v.findViewById(R.id.xq_addcar);
        xq_banner= (Banner) v.findViewById(R.id.xq_banner);
        xq_title= (TextView)v. findViewById(R.id.xq_title);
        xq_price= (TextView)v. findViewById(R.id.xq_price);
        xq_subhead= (TextView) v.findViewById(R.id.xq_subhead);
        xq_RecyclerView= (RecyclerView) v.findViewById(R.id.xq_RecyclerView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        xq_RecyclerView.setLayoutManager(mLayoutManager);
        xq_RecyclerView.setHasFixedSize(true);
        xq_back= (ImageView) v.findViewById(R.id.xq_back);
        xq_fx= (ImageView)v. findViewById(R.id.xq_fx);
    }
    public void initbanner(){
        xq_banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        //设置图片加载器
        xq_banner.setImageLoader(new GlideImageLaoder());
        xq_banner.setImages(mListImage);
        //设置Banner动画效果
        xq_banner.setBannerAnimation(Transformer.DepthPage);
        mListTitle=new ArrayList<>();
        xq_banner.isAutoPlay(false);
        //设置轮播时间
        xq_banner.setDelayTime(1000);
        //设置指示器位置（当banner模式中有指示器时）
        xq_banner.setIndicatorGravity(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //Banner设置方法全部调用完毕时最后调用
        xq_banner.start();
    }
    private void getData() {
        RequestParams params=new RequestParams(url);
        params.addBodyParameter("pid",pid);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if(result!=null){
                    final Gson gson=new Gson();
                    final XqBean bean = gson.fromJson(result, XqBean.class);
                    final XqBean.DataBean data = bean.getData();
                    gscid=data.getPscid()+"";
                    mListImage=new ArrayList<String>();
                    for(int i=0;i<bean.getData().getImages().split("\\|").length;i++){
                        mListImage.add(bean.getData().getImages().split("\\|")[i]);
                    }
                    initbanner();
                    xq_title.setText(bean.getData().getTitle());
                    xq_price.setText("$$"+bean.getData().getPrice()+"");
                    xq_subhead.setText(bean.getData().getSubhead());
                    xq_sj.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getActivity(),bean.getSeller().getName(),Toast.LENGTH_LONG).show();
                        }
                    });
                    //点击添加购物车
                    xq_addcar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //得到判断登录的sp
                            SharedPreferences pref = getActivity().getSharedPreferences("User",MODE_PRIVATE);
                            boolean yes = pref.getBoolean("islog", false);
                            //判断是否登录
                            if(yes){
                                RequestParams params=new RequestParams(Api.ADDCAR);
                                params.addBodyParameter("uid",uid);
                                params.addBodyParameter("pid",data.getPid()+"");
                                params.addBodyParameter("sellerid",data.getSellerid()+"");
                                x.http().post(params, new CommonCallback<String>() {
                                    @Override
                                    public void onSuccess(String result) {
                                        if(result!=null){
                                            Gson gsson=new Gson();
                                            BaseBean bb = gson.fromJson(result, BaseBean.class);
                                            Toast.makeText(getActivity(),bb.msg,Toast.LENGTH_LONG).show();
                                        }
                                    }
                                    @Override
                                    public void onError(Throwable ex, boolean isOnCallback) {
                                    }
                                    @Override
                                    public void onCancelled(CancelledException cex) {
                                    }
                                    @Override
                                    public void onFinished() {
                                    }
                                });
                            }
                            else{
                                Toast.makeText(getActivity(),"请先登录",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }
    public void getdata(){
        RequestParams params=new RequestParams(Api.SPLIST);
        params.addBodyParameter("pscid",gscid);
        params.addBodyParameter("page","1");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                final ListBean listBean = gson.fromJson(result, ListBean.class);
                ListAdapter   adapter=new ListAdapter(listBean.getData(),getActivity());
                xq_RecyclerView.setAdapter(adapter);
                adapter.setOnItemClickListener(new ListAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                       getActivity(). finish();
                    }
                });
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }



}
