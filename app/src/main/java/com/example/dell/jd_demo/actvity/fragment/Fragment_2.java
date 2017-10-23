package com.example.dell.jd_demo.actvity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.dell.jd_demo.R;
import com.example.dell.jd_demo.actvity.adapter.FlAdapter;
import com.example.dell.jd_demo.actvity.bean.Fenlei_bean;
import com.example.dell.jd_demo.actvity.net.Api;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by dell on 2017/10/4.
 */
public class Fragment_2 extends Fragment{
    private List<Fenlei_bean.DataBean> cb;
    private ViewPager flvp;
    private Fragment[] frags;
    private ListView fl_list;
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=View.inflate(getActivity(), R.layout.fragment_2,null);
        initView();
        init();
        flvp.setCurrentItem(0);
        getnet();
        return view;
    }
    public void initView(){
        flvp= (ViewPager) view.findViewById(R.id.flvp);
        fl_list= (ListView) view.findViewById(R.id.fl_list);
    }
    //条目点击切换viewpage 页面
    public void init(){
        fl_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                flvp.setCurrentItem(position);
            }
        });
    }
    public void getnet(){
        RequestParams params=new RequestParams(Api.FENLEI);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                Fenlei_bean fenlei_bean = gson.fromJson(result, Fenlei_bean.class);
                List<Fenlei_bean.DataBean> data = fenlei_bean.getData();
                FlAdapter adapter=new FlAdapter(getActivity(),data);
                fl_list.setAdapter(adapter);
                getvp(data);


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
    public void  getvp(final List<Fenlei_bean.DataBean> data){
        if(data!=null){
            frags = new Fragment[data.size()];
            cb = data;
        }
        flvp.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return getfragment(position);
            }

            @Override
            public int getCount() {
                return data.size();
            }

        });

    }
    private Fragment getfragment(int position) {
        Fragment fg = frags[position];
        if (fg == null) {
            fg = TwoFragment.getiniturl(cb.get(position).getCid()+"");
            frags[position] = fg;
        }
        return fg;
    }

}
