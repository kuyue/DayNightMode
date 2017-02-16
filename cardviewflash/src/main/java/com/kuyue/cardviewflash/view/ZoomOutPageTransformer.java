package com.kuyue.cardviewflash.view;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by sen young on 2017/2/16 17:34.
 * 邮箱:595327086@qq.com.
 */

public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
    private static float a = 0.8f;
    private ViewPager b;

    public ZoomOutPageTransformer(ViewPager viewPager) {
        this.b = viewPager;
    }

    public void transformPage(View view, float f) {
        float a = a(view);
        if (a <= -1.0f) {
            view.setScaleY(a);
        } else if (a <= 0.0f) {
            view.setScaleY((a / 5.0f) + 1.0f);
        } else if (a <= 1.0f) {
            view.setScaleY(1.0f - (a / 5.0f));
        } else {
            view.setScaleY(a);
        }
    }

    protected float a(View view) {
        int paddingLeft = this.b.getPaddingLeft();
        return ((float) ((view.getLeft() - this.b.getScrollX()) - paddingLeft)) / ((float) ((this.b.getMeasuredWidth() - paddingLeft) - this.b.getPaddingRight()));
    }
}
