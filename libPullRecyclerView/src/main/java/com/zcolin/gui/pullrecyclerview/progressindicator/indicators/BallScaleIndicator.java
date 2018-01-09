/*
 * *********************************************************
 *   author   colin
 *   company  telchina
 *   email    wanglin2046@126.com
 *   date     18-1-9 下午3:05
 * ********************************************************
 */

package com.zcolin.gui.pullrecyclerview.progressindicator.indicators;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;

/**
 * Created by Jack on 2015/10/19.
 */
public class BallScaleIndicator extends Indicator {

    float scale = 1;
    int   alpha = 255;

    @Override
    public void draw(Canvas canvas, Paint paint) {
        float circleSpacing = 4;
        paint.setAlpha(alpha);
        canvas.scale(scale, scale, getWidth() / 2, getHeight() / 2);
        paint.setAlpha(alpha);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2 - circleSpacing, paint);
    }

    @Override
    public ArrayList<ValueAnimator> onCreateAnimators() {
        ArrayList<ValueAnimator> animators = new ArrayList<>();
        ValueAnimator scaleAnim = ValueAnimator.ofFloat(0, 1);
        scaleAnim.setInterpolator(new LinearInterpolator());
        scaleAnim.setDuration(1000);
        scaleAnim.setRepeatCount(-1);
        addUpdateListener(scaleAnim, animation -> {
            scale = (float) animation.getAnimatedValue();
            postInvalidate();
        });

        ValueAnimator alphaAnim = ValueAnimator.ofInt(255, 0);
        alphaAnim.setInterpolator(new LinearInterpolator());
        alphaAnim.setDuration(1000);
        alphaAnim.setRepeatCount(-1);
        addUpdateListener(alphaAnim, animation -> {
            alpha = (int) animation.getAnimatedValue();
            postInvalidate();
        });
        animators.add(scaleAnim);
        animators.add(alphaAnim);
        return animators;
    }

}
