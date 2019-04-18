package com.yiguohan.a11_animation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class ProvinceView extends View {
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private String text = "澳门特别行政区";

    public ProvinceView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        mPaint.setTextSize(Utils.dp2px(20));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(text, getWidth() / 2, getHeight() / 2, mPaint);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        invalidate();
    }
}
