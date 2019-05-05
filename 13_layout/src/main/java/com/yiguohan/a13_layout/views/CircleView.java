package com.yiguohan.a13_layout.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.yiguohan.a13_layout.Utils;

public class CircleView extends View {

    private static final float RADIUS = Utils.dp2px(80);

    private static final float PADDING = Utils.dp2px(20);
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);


    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    {
        mPaint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(RADIUS + PADDING, RADIUS + PADDING, RADIUS, mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int size = (int) ((RADIUS + PADDING) * 2);

        int measuredWidth = resolveSize(size, widthMeasureSpec);
        int measuredHeight = resolveSize(size, heightMeasureSpec);

        setMeasuredDimension(measuredWidth, measuredHeight);
    }
}
