package com.example.dell.jd_demo.actvity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.dell.jd_demo.R;

/**
 * Created by dell on 2017/10/4.
 */
public class Fragment_3 extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=View.inflate(getActivity(), R.layout.fragment_3,null);
     WebView web_find= (WebView) view.findViewById(R.id.web_find);
        return view;
    }
}
