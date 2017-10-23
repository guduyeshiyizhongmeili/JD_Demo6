package com.example.dell.jd_demo.actvity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.jd_demo.R;
import com.example.dell.jd_demo.actvity.bean.ListBean;

import java.util.List;

/**
 * Created by dell on 2017/10/11.
 */
public class ListAdapter  extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    public List<ListBean.DataBean> list;
    private Context context;
    private OnItemClickListener mClickListener;
    public ListAdapter(List<ListBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.gs_item,viewGroup,false);
        ViewHolder vh = new ViewHolder(view,mClickListener);
        return vh;
    }
    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.gstitle.setText(list.get(position).getTitle());
       viewHolder.gsprice.setText(list.get(position).getPrice()+"");
        Glide.with(context).load(list.get(position).getImages().split("\\|")[0]).into(viewHolder.gsimg);

    }
    //获取数据的数量
    @Override
    public int getItemCount() {
        return list.size();
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.mClickListener=listener;
    }
    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView gstitle;
        public TextView gsprice;
        public ImageView gsimg;
        private OnItemClickListener mListener;
        public ViewHolder(View view,OnItemClickListener  listener){
            super(view);
            gsprice = (TextView) view.findViewById(R.id.gs_price);
            gstitle = (TextView) view.findViewById(R.id.gs_title);
            gsimg = (ImageView) view.findViewById(R.id.gs_img);

            mListener=listener;
            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            mListener.onItemClick(v,getPosition());
        }
    }
    public interface OnItemClickListener {
        public void onItemClick(View view, int postion);
    }
}
