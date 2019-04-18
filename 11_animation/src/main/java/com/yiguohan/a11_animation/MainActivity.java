package com.yiguohan.a11_animation;

import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ProvinceView view = findViewById(R.id.view);
        /**
         * 使用AnimationSet
         */
//        AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.playSequentially(ObjectAnimator.ofFloat(view, "bottomFlip", 30),
//                ObjectAnimator.ofFloat(view, "flipRotation", 360),
//                ObjectAnimator.ofFloat(view, "topFlip", -30),
//                ObjectAnimator.ofFloat(view, "bottomFlip", 0),
//                ObjectAnimator.ofFloat(view, "topFlip", 0));
//        animatorSet.setStartDelay(1000);
//        animatorSet.setDuration(1000);
//        animatorSet.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                super.onAnimationEnd(animation);
//                animation.start();
//            }
//        });
//        animatorSet.start();
        /**
         * 使用PropertyValuesHolder
         */
//        PropertyValuesHolder bottomFlipHolder = PropertyValuesHolder.ofFloat("bottomFlip", 30);
//        PropertyValuesHolder topFlipHolder = PropertyValuesHolder.ofFloat("topFlip", 0);
//        PropertyValuesHolder flipRotationHolder = PropertyValuesHolder.ofFloat("flipRotation", 270);
//
//        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(view, bottomFlipHolder, flipRotationHolder, topFlipHolder);
//        animator.setStartDelay(1000);
//        animator.setDuration(1000);
//        animator.start();

        /**
         * 使用KeyFrame
         */
//        float distance = Utils.dp2px(300);
//        Keyframe keyframe1 = Keyframe.ofFloat(0, (float) (-0.4 * distance));
//        Keyframe keyframe2 = Keyframe.ofFloat(0.1f, (float) (0.4 * distance));
//        Keyframe keyframe3 = Keyframe.ofFloat(0.9f, (float) (0.6 * distance));
//        Keyframe keyframe4 = Keyframe.ofFloat(1, distance);
//        PropertyValuesHolder holder = PropertyValuesHolder.ofKeyframe("translationX", keyframe1, keyframe2, keyframe3, keyframe4);
//        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(view, holder);
//        animator.setStartDelay(1000);
//        animator.setDuration(1000);
//        animator.start();

        /**
         * 使用ObjectAnimator.ofObject方法和自定义TypedEvaluator
         */
//        ObjectAnimator animator = ObjectAnimator.ofObject(view, "pointF", new TypeEvaluator<PointF>() {
//            @Override
//            public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
//                return new PointF(startValue.x + (endValue.x - startValue.x) * fraction, startValue.y + (endValue.y - startValue.y) * fraction);
//            }
//        }, new PointF(Utils.dp2px(200), Utils.dp2px(200)));
//        animator.setStartDelay(1000);
//        animator.setDuration(1000);
//        animator.start();

        /**
         * 使用自定义TypedEvaluator来进行drawText的动画实现
         */
//        final List<String> provinceList = Utils.provinces;
//        ObjectAnimator animator = ObjectAnimator.ofObject(view, "text", new TypeEvaluator<String>() {
//            @Override
//            public String evaluate(float fraction, String startValue, String endValue) {
//                int start = provinceList.indexOf(startValue);
//                int end = provinceList.indexOf(endValue);
//                int current = (int) (start + (end - start) * fraction);
//                return provinceList.get(current);
//            }
//        }, "北京市", "澳门特别行政区");
//        animator.setStartDelay(1000);
//        animator.setDuration(2000);
//        animator.start();
    }
}
