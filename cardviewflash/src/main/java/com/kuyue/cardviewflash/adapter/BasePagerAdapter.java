package com.kuyue.cardviewflash.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sen young on 2017/2/16 17:36.
 * 邮箱:595327086@qq.com.
 */

public abstract class BasePagerAdapter<T> extends PagerAdapter {
    public List<T> d = new ArrayList();
    public int e;
    public Context f;

    public abstract View initItem(int i);

    public BasePagerAdapter(Context context, List<T> list) {
        this.f = context;
        setList(list);
    }

    public void setList(List<T> list) {
        if (list != null) {
            this.d.clear();
            this.d.addAll(list);
            notifyDataSetChanged();
        }
    }

    public int getCount() {
        int size = this.d.size();
        this.e = size;
        return size;
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View initItem = initItem(i);
        viewGroup.addView(initItem);
        return initItem;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }
}