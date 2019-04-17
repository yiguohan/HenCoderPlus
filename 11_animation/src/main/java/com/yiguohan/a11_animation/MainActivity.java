package com.yiguohan.a11_animation;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CircleView view = findViewById(R.id.view);
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "radius", Utils.dp2px(100));
        animator.setStartDelay(1000);
        animator.setDuration(1000);
        animator.start();
    }
}
