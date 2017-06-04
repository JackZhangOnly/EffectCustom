package com.jackzhang.example.cv_qqstep;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.DecelerateInterpolator;

public class MainActivity extends AppCompatActivity {

    private QQStepView qqStepView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        qqStepView= (QQStepView) findViewById(R.id.stepView);
        qqStepView.setStepMax(4000);

        ValueAnimator valueAnimator=ObjectAnimator.ofFloat(0,3000);
        valueAnimator.setDuration(2000);
        valueAnimator.setInterpolator(new DecelerateInterpolator());

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float curStep=(float)animation.getAnimatedValue();
                qqStepView.setmCurrentStep((int)curStep);
            }
        });
        valueAnimator.start();
    }
}
