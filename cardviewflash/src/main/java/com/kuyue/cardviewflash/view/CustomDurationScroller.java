package com.kuyue.cardviewflash.view;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/**
 * Created by sen young on 2017/2/16 17:04.
 * 邮箱:595327086@qq.com.
 */

public class CustomDurationScroller extends Scroller {
    private double a = 1.0d;

    public CustomDurationScroller(Context context) {
        super(context);
    }

    public CustomDurationScroller(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    public void setScrollDurationFactor(double d) {
        this.a = d;
    }

    public void startScroll(int i, int i2, int i3, int i4, int i5) {
        super.startScroll(i, i2, i3, i4, 500);
    }
}

