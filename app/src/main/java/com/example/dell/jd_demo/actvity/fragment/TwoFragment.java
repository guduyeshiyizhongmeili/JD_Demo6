package com.example.dell.jd_demo.actvity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.dell.jd_demo.R;
import com.example.dell.jd_demo.actvity.adapter.ExpandedAdapter;
import com.example.dell.jd_demo.actvity.bean.ExBean;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2017/10/10.
 */
public class TwoFragment extends Fragment {
    public static TwoFragment getiniturl(String gc_id){
        TwoFragment twoFragment = new TwoFragment();
        Bundle bundle = new Bundle();
        bundle.putString("gc_id",gc_id);
        twoFragment.setArguments(bundle);
        return twoFragment;
    }
    private ExpandableListView expanded;
    private ExpandedAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=View.inflate(getActivity(), R.layout.flitem,null);
        String  gc_id = getArguments().getString("gc_id");
        expanded= (ExpandableListView) v.findViewById(R.id.expanded);
        RequestParams params=new RequestParams("http://120.27.23.105/product/getProductCatagory");
        params.addBodyParameter("cid",gc_id);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                final ExBean exBean = gson.fromJson(result, ExBean.class);
                List<ExBean.DataBean>   grouplist=new ArrayList<ExBean.DataBean>();
                List<List<ExBean.DataBean.ListBean>>  childlist=new ArrayList<List<ExBean.DataBean.ListBean>>();

                for(int i=0;i<exBean.getData().size();i++){
                    grouplist.add(exBean.getData().get(i));
                    }
                for (ExBean.DataBean cb:grouplist) {
                        childlist.add(cb.getList());
                }




                adapter=new ExpandedAdapter(grouplist,childlist,getActivity());
                expanded.setAdapter(adapter);
                //父级列表默认全部展开
                int groupCount = expanded.getCount();
                for (int i=0; i<groupCount; i++)
                {
                    expanded.expandGroup(i);
                };


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

        return  v;
    }
}
