package com.yiguohan.a15_scalableimageview;

import android.content.res.Resources;
import android.util.TypedValue;

public class Utils {

    public static int dp2px(float width) {
       return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, width, Resources.getSystem().getDisplayMetrics());
    }
}
