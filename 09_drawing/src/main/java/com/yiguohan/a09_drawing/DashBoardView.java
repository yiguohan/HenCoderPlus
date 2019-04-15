package com.yiguohan.a09_drawing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class DashBoardView extends View {
    private static final float RADIUS = Utils.dp2px(200);
    private static final int MARKER = 0;
    private Paint outlinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Path outlinePath = new Path();
    private Paint dashPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Path dashPath = new Path();
    private PathMeasure mPathMeasure = new PathMeasure();

    public DashBoardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        outlinePaint.setStyle(Paint.Style.STROKE);
        outlinePaint.setStrokeWidth(Utils.dp2px(2));

        dashPaint.setStyle(Paint.Style.STROKE);
        dashPaint.setStrokeWidth(Utils.dp2px(2));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        outlinePath.addArc(getWidth() / 2 - RADIUS,
                getHeight() / 2 - RADIUS,
                getWidth() / 2 + RADIUS,
                getHeight() / 2 + RADIUS,
                150,
                240);
        mPathMeasure.setPath(outlinePath, false);
        dashPath.addRect(0, 0, Utils.dp2px(2), Utils.dp2px(10), Path.Direction.CW);
        dashPaint.setPathEffect(new PathDashPathEffect(dashPath,
                (mPathMeasure.getLength() - Utils.dp2px(2) / 2) / 20,
                0,
                PathDashPathEffect.Style.ROTATE));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(outlinePath,
                outlinePaint);

        canvas.drawPath(outlinePath,
                dashPaint);

        canvas.drawLine(getWidth() / 2,
                getHeight() / 2,
                (float) (getWidth() / 2 + Math.cos(Math.toRadians(150 + MARKER * (240 / 20))) * RADIUS),
                (float) (getHeight() / 2 + Math.sin(Math.toRadians(150 + MARKER * (240 / 20))) * RADIUS),
                outlinePaint);
    }
}
