package com.yiguohan.a19_constraintlayout;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;

public class CircularPositioningActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView earth = findViewById(R.id.earth);
        ImageView moon = findViewById(R.id.moon);


        ObjectAnimator earthAnimator = ObjectAnimator.ofFloat(earth, "circleAngle", 360);
        ObjectAnimator moonAnimator = ObjectAnimator.ofFloat(moon, "circleAngle", 360);

        earthAnimator.setDuration(1000).start();
        moonAnimator.setDuration(1000).start();
    }
}
