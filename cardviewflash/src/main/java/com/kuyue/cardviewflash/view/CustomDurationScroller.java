package com.kuyue.cardviewflash.view;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/**
 * 滑动
 * Created by sen young on 2017/2/16 17:04.
 * 邮箱:595327086@qq.com.
 */

public class CustomDurationScroller extends Scroller {

    private int mDuration = 500;

    public CustomDurationScroller(Context context) {
        super(context);
    }

    public CustomDurationScroller(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    public void setScrollDurationFactor(int d) {
        this.mDuration = d;
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        super.startScroll(startX, startY, dx, dy, mDuration);
    }
}

