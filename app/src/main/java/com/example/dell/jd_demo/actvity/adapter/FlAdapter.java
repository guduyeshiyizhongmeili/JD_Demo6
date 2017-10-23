package com.example.dell.jd_demo.actvity.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dell.jd_demo.R;
import com.example.dell.jd_demo.actvity.bean.Fenlei_bean;

import java.util.List;

/**
 * Created by dell on 2017/10/6.
 */
public class FlAdapter extends BaseAdapter {
    private Context context;
    private List<Fenlei_bean.DataBean> list;

    public FlAdapter(Context context, List<Fenlei_bean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=View.inflate(context, R.layout.adapter_fenlei,null);
        TextView tv_fenlei= (TextView) view.findViewById(R.id.tv_fenlei);
        tv_fenlei.setText(list.get(position).getName());
        return view;
    }
}
