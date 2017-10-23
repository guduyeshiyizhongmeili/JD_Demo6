package com.example.dell.jd_demo.actvity.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.jd_demo.R;
import com.example.dell.jd_demo.actvity.activity.LoginActivity;
import com.example.dell.jd_demo.actvity.activity.UserActivity;

/**
 * Created by dell on 2017/10/4.
 */
public class Fragment_5 extends Fragment implements View.OnClickListener {

    private View view;
    private ImageView mMyLoginn;
    /**
     * 注册/登录
     */
    private TextView mMyLogin;
    private ImageView mMySet;
    private ImageView mMyNews;
    private View mTextview;
    private View mTextview1;
    private View mTextview2;
    private static final int IMAGE = 1;
    private SharedPreferences sp;
    private Boolean islog = false;
    private String uid = 0 + "";
    private String num;
    private String username;
    /**
     * 退出
     */
    private TextView mTuichu;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_5, null);


        initView(view);
        return view;
    }

    private void initView(View view) {
        mMyLoginn = (ImageView) view.findViewById(R.id.my_loginn);
        mMyLoginn.setOnClickListener(this);
        mMyLogin = (TextView) view.findViewById(R.id.my_login);
        mMyLogin.setOnClickListener(this);
        mMySet = (ImageView) view.findViewById(R.id.my_set);
        mMyNews = (ImageView) view.findViewById(R.id.my_news);
        mTextview = (View) view.findViewById(R.id.textview);
        mTextview1 = (View) view.findViewById(R.id.textview1);
        mTextview2 = (View) view.findViewById(R.id.textview2);

        mTuichu = (TextView) view.findViewById(R.id.tuichu);
        mTuichu.setOnClickListener(this);
        mTextview2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_loginn:
                Intent intent2 = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent2, IMAGE);
                break;
            case R.id.my_login:
                if(islog){
                    Toast.makeText(getActivity(),"已登录",Toast.LENGTH_LONG).show();
                }else{
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }

                break;
            case R.id.tuichu:
                Intent intent1=new Intent(getActivity(),UserActivity.class);
                startActivity(intent1);

                break;
            case R.id.textview2:
                break;
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获取图片路径
        if (requestCode == IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c =getActivity().getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String imagePath = c.getString(columnIndex);
            showImage(imagePath);
            c.close();
        }
    }
    private void showImage(String imaePath){
        Bitmap bm = BitmapFactory.decodeFile(imaePath);
        mMyLoginn.setImageBitmap(bm);
    }
    @Override
    public void onResume() {
        super.onResume();
        sp = getActivity().getSharedPreferences("User", getActivity().MODE_PRIVATE);
        islog = sp.getBoolean("islog", false);
        uid = sp.getString("uid", 0 + "");
        username = sp.getString("username", "未登录");
        num = sp.getString("num", "未登录");
        if(islog){
            mMyLogin.setText("昵称："+num);
        }else{
            mMyLogin.setText("登录/注册");
        }
    }


}
