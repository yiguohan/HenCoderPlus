package com.yiguohan.a10_text_and_transformation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class ImageTextView extends View {
    private static final float IMAGE_WIDTH = Utils.dp2px(200);
    private static final float PADDING = Utils.dp2px(60);
    private static final String text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean justo sem, sollicitudin in maximus a, vulputate id magna. Nulla non quam a massa sollicitudin commodo fermentum et est. Suspendisse potenti. Praesent dolor dui, dignissim quis tellus tincidunt, porttitor vulputate nisl. Aenean tempus lobortis finibus. Quisque nec nisl laoreet, placerat metus sit amet, consectetur est. Donec nec quam tortor. Aenean aliquet dui in enim venenatis, sed luctus ipsum maximus. Nam feugiat nisi rhoncus lacus facilisis pellentesque nec vitae lorem. Donec et risus eu ligula dapibus lobortis vel vulputate turpis. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; In porttitor, risus aliquam rutrum finibus, ex mi ultricies arcu, quis ornare lectus tortor nec metus. Donec ultricies metus at magna cursus congue. Nam eu sem eget enim pretium venenatis. Duis nibh ligula, lacinia ac nisi vestibulum, vulputate lacinia tortor.";
    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    Paint.FontMetrics fontMetrics;

    public ImageTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        textPaint.setTypeface(Typeface.createFromAsset(getResources().getAssets(), "Quicksand-Regular.ttf"));
        textPaint.setTextSize(Utils.dp2px(20));
        bitmap = getBitmap((int) IMAGE_WIDTH);
        fontMetrics = textPaint.getFontMetrics();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, getWidth() - IMAGE_WIDTH, PADDING, mPaint);

        int start = 0;
        int count;
        int currentRow = 1;
        float singleFontSpacing = textPaint.getFontSpacing();
        float fontSpaceing;
        int usableLength;
        while (start < text.length()) {
            fontSpaceing = singleFontSpacing * currentRow;
            if (((fontSpaceing + fontMetrics.ascent) > PADDING && (fontSpaceing + fontMetrics.ascent) < PADDING + bitmap.getHeight()) ||
                    ((fontSpaceing + fontMetrics.descent) > PADDING && (fontSpaceing + fontMetrics.descent) < PADDING + bitmap.getHeight())) {
                usableLength = getWidth() - bitmap.getWidth();
            } else {
                usableLength = getWidth();
            }


            count = textPaint.breakText(text, start, text.length(), true, usableLength, null);
            canvas.drawText(text, start, start + count, 0, fontSpaceing, textPaint);
            currentRow++;
            start += count;
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
}
