package com.yiguohan.a13_layout.views;

import android.content.Context;
import android.util.AttributeSet;

import com.yiguohan.a13_layout.Utils;

public class OneHundredImageView extends android.support.v7.widget.AppCompatImageView {
    public OneHundredImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension((int) Utils.dp2px(100), (int) Utils.dp2px(100));
    }
}
