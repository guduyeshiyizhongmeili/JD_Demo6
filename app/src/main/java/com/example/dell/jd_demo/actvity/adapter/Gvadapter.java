package com.example.dell.jd_demo.actvity.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.jd_demo.R;

import java.util.List;

/**
 * Created by dell on 2017/10/8.
 */
public class Gvadapter extends BaseAdapter {
    private List<String> list;
    private List<String> listimg;
    private Context context;

    public Gvadapter(List<String> listimg,List<String> list, Context context) {
        this.list = list;
        this.listimg = listimg;
        this.context = context;
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
        View view=View.inflate(context,R.layout.item3,null);
        TextView gvname= (TextView) view.findViewById(R.id.gvname);
        gvname.setText(list.get(position));
        ImageView img= (ImageView) view.findViewById(R.id.gvimg);
        Glide.with(context).load(listimg.get(position)).into(img);
        return view;
    }
}
