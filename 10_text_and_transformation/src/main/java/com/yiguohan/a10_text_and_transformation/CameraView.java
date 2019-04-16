package com.yiguohan.a10_text_and_transformation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class CameraView extends View {
    private static final float WIDTH = Utils.dp2px(200);
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap avatar;
    private Camera mCamera = new Camera();
    private float bitmapWidth;
    private float bitmapHeight;

    public CameraView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        avatar = getBitmap((int) WIDTH);
        bitmapWidth = avatar.getWidth();
        bitmapHeight = avatar.getHeight();

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.translate(getWidth() / 2, getHeight() / 2);
        mCamera.rotateX(30);
        mCamera.applyToCanvas(canvas);
        canvas.drawBitmap(avatar, -bitmapWidth / 2, -bitmapHeight / 2, mPaint);
        canvas.translate(-getWidth() / 2, -getHeight() / 2);
        canvas.restore();
    }


    private Bitmap getBitmap(int width) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.avatar, options);
        options.inJustDecodeBounds = false;
        options.inDensity = options.outWidth;
        options.inTargetDensity = width;
        return BitmapFactory.decodeResource(getResources(), R.drawable.avatar, options);
    }
}
