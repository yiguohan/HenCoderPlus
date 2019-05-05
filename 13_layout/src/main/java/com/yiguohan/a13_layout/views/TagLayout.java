package com.yiguohan.a13_layout.views;

import android.content.Context;
import android.graphics.Rect;
import android.text.style.LineHeightSpan;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class TagLayout extends ViewGroup {

    List<Rect> childrenBoundList = new ArrayList<>();

    public TagLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int lineWidthUsed = 0;
        int totalHeightUsed = 0;

        int widthMax = 0;
        int heightMax = 0;

        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            measureChildWithMargins(childView, widthMeasureSpec, 0, heightMeasureSpec, 0);
            Rect rect;
            lineWidthUsed = lineWidthUsed + childView.getMeasuredWidth();
//            heightMax = totalHeightUsed + childView.getMeasuredHeight();
            if (childrenBoundList.size() == i) {
                rect = new Rect(lineWidthUsed, 0, lineWidthUsed, childView.getMeasuredHeight());
            } else {
                rect = childrenBoundList.get(i);
            }
            childrenBoundList.add(rect);
            widthMax = Math.max(lineWidthUsed, widthMax);

        }
//        widthUsed = Math.max(widthUsed, lineHeightUsed);
//        heightUsed = Math.max(heightUsed, lineHeightUsed);

//
        setMeasuredDimension(MeasureSpec.makeMeasureSpec(widthMax, MeasureSpec.getMode(widthMeasureSpec)), MeasureSpec.makeMeasureSpec(heightMax, MeasureSpec.getMode(heightMeasureSpec)));

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            Rect rect = childrenBoundList.get(i);
            child.layout(rect.left, rect.top, rect.right, rect.bottom);
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
