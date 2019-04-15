package com.yiguohan.a09_drawing;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class AvatarView extends View {
    private static final float WIDTH = Utils.dp2px(400);
    private static final float PADDING = Utils.dp2px(15);
    private static final float BORDER_WIDTH = Utils.dp2px(10);
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap avatar;
    private RectF mRectF;
    private RectF border;
    private Xfermode mXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);

    public AvatarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        avatar = getAvatar((int) WIDTH);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRectF = new RectF(PADDING, PADDING, PADDING + WIDTH, PADDING + WIDTH);
        border = new RectF(PADDING - BORDER_WIDTH, PADDING - BORDER_WIDTH, PADDING + WIDTH + BORDER_WIDTH, PADDING + WIDTH + BORDER_WIDTH);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawOval(border, mPaint);
        int index = canvas.saveLayer(mRectF, mPaint);
        canvas.drawOval(mRectF, mPaint);
        mPaint.setXfermode(mXfermode);
        canvas.drawBitmap(avatar, PADDING, PADDING, mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(index);
    }

    private Bitmap getAvatar(int width) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.avatar, options);
        options.inJustDecodeBounds = false;
        options.inDensity = options.outWidth;
        options.inTargetDensity = width;
        return BitmapFactory.decodeResource(getResources(), R.drawable.avatar, options);
    }

}
