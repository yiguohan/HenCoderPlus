package com.yiguohan.a13_layout;

import android.content.res.Resources;
import android.util.TypedValue;

public class Utils {

    public static float dp2px(int dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }
}