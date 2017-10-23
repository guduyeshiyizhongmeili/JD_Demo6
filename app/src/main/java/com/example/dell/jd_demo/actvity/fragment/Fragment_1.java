package com.example.dell.jd_demo.actvity.fragment;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.jd_demo.R;
import com.example.dell.jd_demo.actvity.activity.BannerActivity;
import com.example.dell.jd_demo.actvity.activity.ErweimaActivity;
import com.example.dell.jd_demo.actvity.activity.SousuoActivity;
import com.example.dell.jd_demo.actvity.adapter.Myadapter;
import com.example.dell.jd_demo.actvity.adapter.RvAdapter;
import com.example.dell.jd_demo.actvity.adapter.TjAdapter;
import com.example.dell.jd_demo.actvity.bean.Shouye_bean;
import com.example.dell.jd_demo.actvity.net.Api;
import com.example.dell.jd_demo.actvity.utlis.GlideImageLaoder;
import com.google.gson.Gson;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2017/10/4.
 */
public class Fragment_1 extends Fragment{
    private RecyclerView recycler_view;
    private GridLayoutManager  mLayoutManager;
    private GridLayoutManager  tjLayoutManager;
    private RecyclerView tj_view;
    private List<Shouye_bean.TuijianBean.ListBean> listtj;
    private List<Shouye_bean.MiaoshaBean.ListBeanX> listms;
    private Banner banner;
    private List<Fragment> listf;
    private ViewPager vp;
    private View view;
    private List<String> titles;
    private List<String> imgs;
    private List<String> urls;
    private TextView sousuo;
    private ImageView erweima;
    String TAG = "MainActivity";
    int REQUEST_CODE = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view=View.inflate(getActivity(), R.layout.fragment_1,null);
        listf=new ArrayList<>();
        listf.add(new Fragment_1s());
        listf.add(new Fragment_2s());
        initview();

        //秒杀
//创建默认的线性LayoutManager
        mLayoutManager = new GridLayoutManager(getActivity(),2);
        recycler_view.setLayoutManager(mLayoutManager);
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setHasFixedSize(true);

        tjLayoutManager = new GridLayoutManager(getActivity(),2);
        tj_view.setLayoutManager(tjLayoutManager);
        tj_view.setHasFixedSize(true);




        Myadapter adapter =new Myadapter(getFragmentManager(),getActivity(),listf);
        vp.setAdapter(adapter);
        vp.setCurrentItem(0);
        //点击搜索关键字
        getCameraPermission();
        //ZXingLibrary初始化
        ZXingLibrary.initDisplayOpinion(getActivity());
        //点击二维码扫描
        erweima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });
        //网络请求设置轮播图
        RequestParams params=new RequestParams(Api.SHOUYE);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                Shouye_bean shouye_bean = gson.fromJson(result, Shouye_bean.class);
                imgs = new ArrayList<>();
                titles = new ArrayList<>();
                urls = new ArrayList<>();
                List<Shouye_bean.DataBean> data = shouye_bean.getData();
                RvAdapter radapter= new RvAdapter(getActivity(),shouye_bean.getMiaosha().getList());
                TjAdapter tjadapter= new TjAdapter(getActivity(),shouye_bean.getTuijian().getList());

                recycler_view.setAdapter(radapter);
                tj_view.setAdapter(tjadapter);

                for(int i=0;i<data.size();i++){
                    imgs.add(data.get(i).getIcon());
                    titles.add(data.get(i).getTitle());
                    urls.add(data.get(i).getUrl());
                }
                //设置Banner样式
                banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
                //设置图片加载器
                banner.setImageLoader(new GlideImageLaoder());
                //实例化图片集合
                banner.setImages(imgs);
                //设置Banner动画效果
                banner.setBannerAnimation(Transformer.DepthPage);
                //实例化Title集合
                //设置Banner标题集合（当banner样式有显示title时）
                banner.setBannerTitles(titles);
                //设置轮播时间
                banner.setDelayTime(1000);
                //设置指示器位置（当banner模式中有指示器时）
                banner.setIndicatorGravity(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
                //Banner设置方法全部调用完毕时最后调用
                banner.start();
                banner.setOnBannerListener(new OnBannerListener() {
                  @Override
                  public void OnBannerClick(int position) {
                      String s = urls.get(position);
                      Intent intent=new Intent(getActivity(),BannerActivity.class);
                      intent.putExtra("url",s);
                      startActivity(intent);
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


        return view;
    }
    public void initview(){
        banner= (Banner) view.findViewById(R.id.banner);
        sousuo= (TextView) view.findViewById(R.id.sousuo);
        erweima= (ImageView) view.findViewById(R.id.erweima);
        vp= (ViewPager) view.findViewById(R.id.vp);
        tj_view= (RecyclerView) view.findViewById(R.id.tj_view);
        recycler_view= (RecyclerView) view.findViewById(R.id.recycler_view);
        sousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),SousuoActivity.class);
                startActivity(intent);
            }
        });

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(getActivity(), "解析结果:" + result, Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(getActivity(),ErweimaActivity.class);
                    intent.putExtra("ewm",result);
                    startActivity(intent);
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(getActivity(), "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
    public void getCameraPermission()
    {
        if (Build.VERSION.SDK_INT>22){
            if (ContextCompat.checkSelfPermission(getActivity(),
                    android.Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
                //先判断有没有权限 ，没有就在这里进行权限的申请
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{android.Manifest.permission.CAMERA},2);
            }else {
                //说明已经获取到摄像头权限了 想干嘛干嘛
            }
        }else {
            //这个说明系统版本在6.0之下，不需要动态获取权限。
        }
    }



}
