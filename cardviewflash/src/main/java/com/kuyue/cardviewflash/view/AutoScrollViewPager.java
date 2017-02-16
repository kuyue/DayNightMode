package com.kuyue.cardviewflash.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.Interpolator;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;

/**
 * Created by sen young on 2017/2/16 17:02.
 * 邮箱:595327086@qq.com.
 */

public class AutoScrollViewPager extends ViewPager {
    public static final int g = 1500;
    public static final int h = 0;
    public static final int i = 1;
    public static final int j = 0;
    public static final int k = 1;
    public static final int l = 2;
    public static final int m = 0;
    private float A;
    private float B;
    private float C;
    private CustomDurationScroller D = null;
    private long n = 1500;
    private int o = 1;
    private boolean p = true;
    private boolean q = false;
    private int r = 1;
    private boolean s = true;
    private double t = 1.0d;
    private double u = 1.0d;
    private Handler v;
    private boolean w = false;
    private boolean x = false;
    private float y = 0.0f;
    private float z = 0.0f;

    private static class a extends Handler {
        private final WeakReference<AutoScrollViewPager> a;

        public a(AutoScrollViewPager autoScrollViewPager) {
            this.a = new WeakReference(autoScrollViewPager);
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 0:
                    AutoScrollViewPager autoScrollViewPager = (AutoScrollViewPager) this.a.get();
                    if (autoScrollViewPager != null) {
                        autoScrollViewPager.D.setScrollDurationFactor(autoScrollViewPager.t);
                        autoScrollViewPager.scrollOnce();
                        autoScrollViewPager.D.setScrollDurationFactor(autoScrollViewPager.u);
                        autoScrollViewPager.a(autoScrollViewPager.n + ((long) autoScrollViewPager.D.getDuration()));
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public AutoScrollViewPager(Context context) {
        super(context);
        f();
    }

    public AutoScrollViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        f();
    }

    private void f() {
        this.v = new a(this);
        g();
    }

    public void startAutoScroll() {
        this.w = true;
        a((long) (((double) this.n) + ((((double) this.D.getDuration()) / this.t) * this.u)));
    }

    public void startAutoScroll(int i) {
        this.w = true;
        a((long) i);
    }

    public void stopAutoScroll() {
        this.w = false;
        this.v.removeMessages(0);
    }

    public void setSwipeScrollDurationFactor(double d) {
        this.u = d;
    }

    public void setAutoScrollDurationFactor(double d) {
        this.t = d;
    }

    private void a(long j) {
        this.v.removeMessages(0);
        this.v.sendEmptyMessageDelayed(0, j);
    }

    private void g() {
        try {
            Field declaredField = ViewPager.class.getDeclaredField("x");
            declaredField.setAccessible(true);
            Field declaredField2 = ViewPager.class.getDeclaredField("q");
            declaredField2.setAccessible(true);
            this.D = new CustomDurationScroller(getContext(), (Interpolator) declaredField2.get(null));
            declaredField.set(this, this.D);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void scrollOnce() {
        PagerAdapter adapter = getAdapter();
        if (adapter != null && adapter.getCount() > 1) {
            int currentItem = getCurrentItem();
            currentItem = this.o == 0 ? currentItem - 1 : currentItem + 1;
            if (currentItem == adapter.getCount()) {
                setCurrentItem(1, true);
            } else {
                setCurrentItem(currentItem, true);
            }
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        switch (actionMasked) {
            case 0:
                this.z = motionEvent.getX();
                this.B = motionEvent.getY();
                break;
            case 1:
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
            case 2:
                this.A = motionEvent.getX();
                this.C = motionEvent.getY();
                if (Math.abs(this.C - this.B) < Math.abs(this.A - this.z) && Math.abs(this.A - this.z) > 2.0f) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                    break;
                }
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }
        if (this.q) {
            if (actionMasked == 0 && this.w) {
                this.x = true;
                stopAutoScroll();
            } else if (motionEvent.getAction() == 1 && this.x) {
                startAutoScroll();
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public long getInterval() {
        return this.n;
    }

    public void setInterval(long j) {
        this.n = j;
    }

    public int getDirection() {
        return this.o == 0 ? 0 : 1;
    }

    public void setDirection(int i) {
        this.o = i;
    }

    public boolean isCycle() {
        return this.p;
    }

    public void setCycle(boolean z) {
        this.p = z;
    }

    public boolean isStopScrollWhenTouch() {
        return this.q;
    }

    public void setStopScrollWhenTouch(boolean z) {
        this.q = z;
    }

    public int getSlideBorderMode() {
        return this.r;
    }

    public void setSlideBorderMode(int i) {
        this.r = i;
    }

    public boolean isBorderAnimation() {
        return this.s;
    }

    public void setBorderAnimation(boolean z) {
        this.s = z;
    }
}
