package com.yiguohan.a15_scalableimageview;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.OverScroller;

public class ScalableImageVIew extends View implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener, Runnable {

    private Paint mPaint;
    private Bitmap mBitmap;
    private float smallScale;
    private float bigScale;
    private boolean isBigScale;

    private float offsetX;
    private float offsetY;

    private float scaleFraction;
    private ObjectAnimator mObjectAnimator;
    private GestureDetector mGestureDetector;

    private OverScroller mOverScroller;

    public ScalableImageVIew(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBitmap = getBitmap(Utils.dp2px(200));
        mGestureDetector = new GestureDetector(getContext(), this);
        isBigScale = false;
        mObjectAnimator = ObjectAnimator.ofFloat(this, "scaleFraction", 0, 1);
        mOverScroller = new OverScroller(getContext());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        initScales();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(offsetX * scaleFraction, offsetY * scaleFraction);
        float scale = smallScale + (bigScale - smallScale) * scaleFraction;
        canvas.scale(scale, scale, getWidth() / 2f, getHeight() / 2f);
        canvas.drawBitmap(mBitmap, (getWidth() - mBitmap.getWidth()) / 2f, (getHeight() - mBitmap.getHeight()) / 2f, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        if (isBigScale) {
            offsetX -= distanceX;
            offsetY -= distanceY;
            fixOffset();
            invalidate();
        }
        return false;
    }



    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (isBigScale) {
            mOverScroller.fling((int) offsetX, (int) offsetY, (int) velocityX, (int) velocityY,
                    -(int) ((mBitmap.getWidth() * bigScale - getWidth()) / 2),
                    (int) ((mBitmap.getWidth() * bigScale - getWidth()) / 2),
                    -(int) ((mBitmap.getHeight() * bigScale - getHeight()) / 2),
                    (int) ((mBitmap.getHeight() * bigScale - getHeight()) / 2),
                    1000,
                    1000);
            postOnAnimation(this);
        }
        return false;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        isBigScale = !isBigScale;
        if (isBigScale) {
            offsetX = (e.getX() - getWidth() / 2) * (1 - bigScale / smallScale);
            offsetY = (e.getY() - getHeight() / 2) * (1 - bigScale / smallScale);
            fixOffset();
            mObjectAnimator.start();
        } else {
            mObjectAnimator.reverse();
        }

        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    public float getScaleFraction() {
        return scaleFraction;
    }

    public void setScaleFraction(float scaleFraction) {
        this.scaleFraction = scaleFraction;
        invalidate();
    }

    private void initScales() {
        int bitmapWidth = mBitmap.getWidth();
        int bitmapHeight = mBitmap.getHeight();
        int viewWidth = getWidth();
        int viewHeight = getHeight();

        if ((viewWidth / bitmapWidth) > (viewHeight / bitmapHeight)) {
            bigScale = (float) viewWidth / bitmapWidth * 1.5f;
            smallScale = (float) viewHeight / bitmapHeight;
        } else {
            bigScale = (float) viewHeight / bitmapHeight * 1.5f;
            smallScale = (float) viewWidth / bitmapWidth;
        }
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

    private void fixOffset() {
        offsetX = Math.min((mBitmap.getWidth() * bigScale - getWidth()) / 2, offsetX);
        offsetX = Math.max(-(mBitmap.getWidth() * bigScale - getWidth()) / 2, offsetX);
        offsetY = Math.min((mBitmap.getHeight() * bigScale - getHeight()) / 2, offsetY);
        offsetY = Math.max(-(mBitmap.getHeight() * bigScale - getHeight()) / 2, offsetY);
    }

    @Override
    public void run() {
        if (mOverScroller.computeScrollOffset()) {
            offsetX = mOverScroller.getCurrX();
            offsetY = mOverScroller.getCurrY();
            invalidate();
            postOnAnimation(this);
        }

    }
}
