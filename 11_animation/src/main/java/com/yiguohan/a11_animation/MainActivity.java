package com.yiguohan.a11_animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FancyView view = findViewById(R.id.view);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(ObjectAnimator.ofFloat(view, "bottomFlip", 30),
                ObjectAnimator.ofFloat(view, "flipRotation", 360),
                ObjectAnimator.ofFloat(view, "topFlip", -30),
                ObjectAnimator.ofFloat(view, "bottomFlip", 0),
                ObjectAnimator.ofFloat(view, "topFlip", 0));
        animatorSet.setStartDelay(1000);
        animatorSet.setDuration(1000);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                animation.start();
            }
        });
        animatorSet.start();
    }
}
