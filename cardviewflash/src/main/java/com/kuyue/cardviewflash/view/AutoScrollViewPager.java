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
 * 自动循环滚动ViewPager
 * Created by sen young on 2017/2/16 17:02.
 * 邮箱:595327086@qq.com.
 */

public class AutoScrollViewPager extends ViewPager {

    private float mDownX;
    private float mDownY;
    private CustomDurationScroller mScroller = null;
    private long mSlidingInterval = 1500;
    private int Direction = 1;
    private boolean cycle = true;
    private boolean StopScrollWhenTouch = false;
    private int SlideBorderMode = 1;
    private boolean BorderAnimation = true;
    private int t = 500;
    private int u = 500;
    private Handler mHandler;
    private boolean autoScroll = false;
    private boolean stopScrollWhenTouch = false;

    private static class MyHandler extends Handler {
        private final WeakReference<AutoScrollViewPager> mWeakReference;

        public MyHandler(AutoScrollViewPager autoScrollViewPager) {
            this.mWeakReference = new WeakReference(autoScrollViewPager);
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 0:
                    AutoScrollViewPager autoScrollViewPager = this.mWeakReference.get();
                    if (autoScrollViewPager != null) {
                        autoScrollViewPager.mScroller.setScrollDurationFactor(autoScrollViewPager.t);
                        autoScrollViewPager.scrollOnce();
                        autoScrollViewPager.mScroller.setScrollDurationFactor(autoScrollViewPager.u);
                        autoScrollViewPager.sendMessage(autoScrollViewPager.mSlidingInterval + ((long) autoScrollViewPager.mScroller.getDuration()));
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
        initData();
    }

    public AutoScrollViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initData();
    }

    private void initData() {
        this.mHandler = new MyHandler(this);
        initViewPager();
    }

    public void startAutoScroll() {
        this.autoScroll = true;
        sendMessage((long) (((double) this.mSlidingInterval) + ((((double) this.mScroller.getDuration()) / this.t) * this.u)));
    }

    public void stopAutoScroll() {
        this.autoScroll = false;
        this.mHandler.removeMessages(0);
    }

    /**
     * 设置滑动滚动时间因素
     *
     * @param d
     */
    public void setSwipeScrollDurationFactor(int d) {
        this.u = d;
    }

    /**
     * 设置自动滚动时间因素
     *
     * @param d
     */
    public void setAutoScrollDurationFactor(int d) {
        this.t = d;
    }

    private void sendMessage(long j) {
        this.mHandler.removeMessages(0);
        this.mHandler.sendEmptyMessageDelayed(0, j);
    }

    private void initViewPager() {
        try {
            Field declaredField = ViewPager.class.getDeclaredField("mScroller");
            declaredField.setAccessible(true);
            Field declaredField2 = ViewPager.class.getDeclaredField("sInterpolator");//
            declaredField2.setAccessible(true);
            this.mScroller = new CustomDurationScroller(getContext(), (Interpolator) declaredField2.get(null));
            declaredField.set(this, this.mScroller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void scrollOnce() {
        PagerAdapter adapter = getAdapter();
        if (adapter != null && adapter.getCount() > 1) {
            int currentItem = getCurrentItem();
            currentItem = this.Direction == 0 ? currentItem - 1 : currentItem + 1;
            if (currentItem == adapter.getCount()) {
                setCurrentItem(1, true);
            } else {
                setCurrentItem(currentItem, true);
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        switch (actionMasked) {
            case 0://按下
                this.mDownX = motionEvent.getX();
                this.mDownY = motionEvent.getY();
                break;
            case 1://抬起
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
            case 2://滑动
                float slideX = motionEvent.getX();
                float slideY = motionEvent.getY();
                if (Math.abs(slideY - this.mDownY) < Math.abs(slideX - this.mDownX) && Math.abs(slideX - this.mDownX) > 2.0f) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                    break;
                }
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }
        if (this.StopScrollWhenTouch) {//开启触碰就停止滑动时
            if (actionMasked == 0 && this.autoScroll) {//当按下并在自动滑动时，则停止滑动
                this.stopScrollWhenTouch = true;
                stopAutoScroll();
            } else if (motionEvent.getAction() == 1 && this.stopScrollWhenTouch) {//抬起时则开始滑动
                startAutoScroll();
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public long getInterval() {
        return this.mSlidingInterval;
    }

    /**
     * 设置卡片滑动间隔
     *
     * @param cardSlidingInterval
     */
    public void setCardSlidingInterval(long cardSlidingInterval) {
        this.mSlidingInterval = cardSlidingInterval;
    }

    public int getDirection() {
        return this.Direction == 0 ? 0 : 1;
    }

    public void setDirection(int direction) {
        this.Direction = direction;
    }

    public boolean isCycle() {
        return this.cycle;
    }

    public void setCycle(boolean cycle) {
        this.cycle = cycle;
    }

    public boolean isStopScrollWhenTouch() {
        return this.StopScrollWhenTouch;
    }

    public void setStopScrollWhenTouch(boolean stopScrollWhenTouch) {
        this.StopScrollWhenTouch = stopScrollWhenTouch;
    }

    public int getSlideBorderMode() {
        return this.SlideBorderMode;
    }

    public void setSlideBorderMode(int slideBorderMode) {
        this.SlideBorderMode = slideBorderMode;
    }

    public boolean isBorderAnimation() {
        return this.BorderAnimation;
    }

    public void setBorderAnimation(boolean borderAnimation) {
        this.BorderAnimation = borderAnimation;
    }
}
