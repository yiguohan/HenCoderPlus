package com.yiguohan.a10_text_and_transformation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;

public class SportsView extends View {
    private static final float RADIUS = Utils.dp2px(200);
    private static final int RED = Color.parseColor("#DD2534");
    private static final int GRAY = Color.parseColor("#4D000000");
    private static final String TEXT_BOUNDS = "TextBoundsgy";
    private static final String FONT_METRICS = "FontMetricsgy";
    private static final String ALIGN_LEFT = "AlignLeft";
    Paint circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint arcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint dashPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint.FontMetrics mFontMetrics;
    RectF mRectF = new RectF();
    Rect textBound = new Rect();
    Rect alignLeftBound = new Rect();

    public SportsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setColor(GRAY);
        circlePaint.setStrokeCap(Paint.Cap.ROUND);
        circlePaint.setStrokeWidth(Utils.dp2px(20));

        arcPaint.setStyle(Paint.Style.STROKE);
        arcPaint.setColor(RED);
        arcPaint.setStrokeCap(Paint.Cap.ROUND);
        arcPaint.setStrokeWidth(Utils.dp2px(20));

        dashPaint.setStyle(Paint.Style.STROKE);
        dashPaint.setColor(GRAY);
        dashPaint.setStrokeWidth(Utils.dp2px(1));
        dashPaint.setPathEffect(new DashPathEffect(new float[]{3, 2}, 1));

        textPaint.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "Quicksand-Regular.ttf"));
        textPaint.setColor(RED);
        textPaint.setTextSize(Utils.dp2px(50));
        textPaint.setTextAlign(Paint.Align.CENTER);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mRectF.set(getWidth() / 2 - RADIUS,
                getHeight() / 2 - RADIUS,
                getWidth() / 2 + RADIUS,
                getHeight() / 2 + RADIUS
        );
        //画底圆
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, RADIUS, circlePaint);
        //画进度圆
        canvas.drawArc(mRectF,
                -90,
                215,
                false,
                arcPaint);
        //测量字符串边界
        textPaint.getTextBounds(TEXT_BOUNDS, 0, TEXT_BOUNDS.length(), textBound);
        //设置字符居中（Bound边界居中）
        canvas.drawText(TEXT_BOUNDS, getWidth() / 2, getHeight() / 2 - (textBound.bottom + textBound.top) / 2, textPaint);

        textPaint.setColor(GRAY);
        mFontMetrics = textPaint.getFontMetrics();
        //设置字符居中（assent/decent居中）
        canvas.drawText(FONT_METRICS, getWidth() / 2, getHeight() / 2 - (mFontMetrics.descent + mFontMetrics.ascent) / 2, textPaint);

        //左对齐
        textPaint.setTextAlign(Paint.Align.LEFT);
        textPaint.getTextBounds(ALIGN_LEFT, 0, ALIGN_LEFT.length(), alignLeftBound);
        canvas.drawText(ALIGN_LEFT, -alignLeftBound.left, -alignLeftBound.top, textPaint);

        textPaint.setTextSize(Utils.dp2px(10));
        textPaint.getTextBounds(ALIGN_LEFT, 0, ALIGN_LEFT.length(), alignLeftBound);
        canvas.drawText(ALIGN_LEFT, -alignLeftBound.left, -alignLeftBound.top + textPaint.getFontSpacing(), textPaint);

        //画基准线
        canvas.drawLine(getWidth() / 2 - RADIUS, getHeight() / 2, getWidth() / 2 + RADIUS, getHeight() / 2, dashPaint);
        canvas.drawLine(getWidth() / 2, getHeight() / 2 - RADIUS, getWidth() / 2, getHeight() / 2 + RADIUS, dashPaint);
    }
}
