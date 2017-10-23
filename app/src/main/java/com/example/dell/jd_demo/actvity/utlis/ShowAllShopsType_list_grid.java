package com.example.dell.jd_demo.actvity.utlis;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by dell on 2017/10/11.
 */
public class ShowAllShopsType_list_grid extends GridView {

    public ShowAllShopsType_list_grid(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 设置不滚动
     */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);

    }
}