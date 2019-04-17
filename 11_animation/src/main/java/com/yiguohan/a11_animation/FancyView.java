package com.yiguohan.a11_animation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class FancyView extends View {
    private static final float WIDTH = Utils.dp2px(200);
    private float imageWidth;
    private float imageHeight;
    private float topFlip = 0;
    private float bottomFlip =0;
    private float flipRotation = 0;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap avatar;
    private Camera mCamera;

    public FancyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        mCamera = new Camera();
        avatar = getAvatar((int) WIDTH);
        imageWidth = avatar.getWidth();
        imageHeight = avatar.getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.translate(getWidth() / 2, getHeight() / 2);
        canvas.rotate(-flipRotation);
        canvas.clipRect(-imageWidth * 2, -imageHeight * 2, imageWidth * 2, 0);
        mCamera.save();
        mCamera.rotateX(topFlip);
        mCamera.applyToCanvas(canvas);
        mCamera.restore();
        canvas.rotate(flipRotation);
        canvas.drawBitmap(avatar, -imageWidth / 2, -imageHeight / 2, mPaint);
        canvas.translate(-getWidth() / 2, -getHeight() / 2);
        canvas.restore();

        canvas.save();
        canvas.translate(getWidth() / 2, getHeight() / 2);
        canvas.rotate(-flipRotation);
        canvas.clipRect(-imageWidth * 2, 0, imageWidth * 2, imageHeight * 2);
        mCamera.save();
        mCamera.rotateX(bottomFlip);
        mCamera.applyToCanvas(canvas);
        mCamera.restore();
        canvas.rotate(flipRotation);
        canvas.drawBitmap(avatar, -imageWidth / 2, -imageHeight / 2, mPaint);
        canvas.translate(-getWidth() / 2, -getHeight() / 2);
        canvas.restore();
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

    public float getTopFlip() {
        return topFlip;
    }

    public void setTopFlip(float topFlip) {
        this.topFlip = topFlip;
        invalidate();
    }

    public float getBottomFlip() {
        return bottomFlip;
    }

    public void setBottomFlip(float bottomFlip) {
        this.bottomFlip = bottomFlip;
        invalidate();

    }

    public float getFlipRotation() {
        return flipRotation;
    }

    public void setFlipRotation(float flipRotation) {
        this.flipRotation = flipRotation;
        invalidate();

    }
}
