package com.example.dell.jd_demo.actvity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.dell.jd_demo.R;
import com.example.dell.jd_demo.actvity.adapter.ListAdapter;
import com.example.dell.jd_demo.actvity.bean.ListBean;
import com.example.dell.jd_demo.actvity.net.Api;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class DetailsActivity extends AppCompatActivity {
//商品列表页
private ListBean listBean;
    private TextView aaa;
    private int i=1;
    private Boolean isCheck=false;
    private String page=i+"";
    private String url= Api.SPLIST;
    private  String pscid;
    private ListAdapter adapter;
    private RadioButton horver;
    private RecyclerView listRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent intent = getIntent();
        pscid = intent.getStringExtra("gscid");
        initView();
        horver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCheck=!isCheck;
                horver.setChecked(!isCheck);
               if(horver.isChecked()) {
                   GridLayoutManager mLayoutManager = new GridLayoutManager(DetailsActivity.this,2);
                   listRecyclerView.setLayoutManager(mLayoutManager);
                   listRecyclerView.setHasFixedSize(true);
                   adapter.notifyDataSetChanged();
               }else{
                   LinearLayoutManager mLayoutManager = new LinearLayoutManager(DetailsActivity.this);
                   listRecyclerView.setLayoutManager(mLayoutManager);
                   listRecyclerView.setHasFixedSize(true);
                   adapter.notifyDataSetChanged();
               }
            }
        });
                    LinearLayoutManager mLayoutManager = new LinearLayoutManager(DetailsActivity.this);
                    listRecyclerView.setLayoutManager(mLayoutManager);
                    listRecyclerView.setHasFixedSize(true);

        getdata();
    }
    public void getdata(){
        RequestParams params=new RequestParams(url);
        params.addBodyParameter("pscid",pscid);
        params.addBodyParameter("page",page);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                listBean = gson.fromJson(result, ListBean.class);
                adapter=new ListAdapter(listBean.getData(),DetailsActivity.this);
                listRecyclerView.setAdapter(adapter);
                adapter.setOnItemClickListener(new ListAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int postion) {
                        Intent intent=new Intent(DetailsActivity.this,GoodsDetailActivity.class);
                        intent.putExtra("pid",listBean.getData().get(postion).getPid()+"");
                        startActivity(intent);
                        System.out.println(listBean.getData().get(postion).getPid()+"");
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
    public void initView(){
        listRecyclerView= (RecyclerView) findViewById(R.id.list_RecyclerView);
        horver= (RadioButton) findViewById(R.id.horver);

    }
}
