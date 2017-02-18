package com.kuyue.cardviewflash.view;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * ViewPager切换动画
 * Created by sen young on 2017/2/16 17:34.
 * 邮箱:595327086@qq.com.
 */

public class ZoomOutPageTransformer implements ViewPager.PageTransformer {

    private static float dScaleY = 0.8f;//不是当前选中的的ViewPager时的缩放尺寸
    private ViewPager mViewPager;

    public ZoomOutPageTransformer(ViewPager viewPager) {
        this.mViewPager = viewPager;
    }

    @Override
    public void transformPage(View view, float f) {
        float offset = getOffset(view);
        if (offset <= -1.0f) {//处于前一个状态时
            view.setScaleY(dScaleY);
        } else if (offset <= 0.0f) {//处于前一个和选中时的中间态
            view.setScaleY((offset / 5.0f) + 1.0f);
        } else if (offset <= 1.0f) {//处于选中与后一个时的中间态
            view.setScaleY(1.0f - (offset / 5.0f));
        } else {//处于后一个状态时
            view.setScaleY(dScaleY);
        }
    }

    /**
     * 获得偏移量 pageMargin / pageWidth
     * @param view
     * @return
     */
    protected float getOffset(View view) {
        int paddingLeft = this.mViewPager.getPaddingLeft();
        return ((float) ((view.getLeft() - this.mViewPager.getScrollX()) - paddingLeft)) / ((float) ((this.mViewPager.getMeasuredWidth() - paddingLeft) - this.mViewPager.getPaddingRight()));
    }
}
