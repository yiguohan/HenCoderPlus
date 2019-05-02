package com.yiguohan.a12_bitmap_drawable;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

public class MaterialEditText extends EditText {

    private Context context;
    private static final float HINT_HEIGHT = Utils.dp2px(20);

    private static final float VERTICAL_OFFSET_EXTRA = Utils.dp2px(38);
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private boolean flagLabelShown = true;
    private float verticalOffsetFraction = 1;

    private Rect backgroundPaddings = new Rect();


    public MaterialEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        getBackground().getPadding(backgroundPaddings);//获取父类给到的padding
        setPadding(backgroundPaddings.left, (int) HINT_HEIGHT, backgroundPaddings.right, backgroundPaddings.bottom);

        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(editable) && !flagLabelShown) {
                    getAnimator().start();
                    flagLabelShown = true;
                } else if (!TextUtils.isEmpty(editable) && flagLabelShown) {
                    getAnimator().reverse();
                    flagLabelShown = false;
                }
            }
        });
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setTextSize(Utils.dp2px(14));
        mPaint.setAlpha((int) ((1 - verticalOffsetFraction) * 0xff));
        canvas.drawText(getHint().toString(), backgroundPaddings.left, HINT_HEIGHT + (verticalOffsetFraction * VERTICAL_OFFSET_EXTRA), mPaint);
    }

    public float getVerticalOffsetFraction() {
        return verticalOffsetFraction;
    }

    public void setVerticalOffsetFraction(float verticalOffsetFraction) {
        invalidate();
        this.verticalOffsetFraction = verticalOffsetFraction;
    }

    private ObjectAnimator getAnimator() {
        return ObjectAnimator.ofFloat(MaterialEditText.this, "verticalOffsetFraction", 0, 1);
    }

}
