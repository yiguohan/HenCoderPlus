package com.yiguohan.a11_animation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class PointView extends View {

    private PointF mPointF = new PointF(0, 0);
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public PointView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(Utils.dp2px(20));
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPoint(mPointF.x, mPointF.y, mPaint);
    }

    public PointF getPointF() {
        return mPointF;
    }

    public void setPointF(PointF pointF) {
        mPointF = pointF;
        invalidate();
    }
}
