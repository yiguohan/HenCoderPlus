package com.yiguohan.piechart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class PieChartView extends View {
    private static final int PULLED_INDEX = 2;
    private static final float PULLED_LENGTH = Utils.dp2px(10);
    private static float RADIUS = Utils.dp2px(200);
    private static int[] ANGLES = {
            120, 80, 40, 120
    };
    private static int[] COLORS = {
            Color.parseColor("#DD2534"),
            Color.parseColor("#4A90E2"),
            Color.parseColor("#fd9d00"),
            Color.parseColor("#08CBB6")};

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private RectF mRectF = new RectF();

    public PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRectF.set(getWidth() / 2 - RADIUS,
                getHeight() / 2 - RADIUS,
                getWidth() / 2 + RADIUS,
                getHeight() / 2 + RADIUS);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int currentAngles = 0;
        for (int i = 0; i < ANGLES.length; i++) {
            mPaint.setColor(COLORS[i]);
            if (i == PULLED_INDEX) {
                canvas.save();
                canvas.translate((float) (Math.cos(Math.toRadians(currentAngles + ANGLES[i] / 2)) * PULLED_LENGTH),
                        (float) (Math.sin(Math.toRadians(currentAngles + ANGLES[i] / 2)) * PULLED_LENGTH));
            }
            canvas.drawArc(mRectF, currentAngles, ANGLES[i], true, mPaint);
            if (i == PULLED_INDEX) {
                canvas.restore();
            }
            currentAngles += ANGLES[i];
        }
    }
}
